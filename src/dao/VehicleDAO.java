package dao;
import java.util.List;

import imse.Vehicle;
import imse.Color;
import imse.Accessory;
import imse.Manufacturer;
import imse.Model;

public interface VehicleDAO {
		
		public List<Vehicle> getVehicleList();
		public Vehicle getVehicleListById(int vehicle_ID);
		public List<Vehicle> getVehicleListByType(String type);
		public void addCar(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit);
		public void addTruck(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit);
		public void changeCar(int vehicle_ID, String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit);
		public void changeTruck(int vehicle_ID, String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit);
		public void deleteVehicle(int vehicle_id);
		public int getVehicleCount();
		public int getVehicleCountByType(String type);
		public List<Vehicle> getVehicleListByAge(int age);
		public List<Vehicle> getVehicleByColor(Color color);
		public List<Vehicle> getVehicleByAccessory(Accessory accessory);
		public List<Vehicle> getVehicleByModel(Model mode);
		public List<Vehicle> getTruckByLoadingLimit(int limit);
		public List<Vehicle> getCarByDoors(int doors);
		public List<Vehicle> getVehicleByManufacturer(Manufacturer manufacturer);

}
