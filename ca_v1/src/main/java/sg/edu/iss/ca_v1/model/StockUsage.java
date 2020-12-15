package sg.edu.iss.ca_v1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class StockUsage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int carId;
	private String customerName;
	@OneToMany (mappedBy = "stockUsage")
	private List<StockUsageInventory> usageOfTheCustomer;
	
	public StockUsage(int carId, String customerName) {
		super();
		this.carId = carId;
		this.customerName = customerName;
		this.usageOfTheCustomer = new ArrayList<StockUsageInventory>();
	}
	
	public StockUsage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockUsage(String customerName) {
		super();
		this.customerName = customerName;
	}

	public StockUsage(int carId) {
		super();
		this.carId = carId;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getCarId() {
		return carId;
	}



	public void setCarId(int carId) {
		this.carId = carId;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public List<StockUsageInventory> getUsageOfTheCustomer() {
		return usageOfTheCustomer;
	}



	public void setUsageOfTheCustomer(List<StockUsageInventory> usageOfTheCustomer) {
		this.usageOfTheCustomer = usageOfTheCustomer;
	}

	
}
