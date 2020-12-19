package sg.edu.iss.ca_v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.iss.ca_v1.model.StockUsageInventory;
import sg.edu.iss.ca_v1.repo.StockUsageInventoryRepository;

public class StockUsageInventoryImplementation implements StockUsageInventoryInterface {

	@Autowired
	StockUsageInventoryRepository suirepo;
	
	@Override
	public void saveStockUsageInventory(StockUsageInventory sui) {
		suirepo.save(sui);
	}

	@Override
	public void deleteStockUsageInventory(StockUsageInventory sui) {
		suirepo.delete(sui);
	}

	@Override
	public List<StockUsageInventory> listAllStockUsageInventories() {
		return suirepo.findAll();
	}

	@Override
	public StockUsageInventory findStockUsageInventoryById(Integer id) {
		return suirepo.findById(id).get();
	}


}
