package imse;

// written by a01349198 - IB

public class Accessory {
	int accessory_ID;
	String name;
	String description;
	
	public Accessory(String na, String des){
		//ID MISSING!!
		this.name = na;
		this.description = des;
	}
	
	public int getAccessory_ID() {
		return accessory_ID;
	}
	
	public void setAccessory_ID(int accessory_ID) {
		this.accessory_ID = accessory_ID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
