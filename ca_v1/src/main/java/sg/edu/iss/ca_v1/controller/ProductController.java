package sg.edu.iss.ca_v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.ca_v1.model.Product;

import sg.edu.iss.ca_v1.service.ProductImplementation;
import sg.edu.iss.ca_v1.service.ProductInterface;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductInterface pservice;

	@Autowired
	public void setProductService(ProductImplementation pServiceImpl) {
		this.pservice = pServiceImpl;
	}

	@RequestMapping(value = "/list")
	public String listusers(Model model) {

		return listByPage(model, 1,"name","asc");
	}

	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
    	@Param("sortField")String sortField,
	    @Param("sortDir") String sortDir) {
		Page<Product> page = pservice.findAllProduts(currentPage,sortField,sortDir);

		long total = page.getTotalElements();
		int totalPages = page.getTotalPages();

		List<Product> list = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("total", total);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("list", list);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		String reverseSortDir=sortDir.equals("asc")?"desc":"asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("products", pservice.findAllProduts(currentPage,sortField,sortDir));

		return "products";
	}

	@RequestMapping(value = "/add")
	public String addForm(Model model)
	{
		model.addAttribute("product",new Product());
		return "product-form";
	}
	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) 
	{
		model.addAttribute("product", pservice.findProductById(id));
		return "product-form";
	}
	@RequestMapping(value = "/save")
	public String saveProduct(@ModelAttribute("product") @Valid Product product, 
			BindingResult bindingResult,  Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("error","Unsuccessful,please try again");
		}
		 
		pservice.saveProduct(product);
		return "forward:/product/list";
	}
	@RequestMapping(value = "/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		pservice.deleteProduct(pservice.findProductById(id));
		return "forward:/product/list";
	}
	@RequestMapping(value="/report")
	public String reportbyId(Model model, @RequestParam String id) {
		
		pservice.reorderReport(id);
		return  "forward:/product/list";
	}
//	@RequestMapping(value="/report")
//	public String report() {
//		pservice.reorderReport();
//		return  "forward:/product/list";
//	}

}