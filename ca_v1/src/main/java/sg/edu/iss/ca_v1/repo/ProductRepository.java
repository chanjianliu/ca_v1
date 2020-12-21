package sg.edu.iss.ca_v1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.model.User;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findByName(String name);
    public List<Product> findByColour(String color);
    public List<Product> findByBrand(String brand);
    public List<Product> findByDescriptionContaining(String keyword);
    public User findProductById(Integer id);

//    @Query("select p from Product p where p.supplier.name = :SupplierName")
//    public List<Product> findBySupplier(String SupplierName);
    
  //  @Query("Select p from Product p where p.inventory.quantity < p.reorderLevel")
    @Query("Select p from Product p")
    public List<Product> reorderReport();
    
    @Query("Select p from Product p where p.supplier.id = :id")
    public List<Product> reorderReport(int id);
    
	public List<Product> findBySupplier(String supplierName);
}
