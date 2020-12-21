package sg.edu.iss.ca_v1.restConvertor.restModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CustomerAPI {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private int carId;
	@Column(nullable = false)
	private String customerName;
	@OneToMany(mappedBy = "customerAPI")
	private List<StockUsageRecordAPI> usageOfTheCustomer = new ArrayList<StockUsageRecordAPI>();

	public CustomerAPI() {
		super();
	}
	public CustomerAPI(int carId,String customerName ) {
		super();
		this.customerName = customerName;
		this.carId = carId;
	}

	public CustomerAPI(String customerName) {
		super();
		this.customerName = customerName;
	}

	public CustomerAPI(int carId) {
		super();
		this.carId = carId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addStockUsageInventory(StockUsageRecordAPI item) {
		this.usageOfTheCustomer.add(item);
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

	public List<StockUsageRecordAPI> getUsageOfTheCustomer() {
		return usageOfTheCustomer;
	}

	public void setUsageOfTheCustomer(List<StockUsageRecordAPI> usageOfTheCustomer) {
		this.usageOfTheCustomer = usageOfTheCustomer;
	}
}
