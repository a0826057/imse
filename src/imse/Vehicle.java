package imse;

import java.util.List;

import dao.AccessoryDAOI;
import dao.ColorDAOI;
import dao.ManufacturerDAOI;
import imse.Accessory;


public class Vehicle{
	int vehicle_ID;
	String license_plate_number;
	Color color;
	Model model;
	Manufacturer manufactur;
	List<Accessory> accessory;
	int mileage;
	int manufacture_year;
	Boolean active;
	

	public Vehicle(int vehicle_ID, String license_plate_number,	Color color,Model model,Manufacturer manufactur, List<Accessory> accessory, int mileage,int manufacture_year,Boolean active){
		this.vehicle_ID=vehicle_ID;
		this.license_plate_number=license_plate_number;
		this.manufactur= manufactur;
		this.color=color;
		this.model=model;
		this.accessory=accessory;
		this.mileage=mileage;
		this.manufacture_year=manufacture_year;
		this.active=active;
	}	
		
	public Vehicle(int int1, String string, ColorDAOI col, ManufacturerDAOI man, AccessoryDAOI acc, int int2, int int3,
			boolean boolean1) {
		// TODO Auto-generated constructor stub
	}

	public int getVehicle_ID() {
		return vehicle_ID;
	}
	public void setVehicle_ID(int vehicle_ID) {
		this.vehicle_ID = vehicle_ID;
	}
	public String getLicense_plate_number() {
		return license_plate_number;
	}
	public void setLicense_plate_number(String license_plate_number) {
		this.license_plate_number = license_plate_number;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public List<Accessory> getAccessory() {
		return accessory;
	}
	public void setAccessory(List<Accessory> accessory) {
		this.accessory = accessory;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getManufacture_year() {
		return manufacture_year;
	}
	public void setManufacture_year(int manufacture_year) {
		this.manufacture_year = manufacture_year;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	
	
	
}
