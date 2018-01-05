package management;

//written by a01349198 - IB

import java.util.ArrayList;
import java.util.List;
import model.Accessory;

public class AccessoryManagement {
	private List<Accessory> accessory;
	private static AccessoryManagement accessoryInstance = null;
	
	private AccessoryManagement(){
		accessory = new ArrayList<Accessory>();
	}

	public static AccessoryManagement getInstanace(){
		if(accessoryInstance == null){
			accessoryInstance = new AccessoryManagement();
		}
		
		return accessoryInstance;
	}	
	
	public List<Accessory> getAccessory() {
		return accessory;
	}

	public void setAccessory(List<Accessory> accessory) {
		this.accessory = accessory;
	}
	
	public Accessory getAccessoryById(int accessory_id){
		for(Accessory acc : accessory){
			if(acc.getAccessory_ID() == accessory_id){
				return acc;
			}
		}
		return null;
	}
	
	public void addAccessory(String name, String description){
		//Accessory acc = new Accessory(name,description);
		//accessory.add(acc);
	}
	
	public void changeAccessory(String name, String description){
		
	}
	
	public void deleteAccessory(int accessory_id){
		for(Accessory acc : accessory){
			if(acc.getAccessory_ID() == accessory_id){
				accessory.remove(acc);
				break;
			}
		}
	}
	
	
	public int getAccessoryCount(){
		return accessory.size();
	}
	
	public Accessory getAccessoryByName(String name){
		for(Accessory acc : accessory){
			if(acc.getName() == name){
				return acc;
			}
		}
		return null;
	}
}
