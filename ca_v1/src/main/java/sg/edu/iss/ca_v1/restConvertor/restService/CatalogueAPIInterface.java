package sg.edu.iss.ca_v1.restConvertor.restService;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.ca_v1.restConvertor.restModel.CustomerAPI;
import sg.edu.iss.ca_v1.restConvertor.restModel.InventoryAPI;
import sg.edu.iss.ca_v1.restConvertor.restModel.StockUsageRecordAPI;

public interface CatalogueAPIInterface {
	
	public List<StockUsageRecordAPI> findStockUsageRecordByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);
	
	public List<InventoryAPI> findPartsByName(String name);
	public List<InventoryAPI> findPartsByColor(String color);
	public List<InventoryAPI> findPartsByBrand(String name);
	public List<InventoryAPI> findPartsBySupplier(String supplierName);
	public List<InventoryAPI> FilteringPartByDescription(String keyword);
	public InventoryAPI findPartById(int id);
	
	public void saveInventory(InventoryAPI inventoryAPI);
	public void saveCustomer(CustomerAPI su);
	public void saveStockUsageInventory(StockUsageRecordAPI si);

	public List<InventoryAPI> listAllInventories();
	
	public List<StockUsageRecordAPI> listAllStockUsageInventories();
	public List<StockUsageRecordAPI> listUsageByCustomer(String name);
	public List<StockUsageRecordAPI> listUsageByCarId(int id);
	
	public StockUsageRecordAPI findUsageById(int id);
	public List<CustomerAPI> listAllStockUsages();
	public CustomerAPI findCustomerById(int id);
}
