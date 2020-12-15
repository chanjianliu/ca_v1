package sg.edu.iss.ca_v1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.iss.ca_v1.model.StockUsageInventory;


@Repository
public interface StockUsageInventoryRepository extends JpaRepository<StockUsageInventory, Integer> {
	//stockUsageInventory.inventory.product.brand
	//input: brand
	//
}
