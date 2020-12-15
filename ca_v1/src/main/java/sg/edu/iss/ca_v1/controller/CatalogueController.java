package sg.edu.iss.ca_v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	///add usage
	@RequestMapping(value = "/addusageform", method = RequestMethod.GET)
	public String addUsageForm(Model model) {
		StockUsageInventory stockUsageInventory = new StockUsageInventory();
		
		StockUsage stockUsage = new StockUsage();
		stockUsageInventory.setStockUsage(stockUsage);
		
		model.addAttribute("stockUsageInventory", stockUsageInventory);
		return "usageform";
	}
	@RequestMapping(value = "/saveusagerecord", method = RequestMethod.POST)
	public String saveFixRecord(@ModelAttribute("stockUsageInventory")@Valid StockUsageInventory usage, BindingResult bindingResult, Model model) {
//		cservice.saveInventory(usage);
//		cservice.saveStockUsage(usage);
		cservice.saveStockUsageInventory(usage);
		return "forward:/catalogue/list";
	}
	@RequestMapping(value = "/usage")
	public String list(Model model) {
		List<StockUsageInventory> inventories = cservice.listAllUsages();
		model.addAttribute("inventories", inventories);
		return "catalogue";
	}

}
