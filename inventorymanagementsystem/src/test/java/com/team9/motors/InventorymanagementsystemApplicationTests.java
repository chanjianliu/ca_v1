package com.team9.motors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.team9.motors.interfacemethods.CatalogueInterface;
import com.team9.motors.repository.ProductRepository;
import com.team9.motors.repository.SupplierRepository;
import com.team9.motors.repository.UserRepository;

@SpringBootTest
class InventorymanagementsystemApplicationTests {

	@Autowired
	private SupplierRepository srepo;
	@Autowired
	private ProductRepository prepo;
	@Autowired
	private UserRepository urepo;
	@Autowired
	CatalogueInterface cservice;

	@Test
	void contextLoads_ProductInventorySupplier() {
		System.out.println("JJHIUHIUH");
	}

}
