package sg.edu.iss.ca_v1.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca_v1.model.Inventory;
import sg.edu.iss.ca_v1.repo.InventoryRepository;

@Service
@Transactional
public class InventoryImplementation implements InventoryInterface {
	@Autowired
	InventoryRepository irepo;

	@Override
	@Transactional
	public boolean saveInventory(Inventory inventory) {
		if (irepo.save(inventory) != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public List<Inventory> findAllInventorys() {
		return irepo.findAll();
	}

	@Override
	@Transactional
	public Inventory findInventoryById(Integer id) {
		return irepo.findById(id).get();
	}

	@Override
	@Transactional
	public void deleteInventory(Inventory inventory) {
		irepo.delete(inventory);
	}
//	@Transactional
//	public ArrayList<String> findAllInventoryIds()
//	{
//		List<Inventory> inventorys = irepo.findAll();
//		ArrayList<int> id = new ArrayList<int>();
//		for (Iterator<Inventory> iterator = inventorys.iterator();iterator.hasNext();)
//		{
//			Inventory inventory = (Inventory) iterator.next();
//			id.add(inventory.getId());
//		}
//		return id;
//		
//	}

}
