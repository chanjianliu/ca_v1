package sg.edu.iss.ca_v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.ca_v1.model.User;
import sg.edu.iss.ca_v1.service.AdminUserImplementation;
import sg.edu.iss.ca_v1.service.AdminUserInterface;


@Controller
@RequestMapping("/user")
public class AdminUserController {

	@Autowired
	AdminUserInterface adminservice;
	
	@Autowired
	public void setAdminUserImplementation(AdminUserImplementation uimpl)
	{
		this.adminservice=uimpl;
	}
	@RequestMapping(value="/list")
	public String listusers(Model model) {
		model.addAttribute("users", adminservice.findAllUsers());
		
		return "users";
	}
	
	@RequestMapping(value="/adduser")
public String addForm(Model model) {
		
		model.addAttribute("user", new User());
		
		return "userform";
	}
	@RequestMapping(value="/save")
	public String saveUser(@ModelAttribute("user") @Valid User user, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "userform";
		}
		adminservice.saveUser(user);
		return "forward:/user/list";
	}
	
	
	@RequestMapping(value="/edit/{id}")
	public String editForm(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("user", adminservice.findUserById(id));
		return "userform";
	}
	@RequestMapping(value="/delete/{id}")
	public String deleteUser(Model model,@PathVariable("id") Integer id) {
		adminservice.deleteUser(adminservice.findUserById(id));
		return "forward:/user/list";
	}
}
