package sg.edu.iss.ca_v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca_v1.model.Supplier;

import sg.edu.iss.ca_v1.service.SupplierImplementation;
import sg.edu.iss.ca_v1.service.SupplierInterface;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierInterface sservice;

	@Autowired
	public void setSupplierInterface(SupplierImplementation simpl) {
		this.sservice = simpl;
	}

	@RequestMapping(value="/list")
	public String listusers(Model model) {
 		
 		return listByPage(model, 1);
	}
	
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model,@PathVariable("pageNumber") int currentPage) {
 		Page<Supplier> page=sservice.listAllSuppliers(currentPage);
 		
 		long total=page.getTotalElements();
 		int totalPages=page.getTotalPages();
 		
 		List<Supplier> list=page.getContent();
 		
 		model.addAttribute("currentPage",currentPage);
 		model.addAttribute("total",total);
 		model.addAttribute("totalPages",totalPages);
		model.addAttribute("list", list);
		model.addAttribute("supplierlist", sservice.listAllSuppliers(currentPage));
		
		return "supplierlist";
 	}

//	@RequestMapping(value = "/add", method = RequestMethod.GET)
//	public String showForm(Model model) {
//		Supplier supplier = new Supplier();
//		model.addAttribute("supplier", supplier);
//		return "showsupplier";
//	}


	
	@RequestMapping(value = "/detail/{id}")
	public String showSupplier(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("supplier", sservice.findSupplierById(id));
		return "showsupplier";
	}

	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "addsupplier";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("supplier", sservice.findSupplierById(id));
		return "addsupplier";
	}

	@RequestMapping(value = "/save")
	public String saveSupplier(@ModelAttribute("supplier") @Valid Supplier supplier, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "addsupplier";
		}
		sservice.saveSupplier(supplier);
		return "forward:/supplier/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Integer id) {
		sservice.deleteSupplier(sservice.findSupplierById(id));
		return "forward:/supplier/list";
	}
}

