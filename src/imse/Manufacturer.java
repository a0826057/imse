package imse;

//written by a01349198 - IB

public class Manufacturer {
	int manufacturer_ID;
	String name;
	String country;
	
	public Manufacturer(String nam, String co){
		//ID MISSING!!
		this.name = nam;
		this.country = co;
	}
	
	public int getManufacturer_ID() {
		return manufacturer_ID;
	}
	
	public void setManufacturer_ID(int manufacturer_ID) {
		this.manufacturer_ID = manufacturer_ID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
