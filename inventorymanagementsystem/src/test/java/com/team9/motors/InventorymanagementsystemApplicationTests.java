package com.team9.motors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.team9.motors.interfacemethods.CatalogueInterface;
import com.team9.motors.mail.JavaMailUtil;
import com.team9.motors.model.Role;
import com.team9.motors.model.User;
import com.team9.motors.repository.ProductRepository;
import com.team9.motors.repository.SupplierRepository;
import com.team9.motors.repository.UserRepository;
import com.team9.motors.service.UserImplementation;

import javassist.compiler.ast.NewExpr;

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
	@Autowired
	UserImplementation userImplementation; 

	@Test
	void findAdminEmail() {
		// user email
		List<User> ulist  = urepo.findUsersByRole(Role.ADMIN);
		String[] mailList = new String[ulist.size()];
		
		int counter = 0;
		for (User user : ulist) {
			mailList[counter] = user.getEmail();
			counter++;
		}
		for (String string : mailList) {
			System.out.println(string);
		}
	}
	
	@Test
	void test2() {
		System.out.println(userImplementation.findAdminEmail()[0]);
	}
}
