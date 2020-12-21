package sg.edu.iss.ca_v1.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca_v1.model.User;
import sg.edu.iss.ca_v1.model.UserRole;
import sg.edu.iss.ca_v1.service.UserImplementation;
import sg.edu.iss.ca_v1.service.UserInterface;

@SpringBootTest
class UserListTest {

	@Autowired
	UserInterface uservice;
	
	@Autowired
	public void setUserInterface(UserImplementation uimpl) {
		this.uservice = uimpl;
	}
	
	@Test
	void contextLoads() {
		User u1 = new User("John Appleseed", "john", "john", UserRole.ADMIN);
		User u2 = new User("Maggie Smith", "maggie", "maggie", UserRole.MECHANIC);
		uservice.saveUser(u1);
		uservice.saveUser(u2);
		
		
		
		User u3 = new User("Tony Stark", "tony", "tony", UserRole.ADMIN);
		User u4 = new User("Bruce Banner", "banner", "banner", UserRole.MECHANIC);
		User u5 = new User("Thor Odinson", "thor", "thor", UserRole.ADMIN);
		User u6 = new User("Steve Rogers", "steve", "steve", UserRole.MECHANIC);
		User u7 = new User("Natasha Romanoff", "natasha", "natasha", UserRole.ADMIN);
		User u8 = new User("Clint Barton", "clint", "clint", UserRole.MECHANIC);
		User u9 = new User("Clark Kent", "clark", "clark", UserRole.ADMIN);
		User u10 = new User("Bruce Wayne", "bruce", "bruce", UserRole.MECHANIC);
		User u11 = new User("Barry Allen", "barry", "barry", UserRole.ADMIN);
		User u12 = new User("Lex Luthor", "lex", "lex", UserRole.MECHANIC);
		User u13 = new User("Tom Baker", "tom", "tom", UserRole.ADMIN);
		User u14 = new User("Kate Baker", "kate", "kate", UserRole.MECHANIC);
		User u15 = new User("Lorraine Baker", "lorraine", "lorraine", UserRole.ADMIN);
		User u16 = new User("Charlie Baker", "charlie", "charlie", UserRole.MECHANIC);

		uservice.saveUser(u3);
		uservice.saveUser(u4);
		uservice.saveUser(u5);
		uservice.saveUser(u6);
		uservice.saveUser(u7);
		uservice.saveUser(u8);
		uservice.saveUser(u9);
		uservice.saveUser(u10);
		uservice.saveUser(u11);
		uservice.saveUser(u12);
		uservice.saveUser(u13);
		uservice.saveUser(u14);
		uservice.saveUser(u15);
		uservice.saveUser(u16);
	}

}