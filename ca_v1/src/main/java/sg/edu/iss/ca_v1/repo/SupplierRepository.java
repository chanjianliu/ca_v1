package sg.edu.iss.ca_v1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca_v1.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
