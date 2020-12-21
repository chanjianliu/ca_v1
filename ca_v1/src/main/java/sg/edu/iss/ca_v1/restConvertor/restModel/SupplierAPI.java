package sg.edu.iss.ca_v1.restConvertor.restModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SupplierAPI {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String address;
	private String email;
	private String phoneNumber;
	@OneToMany (mappedBy = "supplierAPI")
	private List<ProductAPI> productList;

	public SupplierAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierAPI(String name, String address, String email, String phoneNumber, List<ProductAPI> productList) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.productList = productList;
	}
	public SupplierAPI(String name, String address, String email, String phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.productList = new ArrayList<ProductAPI>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public List<ProductAPI> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductAPI> productList) {
		this.productList = productList;
	}

}
