package sg.edu.iss.ca_v1.service;

import java.util.List;

import sg.edu.iss.ca_v1.model.Product;

public interface ProductInterface {
	public boolean saveProduct(Product product);
	public List<Product> findAllProducts();
	public Product findProductById(Integer id);
	public void deleteProduct(Product product);
	public List<String> findAllProductNames();
	public List<Product> findBySupplierId(int id);
}
