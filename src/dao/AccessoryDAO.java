package dao;

//written by a01349198 - IB

import java.util.List;
import imse.Accessory;

public interface AccessoryDAO {
	public List<Accessory> getAccessoryList();
	public Accessory getAccessoryById(int accessory_id);
	public void addAccessory(String name, String description);
	public void changeAccessory(int accessory_ID, String name, String description);
	public void deleteAccessory(int accessory_id);
	public int getAccessoryCount();
	public List<Accessory> getAccessoriesByName(String name);
}
