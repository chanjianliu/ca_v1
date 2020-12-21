package sg.edu.iss.ca_v1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca_v1.model.User;
import sg.edu.iss.ca_v1.repo.UserRepository;

@Service
@Transactional
public class UserImplementation implements UserInterface {

	@Autowired
	UserRepository urepo;
	
	@Override
	public void saveUser(User user) {
		urepo.save(user);
	}

	@Override
	public List<User> listAllUser() {
		return urepo.findAll();
	}
	
	public Page<User> findAll(int pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber - 1, 10);
		return  urepo.findAll(pageable);
	}
	
	@Override
	public void deleteUser(User user) {
		urepo.delete(user);
	}

	@Override
	public boolean authenticate(User user) {
		User dbUser = urepo.findUserByUsername(user.getUsername());
		if (dbUser != null 
				&& dbUser.getUsername().equals(user.getUsername())
				&& dbUser.getPassword().equals(user.getPassword()))
			return true;
		else
			return false;
	}
	
	@Override
	public User findById(Integer id) {
		return urepo.findById(id).get();
	}
	
	@Override
	public boolean equals(User other) {
		return urepo.equals(other);
	}
	
	public User findByName(String name) {
		return urepo.findUserByUsername(name);
	}
}
