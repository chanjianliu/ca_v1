package sg.edu.iss.ca_v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca_v1.model.User;
import sg.edu.iss.ca_v1.service.UserImplementation;
import sg.edu.iss.ca_v1.service.UserInterface;

@Controller
@RequestMapping("/user")
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
	
	@RequestMapping(value="/add") 
	public String add(Model model){
		model.addAttribute("user", new User());
		return "addUser";
	}
	
	@RequestMapping(value="/save")
	public String save(@ModelAttribute("user") @Valid User user, 
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Unsuccessful, please try again.");
			return "addUser";
		}
		
		User dbUser = uservice.findUserById(user.getId());
		
		if (dbUser != null)
			uservice.updateUser(user);
		else
			uservice.createUser(user);
		
		return "forward:/user/list";
	}
}
