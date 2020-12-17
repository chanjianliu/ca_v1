package sg.edu.iss.ca_v1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.StockUsage;
import sg.edu.iss.ca_v1.model.StockUsageInventory;
import sg.edu.iss.ca_v1.model.User;
import sg.edu.iss.ca_v1.service.CatalogueInterface;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {
	
	
	@Autowired
	private CatalogueInterface cservice;
	
	@Autowired
	public void setCatalogue(CatalogueInterface catalogue) {
		this.cservice = catalogue;
	}
	
	//add customer
	@RequestMapping(value = "/addcustomer")
	public String addCustomerForm(ModelMap model) {
	
		StockUsage stockUsage = new StockUsage();

		model.addAttribute("stockUsage", stockUsage);
		return "addcustomer";
	}
	
	@RequestMapping(value = "/savecustomer")
	public String saveCustomerForm(@ModelAttribute("stockUsage") StockUsage customer, ModelMap model) {
		cservice.saveStockUsage(customer);
		return "forward:/catalogue/showcustomer/"+customer.getId();
	}
	
	///add usage
	@RequestMapping(value = "/addusageform/{id}", method=RequestMethod.GET)
	public String addUsageForm(ModelMap model, @PathVariable ("id") Integer id) {
	
		StockUsage customer = cservice.findCustomerById(id);
		customer.addStockUsageInventory(new StockUsageInventory());
		
		model.addAttribute("stockUsage", customer);
		model.addAttribute("inventories", cservice.listAllInventories());
		
//		Map<StockUsage, List<Inventory>> doubleObj = new HashMap<>();
//		doubleObj.put(stockUsage, cservice.listAllInventories());
//		model.addAllAttributes(attributes);
		return "usageform";
	}
	
	@RequestMapping(value = "/saveusagerecord", method = RequestMethod.POST)
	public String saveFixRecord(@ModelAttribute("stockUsage")@Valid StockUsage usage, BindingResult bindingResult, Model model) {
//		cservice.saveInventory(usage);
//		cservice.saveStockUsage(usage);
		cservice.saveStockUsage(usage);
		
		List<StockUsageInventory> usageOfTheCustomer = usage.getUsageOfTheCustomer();
		for (StockUsageInventory item : usageOfTheCustomer) {
			
			if (item.getInventory() == null) {
				Inventory invItem = cservice.findPartById(item.getProductId());
				item.setInventory(invItem);
				item.setStockUsage(usage);
			}
			cservice.saveStockUsageInventory(item);
			
			System.out.println(item.getInventory().getProduct().getName());
			System.out.println(item.getStockUsage().getCustomerName());
		}
		cservice.saveStockUsage(usage);
		
		return "forward:/catalogue/showcustomer/"+usage.getId();
	}
	
	@RequestMapping(value = "/showcustomer/{id}")
	public String listUsageOfThisCustomer(@PathVariable("id") Integer id, ModelMap model) {
		
		StockUsage customer = cservice.findCustomerById(id); //to get customerName and CarId
		List<StockUsageInventory> usagesOfTheCustomer = customer.getUsageOfTheCustomer();

		model.addAttribute("customer", customer);
		model.addAttribute("usagesOfTheCustomer", usagesOfTheCustomer);
		
		return "customerusage";
	}
	
	@RequestMapping(value = "/edit/{id}")
	  public String showEditForm(@PathVariable("id") Integer id, ModelMap model) {

		StockUsage stockUsage = cservice.findCustomerById(id);
		List<StockUsageInventory> usageOfTheCustomer = stockUsage.getUsageOfTheCustomer();
		
		model.addAttribute("stockUsage", stockUsage);
		model.addAttribute("usageOfTheCustomer", usageOfTheCustomer);
		
		return "editusageform";
	  }
	
	@RequestMapping(value = "/customers")
	public String customerList(Model model) {
		List<StockUsage> customerList = cservice.listAllStockUsages();
		model.addAttribute("customers", customerList);
		return "customerlist";
	}
}
