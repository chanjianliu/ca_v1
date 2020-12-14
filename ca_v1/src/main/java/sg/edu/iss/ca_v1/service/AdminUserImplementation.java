package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.ca_v1.model.User;

import sg.edu.iss.ca_v1.repo.UserRepository;

@Service

public class AdminUserImplementation implements AdminUserInterface {

	@Autowired
	private UserRepository urepo;

	@Transactional
	public boolean saveUser(User user) {
		if (urepo.save(user) != null)
			return true;
		else
			return false;
	}

	@Transactional
	public ArrayList<User> findAll() {
		return (ArrayList<User>) urepo.findAll();
	}

	@Transactional
	public User findUserById(Integer id) {
		return urepo.findById(id).get();
	}

	@Transactional
	public void deleteUser(User user) {
		urepo.delete(user);

	}

	@Transactional
	public void updateUser(User user) {
		urepo.save(user);

	}

	@Transactional

	public ArrayList<String> findAllUsers() {
		List<User> users = urepo.findAll();
		ArrayList<String> names = new ArrayList<String>();
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			names.add(user.getUsername());
		}
		return names;
	}

	@Transactional
	public User findUserByName(String username) {
		return urepo.findUserByName(username).get(0);
	}

}
