package imse;

import java.util.List;

public class Truck extends Vehicle {
	int lenght;
	int height;
	int loading_limit;
	
	public Truck(String plate, Color color2, Model model2, List<Accessory> accessory2, int mileage2, int year,
			Boolean active2, int len, int height, int load_limit) {
		super();
		this.license_plate_number=plate;
		this.color=color2;
		this.model=model2;
		this.accessory=accessory2;
		this.mileage=mileage2;
		this.manufacture_year=year;
		this.active=active2;
		this.height=height;
		this.lenght=len;
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
