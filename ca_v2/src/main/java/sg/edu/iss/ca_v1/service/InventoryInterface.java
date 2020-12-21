package sg.edu.iss.ca_v1.service;

import java.util.List;

import sg.edu.iss.ca_v1.model.Inventory;

public interface InventoryInterface {
	public boolean saveInventory(Inventory inventory);
	public List<Inventory> findAllInventorys();
	public Inventory findInventoryById(Integer id);
	public void deleteInventory(Inventory inventory);
	//public ArrayList<String> findAllInventoryNames();
}
