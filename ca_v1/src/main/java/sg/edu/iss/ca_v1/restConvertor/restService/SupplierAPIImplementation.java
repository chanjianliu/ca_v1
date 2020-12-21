package sg.edu.iss.ca_v1.restConvertor.restService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca_v1.model.Supplier;
import sg.edu.iss.ca_v1.repo.SupplierRepository;

@Service
@Transactional
public class SupplierAPIImplementation implements SupplierAPIInterface{
	@Autowired
	SupplierRepository srepo;
		
	@Override
	public void saveSupplier(Supplier supplier) {
		srepo.save(supplier);
	}

	@Override
	public List<Supplier> listAllSuppliers() {
		return srepo.findAll();
	}
	
	@Override
	@Transactional
	public Supplier findSupplierById(Integer id) {
		return srepo.findById(id).get();
	}

	@Override
	@Transactional
	public void deleteSupplier(Supplier member) {
		srepo.delete(member);
	}
}
