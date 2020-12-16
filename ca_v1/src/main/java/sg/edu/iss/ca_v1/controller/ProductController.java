package sg.edu.iss.ca_v1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca_v1.service.ProductService;
import sg.edu.iss.ca_v1.service.ProductServiceImpl;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	public void setProductService(ProductServiceImpl pServiceImpl) {
		this.pservice = pServiceImpl;
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("products", pservice.findAllProduts());
		return "products";
	}

}
