package sg.edu.iss.ca_v1.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.ca_v1.model.DateSelector;
import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.StockUsage;
import sg.edu.iss.ca_v1.model.StockUsageInventory;
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
	
	//add usage
	@RequestMapping(value = "/addusageform/{id}", method=RequestMethod.GET)
	public String addUsageForm(ModelMap model, @PathVariable ("id") Integer id) {
	
		StockUsage customer = cservice.findCustomerById(id);
		StockUsageInventory newUsageRecord = new StockUsageInventory();
		newUsageRecord.setStockUsage(customer);
		
		model.addAttribute("newUsageRecord", newUsageRecord);
		model.addAttribute("inventories", cservice.listAllInventories());
		
//		Map<StockUsage, List<Inventory>> doubleObj = new HashMap<>();
//		doubleObj.put(stockUsage, cservice.listAllInventories());
//		model.addAllAttributes(attributes);
		return "usageform";
	}
	

	@RequestMapping(value = "/saveusagerecord/{id}", method = RequestMethod.POST)
	public String saveFixRecord(@PathVariable("id") int id, @ModelAttribute("newUsageRecord")@Valid StockUsageInventory usage, BindingResult bindingResult, Model model) {
//		cservice.saveInventory(usage);
//		cservice.saveStockUsage(usage);
	
		//find the customer by using the record id (which was save in database when user press add item)
		StockUsage customer = cservice.findCustomerById(id);
		//add record into customer record List
		customer.addStockUsageInventory(usage);
		//cservice.saveStockUsage(customer);
		//find Inventory object with the inventory id retrieved above
		Inventory item = cservice.findPartById(usage.getProductId());
		//save the item into the record
		usage.setInventory(item);
		usage.setStockUsage(customer);
		cservice.saveStockUsageInventory(usage);
		
//		System.out.println(usage.getInventory().getProduct().getName());
//		System.out.println(usage.getStockUsage().getCustomerName());
		
		return "forward:/catalogue/showcustomer/"+customer.getId();
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
	
	
	@RequestMapping(value = "/listrecord")
	public String showRecordLsit(ModelMap model) {
		DateSelector dates = new DateSelector();
		model.addAttribute("dates",dates);
		
		List<StockUsageInventory> records = cservice.listAllStockUsageInventories();
		model.addAttribute("records",records);
		return "allrecordlist";
		
	}
	
	//"${#temporals.format(attributename, 'MM-yyyy')}"
	@RequestMapping(value = "/stockusgeinventorybydaterange")
	public String showRecordByDateRange(@ModelAttribute("dates")@Valid DateSelector dates, Model model) {
		LocalDate start = LocalDate.parse(dates.getStartDate());
		LocalDate end = LocalDate.parse(dates.getEndDate());
		
		List<StockUsageInventory> recordsByDateRange = cservice.findStockUsageInventoryByRegistrationDateBetween(start, end);
		model.addAttribute("recordsByDateRange",recordsByDateRange);
		
		return "recordsbydates";
	}
}
