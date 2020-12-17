package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;
import java.util.List;

import sg.edu.iss.ca_v1.model.Supplier;

public interface SupplierInterface {

	public List<Supplier> listAllSuppliers();
	
	 public void saveSupplier(Supplier supplier);
//	 public ArrayList<Supplier> findAllSupplier();
	 public Supplier findSupplierById(Integer id);
	 public void deleteSupplier(Supplier supplier);
//	 public ArrayList<String> findAllSupplierNames();
}
