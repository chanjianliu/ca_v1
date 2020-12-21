package sg.edu.iss.ca_v1.restConvertor.restModel;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ProductAPI {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String name;
	private String brand;
	private LocalDate dom;
	private String colour;
//	@Min(1)
	@Column(nullable = false)
	private double oriPrice; 
//	@Min(1)
	private double wholesalePrice; //lower price 
//	@Min(1)
	private double retailPrice; //highest price
//	@Min(1)
	private double partnerPrice; //2nd highest
	private String description;
	private String dimension;
	private String category;
//	@Min(1)
//	@Column(nullable = false)
	private int reorderLevel; // reorder level
//	@Min(1)
//	@Column(nullable = false)
	private int minReorderQuantity; // minimum reorder quantity
	@ManyToOne (cascade=CascadeType.PERSIST)
	private SupplierAPI supplierAPI;
	@OneToOne (cascade=CascadeType.PERSIST)
	private InventoryAPI inventoryAPI;
	
	public ProductAPI() {
		super();
	}

	public ProductAPI(String name, String brand, LocalDate dom, String colour, double oriPrice, double wholesalePrice,
			double retailPrice, double partnerPrice, String description, String dimension, String category,
			int reorderLevel, int minReorderQuantity, SupplierAPI supplierAPI) {
		super();
		this.name = name;
		this.brand = brand;
		this.dom = dom;
		this.colour = colour;
		this.oriPrice = oriPrice;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
		this.partnerPrice = partnerPrice;
		this.description = description;
		this.dimension = dimension;
		this.category = category;
		this.reorderLevel = reorderLevel;
		this.minReorderQuantity = minReorderQuantity;
		this.supplierAPI = supplierAPI;
		this.inventoryAPI = new InventoryAPI();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public LocalDate getDom() {
		return dom;
	}

	public void setDom(LocalDate dom) {
		this.dom = dom;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public double getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(double oriPrice) {
		this.oriPrice = oriPrice;
	}

	public double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getPartnerPrice() {
		return partnerPrice;
	}

	public void setPartnerPrice(double partnerPrice) {
		this.partnerPrice = partnerPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public int getMinReorderQuantity() {
		return minReorderQuantity;
	}

	public void setMinReorderQuantity(int minReorderQuantity) {
		this.minReorderQuantity = minReorderQuantity;
	}

	public SupplierAPI getSupplier() {
		return supplierAPI;
	}

	public void setSupplier(SupplierAPI supplierAPI) {
		this.supplierAPI = supplierAPI;
	}

	public InventoryAPI getInventory() {
		return inventoryAPI;
	}

	public void setInventory(InventoryAPI inventoryAPI) {
		this.inventoryAPI = inventoryAPI;
	}

}
