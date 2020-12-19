package sg.edu.iss.ca_v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.model.Supplier;
import sg.edu.iss.ca_v1.repo.SupplierRepository;
import sg.edu.iss.ca_v1.service.InventoryImplementation;
import sg.edu.iss.ca_v1.service.InventoryInterface;
import sg.edu.iss.ca_v1.service.ProductImplementation;
import sg.edu.iss.ca_v1.service.ProductInterface;
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

	@Autowired
	private ProductInterface pservice;

	@Autowired
	public void setProductService(ProductImplementation pserviceImpl) {
		this.pservice = pserviceImpl;
	}
	
	@Autowired
	private InventoryInterface iservice;

	@Autowired
	public void setInventoryService(InventoryImplementation iserviceImpl) {
		this.iservice = iserviceImpl;
	}
	
//	//old supplier list page w/o pagination
//	@RequestMapping(value = "/list")
//	public String list(Model model) {
//		model.addAttribute("suppliers", sservice.listAllSuppliers());
//		return "supplierlist";
//	}

    @GetMapping("/page/{pageNumber}")
    public String listByPage(ModelMap model,@PathVariable("pageNumber") int currentPage) {
        Page<Supplier> page=sservice.listAllSuppliers(currentPage);

        long total=page.getTotalElements();
        int totalPages=page.getTotalPages();

        List<Supplier> list=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("total",total);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("list", list);
        model.addAttribute("suppliers", sservice.listAllSuppliers(currentPage));

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
		return "forward:/supplier/detail/"+supplier.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Integer id) {
		Supplier supplier =  sservice.findSupplierById(id);
		List<Product> supplierProducts = supplier.getProductList();
		for (Product p : supplierProducts) {
			Inventory productInventory = p.getInventory();
			iservice.deleteInventory(productInventory);
			pservice.deleteProduct(p);
		}
		
		sservice.deleteSupplier(supplier);
		return "forward:/supplier/page/1";
	}
}
