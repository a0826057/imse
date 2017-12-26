package imse;

import java.util.List;

public class Truck extends Vehicle {
	int lenght;
	int height;
	int loading_limit;
	
	public Truck(int id, String plate, Color color, Model model, Manufacturer manufacturer, List<Accessory> accessory, int mileage, int year, Boolean active, int length, int height, int load_limit) {
		super(id, plate, color, model, manufacturer, accessory, mileage, year, active);
		this.height=height;
		this.lenght=length;
		this.loading_limit=load_limit;
	}
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getLoading_limit() {
		return loading_limit;
	}
	public void setLoading_limit(int loading_limit) {
		this.loading_limit = loading_limit;
	}
	
}