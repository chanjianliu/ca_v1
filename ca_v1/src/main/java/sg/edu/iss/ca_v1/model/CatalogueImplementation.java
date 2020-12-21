package sg.edu.iss.ca_v1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<StockUsageInventory> findStockUsageInventoryByRegistrationDateBetween(LocalDate startDate, LocalDate endDate){
        return sirepo.findByRegistrationDateBetween(startDate, endDate);
    }

    @Override
    public List<Inventory> findPartsByName(String name) {
        List<Product> pros = prepo.findByName(name);
        List<Inventory> invList = new ArrayList<>();
        for(Product pro : pros) {
            Inventory inv = irepo.getOne(pro.getId());
            invList.add(inv);
        }
        return invList;

    }

    @Override
    public List<Inventory> findPartsByBrand(String name) {
        List<Product> pros = prepo.findByBrand(name);
        List<Inventory> invList = new ArrayList<>();
        for(Product pro : pros) {
            Inventory inv = irepo.getOne(pro.getId());
            invList.add(inv);
        }
        return invList;
    }

    @Override
    public List<Inventory> findPartsByColor(String color) {
        List<Product> pros = prepo.findByColour(color);
        List<Inventory> invList = new ArrayList<>();
        for(Product pro : pros) {
            Inventory inv = irepo.getOne(pro.getId());
            invList.add(inv);
        }
        return invList;
    }

    @Override
    public List<Inventory> findPartsBySupplier(String supplierName) {
        List<Product> pros = prepo.findBySupplier(supplierName);
        List<Inventory> invList = new ArrayList<>();
        for(Product pro : pros) {
            Inventory inv = irepo.getOne(pro.getId());
            invList.add(inv);
        }
        return invList;
    }

    @Override
    public List<Inventory> FilteringPartByDescription(String keyword) {
        List<Product> pros = prepo.findByDescriptionContaining(keyword);
        List<Inventory> invList = new ArrayList<>();
        for(Product pro : pros) {
            Inventory inv = irepo.getOne(pro.getId());
            invList.add(inv);
        }
        return invList;
    }

    @Override
    public Inventory findPartById(int id) {
        return irepo.findInventoryById(id);
    }

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
    public void saveStockUsageInventory(StockUsageInventory si) {
        sirepo.save(si);

    }

    @Override
    @Transactional
    public List<Inventory> listAllInventories() {
        return irepo.findAll();
    }

    @Override
    @Transactional
    public List<StockUsageInventory> listAllStockUsageInventories() {
        return sirepo.findAll();
    }

    @Override
    @Transactional
    public List<StockUsage> listAllStockUsages() {
        return surepo.findAll();
    }

    @Override
    @Transactional
    public List<StockUsageInventory> listUsageByCustomer(String name) {
        return surepo.findByCustomerName(name).getUsageOfTheCustomer();
    }

    @Override
    @Transactional
    public List<StockUsageInventory> listUsageByCarId(int id) {
        return surepo.findByCarId(id).getUsageOfTheCustomer();
    }

    @Override
    public StockUsage findCustomerById(int id) {
        return surepo.findById(id).get();
    }

    @Override
    public StockUsageInventory findUsageById(int id) {
        return sirepo.findById(id).get();
    }
}
