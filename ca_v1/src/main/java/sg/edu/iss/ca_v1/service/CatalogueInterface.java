package sg.edu.iss.ca_v1.service;

import java.util.List;


import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.StockUsage;
import sg.edu.iss.ca_v1.model.StockUsageInventory;

public interface CatalogueInterface {
	
	public List<Inventory> findPartByName(String name);
	public List<Inventory> findPartByColor(String color);
	public List<Inventory> findPartByBrand(String name);
	public List<Inventory> findPartBySupplier(String supplierName);
	public List<Inventory> FilteringPartByDescription(String keyword);
	public Inventory findPartById(int id);
	
//	public void saveInventory(StockUsageInventory usage);
//	public void saveStockUsage(StockUsageInventory usage);
//	public void saveStockUsageInventory(StockUsageInventory usage);
	
	public void saveInventory(Inventory inventory);
	public void saveStockUsage(StockUsage su);
	public void saveStockUsageInventory(StockUsageInventory si);
	
	public List<Inventory> listAllInventories();
	public List<StockUsageInventory> listAllUsages();
	public List<StockUsageInventory> listUsageByCustomer(String name);
	public List<StockUsageInventory> listUsageByCarId(int id);
	
	public StockUsage findStockUsageByCustomer(String name);
	public StockUsageInventory findStockUsageInventoryByPartId(int id);
	public StockUsage findStockUsageByCarId(int id);
	public void deleteStockUsageInventoryById(int id);
	public StockUsageInventory findStockUsageInventoryById(int id);
	
	
}
