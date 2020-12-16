package sg.edu.iss.ca_v1.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Inventory{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int shelfLocation;
	private ProductState productState;
	private int quantity;
	@OneToOne(mappedBy = "inventory")
	private Product product;
	@OneToMany (mappedBy = "inventory")
	private List<StockUsageInventory> stockUsageInventory;
	
	public Inventory() {
		super();
	}

	public Inventory(int shelfLocation, ProductState productState, int quantity) {
		super();
		this.shelfLocation = shelfLocation;
		this.productState = productState;
		this.quantity = quantity;
	}

	public List<StockUsageInventory> getStockUsageInventory() {
		return stockUsageInventory;
	}

	public void setStockUsageInventory(List<StockUsageInventory> stockUsageInventory) {
		this.stockUsageInventory = stockUsageInventory;
	}

	public int getId() {
		return id;
	}

	public int getShelfLocation() {
		return shelfLocation;
	}

	public void setShelfLocation(int shelfLocation) {
		this.shelfLocation = shelfLocation;
	}

	public ProductState getProductState() {
		return productState;
	}

	public void setProductState(ProductState productState) {
		this.productState = productState;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
