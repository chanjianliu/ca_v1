package sg.edu.iss.ca_v1.restConvertor.restRepo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca_v1.restConvertor.restModel.StockUsageRecordAPI;

public interface StockUsageRecordAPIRepository extends JpaRepository<StockUsageRecordAPI, Integer> {
	public List<StockUsageRecordAPI> findByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);
	
//	@Query(value = "from StockUsageInventory t where registrationDate BETWEEN :startDate AND :endDate")
//	public List<StockUsageInventory> findByStockUsageInventoryBetween(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);
}
