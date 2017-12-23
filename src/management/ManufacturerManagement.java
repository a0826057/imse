package management;

//written by a01349198 - IB

import java.util.ArrayList;
import java.util.List;
import imse.Manufacturer;

public class ManufacturerManagement {
	List<Manufacturer> manufacturers;

	public ManufacturerManagement(){
		this.manufacturers = new ArrayList<Manufacturer>();
	}
	
	public ManufacturerManagement(List<Manufacturer> man){
		this.manufacturers = man;
	}
	
	public List<Manufacturer> getManufacturerList() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}
	
	public Manufacturer getManufacturerById(int manufacturer_id){
		for(Manufacturer man : manufacturers){
			if(man.getManufacturer_ID() == manufacturer_id){
				return man;
			}
		}
		return null;
	}
	
	public void addManufacturer(String name,  String country){
		Manufacturer man = new Manufacturer(name, country);
		manufacturers.add(man);
	}
	
	public void changeManufacturer(String name, String country){
		
	}
	
	public void deleteManufacturer(int manufacturer_id){
		for(Manufacturer man : manufacturers){
			if(man.getManufacturer_ID() == manufacturer_id){
				manufacturers.remove(man);
				break;
			}
		}
	}
	
	public int getManufacturerCount(){
		return manufacturers.size();
	}
	
	public Manufacturer getManufacturerByName(String name){
		for(Manufacturer man : manufacturers){
			if(man.getName() == name){
				return man;
			}
		}
		return null;
	}
	
	public Manufacturer getManufacturerByCountry(String country){
		for(Manufacturer man : manufacturers){
			if(man.getCountry() == country){
				return man;
			}
		}
		return null;
	}
}
