package sg.edu.iss.ca_v1.service;

import java.util.List;

import sg.edu.iss.ca_v1.model.User;

public interface UserInterface {
	public void createUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	public List<User> listAllUser();
	public boolean authenticate (User user); 
	public User findUserById(int id);
	public boolean equals(User other);
}
