package sg.edu.iss.ca_v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca_v1.repo.UserRepository;

@SpringBootTest
class CaV1ApplicationTests {

	@Autowired
	UserRepository urepo;
	
	@Test
	void contextLoads() {
	}

}
