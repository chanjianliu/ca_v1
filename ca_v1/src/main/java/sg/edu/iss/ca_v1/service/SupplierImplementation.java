package sg.edu.iss.ca_v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.ca_v1.model.Supplier;
import sg.edu.iss.ca_v1.repo.SupplierRepository;

@Service
@Transactional
public class SupplierImplementation implements SupplierInterface {
	@Autowired
	SupplierRepository srepo;
		
	@Override
	public void saveSupplier(Supplier supplier) {
		srepo.save(supplier);
	}

	
	public Page<Supplier> listAllSuppliers(int pageNumber,String sortField,String sortDir) {
		Sort sort=Sort.by(sortField);
		sort=sortDir.equals("asc")?sort.ascending():sort.descending();
		Pageable pageable=PageRequest.of(pageNumber - 1, 5,sort);
		return srepo.findAll(pageable);
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

