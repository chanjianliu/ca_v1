package sg.edu.iss.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.ca.service.SearchImplementation;
import sg.edu.iss.ca.service.SearchInterface;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private SearchInterface sservice;

	@Autowired
	public void setSearchInterface(SearchImplementation simpl) {
		this.sservice = simpl;
	}
	
	@RequestMapping(value = "/index")
	public String list(Model model) {
		return "Search";
	}
	
	@RequestMapping(value="searching")
	public String search(@RequestParam("keyword") String k,@RequestParam("searchtype") String t,Model model)
	{
		System.out.println(t);
		System.out.println(k);
		String name=new String("name");
		String brand=new String("brand");
		String colour=new String("colour");
		String catagory=new String("category");
		String description=new String("description");
		if(t.equals(name))
		{
			model.addAttribute("products", sservice.SearchProductByName(k));
		}
		else if(t.equals(brand))
		{
			model.addAttribute("products", sservice.SearchProductByBrand(k));
		}
		else if(t.equals(colour))
		{
			model.addAttribute("products", sservice.SearchProductByColour(k));
		}
		else if(t.equals(catagory))
		{
			model.addAttribute("products", sservice.SearchProductByCategory(k));
		}
		else if(t.equals(description))
		{
			model.addAttribute("products", sservice.SearchProductByDescription(k));
		}
		else
		{
			System.out.println("Something wrong");
		}
		
		
		return "SearchResult";
	}
	

}
