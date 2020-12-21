package sg.edu.iss.ca_v1.restConvertor.restRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca_v1.restConvertor.restModel.InventoryAPI;

public interface InventoryAPIRepository extends JpaRepository<InventoryAPI, Integer> {
	public InventoryAPI findInventoryById(int id);
}
