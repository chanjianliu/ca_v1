package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.repo.InventoryRepository;
import sg.edu.iss.ca_v1.repo.ProductRepository;




public class TestService {

	@Autowired
	InventoryRepository irepo;
	
	@Autowired
	ProductRepository prepo;
	
//	public Set<Object[]> reorderReport(int id) {
//			Set<Object[]> products = irepo.findById(id);
//			return products;
//			
//			ArrayList<Product> plist = new ArrayList<Product>();
//			plist = (ArrayList<Product>) irepo.findById(id);
//			for (Iterator<Product> iterator = plist.iterator(); iterator.hasNext();) {
//				Product product = iterator.next();
//				System.out.println(product);
//			}
		}
		
	

