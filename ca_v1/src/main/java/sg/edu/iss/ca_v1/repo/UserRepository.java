package sg.edu.iss.ca_v1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca_v1.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUsername(String username);
}
