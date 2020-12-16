package sg.edu.iss.ca_v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	private AdminUserInterface adminservice;
	
	@Autowired
	public void setAdminUserImplementation(AdminUserImplementation uimpl)
	{
		this.adminservice=uimpl;
	}
// 	@RequestMapping(value="/list")
//	public String listusers(Model model) {
//		model.addAttribute("users", adminservice.findAll());
//		
//		return "users";
//	}
 	@RequestMapping(value="/list")
	public String listusers(Model model) {
 		
 		return listByPage(model, 1);
	}
 	
 	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model,@PathVariable("pageNumber") int currentPage) {
 		Page<User> page=adminservice.findAll(currentPage);
 		
 		long total=page.getTotalElements();
 		int totalPages=page.getTotalPages();
 		
 		List<User> list=page.getContent();
 		
 		model.addAttribute("currentPage",currentPage);
 		model.addAttribute("total",total);
 		model.addAttribute("totalPages",totalPages);
		model.addAttribute("list", list);
		model.addAttribute("users", adminservice.findAll(currentPage));
		
		return "users";
 	}
	
	
	@RequestMapping(value="/showuser/{id}",method = RequestMethod.GET)
	public String showdetail(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("users",adminservice.findById(id));
		
		return "showdetail";
	}
//	@RequestMapping(value="/showduser")
//	public String list1(Model model,@PathVariable("id") Integer id) {
//		model.addAttribute("users", adminservice.findById(id));
//		
//		return "userform";
//	}

	
	
		
	@RequestMapping(value="/adduser", method = RequestMethod.GET)
public String addForm(Model model) {
		
		model.addAttribute("user", new User());
		return "userform";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") @Valid User user, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error","Unsuccessful,please try again");
		}
		adminservice.saveUser(user);
		return "forward:/user/list";
	}
	
	
	@RequestMapping(value="/edit/{id}")
	public String editForm(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("user", adminservice.findById(id));
		return "userform";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String deleteUser(Model model,@PathVariable("id") Integer id) {
		adminservice.deleteUser(adminservice.findById(id));
		return "forward:/user/list";
	}
}
