package sg.edu.iss.ca_v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.model.StockUsageInventory;
import sg.edu.iss.ca_v1.model.Supplier;
import sg.edu.iss.ca_v1.service.InventoryImplementation;
import sg.edu.iss.ca_v1.service.InventoryInterface;
import sg.edu.iss.ca_v1.service.ProductImplementation;
import sg.edu.iss.ca_v1.service.ProductInterface;
import sg.edu.iss.ca_v1.service.StockUsageInventoryImplementation;
import sg.edu.iss.ca_v1.service.StockUsageInventoryInterface;
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

	@Autowired
	private StockUsageInventoryInterface suiservice;
	
	@Autowired
	public void setStockUsageInventoryService(StockUsageInventoryImplementation suiserviceImpl) {
		this.suiservice = suiserviceImpl;
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
	public String editProductForm(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("product", pservice.findProductById(id));
		model.addAttribute("suppliers", sservice.listAllSuppliers());
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

		return "forward:/product/inventory/add/"+product.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id) {
		Product product = pservice.findProductById(id);
		Inventory productInventory = iservice.findInventoryById(product.getInventory().getId());
		
		List<StockUsageInventory> recordLists = productInventory.getStockUsageInventory();
		
		if (recordLists != null)
		{
			for (StockUsageInventory record : recordLists) {
				suiservice.deleteStockUsageInventory(record);
			}
		}
		
//		iservice.deleteInventory(productInventory); //not working
		pservice.deleteProduct(product);
		
		return "forward:/product/list";
	}

	
	@RequestMapping(value = "/inventory/add/{id}")
	public String addForm(@PathVariable("id") Integer id, ModelMap model) {
		Product product = pservice.findProductById(id);
		Inventory inventory = new Inventory();
		model.addAttribute("product", product);
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
	
	@RequestMapping(value = "/inventory/saveedit/{id}")
	public String saveInventoryEdit(@PathVariable("id") Integer id, @ModelAttribute("inventory") @Valid Inventory inventory, 
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "inventoryedit";
		}
		
		Product product = pservice.findProductById(id);
		product.setInventory(inventory);
		pservice.saveProduct(product);
		
		return "forward:/catalogue/showinventory/"+inventory.getId();
	}
	
	@RequestMapping(value = "/inventory/edit/{id}")
	public String editInventoryForm(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("inventory", iservice.findInventoryById(id));
		return "inventoryedit";
	}
	
	//changwei
//	@RequestMapping(value = "/index")
//	public String list(Model model) {
//		return "Search";
//	}
	
	@RequestMapping(value="searching")
	public String search(@RequestParam("keyword") String k,@RequestParam("searchtype") String t,Model model)
	{
		System.out.println(t);
		System.out.println(k);
		String name=new String("name");
		String brand=new String("brand");
		String colour=new String("colour");
		String catagory=new String("category");
		String description=new String("description");
		if(t.equals(name))
		{
			model.addAttribute("products", pservice.SearchProductByName(k));
		}
		else if(t.equals(brand))
		{
			model.addAttribute("products", pservice.SearchProductByBrand(k));
		}
		else if(t.equals(colour))
		{
			model.addAttribute("products", pservice.SearchProductByColour(k));
		}
		else if(t.equals(catagory))
		{
			model.addAttribute("products", pservice.SearchProductByCategory(k));
		}
		else if(t.equals(description))
		{
			model.addAttribute("products", pservice.SearchProductByDescription(k));
		}
		else
		{
			System.out.println("Something wrong");
		}
		
		
		return "searchResult";
	}
	
	
}
