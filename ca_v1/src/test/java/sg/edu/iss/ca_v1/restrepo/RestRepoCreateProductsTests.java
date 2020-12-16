package sg.edu.iss.ca_v1.restrepo;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.model.ProductState;
import sg.edu.iss.ca_v1.model.Supplier;
import sg.edu.iss.ca_v1.repo.ProductRepository;

@SpringBootTest
public class RestRepoCreateProductsTests {
	@Autowired
	ProductRepository prepo;
	
	@Test
	void createProducts() {
		Supplier s1 = new Supplier("Jerry", "120708", "jerry@gmail.com", "88888888");
		Inventory i1 = new Inventory(1, ProductState.InStock, 100);
		LocalDate t1 = LocalDate.now();
		Product p1 = new Product("water", "Ui", t1, "red", 10.0, 9.0, 8.0, 7.0, "Holy Water", "dimension", "water", 1, 10, s1, i1);
		prepo.save(p1);
	}
}
