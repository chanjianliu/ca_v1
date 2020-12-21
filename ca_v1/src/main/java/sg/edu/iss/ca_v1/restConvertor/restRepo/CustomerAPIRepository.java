package sg.edu.iss.ca_v1.restConvertor.restRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca_v1.restConvertor.restModel.CustomerAPI;

public interface CustomerAPIRepository extends JpaRepository<CustomerAPI, Integer> {
	public CustomerAPI findByCustomerName(String name);
	public CustomerAPI findByCarId(int CarId);
}
