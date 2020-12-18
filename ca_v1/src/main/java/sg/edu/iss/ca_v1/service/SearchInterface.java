package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Product;

public interface SearchInterface {
	
	 public List<Product> SearchProductByName(String name);
	 public List<Product> SearchProductByBrand(String brand);
	 public List<Product> SearchProductByColour(String colour);
	 public List<Product> SearchProductByCategory(String category);
	 public List<Product> SearchProductByDescription(String description);
	 //public List<Product> SearchProductByInventoryID(String inbentoryID);
	 //public List<Product> SearchProductBySupplierID(String supplierID);
}
