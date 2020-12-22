package com.team9.motors.test;

import com.team9.motors.interfacemethods.CatalogueInterface;
import com.team9.motors.model.Inventory;
import com.team9.motors.model.Product;
import com.team9.motors.model.ProductState;
import com.team9.motors.model.Supplier;
import com.team9.motors.repository.ProductRepository;
import com.team9.motors.repository.SupplierRepository;
import com.team9.motors.service.CatalogueImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class CreateInventoryRecordTest {

    @Autowired
    private SupplierRepository srepo;

    @Autowired
    private ProductRepository prepo;

    @Autowired
    CatalogueInterface cservice;

    @Autowired
    public void setCatalogueService(CatalogueImplementation cimpl) {
        this.cservice = cimpl;
    }

    @Test
    void contextLoads() {
        LocalDate date = LocalDate.now();
        Supplier s = new Supplier();
        s.setAddress("1 Pasir Panjang");
        s.setEmail("123@gmail.com");
        s.setName("ABC");
        s.setPhoneNumber("123456");


        //String name, String brand, LocalDate dom, String colour, double oriPrice, double wholesalePrice,
        //double retailPrice, double partnerPrice, String description, String dimension, String category,
        //int reorderLevel, int minReorderQuantity, Supplier supplier
        Product pWheel = new Product("Steer wheel", "BMW",date,"red",1000,1000,1000,1000,"large","round","Wheel",10,5,s);


        Inventory iWheel = new Inventory();
        iWheel.setProductState(ProductState.InStock);
        iWheel.setQuantity(50);
        iWheel.setShelfLocation(3);
        pWheel.setInventory(iWheel);

        prepo.save(pWheel);

        //2nd set
        Supplier s2 = new Supplier();
        s2.setAddress("4 Bedok Street");
        s2.setEmail("123@gmail.com");
        s2.setName("Jim's Light");
        s2.setPhoneNumber("123456");


        //String name, String brand, LocalDate dom, String colour, double oriPrice, double wholesalePrice,
        //double retailPrice, double partnerPrice, String description, String dimension, String category,
        //int reorderLevel, int minReorderQuantity, Supplier supplier
        Product pWheel2 = new Product("Front Light", "Mercedes",date,"yellow",1000,1000,1000,1000,"small","round","light",10,5,s2);


        Inventory iWheel2 = new Inventory();
        iWheel2.setProductState(ProductState.InStock);
        iWheel2.setQuantity(100);
        iWheel2.setShelfLocation(5);
        pWheel2.setInventory(iWheel2);

        //iWheel2.setProduct(pWheel2);
        prepo.save(pWheel2);


        //3rd set
        Supplier s3 = new Supplier();
        s3.setAddress("Changi Airport");
        s3.setEmail("123@gmail.com");
        s3.setName("BMW Engines");
        s3.setPhoneNumber("123456");


        //String name, String brand, LocalDate dom, String colour, double oriPrice, double wholesalePrice,
        //double retailPrice, double partnerPrice, String description, String dimension, String category,
        //int reorderLevel, int minReorderQuantity, Supplier supplier
        Product pWheel3 = new Product("Engine", "Toyota",date,"black",1000,1000,1000,1000,"large","square","engine",10,5,s3);


        Inventory iWheel3 = new Inventory();
        iWheel3.setProductState(ProductState.InStock);
        iWheel3.setQuantity(100);
        iWheel3.setShelfLocation(7);
        pWheel3.setInventory(iWheel3);

        prepo.save(pWheel3);
    }
    
}
