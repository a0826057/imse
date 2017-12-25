package imse;
import imse.Vehicle;

import java.util.List;

import imse.Accessory;

public class Car extends Vehicle {
	int doors;
	int passenger_limit;
	
	public Car(int doors, int pass_limit) {
		super();
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
