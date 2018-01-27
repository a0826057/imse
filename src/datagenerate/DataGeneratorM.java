package datagenerate;

import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import dao.AccessoryDAOI;
import dao.AccessoryDAOM;
import dao.ColorDAOI;
import dao.ColorDAOM;
import dao.CostumerDAOI;
import dao.CostumerDAOM;
import dao.EmployeeDAOI;
import dao.EmployeeDAOM;
import dao.ManufacturerDAOI;
import dao.ManufacturerDAOM;
import dao.ModelDAOI;
import dao.ModelDAOM;
import dao.RentalDAOI;
import dao.RentalDAOM;
import dao.VehicleDAOI;
import dao.VehicleDAOM;
import model.Accessory;
import model.Car;
import model.Color;
import model.Costumer;
import model.Employee;
import model.Manufacturer;
import model.Model;
import model.Rental;
import model.Truck;
import model.Vehicle;

public class DataGeneratorM {
	private AccessoryDAOI acDAOI = new AccessoryDAOI();
	private AccessoryDAOM acDAOM = new AccessoryDAOM();
	private ColorDAOI colDAOI = new ColorDAOI();
	private ColorDAOM colDAOM = new ColorDAOM();
	private ManufacturerDAOI manDAOI = new ManufacturerDAOI();
	private ManufacturerDAOM manDAOM = new ManufacturerDAOM();
	private ModelDAOI modDAOI = new ModelDAOI();
	private ModelDAOM modDAOM = new ModelDAOM();
	private EmployeeDAOI empDAOI = new EmployeeDAOI();
	private EmployeeDAOM empDAOM = new EmployeeDAOM();
	private VehicleDAOI vehDAOI = new VehicleDAOI();
	private VehicleDAOM vehDAOM = new VehicleDAOM();
	private RentalDAOI rentDAOI = new RentalDAOI();
	private RentalDAOM rentDAOM = new RentalDAOM();
	private CostumerDAOI coDAOI = new CostumerDAOI();
	private CostumerDAOM coDAOM = new CostumerDAOM();
	
	public void SQLToMongoDB(){
		for(Accessory acc : acDAOI.getAccessoryList())
			acDAOM.addAccessory(acc.getName(), acc.getDescription());
		
		for(Color cc : colDAOI.getColorList())
			colDAOM.addColor(cc.getDescription(), cc.getManufacturer_color_code());
		
		for(Manufacturer cc : manDAOI.getManufacturerList())
			manDAOM.addManufacturer(cc.getName(), cc.getCountry());
		
		for(Model cc : modDAOI.getModelList())
			modDAOM.addModel(cc.getManufacturer(), cc.getDescription(), cc.getPrice());
		
		for(Employee cc : empDAOI.getEmployeeList())
			empDAOM.addEmployee(cc);
		
		for(Vehicle cc : vehDAOI.getVehicleListByType("car")){
			if(cc instanceof Car){
				Car car = (Car) cc;
				vehDAOM.addCar(cc.getLicense_plate_number(), cc.getColor(), cc.getModel(), cc.getManufactur(), cc.getAccessory().get(0), cc.getMileage(), cc.getManufacture_year(), cc.getActive(), car.getDoors()	, car.getPassenger_limit());
				
				List<Accessory> accs = acDAOI.getHasAccessory(car.getVehicle_ID());
				if(accs != null){
					for(Accessory ac : accs)
						acDAOM.addHasAccessory(ac.getAccessory_ID(), car.getVehicle_ID());
				}
			
			}
		}
		
		for(Vehicle cc : vehDAOI.getVehicleListByType("truck")){
			if(cc instanceof Truck){
				Truck truck = (Truck) cc;
				vehDAOM.addTruck(cc.getLicense_plate_number(), cc.getColor(), cc.getModel(), cc.getManufactur(), cc.getAccessory().get(0), cc.getMileage(), cc.getManufacture_year(), cc.getActive(), truck.getLenght(), truck.getHeight(), truck.getLoading_limit());
				
				List<Accessory> accs = acDAOI.getHasAccessory(truck.getVehicle_ID());
				if(accs != null){
					for(Accessory ac : accs)
						acDAOM.addHasAccessory(ac.getAccessory_ID(), truck.getVehicle_ID());
				}
				
			}
		}
	
		for(Rental r : rentDAOI.getRentalList())
			rentDAOM.addRental(r);
		
		for(Costumer r : coDAOI.getCostumerList())
			coDAOM.addCostumer(r);
		
		System.out.println("Finished Migration");
		
	//	DataGenerator.dropDB();
		
	}
	
	public void MongoDBToSQL(){
		DataGenerator.createDB();
		
		for(Accessory acc : acDAOM.getAccessoryList())
			acDAOI.addAccessory(acc.getName(), acc.getDescription());
		
		for(Color cc : colDAOM.getColorList())
			colDAOI.addColor(cc.getDescription(), cc.getManufacturer_color_code());
		
		for(Manufacturer cc : manDAOM.getManufacturerList())
			manDAOI.addManufacturer(cc.getName(), cc.getCountry());
		
		for(Model cc : modDAOM.getModelList())
			modDAOI.addModel(cc.getManufacturer(), cc.getDescription(), cc.getPrice());

		for(Employee cc : empDAOM.getEmployeeList())
			empDAOI.addEmployee(cc);
		
		for(Vehicle cc : vehDAOM.getVehicleListByType("car")){
			if(cc instanceof Car){
				Car car = (Car) cc;
				List<Accessory> accs = acDAOM.getHasAccessory(car.getVehicle_ID());
				vehDAOI.addCar(cc.getLicense_plate_number(), cc.getColor(), cc.getModel(), cc.getManufactur(), cc.getAccessory().get(0), cc.getMileage(), cc.getManufacture_year(), cc.getActive(), car.getDoors()	, car.getPassenger_limit());
			
				for(Accessory ac : accs)
					acDAOI.addHasAccessory(ac.getAccessory_ID(), car.getVehicle_ID());
			}
		}
		
		for(Vehicle cc : vehDAOM.getVehicleListByType("truck")){
			if(cc instanceof Truck){
				Truck truck = (Truck) cc;
				List<Accessory> accs = acDAOM.getHasAccessory(truck.getVehicle_ID());
				vehDAOI.addTruck(cc.getLicense_plate_number(), cc.getColor(), cc.getModel(), cc.getManufactur(), cc.getAccessory().get(0), cc.getMileage(), cc.getManufacture_year(), cc.getActive(), truck.getLenght(), truck.getHeight(), truck.getLoading_limit());
			
				for(Accessory ac : accs)
					acDAOI.addHasAccessory(ac.getAccessory_ID(), truck.getVehicle_ID());
			}
		}

		for(Costumer r : coDAOM.getCostumerList())
			coDAOI.addCostumer(r);
	
		for(Rental r : rentDAOM.getRentalList())
			rentDAOI.addRental(r);
		
		dropMongoDB();
	}
	
	public static void dropMongoDB(){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		database.drop();
		
		if(mongoClient != null)
			mongoClient.close();
	}
	
	public static void main(String[] args) {
		DataGeneratorM dgenMongo = new DataGeneratorM();
		
		// ADD PROXY TO KNOW FROM WHICH TO WHICH 
		dropMongoDB();
		dgenMongo.SQLToMongoDB();
		
		//dgenMongo.MongoDBToSQL();
	}
}
