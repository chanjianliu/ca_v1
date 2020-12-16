package sg.edu.iss.ca_v1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.iss.ca_v1.model.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	public Inventory findInventoryById(int id);
}
