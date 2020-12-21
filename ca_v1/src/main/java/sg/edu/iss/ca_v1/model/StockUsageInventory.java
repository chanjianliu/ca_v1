package sg.edu.iss.ca_v1.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class StockUsageInventory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @ManyToOne (cascade=CascadeType.MERGE)
    private StockUsage stockUsage; //connect back to which car
    @ManyToOne (cascade=CascadeType.MERGE)
    private Inventory inventory; //connect back to which product
    private int productId;
    private int customerId; //remember getter setter

    private int quantity; //quantity of the product used
    //	@FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate; //date the product was used on the car

    public StockUsageInventory() {
        super();
    }

    public StockUsageInventory(int quantity, LocalDate registrationDate) {
        super();
        this.quantity = quantity;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StockUsage getStockUsage() {
        return stockUsage;
    }

    public void setStockUsage(StockUsage stockUsage) {
        this.stockUsage = stockUsage;
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

}