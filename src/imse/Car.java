package imse;

public class Car extends Vehicle {
	int doors;
	int passenger_limit;
	
	public Car(int doors, int passenger_limit) {
		super();
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