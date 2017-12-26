package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import imse.Accessory;
import imse.Vehicle;
import imse.Car;
import imse.Color;
import imse.Manufacturer;
import imse.Model;
import imse.Truck;

public class VehicleDAOI implements VehicleDAO{
	private List<Vehicle> vehicles;
	ColorDAOI can= new ColorDAOI();
	AccessoryDAOI ac= new AccessoryDAOI();
	ModelDAOI mod = new ModelDAOI();
	
	@Override
	public List<Vehicle> getVehicleList(){
		vehicles = new ArrayList<Vehicle>();
		Connection veh = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			veh = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = veh.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle");
			
			while(result.next()){
				Color c = can.getColorById( result.getInt("color_ID"));
				List<Accessory> a= ac.getAccessoryList(); 
				Model m = mod.getModelById(result.getInt("model_id"));
				Vehicle vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
				vehicles.add(vehic);			
			}
		
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (veh != null)
					veh.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		
		return vehicles;
	}
	
	public Vehicle getVehicleListById(int vehicle_ID) {
		Connection con = null;
		Vehicle vehic = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle WHERE vehicle_ID = " + vehicle_ID + ";");
			
			while(result.next()){
				Color c = can.getColorById( result.getInt("color_ID"));
				List<Accessory> a= ac.getAccessoryList(); 
				Model m = mod.getModelById(result.getInt("model_id"));
				vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
			}
		
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		return vehic;
	}
	
	public List<Vehicle> getVehicleListByType(String type);
	
	public void addCar(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("INSERT INTO vehicle(lisence_plate_number,color_ID,model_ID,manufacturer_ID,accessory_ID,mileage,manufacturer_year,active) VALUES(" +
									plate + "," +
									color.getColor_ID() + "," +
									model.getModel_ID() + "," +
									manufacturer.getManufacturer_ID() + "," +
									accessory.getAccessory_ID() + "," +
									mileage + "," +
									year + "," +
									active +
									");"
									);
			
			VehicleDAOI veh = new VehicleDAOI();
			List<Vehicle> lsv = veh.getVehicleList();
			statement.executeUpdate("INSERT INTO car(car_ID,doors,passenger_limit) VALUES(" +
															lsv.get(lsv.size()-1).getVehicle_ID() + "," +
															doors + "," +
															pass_limit +
															");");
			
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	public void addTruck(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
		
	}
	
	public void changeCar(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
		
	}
	public void changeTruck(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
		
	}
	public void deleteVehicle(int vehicle_id){
		
	}
	public int getVehicleCount(){
	}
	public int getVehicleCountByType(String type);
	public List<Vehicle> getVehicleListByAge(int age);
	public List<Vehicle> getVehicleByColor(Color color);
	public List<Vehicle> getVehicleByAccessory(Accessory accessory);
	public List<Vehicle> getVehicleByModel(Model mode);
	public List<Vehicle> getTruckByLoadingLimit(int limit);
	public List<Vehicle> getCarByDoors(int doors);
	public List<Vehicle> getVehicleByManufacturer(Manufacturer manufacturer);
	
}
