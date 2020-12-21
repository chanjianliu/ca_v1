package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.model.User;

public interface ProductInterface {

	public Page<Product> findAllProduts(int pageNumber,String sortField,String sortDir);
	public void saveProduct(Product product);
	
//	public void reorderReport();
	public void reorderReport(String Id);
	public Product findProductById(Integer id);
	public void deleteProduct(Product product);
}
