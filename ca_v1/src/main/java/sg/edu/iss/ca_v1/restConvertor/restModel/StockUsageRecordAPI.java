package sg.edu.iss.ca_v1.restConvertor.restModel;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class StockUsageRecordAPI {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne (cascade=CascadeType.MERGE)
	private CustomerAPI customerAPI; //connect back to which car
	@ManyToOne (cascade=CascadeType.MERGE)
	private InventoryAPI inventoryAPI; //connect back to which product
	private int productId;
	private int customerId; //remember getter setter
	
	@PositiveOrZero(message = "You cannot have negative numbers of car parts.")
	private int quantity; //quantity of the product used
//	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registrationDate; //date the product was used on the car
	
	public StockUsageRecordAPI() {
		super();
	}

	public StockUsageRecordAPI(int quantity, LocalDate registrationDate) {
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

	public CustomerAPI getStockUsage() {
		return customerAPI;
	}

	public void setStockUsage(CustomerAPI customerAPI) {
		this.customerAPI = customerAPI;
	}
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public InventoryAPI getInventory() {
		return inventoryAPI;
	}

	public void setInventory(InventoryAPI inventoryAPI) {
		this.inventoryAPI = inventoryAPI;
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
