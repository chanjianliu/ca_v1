package sg.edu.iss.ca_v1.controller;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("user") @Valid User user, 
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Unsuccessful, please try again.");
			return "addUser";
		}
		
		User dbUser = uservice.findById(user.getId());
		System.out.println("Inside dbUser");
//		dbUser.setId(user.getId());
		dbUser.setName(user.getName());
		dbUser.setRole(user.getRole());
		dbUser.setUsername(user.getUsername());
//		dbUser.setPassword(user.getPassword());
		uservice.saveUser(dbUser);
//		else
//		{
//			System.out.println("new user");
		
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
		model.addAttribute("user", urepo.findById(id).get());
		return "editUser";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String deleteUser(Model model, @PathVariable("id") Integer id) {
		uservice.deleteUser(urepo.findById(id).get());
		return "forward:/user/list";
	}
	
//All the login and logout stuff
	@RequestMapping(path = "/login")
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(path = "/authenticate")
	public String authenticate(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(uservice.authenticate(user))
		{
			User u = uservice.findByName(user.getUsername());
			session.setAttribute("usession", u);
			model.addAttribute("user",u);
			return "landing";
		}
		else
			return "index";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "forward:/user/login";
	}
	
	@RequestMapping(path = "/landing")
	public String backToLanding(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(session.getId() != null || session.getId() != "") 
		{
			User u = uservice.findByName(user.getUsername());
			model.addAttribute("user", u);
			return "landing";
		}
		else
			return "index";
	}
}
