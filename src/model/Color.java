package model;

//written by a01349198 - IB

public class Color {
	int color_ID;
	String description;
	String manufacturer_color_code;
	
	public Color(int id, String des, String man){
		this.color_ID = id;
		this.description = des;
		this.manufacturer_color_code = man;
	}
	
	public int getColor_ID() {
		return color_ID;
	}
	
	public void setColor_ID(int color_ID) {
		this.color_ID = color_ID;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getManufacturer_color_code() {
		return manufacturer_color_code;
	}
	
	public void setManufacturer_color_code(String manufacturer_color_code) {
		this.manufacturer_color_code = manufacturer_color_code;
	}
	
	
}
