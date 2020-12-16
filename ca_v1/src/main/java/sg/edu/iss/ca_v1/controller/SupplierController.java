package sg.edu.iss.ca_v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.ca_v1.model.Supplier;
import sg.edu.iss.ca_v1.repo.SupplierRepository;
import sg.edu.iss.ca_v1.service.SupplierImplementation;
import sg.edu.iss.ca_v1.service.SupplierInterface;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierInterface sservice;

	@Autowired
	public void setSupplierInterface(SupplierImplementation simpl) {
		this.sservice = simpl;
	}

	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("suppliers", sservice.listAllSuppliers());
		return "supplierlist";
	}

//	@RequestMapping(value = "/add", method = RequestMethod.GET)
//	public String showForm(Model model) {
//		Supplier supplier = new Supplier();
//		model.addAttribute("supplier", supplier);
//		return "showsupplier";
//	}

	@RequestMapping(value = "/detail/{id}")
	public String showSupplier(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("supplier", sservice.findSupplierById(id));
		return "showsupplier";
	}

	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "addsupplier";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("supplier", sservice.findSupplierById(id));
		return "addsupplier";
	}

	@RequestMapping(value = "/save")
	public String saveSupplier(@ModelAttribute("supplier") @Valid Supplier supplier, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "addsupplier";
		}
		sservice.saveSupplier(supplier);
		return "forward:/supplier/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Integer id) {
		sservice.deleteSupplier(sservice.findSupplierById(id));
		return "forward:/supplier/list";
	}
}
