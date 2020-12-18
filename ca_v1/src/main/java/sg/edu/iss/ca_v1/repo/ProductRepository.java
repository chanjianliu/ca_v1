package sg.edu.iss.ca.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.Product;



public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByName(String name);
	public List<Product> findByColour(String color);
	public List<Product> findByBrand(String brand);
	public List<Product> findByDescriptionContaining(String keyword);
	
	@Query("select p from Product p where p.supplier.name = :SupplierName")
	public List<Product> findBySupplier(String SupplierName);
	
	@Query("Select p from Product as p where p.name like CONCAT('%',:k,'%') ")
	public ArrayList<Product> SearchProductByName(@Param("k") String keyword);
	
	@Query("Select p from Product as p where p.brand like CONCAT('%',:k,'%') ")
	public ArrayList<Product> SearchProductByBrand(@Param("k") String keyword);

	@Query("Select p from Product as p where p.colour like CONCAT('%',:k,'%') ")
	public ArrayList<Product> SearchProductByColour(@Param("k") String keyword);
	
	@Query("Select p from Product as p where p.category like CONCAT('%',:k,'%') ")
	public ArrayList<Product> SearchProductByCategory(@Param("k") String keyword);
	
	@Query("Select p from Product as p where p.description like CONCAT('%',:k,'%') ")
	public ArrayList<Product> SearchProductByDescription(@Param("k") String keyword);
	/*
	@Query("Select p from Product as p where p.inventory_id like CONCAT('%',:k,'%') ")
	public ArrayList<Product> SearchProductByInventoryID(@Param("k") Integer keyword);
	*/
	
}
