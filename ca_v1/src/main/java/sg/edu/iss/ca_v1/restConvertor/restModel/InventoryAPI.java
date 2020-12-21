package sg.edu.iss.ca_v1.restConvertor.restModel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class InventoryAPI{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int shelfLocation;
	private ProductState productState;
	@Column(nullable = false)
	@PositiveOrZero(message = "You cannot have negative numbers of products.")
	private int quantity;
	@OneToOne(mappedBy = "inventoryAPI")
	private ProductAPI productAPI;
	@OneToMany (mappedBy = "inventoryAPI")
	private List<StockUsageRecordAPI> stockUsageInventory;
	
	public InventoryAPI() {
		super();
	}

	public InventoryAPI(int shelfLocation, ProductState productState, int quantity, ProductAPI productAPI) {
		super();
		this.shelfLocation = shelfLocation;
		this.productState = productState;
		this.quantity = quantity;
		this.productAPI = productAPI;
	}

	public List<StockUsageRecordAPI> getStockUsageInventory() {
		return stockUsageInventory;
	}

	public void setStockUsageInventory(List<StockUsageRecordAPI> stockUsageInventory) {
		this.stockUsageInventory = stockUsageInventory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ProductAPI getProduct() {
		return productAPI;
	}

	public void setProduct(ProductAPI productAPI) {
		this.productAPI = productAPI;
	}
}
