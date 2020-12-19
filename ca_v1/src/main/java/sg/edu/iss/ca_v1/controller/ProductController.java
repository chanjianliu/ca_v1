package sg.edu.iss.ca_v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.model.Supplier;
import sg.edu.iss.ca_v1.service.InventoryImplementation;
import sg.edu.iss.ca_v1.service.InventoryInterface;
import sg.edu.iss.ca_v1.service.ProductImplementation;
import sg.edu.iss.ca_v1.service.ProductInterface;
import sg.edu.iss.ca_v1.service.SupplierImplementation;
import sg.edu.iss.ca_v1.service.SupplierInterface;

@Controller
@RequestMapping("/product")
public class ProductController {

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

	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("products", pservice.findAllProducts());
		return "products";
	}

	@RequestMapping(value = "/add")
	public String addForm(ModelMap model) {
		model.addAttribute("product", new Product());
		model.addAttribute("suppliers", sservice.listAllSuppliers());
		return "addproduct";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("product", pservice.findProductById(id));
		return "product-form";
	}

	@RequestMapping(value = "/save")
	public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			return "product-form";
		}
		
		Supplier supplier = sservice.findSupplierById(product.getSupId());
		product.setSupplier(supplier);
		
		Product dbProduct = pservice.findProductById(product.getId());
		if (dbProduct != null) {
			Inventory productInventory = dbProduct.getInventory();
			if (productInventory != null)
				product.setInventory(productInventory);
		}
		
		pservice.saveProduct(product);
		
		return "forward:/product/list";
	}
	
	@RequestMapping(value = "/savenew")
	public String saveNewProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			return "product-form";
		}
		
		Supplier supplier = sservice.findSupplierById(product.getSupId());
		product.setSupplier(supplier);
		pservice.saveProduct(product);

		return "forward:/inventory/add/"+product.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		Product product = pservice.findProductById(id);
		Inventory productInventory = product.getInventory();
		iservice.deleteInventory(productInventory);
		pservice.deleteProduct(product);
		return "forward:/product/list";
	}

	
	@RequestMapping(value = "/inventory/add/{id}")
	public String addForm(@PathVariable("id") Integer id, ModelMap model) {
		Product product = pservice.findProductById(id);
		Inventory inventory = new Inventory();
		inventory.setProduct(product);
		model.addAttribute("inventory", inventory);
		return "inventory-form";
	}
	
	@RequestMapping(value = "/inventory/save/{id}")
	public String saveInventory(@PathVariable("id") Integer id, @ModelAttribute("inventory") @Valid Inventory inventory, 
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "inventory-form";
		}
		
		Product product = pservice.findProductById(id);
		product.setInventory(inventory);
		pservice.saveProduct(product);
		
		return "forward:/product/list";
	}
}
