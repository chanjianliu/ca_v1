package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.model.StockUsage;
import sg.edu.iss.ca_v1.model.StockUsageInventory;
import sg.edu.iss.ca_v1.repo.InventoryRepository;
import sg.edu.iss.ca_v1.repo.ProductRepository;
import sg.edu.iss.ca_v1.repo.StockUsageInventoryRepository;
import sg.edu.iss.ca_v1.repo.StockUsageRepository;


@Service
@Transactional
public class CatalogueImplementation implements CatalogueInterface {

	@Autowired
	InventoryRepository irepo;
	@Autowired
	ProductRepository prepo;
	@Autowired
	StockUsageRepository surepo;
	@Autowired
	StockUsageInventoryRepository sirepo;
	
	@Override
	@Transactional
	public List<Inventory> findPartByName(String name) {
		List<Product> pros = prepo.findByName(name);
		List<Inventory> invList = new ArrayList<>();
		for(Product pro : pros) {
			Inventory inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
		
	}
	
	@Override
	@Transactional
	public List<Inventory> findPartByBrand(String name) {
		List<Product> pros = prepo.findByBrand(name);
		List<Inventory> invList = new ArrayList<>();
		for(Product pro : pros) {
			Inventory inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
	}
	
	@Override
	@Transactional
	public List<Inventory> findPartByColor(String color) {
		List<Product> pros = prepo.findByColour(color);
		List<Inventory> invList = new ArrayList<>();
		for(Product pro : pros) {
			Inventory inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
	}
	
	@Override
	@Transactional
	public List<Inventory> findPartBySupplier(String supplierName) {
		List<Product> pros = prepo.findBySupplier(supplierName);
		List<Inventory> invList = new ArrayList<>();
		for(Product pro : pros) {
			Inventory inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
	}
	
	@Override
	@Transactional
	public List<Inventory> FilteringPartByDescription(String keyword) {
		List<Product> pros = prepo.findByDescriptionContaining(keyword);
		List<Inventory> invList = new ArrayList<>();
		for(Product pro : pros) {
			Inventory inv = irepo.getOne(pro.getId());
			invList.add(inv);
		}
		return invList;
	}
	
//	@Override
//	@Transactional
//	public void saveInventory(StockUsageInventory usage) {
//		irepo.save(usage.getInventory());
//	}
//	
//	@Override
//	@Transactional
//	public void saveStockUsage(StockUsageInventory usage) {
//		surepo.save(usage.getStockUsage());
//		
//	}
	@Override
	@Transactional
	public void saveInventory(Inventory inventory) {
		irepo.save(inventory);
	}
	
	@Override
	@Transactional
	public void saveStockUsage(StockUsage su) {
		surepo.save(su);
		
	}
	
	
	@Override
	@Transactional
	public void saveStockUsageInventory(StockUsageInventory usage) {
		sirepo.save(usage);
		
	}
	
	
	
	@Override
	@Transactional
	public List<Inventory> listAllInventories() {
		return irepo.findAll();
	}
	

	@Override
	@Transactional
	public List<StockUsageInventory> listUsageByCarId(int id) {
		return surepo.findByCarId(id).getUsageOfTheCustomer();
	}

	@Override
	@Transactional
	public Inventory findPartById(int id) {
		return irepo.findInventoryById(id);
	}
	
	
	@Override
	@Transactional
	public List<StockUsageInventory> listAllUsages() {
		return sirepo.findAll();
	}
	
	@Override
	@Transactional
	public List<StockUsageInventory> listUsageByCustomer(String name) {
		return surepo.findByCustomerName(name).getUsageOfTheCustomer();
	}


	@Override
	@Transactional
	public StockUsage findStockUsageByCustomer(String name) {
		return surepo.findByCustomerName(name);
	}
	@Override
	@Transactional
	public StockUsage findStockUsageByCarId(int id) {
		return surepo.findByCarId(id);
	}
	
	
	@Override
	@Transactional
	public StockUsageInventory findStockUsageInventoryByPartId(int id) {
		return sirepo.findStockUsageInventoryByProductId(id);
	}
	
	@Override
	@Transactional
	public StockUsageInventory findStockUsageInventoryById(int id) {
		return sirepo.findStockUsageInventoryById(id);
	}
	
	@Override
	@Transactional
	public void deleteStockUsageInventoryById(int id) {
		sirepo.deleteById(id);
	}

}
