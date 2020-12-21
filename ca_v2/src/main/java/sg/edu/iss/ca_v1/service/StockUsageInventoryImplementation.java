package sg.edu.iss.ca_v1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca_v1.model.StockUsageInventory;
import sg.edu.iss.ca_v1.repo.StockUsageInventoryRepository;

@Service
@Transactional
public class StockUsageInventoryImplementation implements StockUsageInventoryInterface {

	@Autowired
	StockUsageInventoryRepository suirepo;
	
	@Override
	@Transactional
	public void saveStockUsageInventory(StockUsageInventory sui) {
		suirepo.save(sui);
	}

	@Override
	@Transactional
	public void deleteStockUsageInventory(StockUsageInventory sui) {
		suirepo.delete(sui);
	}

	@Override
	@Transactional
	public List<StockUsageInventory> listAllStockUsageInventories() {
		return suirepo.findAll();
	}

	@Override
	@Transactional
	public StockUsageInventory findStockUsageInventoryById(Integer id) {
		return suirepo.findById(id).get();
	}


}
