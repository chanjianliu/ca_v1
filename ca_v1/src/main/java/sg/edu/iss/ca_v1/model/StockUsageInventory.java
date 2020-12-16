package sg.edu.iss.ca_v1.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class StockUsageInventory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne (cascade = CascadeType.PERSIST)
	private StockUsage stockUsage; //connect back to which car
	@ManyToOne (cascade = CascadeType.PERSIST)
	private Inventory inventory; //connect back to which product
	private int productId;

	private int quantity; //quantity of the product used
	private LocalDate registrationDate; //date the product was used on the car
	
	public StockUsageInventory() {
		super();
	}
	
	public StockUsageInventory(Inventory inventory, int quantity, LocalDate registrationDate) {
		
		this.stockUsage = new StockUsage();
		this.inventory = inventory;
		this.quantity = quantity;
		this.registrationDate = registrationDate;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getId() {
		return id;
	}
	
	public StockUsage getStockUsage() {
		return stockUsage;
	}

	public void setStockUsage(StockUsage stockUsage) {
		this.stockUsage = stockUsage;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
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
