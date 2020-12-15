package sg.edu.iss.ca_v1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.iss.ca_v1.model.StockUsage;


@Repository
public interface StockUsageRepository extends JpaRepository<StockUsage, Integer> {
	
	public StockUsage findByCustomerName(String name);
	public StockUsage findByCarId(int CarId);
	
	

}
