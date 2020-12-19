package sg.edu.iss.ca_v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.service.InventoryImplementation;
import sg.edu.iss.ca_v1.service.InventoryInterface;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryInterface iservice;

	@Autowired
	public void setInventoryService(InventoryImplementation iserviceImpl) {
		this.iservice = iserviceImpl;
	}

//	@RequestMapping(value = "/list")
//	public String list(Model model) {
//		model.addAttribute("inventories", iservice.findAllInventorys());
//		return "inventories";
//	}



	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("inventory", iservice.findInventoryById(id));
		return "inventory-form";
	}



	@RequestMapping(value = "/delete/{id}")
	public String deleteInventory(@PathVariable("id") Integer id) {
		iservice.deleteInventory(iservice.findInventoryById(id));
		return "forward:/inventory/list";
	}

}
