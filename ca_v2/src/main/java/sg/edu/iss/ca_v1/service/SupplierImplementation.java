package sg.edu.iss.ca_v1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca_v1.model.Supplier;
import sg.edu.iss.ca_v1.repo.SupplierRepository;

@Service
@Transactional
public class SupplierImplementation implements SupplierInterface{
	@Autowired
	SupplierRepository srepo;
	
    public Page<Supplier> listAllSuppliers(int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber - 1, 10);
        return srepo.findAll(pageable);
    }
	
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
