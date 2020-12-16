package sg.edu.iss.ca_v1.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import sg.edu.iss.ca_v1.model.BindingMachineObj;
import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.Product;
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
	
	@RequestMapping(value = "/addusageform", method = RequestMethod.GET)
	public String addUsageForm(ModelMap model) {
	
		StockUsage stockUsage = new StockUsage();
		for (int i = 0; i < 3; i++) {
			stockUsage.addStockUsageInventory(new StockUsageInventory());
		}

		model.addAttribute("stockUsage", stockUsage);
		model.addAttribute("inventories", cservice.listAllInventories());
		return "usageform";
	}
	
	@RequestMapping(value = "/saveusagerecord", method = RequestMethod.POST)
	public String saveFixRecord(@ModelAttribute("stockUsage")@Valid StockUsage usage, BindingResult bindingResult, Model model) {
//		cservice.saveInventory(usage);
//		cservice.saveStockUsage(usage);
		cservice.saveStockUsage(usage);
		List<StockUsageInventory> usageOfTheCustomer = usage.getUsageOfTheCustomer();
		for (StockUsageInventory item : usageOfTheCustomer) {
			Inventory invItem = cservice.findPartById(item.getProductId());
			item.setInventory(invItem);
			item.setStockUsage(usage);
			cservice.saveStockUsageInventory(item);
			System.out.println(item.getInventory().getProduct().getName());
			System.out.println(item.getStockUsage().getCustomerName());
		}
		cservice.saveStockUsage(usage);
		
		return "forward:/catalogue/listusageofthiscustomer/" + usage.getCustomerName();
	}
	
	@RequestMapping(value = "/listusageofthiscustomer/{name}")
	public String listUsageOfThisCustomer(ModelMap model, @PathVariable("name") String name) {
		
		StockUsage wholeCustomerInfo = cservice.findStockUsageByCustomer(name); //to get custoerName and CarId
		List<StockUsageInventory> usagesOfTheCustomer = wholeCustomerInfo.getUsageOfTheCustomer();
		List<Inventory> inventoryOfTheCustomer = new ArrayList<>();
		for(StockUsageInventory sui: usagesOfTheCustomer) {
			int invId = sui.getProductId();
			Inventory i = cservice.findPartById(invId);
			inventoryOfTheCustomer.add(i);
			
		}
		
		List<BindingMachineObj> bindingMachine= new ArrayList<BindingMachineObj>();
		for(StockUsageInventory sui : usagesOfTheCustomer) {
			for(Inventory i : inventoryOfTheCustomer) {
				if(sui.getProductId() == i.getId()) {
					int usageRecordId = sui.getId();
					int partId = i.getId();
					LocalDate registrationDate = sui.getRegistrationDate();
					String partName = i.getProduct().getName();
					String brand = i.getProduct().getBrand();
					Double price = i.getProduct().getOriPrice();
					String description = i.getProduct().getDescription();
					String colour = i.getProduct().getColour();
					int quantity = sui.getQuantity();
					
					BindingMachineObj obj = new BindingMachineObj(usageRecordId,partId,partName,brand,price, description,colour,quantity,registrationDate,i) ;
					bindingMachine.add(obj);
				}
			}
		}

		model.addAttribute("wholeCustomerInfo", wholeCustomerInfo);
		model.addAttribute("bindingMachine", bindingMachine);
		
		
		return "customerusage";
		
	}
	
	@RequestMapping(value = "/edit/{carid}")
	  public String showEditForm(ModelMap model, @PathVariable("carid") Integer carid) {
		

		//StockUsage stockUsage = new StockUsage();
		
		StockUsage stockUsage = cservice.findStockUsageByCarId(carid);
		List<StockUsageInventory> usagesOfTheCustomer = stockUsage.getUsageOfTheCustomer();

		List<Inventory> inventoryOfTheCustomer = new ArrayList<>();
		for(StockUsageInventory sui: usagesOfTheCustomer) {
			int invId = sui.getProductId();
			Inventory i = cservice.findPartById(invId);
			inventoryOfTheCustomer.add(i);
			
		}
		model.addAttribute("stockUsage", stockUsage);
		model.addAttribute("inventories", inventoryOfTheCustomer);

		
		return "usageform";
	  }
	  
	  @GetMapping("/delete/{id}")
	  public String deleteMethod(Model model, @PathVariable("id") Integer id) {
		StockUsageInventory sui = cservice.findStockUsageInventoryById(id);
		String name = sui.getStockUsage().getCustomerName();
		
		cservice.deleteStockUsageInventoryById(id);
		return "forward:/catalogue/listusageofthiscustomer/" + name;
	  }

}

