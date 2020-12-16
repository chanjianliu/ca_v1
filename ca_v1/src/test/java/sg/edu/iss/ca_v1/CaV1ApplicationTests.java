//package sg.edu.iss.ca_v1;
//
//
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import sg.edu.iss.ca_v1.model.Inventory;
//import sg.edu.iss.ca_v1.model.Product;
//import sg.edu.iss.ca_v1.model.ProductState;
//import sg.edu.iss.ca_v1.model.StockUsage;
//import sg.edu.iss.ca_v1.model.StockUsageInventory;
//import sg.edu.iss.ca_v1.model.Supplier;
//import sg.edu.iss.ca_v1.repo.InventoryRepository;
//import sg.edu.iss.ca_v1.repo.ProductRepository;
//import sg.edu.iss.ca_v1.repo.StockUsageInventoryRepository;
//import sg.edu.iss.ca_v1.repo.StockUsageRepository;
//import sg.edu.iss.ca_v1.repo.SupplierRepository;
//
//@SpringBootTest
//class CaV1ApplicationTests {
//
//	@Autowired
//	InventoryRepository irepo;
//	@Autowired
//	ProductRepository prepo;
//	@Autowired
//	StockUsageRepository surepo;
//	@Autowired
//	StockUsageInventoryRepository sirepo;
//	@Autowired
//	SupplierRepository srepo;
//
//	@Test
//	void contextLoads() {
//		LocalDate date = LocalDate.now();
//		Supplier s = new Supplier();
//		s.setAddress("1 Pasir Panjang");
//		s.setEmail("123@gmail.com");
//		s.setName("ABC");
//		s.setPhoneNumber("123456");
//	
//		//srepo.save(s);
//		
//		//String name, String brand, LocalDate dom, String colour, double oriPrice, double wholesalePrice,
//		//double retailPrice, double partnerPrice, String description, String dimension, String category,
//		//int reorderLevel, int minReorderQuantity, Supplier supplier
//		Product pWheel = new Product("Steer wheel", "BMW",date,"red",1000,1000,1000,1000,"large","round","Wheel",10,5,s);
//		prepo.save(pWheel);
//		
//		Inventory iWheel = new Inventory();
//		iWheel.setProductState(ProductState.InStock);
//		iWheel.setQuantity(50);
//		iWheel.setShelfLocation(3);
//		iWheel.setProduct(pWheel);
//		
//		//irepo.save(iWheel);
//		
//		StockUsage su = new StockUsage();
//		su.setCarId(1);
//		su.setCustomerName("John");
//		//surepo.save(su);
//		
//		StockUsageInventory si = new StockUsageInventory();
//		si.setStockUsage(su);
//		si.setInventory(iWheel);
//		si.setRegistrationDate(date);
//		si.setQuantity(2);
//		
//		sirepo.save(si);
//		
//		
//		//sirepo.findAll();
//		}
//	
//
//	
//	
//	
//	
//	}
package sg.edu.iss.ca_v1;


import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.model.ProductState;
import sg.edu.iss.ca_v1.model.StockUsage;
import sg.edu.iss.ca_v1.model.StockUsageInventory;
import sg.edu.iss.ca_v1.model.Supplier;
import sg.edu.iss.ca_v1.repo.ProductRepository;
import sg.edu.iss.ca_v1.repo.SupplierRepository;
import sg.edu.iss.ca_v1.service.CatalogueImplementation;
import sg.edu.iss.ca_v1.service.CatalogueInterface;

@SpringBootTest
class CaV1ApplicationTests {

	@Autowired
	private SupplierRepository srepo;
	
	@Autowired
	private ProductRepository prepo;
	
	@Autowired
	CatalogueInterface cservice;

	@Autowired
	public void setCatalogueService(CatalogueImplementation cimpl) {
		this.cservice = cimpl;
	}
	
	@Test
	void contextLoads() {
		LocalDate date = LocalDate.now();
		Supplier s = new Supplier();
		s.setAddress("1 Pasir Panjang");
		s.setEmail("123@gmail.com");
		s.setName("ABC");
		s.setPhoneNumber("123456");

		
		//String name, String brand, LocalDate dom, String colour, double oriPrice, double wholesalePrice,
		//double retailPrice, double partnerPrice, String description, String dimension, String category,
		//int reorderLevel, int minReorderQuantity, Supplier supplier
		Product pWheel = new Product("Steer wheel", "BMW",date,"red",1000,1000,1000,1000,"large","round","Wheel",10,5,s);
		
		
		Inventory iWheel = new Inventory();
		iWheel.setProductState(ProductState.InStock);
		iWheel.setQuantity(50);
		iWheel.setShelfLocation(3);
		iWheel.setProduct(pWheel);

		prepo.save(pWheel);
		
		//2nd set
		Supplier s2 = new Supplier();
		s2.setAddress("4 Bedok Street");
		s2.setEmail("123@gmail.com");
		s2.setName("Jim's Light");
		s2.setPhoneNumber("123456");

		
		//String name, String brand, LocalDate dom, String colour, double oriPrice, double wholesalePrice,
		//double retailPrice, double partnerPrice, String description, String dimension, String category,
		//int reorderLevel, int minReorderQuantity, Supplier supplier
		Product pWheel2 = new Product("Front Light", "Mercedes",date,"yellow",1000,1000,1000,1000,"small","round","light",10,5,s2);
		
		
		Inventory iWheel2 = new Inventory();
		iWheel2.setProductState(ProductState.InStock);
		iWheel2.setQuantity(100);
		iWheel2.setShelfLocation(5);
		iWheel2.setProduct(pWheel2);

		prepo.save(pWheel2);
		
		
		//3rd set
		Supplier s3 = new Supplier();
		s3.setAddress("Changi Airport");
		s3.setEmail("123@gmail.com");
		s3.setName("BMW Engines");
		s3.setPhoneNumber("123456");

		
		//String name, String brand, LocalDate dom, String colour, double oriPrice, double wholesalePrice,
		//double retailPrice, double partnerPrice, String description, String dimension, String category,
		//int reorderLevel, int minReorderQuantity, Supplier supplier
		Product pWheel3 = new Product("Engine", "Toyota",date,"black",1000,1000,1000,1000,"large","square","engine",10,5,s3);
		
		
		Inventory iWheel3 = new Inventory();
		iWheel3.setProductState(ProductState.InStock);
		iWheel3.setQuantity(100);
		iWheel3.setShelfLocation(7);
		iWheel3.setProduct(pWheel3);

		prepo.save(pWheel3);
		}
	}

