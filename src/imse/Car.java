package imse;
import imse.Vehicle;

import java.util.List;

import imse.Accessory;

public class Car extends Vehicle {
	int doors;
	int passenger_limit;
	
	public Car(String plate, Color color2, Model model2, List<Accessory> accessory2, int mileage2, int year,
			Boolean active2, int doors, int pass_limit) {
		super();
		this.license_plate_number=plate;
		this.color=color2;
		this.model=model2;
		this.accessory=accessory2;
		this.mileage=mileage2;
		this.manufacture_year=year;
		this.active=active2;
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
