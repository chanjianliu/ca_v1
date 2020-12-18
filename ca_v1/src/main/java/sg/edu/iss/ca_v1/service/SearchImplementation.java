package sg.edu.iss.ca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.repo.ProductRepository;

@Service
@Transactional
public class SearchImplementation implements SearchInterface {
	
	@Autowired
	ProductRepository prepo;

	@Override
	@Transactional
	public List<Product> SearchProductByName(String name) {
		// TODO Auto-generated method stub
		return prepo.SearchProductByName(name);
	}

	@Override
	@Transactional
	public List<Product> SearchProductByBrand(String brand) {
		// TODO Auto-generated method stub
		return prepo.SearchProductByBrand(brand);
	}

	@Override
	@Transactional
	public List<Product> SearchProductByColour(String colour) {
		// TODO Auto-generated method stub
		return prepo.SearchProductByColour(colour);
	}

	@Override
	@Transactional
	public List<Product> SearchProductByCategory(String category) {
		// TODO Auto-generated method stub
		return prepo.SearchProductByCategory(category);
	}

	@Override
	@Transactional
	public List<Product> SearchProductByDescription(String description) {
		// TODO Auto-generated method stub
		return prepo.SearchProductByDescription(description);
	}
}
