package sg.edu.iss.ca_v1.model;

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
	@OneToMany (mappedBy = "stockUsage")
	private List<StockUsageInventory> stockUsageInventory;

	public StockUsage(int carId) {
		super();
		this.carId = carId;
	}

	public StockUsage() {
		super();
	}

	public int getId() {
		return id;
	}
	
	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	public List<StockUsageInventory> getStockUsageInventory() {
		return stockUsageInventory;
	}

	public void setStockUsageInventory(List<StockUsageInventory> stockUsageInventory) {
		this.stockUsageInventory = stockUsageInventory;
	}
}
