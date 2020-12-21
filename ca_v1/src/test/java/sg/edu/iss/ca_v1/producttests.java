package sg.edu.iss.ca_v1;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.repo.ProductRepository;

@SpringBootTest
public class producttests {

	@Autowired
	private ProductRepository prepo;
	
//	@Test
//	void productCreation() {
//		
//		prepo.save(entity)
//	}
}
