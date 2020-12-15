package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;


import sg.edu.iss.ca_v1.model.User;



public interface AdminUserInterface {

	public void saveUser(User user);	
	public ArrayList<User> findAll();	
	public User findById(Integer id);	
	public void deleteUser(User user);	
	public boolean authenticate (User user); 
	
	
	

	

	
}
