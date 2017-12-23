package management;

import java.util.ArrayList;
import java.util.List;

import imse.Accessory;

import imse.Color;
import imse.Manufacturer;
import imse.Model;

import imse.Vehicle;

public class VehicleManagement {
	List<Vehicle> vehicleList;
	Vehicle vehicle;
	private static VehicleManagement vehicleInstance = null;
	
	private VehicleManagement(){
		vehicleList = new ArrayList<Vehicle>();
	}
	public VehicleManagement getInstance(){
		if(vehicleInstance == null){
			vehicleInstance = new VehicleManagement();
		}
		
		return vehicleInstance;
	}
	public List<Vehicle> getVehicle() {
		return vehicleList;
	}

	public void setVehicle(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
	
	public List<Vehicle> getVehicleList(){
		return vehicleList;
	}
	public Vehicle getVehicleListById(int vehicle_ID) {
		for(Vehicle veh : vehicleList){
			if(veh.getVehicle_ID() == vehicle_ID){
				vehicle=veh;
				return vehicle;
			}
		}
		return null;
	}
	public List<Vehicle> getVehicleListByType(String type) {
	/*	List<Vehicle> typeList= null;
		for(Vehicle veh : vehicleList){
			if(type== "Car") {
				if(veh instanceof Car){
				//needs to add here
				 typeList.add(veh);
			}
			}
			if(type == "Truck") {
				if(veh instanceof Truck){
				//needs to add here
				typeList.add(veh);
			}
			}
			
		}
		if(typeList!=null) {
			return typeList; 
		}*/		
		return null;
		
	}
	public void addCar(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit) {
		Vehicle veh= new Vehicle(plate,color,model,accessory,mileage,year,active,doors,pass_limit);
		vehicleList.add(veh);
	}
	public void addTruck(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit) {
		Vehicle veh= new Vehicle(plate,color,model,accessory,mileage,year,active,length,height,load_limit);
		vehicleList.add(veh);
	}
	public void changeCar(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit) {
		
	}
	public void changeTruck(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit) {
		
	}
	public void deleteVehicle(int vehicle_id) {
		
	}
	public int getVehicleCount() {
		int numberVehicle=0;
		return numberVehicle;
	}
	public int getVehicleCountByType(String type) {
		int numberTypeVehicle=0;
		return numberTypeVehicle;
	}
	public List<Vehicle> getVehicleListByAge(int age){
		List<Vehicle> ageList=null;
		return ageList;		
	}
	public List<Vehicle> getVehicleByColor(Color color){
		List<Vehicle> colorList=null;
		return colorList;
	}
	public List<Vehicle> getVehicleByAccessory(Accessory accessory){
		List<Vehicle> accessoryList=null;
		return accessoryList;
	}
	public List<Vehicle> getVehicleByModel(Model mode){
		List<Vehicle> modelList=null;
		return modelList;
	}
	public List<Vehicle> getTruckByLoadingLimit(int limit){
		List<Vehicle> loadingLimitList=null;
		return loadingLimitList;
	}
	public List<Vehicle> getCarByDoor(int doors){
		List<Vehicle> doorList=null;
		return doorList;
	}
	public List<Vehicle> getVehicleByManufacturer(Manufacturer manufacturer){
		List<Vehicle> manufacturerList=null;
		return manufacturerList;
	}
	
	
	
}
