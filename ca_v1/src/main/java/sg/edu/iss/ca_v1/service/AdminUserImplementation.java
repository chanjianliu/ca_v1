package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.ca_v1.model.User;

import sg.edu.iss.ca_v1.repo.UserRepository;

@Service

public class AdminUserImplementation implements AdminUserInterface {

	@Autowired
	private UserRepository urepo;

	@Transactional
	public void saveUser(User user) {
//		if (urepo.save(user) != null)
//			return true;
//		else
//			return false;
		urepo.save(user);
	}


	public Page<User> findAll(int pageNumber,String sortField,String sortDir) {
		Sort sort=Sort.by(sortField,sortField,sortField,sortField);
		sort=sortDir.equals("asc")?sort.ascending():sort.descending();
		Pageable pageable=PageRequest.of(pageNumber - 1, 5,sort);
		return  urepo.findAll(pageable);
	}

	@Transactional
	public User findById(Integer id) {
		return urepo.findById(id).get();
	}

	@Transactional
	public void deleteUser(User user) {
		urepo.delete(user);

	}
	@Transactional
	
	public boolean authenticate(User user) {
		User dbUser = urepo.findUserByUsername(user.getUsername());
		if (dbUser != null 
				&& dbUser.getUsername().equals(user.getUsername())
				&& dbUser.getPassword().equals(user.getPassword()))
			return true;
		else
			return false;
	}

	
	
	

}
