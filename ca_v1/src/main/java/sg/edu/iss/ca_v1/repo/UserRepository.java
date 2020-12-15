package sg.edu.iss.ca_v1.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.iss.ca_v1.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public ArrayList<User> findAllById(int id);
}
