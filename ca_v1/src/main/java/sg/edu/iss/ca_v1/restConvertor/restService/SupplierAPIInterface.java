package sg.edu.iss.ca_v1.restConvertor.restService;

import java.util.ArrayList;
import java.util.List;

import sg.edu.iss.ca_v1.model.Supplier;

public interface SupplierAPIInterface {

	public List<Supplier> listAllSuppliers();
	
	 public void saveSupplier(Supplier supplier);
//	 public ArrayList<Supplier> findAllSupplier();
	 public Supplier findSupplierById(Integer id);
	 public void deleteSupplier(Supplier supplier);
//	 public ArrayList<String> findAllSupplierNames();
}
