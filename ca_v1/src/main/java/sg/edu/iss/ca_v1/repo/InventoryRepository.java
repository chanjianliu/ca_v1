package sg.edu.iss.ca_v1.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.Product;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

	
	@Query("Select p.id, p.minReorderQuantity,p.oriPrice,p.reorderLevel,inventory.quantity from Product p where p.id=:pid ")
	ArrayList<Product> findProductBySupplierId(@Param("pid") Integer id);


}
