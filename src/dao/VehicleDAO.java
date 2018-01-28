package dao;
import java.util.List;

import model.Vehicle;
import model.Color;
import model.Accessory;
import model.Manufacturer;
import model.Model;

public interface VehicleDAO {
		
		public List<Vehicle> getVehicleList();
		public Vehicle getVehicleById(int vehicle_ID);
		public List<Vehicle> getVehicleListByType(String type);
		public void addCar(String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int doors, int pass_limit);
		public void addTruck(String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int length, int height, int load_limit);
		public void changeCar(int vehicle_ID, String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int doors, int pass_limit);
		public void changeTruck(int vehicle_ID, String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int length, int height, int load_limit);
		public void deleteVehicle(int vehicle_id);
		public int getVehicleCount();
		public List<Vehicle> getVehicleListByAge(int age);
		public List<Vehicle> getVehicleByModel(Model mode);
		public List<Vehicle> getTruckByLoadingLimit(int limit);
		public List<Vehicle> getVehicleByManufacturer(Manufacturer manufacturer);
		public void addCar(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, boolean active, int doors, int pass_limit);
		public void addTruck(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, boolean active, int length, int height, int load_limit);
		public List<Vehicle> searchVehicle(int color, int model, int manufacturer, int accessory);
}
