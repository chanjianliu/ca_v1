package sg.edu.iss.ca_v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca_v1.model.User;
import sg.edu.iss.ca_v1.repo.UserRepository;
import sg.edu.iss.ca_v1.service.UserImplementation;
import sg.edu.iss.ca_v1.service.UserInterface;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserInterface uservice;
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	public void setUserInterface(UserImplementation uimpl) {
		this.uservice = uimpl;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
				
	}
	
	@RequestMapping(value="/list")
	public String list(Model model) {
		model.addAttribute("users", uservice.listAllUser());
		return "userList";
	}
	
	@RequestMapping(value="/add") 
	public String add(ModelMap model){
		model.addAttribute("user", new User());
		model.addAttribute("new", "new");
		return "addUser";
	}
	
	@RequestMapping("/save")
	public String save(@ModelAttribute("user") @Valid User user, 
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Unsuccessful, please try again.");
			return "addUser";
		}
		
//		User dbUser = uservice.findById(user.getId());
//		if (dbUser != null && user.getPassword().equals(dbUser.getPassword()))
//		{
//			System.out.println("Inside dbUser");
//			dbUser.setName(user.getName());
//			dbUser.setRole(user.getRole());
//			dbUser.setUsername(user.getUsername());
//			uservice.saveUser();
//		}
//		else
//		{
//			System.out.println("new user");
			uservice.saveUser(user);
//		}
		return "forward:/user/showuser/"+user.getId();
	}
	
	@RequestMapping(value="/showuser/{id}")
	public String showdetail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("user", urepo.findById(id).get());
		
		return "showuser";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editForm(Model model, @PathVariable("id") Integer id) {
		User user = urepo.findById(id).get();
		model.addAttribute("user", user);
		return "editUser";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String deleteUser(Model model, @PathVariable("id") Integer id) {
		uservice.deleteUser(urepo.findById(id).get());
		return "forward:/user/list";
	}
}
