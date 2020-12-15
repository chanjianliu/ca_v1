package sg.edu.iss.ca_v1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.ca_v1.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByName(String name);
	public List<Product> findByColor(String color);
	public List<Product> findByBrand(String brand);
	public List<Product> findByDescriptionContaining(String keyword);
	
	@Query("select p from Product p where p.supplier.name = :SupplierName")
	public List<Product> findBySupplier(String SupplierName);
	
}
