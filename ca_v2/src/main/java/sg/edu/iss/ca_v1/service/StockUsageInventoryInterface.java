package sg.edu.iss.ca_v1.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.ca_v1.model.StockUsageInventory;

public interface StockUsageInventoryInterface {
	public void saveStockUsageInventory(StockUsageInventory sui);
	public void deleteStockUsageInventory(StockUsageInventory sui);
	public List<StockUsageInventory> listAllStockUsageInventories();
	public StockUsageInventory findStockUsageInventoryById(Integer id);

}
