package sg.edu.iss.ca_v1.restConvertor.restService;

import java.util.List;

import sg.edu.iss.ca_v1.model.User;

public interface UserAPIInterface {
	public void saveUser(User user);
	public void deleteUser(User user);
	public List<User> listAllUser();
	public boolean authenticate (User user); 
	public User findById(Integer id);
	public boolean equals(User other);
	public User findByName(String name);
}
