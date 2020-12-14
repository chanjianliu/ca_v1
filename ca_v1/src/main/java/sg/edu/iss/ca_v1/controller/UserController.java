package sg.edu.iss.ca_v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca_v1.service.UserImplementation;
import sg.edu.iss.ca_v1.service.UserInterface;

@Controller
@RequestMapping("/login")
public class UserController {
	
	@Autowired
	private UserInterface uservice;
	
	@Autowired
	public void setUserInterface(UserImplementation uimpl) {
		this.uservice = uimpl;
	}
	
//	newUser.setName(user.getName());
//	newUser.setPassword(user.getPassword());
//	newUser.setRole(user.getRole());
//	newUser.setUsername(user.getUsername());
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		model.addAttribute("users", uservice.listAllUser());
		return "userList";
	}
}
