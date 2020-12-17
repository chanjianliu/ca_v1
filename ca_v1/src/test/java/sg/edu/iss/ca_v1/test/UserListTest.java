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
	}

}