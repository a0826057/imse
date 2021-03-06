package model;
import model.Vehicle;
import model.Accessory;

import java.util.List;

public class Car extends Vehicle {
	int doors;
	int passenger_limit;
	
	public Car(int id, String plate, Color color, Model model, Manufacturer manufacturer, List<Accessory> accessory, int mileage, int year, Boolean active, int doors, int pass_limit) {
		super(id, plate, color, model,manufacturer,accessory,mileage,year,active);
		this.doors=doors;
		this.passenger_limit=pass_limit;
	}
	public int getDoors() {
		return doors;
	}
	public void setDoors(int doors) {
		this.doors = doors;
	}
	public int getPassenger_limit() {
		return passenger_limit;
	}
	public void setPassenger_limit(int passenger_limit) {
		this.passenger_limit = passenger_limit;
	}
	
}
