package dao;

//written by a01349198 - IB

import java.util.List;

import imse.Manufacturer;

public interface ManufacturerDAO {
	public List<Manufacturer> getManufacturerList();
	public Manufacturer getManufacturerById(int manufacturer_id);
	public void addManufacturer(String name,  String country);
	public void changeManufacturer(int manufacturer_ID, String name, String country);
	public void deleteManufacturer(int manufacturer_id);
	public int getManufacturerCount();
	public Manufacturer getManufacturerByName(String name);
	public List<Manufacturer> getManufacturersByCountry(String country);
}
