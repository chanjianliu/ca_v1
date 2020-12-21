package com.team9.motors.controller;

import com.team9.motors.interfacemethods.InventoryInterface;
import com.team9.motors.service.InventoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userdetails")
@RequestMapping("/all/inventory")
public class InventoryController {

    @Autowired
    private InventoryInterface iservice;

    @Autowired
    public void setInventoryService(InventoryImplementation iserviceImpl) {
        this.iservice = iserviceImpl;
    }

    	@RequestMapping(value = "/list")
        public String list(Model model) {
	        model.addAttribute("inventories", iservice.findAllInventorys());
	        return "inventories";
        }


    @RequestMapping(value = "/all/delete/{id}")
    public String deleteInventory(@PathVariable("id") Integer id) {
        iservice.deleteInventory(iservice.findInventoryById(id));
        return "forward:/all/inventory/list";
    }

}

