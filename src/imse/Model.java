package imse;

public class Model {
	int model_ID;
	Manufacturer manufacturer;
	double price;
	
	public Model(){
	}

	public int getModel_ID() {
		return model_ID;
	}

	public void setModel_ID(int model_ID) {
		this.model_ID = model_ID;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

}
