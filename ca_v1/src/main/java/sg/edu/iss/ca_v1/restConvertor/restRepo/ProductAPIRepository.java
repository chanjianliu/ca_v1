package sg.edu.iss.ca_v1.restConvertor.restRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.ca_v1.restConvertor.restModel.ProductAPI;

public interface ProductAPIRepository extends JpaRepository<ProductAPI, Integer> {
	public List<ProductAPI> findByName(String name);
	public List<ProductAPI> findByColour(String color);
	public List<ProductAPI> findByBrand(String brand);
	public List<ProductAPI> findByDescriptionContaining(String keyword);
	
	@Query("select p from Product p where p.supplier.name = :SupplierName")
	public List<ProductAPI> findBySupplier(String SupplierName);
}
