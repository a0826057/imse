package imse;

public class Truck extends Vehicle {
	int lenght;
	int height;
	int loading_limit;
	
	public Truck(int lenght, int heigtht, int loading_limit) {
		super();
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
