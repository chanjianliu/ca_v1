package sg.edu.iss.ca_v1.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.ca_v1.model.Product;
import sg.edu.iss.ca_v1.repo.ProductRepository;


@Service
public class ProductImplementation implements ProductInterface{

	@Autowired
	ProductRepository prepo;
	
	@Transactional
	public Page<Product> findAllProduts(int pageNumber,String sortField,String sortDir) {
		Sort sort=Sort.by(sortField);
		sort=sortDir.equals("asc")?sort.ascending():sort.descending();
		Pageable pageable=PageRequest.of(pageNumber - 1, 5,sort);
		return prepo.findAll(pageable);
	}
	@Transactional
	public void saveProduct(Product product) {
	prepo.save(product);
	}
	@Transactional
	public Product findProductById(Integer id) {
		return prepo.findById(id).get();
	}
	@Transactional
	public void deleteProduct(Product product) {
		prepo.delete(product);
		
	}
	
	
	@Transactional
	public void reorderReport(String Id) {
		BufferedWriter bw = null;
		try {
			List<Product> mycontent=prepo.reorderReport(Integer.parseInt(Id));
			if(mycontent.size()==0) {
				return;
			}
			File file = new File("C:\\Users\\eswarraj.rajendran\\myfile.dat");
			if (!file.exists()) {
			     file.createNewFile();
			  }

			  FileWriter fw = new FileWriter(file);
			  
			  bw = new BufferedWriter(fw);
			  bw.write("\t\t\t\tInventory Reorder Report for products from Supplier: " + Id);
			  bw.newLine();
			  bw.write("\t\t\t\t-------------------------------------------------------------");
			  bw.newLine();
			  bw.write("=========================================================================================");
			  
			  bw.newLine();
			  
			  double total = 0;
			  for(Product p:mycontent) {
				  if(mycontent.get(0) == p)
				  {
					  bw.newLine();
					  bw.write(p.toString1());
					  bw.newLine();
				  }		
				  bw.newLine();
				  bw.write(p.toString2());
				  bw.newLine();
				  total = total + p.getPrice();
				  bw.newLine();
				 
			  }			
			  bw.newLine();
			  bw.write("=========================================================================================");
			  bw.newLine();
			  bw.write("TOTAL: " +  Math.round(total * 100.0) / 100.0);
			  bw.newLine();
			  bw.write("=========================================================================================");
			  bw.newLine();
			  bw.write("End Of Report");   

		      } catch (IOException ioe) {
			   ioe.printStackTrace();
			}
			finally
			{ 
			   try{
			      if(bw!=null)
				 bw.close();
			   }catch(Exception ex){
			       System.out.println("Error in closing the BufferedWriter"+ex);
			    }
			}
		   }
	
		}