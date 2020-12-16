package sg.edu.iss.ca_v1.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca_v1.model.Product;


public interface ProductRepository extends JpaRepository<Product,Integer>{
	
}
