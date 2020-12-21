package sg.edu.iss.ca_v1.service;

import java.util.List;

import sg.edu.iss.ca_v1.model.Product;

public interface ProductInterface {
	public boolean saveProduct(Product product);
	public List<Product> findAllProducts();
	public Product findProductById(Integer id);
	public void deleteProduct(Product product);
	public List<String> findAllProductNames();
	
	
	//changwei
	 public List<Product> SearchProductByName(String name);
	 public List<Product> SearchProductByBrand(String brand);
	 public List<Product> SearchProductByColour(String colour);
	 public List<Product> SearchProductByCategory(String category);
	 public List<Product> SearchProductByDescription(String description);
}
