package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;
import java.util.List;

import sg.edu.iss.ca_v1.model.User;



public interface AdminUserInterface {

	public boolean saveUser(User user);	
	public ArrayList<User> findAll();	
	public User findUserById(Integer id);	
	public void deleteUser(User user);	
	public void updateUser(User user);
	public ArrayList<String> findAllUsers();
	public User findUserByName(String username);

	

	
}
