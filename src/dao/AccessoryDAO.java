package dao;

//written by a01349198 - IB

import java.util.List;
import imse.Accessory;

public interface AccessoryDAO {
	public List<Accessory> getAccessoryList();
	public Accessory getAccessoryById(int accessory_id);
	public void addAccessory(String name, String description);
	public void changeAccessory(String name, String description);
	public void deleteAccessory(int accessory_id);
	public int getAccessoryCount();
	public Accessory getAccessoryByName(String name);
}
