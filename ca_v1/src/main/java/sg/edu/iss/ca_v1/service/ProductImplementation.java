package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.repo.ProductRepository;

@Service
@Transactional
public class ProductImplementation implements ProductInterface {
	@Autowired
	ProductRepository prepo;
	
	@Override
	@Transactional
	public boolean saveProduct(Product product) {
		if (prepo.save(product) != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public List<Product> findAllProducts() {
		return prepo.findAll();
	}

	@Override
	@Transactional
	public Product findProductById(Integer id) {
		return prepo.findById(id).get();
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		prepo.delete(product);
	}

	@Override
	@Transactional
	public List<String> findAllProductNames() {
		List<Product> products = prepo.findAll();
		List<String> names = new ArrayList<String>();
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			names.add(product.getName());
		}
		return names;

	}
}