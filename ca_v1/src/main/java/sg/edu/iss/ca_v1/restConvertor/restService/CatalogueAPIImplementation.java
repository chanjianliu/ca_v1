package sg.edu.iss.ca_v1.restConvertor.restService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca_v1.restConvertor.restModel.InventoryAPI;
import sg.edu.iss.ca_v1.restConvertor.restModel.ProductAPI;
import sg.edu.iss.ca_v1.restConvertor.restModel.CustomerAPI;
import sg.edu.iss.ca_v1.restConvertor.restModel.StockUsageRecordAPI;

import sg.edu.iss.ca_v1.restConvertor.restRepo.InventoryAPIRepository;
import sg.edu.iss.ca_v1.restConvertor.restRepo.ProductAPIRepository;
import sg.edu.iss.ca_v1.restConvertor.restRepo.StockUsageRecordAPIRepository;
import sg.edu.iss.ca_v1.restConvertor.restRepo.CustomerAPIRepository;


@Service
@Transactional
public class CatalogueAPIImplementation implements CatalogueAPIInterface {

	@Autowired
	InventoryAPIRepository irepo;
	@Autowired
	ProductAPIRepository prepo;
	@Autowired
	CustomerAPIRepository surepo;
	@Autowired
	StockUsageRecordAPIRepository sirepo;
	
	@Override
	public List<StockUsageRecordAPI> findStockUsageRecordByRegistrationDateBetween(LocalDate startDate, LocalDate endDate){
		return sirepo.findByRegistrationDateBetween(startDate, endDate);
	}
	
	@Override
	public List<InventoryAPI> findPartsByName(String name) {
		List<ProductAPI> pros = prepo.findByName(name);
		List<InventoryAPI> invList = new ArrayList<>();
		for(ProductAPI pro : pros) {
			InventoryAPI inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
		
	}
	
	@Override
	public List<InventoryAPI> findPartsByBrand(String name) {
		List<ProductAPI> pros = prepo.findByBrand(name);
		List<InventoryAPI> invList = new ArrayList<>();
		for(ProductAPI pro : pros) {
			InventoryAPI inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
	}
	
	@Override
	public List<InventoryAPI> findPartsByColor(String color) {
		List<ProductAPI> pros = prepo.findByColour(color);
		List<InventoryAPI> invList = new ArrayList<>();
		for(ProductAPI pro : pros) {
			InventoryAPI inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
	}
	
	@Override
	public List<InventoryAPI> findPartsBySupplier(String supplierName) {
		List<ProductAPI> pros = prepo.findBySupplier(supplierName);
		List<InventoryAPI> invList = new ArrayList<>();
		for(ProductAPI pro : pros) {
			InventoryAPI inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
	}
	
	@Override
	public List<InventoryAPI> FilteringPartByDescription(String keyword) {
		List<ProductAPI> pros = prepo.findByDescriptionContaining(keyword);
		List<InventoryAPI> invList = new ArrayList<>();
		for(ProductAPI pro : pros) {
			InventoryAPI inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
	}
	
	@Override
	public InventoryAPI findPartById(int id) {
		return irepo.findInventoryById(id);
	}
	
	@Override
	@Transactional
	public void saveInventory(InventoryAPI inventoryAPI) {
		irepo.save(inventoryAPI);
	}
	
	@Override
	@Transactional
	public void saveCustomer(CustomerAPI su) {
		surepo.save(su);
		
	}
	
	@Override
	@Transactional
	public void saveStockUsageInventory(StockUsageRecordAPI si) {
		sirepo.save(si);
		
	}
	
	@Override
	@Transactional
	public List<InventoryAPI> listAllInventories() {
		return irepo.findAll();
	}
	
	@Override
	@Transactional
	public List<StockUsageRecordAPI> listAllStockUsageInventories() {
		return sirepo.findAll();
	}
	
	@Override
	@Transactional
	public List<CustomerAPI> listAllStockUsages() {
		return surepo.findAll();
	}
	
	@Override
	@Transactional
	public List<StockUsageRecordAPI> listUsageByCustomer(String name) {
		return surepo.findByCustomerName(name).getUsageOfTheCustomer();
	}
	
	@Override
	@Transactional
	public List<StockUsageRecordAPI> listUsageByCarId(int id) {
		return surepo.findByCarId(id).getUsageOfTheCustomer();
	}

	@Override
	public CustomerAPI findCustomerById(int id) {
		return surepo.findById(id).get();
	}

	@Override
	public StockUsageRecordAPI findUsageById(int id) {
		return sirepo.findById(id).get();
	}
}
