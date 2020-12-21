package sg.edu.iss.ca_v1.restConvertor.restRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca_v1.restConvertor.restModel.UserAPI;

public interface UserAPIRepository extends JpaRepository<UserAPI, Integer>{
	public UserAPI findUserById(int id);
	public UserAPI findUserByUsername(String username);
}
