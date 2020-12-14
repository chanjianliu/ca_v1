package sg.edu.iss.ca_v1.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca_v1.model.User;
import sg.edu.iss.ca_v1.model.UserRole;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	 @Query("Select u from User u where u.username LIKE :name")
	 List<User> findUserByName(@Param("name") String name);
}
