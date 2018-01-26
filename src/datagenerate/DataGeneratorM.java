package datagenerate;

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
import model.Color;
import model.Costumer;
import model.Employee;
import model.Manufacturer;
import model.Model;
import model.Rental;
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
		
//		for(Vehicle cc : vehDAOI.getVehicleList())
		//	vehDAOM.add
	
		for(Rental r : rentDAOI.getRentalList())
			rentDAOM.addRental(r);
		
		for(Costumer r : coDAOI.getCostumerList())
			coDAOM.addCostumer(r);
	}
	
	public void MongoDBToSQL(){
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
		
//		for(Vehicle cc : vehDAOI.getVehicleList())
		//	vehDAOM.add

		for(Costumer r : coDAOM.getCostumerList())
			coDAOI.addCostumer(r);
	
		for(Rental r : rentDAOM.getRentalList())
			rentDAOI.addRental(r);
	}
	
	
	public static void main(String[] args) {
		
		// IMPLEMENT DROPPING ALL TABLES BEFORE ADDING ON BOTH SIDES
		DataGeneratorM dgen = new DataGeneratorM();
		
		dgen.SQLToMongoDB();
		
		dgen.MongoDBToSQL();
	}
}
