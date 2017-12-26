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
	private List<Vehicle> listVeh;
	public VehicleDAOI ad;
	public ColorDAOI col;
	public ManufacturerDAOI man;
	public ModelDAOI mod2;
	public List<Accessory> lsa;

	
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
				Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
				Model m = mod.getModelById(result.getInt("model_id"));
				Vehicle vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
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
				Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
				Model m = mod.getModelById(result.getInt("model_id"));
				vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
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

	public List<Vehicle> getVehicleListByType(String type){
		Connection con = null;
		Vehicle vehic = null;
		Car cars=null;
		Truck trucks=null;
		listVeh = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			//needs to be changed
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle ;");
			ResultSet cresult = statement.executeQuery("SELECT * FROM car ;");
			ResultSet tresult = statement.executeQuery("SELECT * FROM truck ;");
			
			if(type=="Car") {
				while(cresult.next()) {
					Color c = can.getColorById( result.getInt("color_ID"));
					List<Accessory> a= ac.getAccessoryList(); 
					Model m = mod.getModelById(result.getInt("model_id"));
					Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
					vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
					listVeh.add(vehic);
					cars= new Car(cresult.getInt("doors"), cresult.getInt("passenger_limit"));
					listVeh.add(cars);
				}
			}
			else {
				while(tresult.next()) {
					Color c = can.getColorById( result.getInt("color_ID"));
					List<Accessory> a= ac.getAccessoryList(); 
					Model m = mod.getModelById(result.getInt("model_id"));
					Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
					vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
					listVeh.add(vehic);
					trucks= new Truck(tresult.getInt("length"), tresult.getInt("height"),tresult.getInt("loading_limit"));
					listVeh.add(trucks);
				}
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
		return listVeh;
	}
	
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
	
	public void addTruck(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
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
															length + "," +
															height +","+
															load_limit+
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
	
	public void changeCar(int vehicle_ID,String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("UPDATE vehicle SET" +
									plate + "," +
									color.getColor_ID() + "," +
									model.getModel_ID() + "," +
									manufacturer.getManufacturer_ID() + "," +
									accessory.getAccessory_ID() + "," +
									mileage + "," +
									year + "," +
									active +
									" WHERE vehicle_ID = " + vehicle_ID + ";" );
			
			
			
			statement.setQueryTimeout(60);
			statement.executeUpdate("UPDATE car SET"+ 	doors + "," +
														pass_limit +","+
														" WHERE car_ID = " + vehicle_ID + ";" );
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
	public void changeTruck(int vehicle_ID,String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("UPDATE vehicle SET" +
									plate + "," +
									color.getColor_ID() + "," +
									model.getModel_ID() + "," +
									manufacturer.getManufacturer_ID() + "," +
									accessory.getAccessory_ID() + "," +
									mileage + "," +
									year + "," +
									active +
									" WHERE vehicle_ID = " + vehicle_ID + ";" );
			
			
			
			statement.setQueryTimeout(60);
			statement.executeUpdate("UPDATE truck SET"+ length + "," + height +","+load_limit+","+
														" WHERE truck_ID = " + vehicle_ID + ";" );
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
	
	public void deleteVehicle(int vehicle_id){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeQuery("DELETE * FROM vehicle WHERE vehicle_id = " + vehicle_id + ";");
			Statement statement2 = con.createStatement();
			statement2.setQueryTimeout(60);
			statement2.executeQuery("DELETE * FROM car WHERE car_ID = " + vehicle_id + ";");
			Statement statement3 = con.createStatement();
			statement3.setQueryTimeout(60);
			statement3.executeQuery("DELETE * FROM truck WHERE truck_ID = " + vehicle_id + ";");
		
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
	public int getVehicleCount(){
		vehicles = new ArrayList<Vehicle>();
		Connection con = null;
		ad = new VehicleDAOI();
		col = new ColorDAOI();
		man = new ManufacturerDAOI();
		mod2 = new ModelDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		lsa = acc.getAccessoryList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle");
			
			while(result.next()){
				Color c = can.getColorById( result.getInt("color_ID"));
				List<Accessory> a= ac.getAccessoryList(); 
				Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
				Model m = mod.getModelById(result.getInt("model_id"));
				Vehicle vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
				vehicles.add(vehic);
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
		
		return vehicles.size();
	}
	//ADD changes
	public int getVehicleCountByType(String type) {
		Connection con = null;
		Vehicle vehic = null;
		Car cars=null;
		Truck trucks=null;
		listVeh = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle ;");
			ResultSet cresult = statement.executeQuery("SELECT * FROM car ;");
			ResultSet tresult = statement.executeQuery("SELECT * FROM truck ;");
			
			if(type=="Car") {
				while(cresult.next()) {
					Color c = can.getColorById( result.getInt("color_ID"));
					List<Accessory> a= ac.getAccessoryList(); 
					Model m = mod.getModelById(result.getInt("model_id"));
					Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
					vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
					listVeh.add(vehic);
					cars= new Car(cresult.getInt("doors"), cresult.getInt("passenger_limit"));
					listVeh.add(cars);
				}
			}
			else {
				while(tresult.next()) {
					Color c = can.getColorById( result.getInt("color_ID"));
					List<Accessory> a= ac.getAccessoryList(); 
					Model m = mod.getModelById(result.getInt("model_id"));
					Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
					vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
					listVeh.add(vehic);
					trucks= new Truck(tresult.getInt("length"), tresult.getInt("height"),tresult.getInt("loading_limit"));
					listVeh.add(trucks);
				}
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
		
		return listVeh.size();
	}
	public List<Vehicle> getVehicleListByAge(int age){
		Connection con = null;
		Vehicle vehic = null;
		int year=2017;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle WHERE"+ year + "- manufacturer_year  = " + age);
			
			while(result.next()){
				Color c = can.getColorById( result.getInt("color_ID"));
				List<Accessory> a= ac.getAccessoryList(); 
				Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
				Model m = mod.getModelById(result.getInt("model_id"));
				vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
				vehicles.add(vehic);
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
		
		return vehicles;
		
	}
	public List<Vehicle> getVehicleByColor(Color color){
		vehicles = new ArrayList<Vehicle>();
		Connection con = null;
		ad = new VehicleDAOI();
		col = new ColorDAOI();
		man = new ManufacturerDAOI();
		mod2 = new ModelDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		lsa = acc.getAccessoryList();
	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle");
			
			while(result.next()){
				Color c = can.getColorById( result.getInt("color_ID"));
				List<Accessory> a= ac.getAccessoryList(); 
				Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
				Model m = mod.getModelById(result.getInt("model_id"));
				Vehicle vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
				vehicles.add(vehic);
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
		
		return vehicles;
	}
	public List<Vehicle> getVehicleByAccessory(Accessory accessory);
	public List<Vehicle> getVehicleByModel(Model mode);
	public List<Vehicle> getTruckByLoadingLimit(int limit);
	public List<Vehicle> getCarByDoors(int doors);
	public List<Vehicle> getVehicleByManufacturer(Manufacturer manufacturer);

	
}