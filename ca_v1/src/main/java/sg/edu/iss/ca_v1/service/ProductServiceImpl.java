package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository prepo;
	
	@Transactional
	public ArrayList<Product> findAllProduts() {
		ArrayList<Product> products = (ArrayList<Product>)prepo.findAll();
		return products;
	}
	
}
