package imse;

//written by a01349198 - IB

public class Model {
	int model_ID;
	Manufacturer manufacturer;
	String description;
	double price;
	
	public Model(int id, Manufacturer man, String des, double pr){
		this.model_ID = id;
		this.manufacturer = man;
		this.description = des;
		this.price = pr;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
