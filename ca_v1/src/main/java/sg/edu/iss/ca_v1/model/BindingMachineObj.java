package sg.edu.iss.ca_v1.model;

import java.time.LocalDate;

public class BindingMachineObj {

	public int usageRecordId;
	public int partId;
	public String partName;
	public String brand;
	public Double price;
	public String description;
	public String colour;
	public LocalDate registrationDate;
	public int quantity;
	public Inventory invenory;
	public BindingMachineObj(int usageRecordId, int partId, String partName,String brand, Double price, String description, String colour, int quantity,LocalDate registrationDate, Inventory invenory) {
		this.usageRecordId = usageRecordId;
		this.partId = partId;
		this.partName = partName;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.colour = colour;
		this.quantity = quantity;
		this.registrationDate = registrationDate;
		this.invenory = invenory;
	}
}
