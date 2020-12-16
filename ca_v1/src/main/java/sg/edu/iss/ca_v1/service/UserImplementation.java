package sg.edu.iss.ca_v1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca_v1.model.User;
import sg.edu.iss.ca_v1.repo.UserRepository;

@Service
@Transactional
public class UserImplementation implements UserInterface {

	@Autowired
	UserRepository urepo;
	
	@Override
	public void createUser(User user) {
		urepo.save(user);
	}

	@Override
	public void updateUser(User user) {
		urepo.save(user);
	}

	public List<User> listAllUser() {
		return urepo.findAll();
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
	
	public User findUserById(int id) {
		return urepo.findUserById(id);
	}
	
	public boolean equals(User other) {
		return urepo.equals(other);
	}
}