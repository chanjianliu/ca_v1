package sg.edu.iss.ca_v1.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.StockUsage;
import sg.edu.iss.ca_v1.model.StockUsageInventory;

public interface CatalogueInterface {
	
	public List<StockUsageInventory> findStockUsageInventoryByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);
	
	public List<Inventory> findPartsByName(String name);
	public List<Inventory> findPartsByColor(String color);
	public List<Inventory> findPartsByBrand(String name);
	public List<Inventory> findPartsBySupplier(String supplierName);
	public List<Inventory> FilteringPartByDescription(String keyword);
	public Inventory findPartById(int id);
	
	public void saveInventory(Inventory inventory);
	public void saveStockUsage(StockUsage su);
	public void saveStockUsageInventory(StockUsageInventory si);

	public List<Inventory> listAllInventories();
	
	public List<StockUsageInventory> listAllStockUsageInventories();
	public List<StockUsageInventory> listUsageByCustomer(String name);
	public List<StockUsageInventory> listUsageByCarId(int id);
	
	public StockUsageInventory findUsageById(int id);
	public List<StockUsage> listAllStockUsages();
	public StockUsage findCustomerById(int id);
}
