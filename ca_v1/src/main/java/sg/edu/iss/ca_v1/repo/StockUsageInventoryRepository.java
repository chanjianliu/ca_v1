package sg.edu.iss.ca_v1.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca_v1.model.StockUsageInventory;

public interface StockUsageInventoryRepository extends JpaRepository<StockUsageInventory, Integer> {
	public List<StockUsageInventory> findByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);
}
