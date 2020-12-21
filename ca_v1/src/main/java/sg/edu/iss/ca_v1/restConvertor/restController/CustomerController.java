package sg.edu.iss.ca_v1.restConvertor.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ca_v1.restConvertor.restModel.CustomerAPI;
import sg.edu.iss.ca_v1.restConvertor.restRepo.CustomerAPIRepository;
import sg.edu.iss.ca_v1.restConvertor.restService.CatalogueAPIInterface;

@CrossOrigin
@RestController
@RequestMapping("apii/catalogue")
public class CustomerController {
	
	
	@Autowired
	private CatalogueAPIInterface cservice;
	@Autowired
	private CustomerAPIRepository crepo;
	
	
	@Autowired
	public void setCatalogue(CatalogueAPIInterface catalogue) {
		this.cservice = catalogue;
	}
	
	@GetMapping("/customers")
	public List<CustomerAPI> customerList() {
		//return cservice.listAllStockUsages();
		return crepo.findAll();
	}
//	@RequestMapping(value = "/customers")
//	public String customerList(Model model) {
//		List<StockUsage> customerList = cservice.listAllStockUsages();
//		model.addAttribute("customers", customerList);
//		return "customerlist";
//	}
	@PostMapping("/customers")  //add and save a customer
	public ResponseEntity<CustomerAPI> createStudent(@RequestBody CustomerAPI customerAPI) {
		try {
			//need to return a customer, fix later
			CustomerAPI c = crepo.save(new CustomerAPI(customerAPI.getCarId(),customerAPI.getCustomerName()));
			//(new Student(student.studentId, student.getName(), student.getNickName(), student.getMarks() ));
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

//	//create customer
//	@RequestMapping(value = "/addcustomer")
//	public String addCustomerForm(ModelMap model) {
//	
//		Customer stockUsage = new Customer();
//
//		model.addAttribute("stockUsage", stockUsage);
//		return "addcustomer";
//	}
//	
//	
//	@RequestMapping(value = "/savecustomer")
//	public String saveCustomerForm(@ModelAttribute("stockUsage") Customer customer, ModelMap model) {
//		cservice.saveCustomer(customer);
//		return "forward:/catalogue/showcustomer/"+customer.getId();
//	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerAPI> getStudentById(@PathVariable("id") Integer id) {
		int i = id;
		Optional<CustomerAPI> cData = crepo.findById(i);
		if (cData.isPresent()) {
			return new ResponseEntity<>(cData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
//	@RequestMapping(value = "/showcustomer/{id}")
//	public String listUsageOfThisCustomer(@PathVariable("id") Integer id, ModelMap model) {
//		
//		Customer customer = cservice.findCustomerById(id); //to get customerName and CarId
//		List<StockUsageRecord> usagesOfTheCustomer = customer.getUsageOfTheCustomer();
//
//		model.addAttribute("customer", customer);
//		model.addAttribute("usagesOfTheCustomer", usagesOfTheCustomer);
//		
//		return "customerusage";
//	}
	
	@PutMapping("/customers/edit/{id}")
	public ResponseEntity<CustomerAPI> editStudent(@PathVariable("id") int id, @RequestBody CustomerAPI customerAPI) {
		Optional<CustomerAPI> sData = crepo.findById(id);
		if (sData.isPresent()) {
			CustomerAPI _customer = sData.get();
			_customer.setCarId(customerAPI.getCarId());
			_customer.setCustomerName(customerAPI.getCustomerName());
			
			return new ResponseEntity<>(crepo.save(_customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
//	@RequestMapping(value = "/edit/{id}")
//	  public String showEditForm(@PathVariable("id") Integer id, ModelMap model) {
//
//		Customer stockUsage = cservice.findCustomerById(id);
//		List<StockUsageRecord> usageOfTheCustomer = stockUsage.getUsageOfTheCustomer();
//		
//		model.addAttribute("stockUsage", stockUsage);
//		model.addAttribute("usageOfTheCustomer", usageOfTheCustomer);
//		
//		return "editusageform";
//	  }
	

}
