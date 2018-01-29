package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Accessory;
import model.Vehicle;
import model.Car;
import model.Color;
import model.Manufacturer;
import model.Model;
import model.Truck;

public class VehicleDAOI implements VehicleDAO{
	private List<Vehicle> vehicles;
	
	@Override
	public List<Vehicle> getVehicleList(){
		vehicles = new ArrayList<Vehicle>();
		ManufacturerDAOI man = new ManufacturerDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		ColorDAOI col = new ColorDAOI();
		ModelDAOI mod = new ModelDAOI();
		Connection veh = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			veh = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = veh.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle WHERE active = 1;");
			
			while(result.next()){
				Color c = col.getColorById( result.getInt("color_ID"));
				List<Accessory> a= acc.getHasAccessory(result.getInt("vehicle_ID")); 
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
	
	public Vehicle getVehicleById(int vehicle_ID) {
		Connection con = null;
		Vehicle vehic = null;
		ManufacturerDAOI man = new ManufacturerDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		ColorDAOI col = new ColorDAOI();
		ModelDAOI mod = new ModelDAOI();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM vehicle WHERE vehicle_ID = ?";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, vehicle_ID);
			ResultSet result = prepared.executeQuery();
			
			while(result.next()){
				Color c = col.getColorById( result.getInt("color_ID"));
				List<Accessory> a= acc.getHasAccessory(result.getInt("vehicle_ID")); 
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
		List<Vehicle> ls = new ArrayList<Vehicle> ();
		VehicleDAOI veh = new VehicleDAOI();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			
			if(type.equals("car")) {
				ResultSet cresult = statement.executeQuery("SELECT * FROM car;");
				
				while(cresult.next()) {
					Vehicle v = veh.getVehicleById(cresult.getInt("car_ID"));
					if(v.getActive()){
						Car car = new Car(v.getVehicle_ID(), v.getLicense_plate_number(), v.getColor(), v.getModel(), v.getManufactur(), v.getAccessory(), v.getMileage() ,v.getManufacture_year(), v.getActive() , cresult.getInt("doors"), cresult.getInt("passenger_limit"));
						ls.add(car);
					}
				}
			}
			else if(type.equals("truck")){
				ResultSet tresult = statement.executeQuery("SELECT * FROM truck;");
				
				while(tresult.next()) {
					Vehicle v = veh.getVehicleById(tresult.getInt("truck_ID"));
					if(v.getActive()){
						Truck truck = new Truck(v.getVehicle_ID(), v.getLicense_plate_number(), v.getColor(), v.getModel(), v.getManufactur() , v.getAccessory(), v.getMileage(), v.getManufacture_year(), v.getActive(), tresult.getInt("length"),tresult.getInt("height"), tresult.getInt("loading_limit"));
						ls.add(truck);
					}
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
		return ls;
	}
	
	public void addCar(String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "INSERT INTO vehicle(lisence_plate_number,color_ID,model_ID,manufacturer_ID,accessory_ID,mileage,manufacturer_year,active) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setString(1, plate);
			prepared.setInt(2, color);
			prepared.setInt(3,model);
			prepared.setInt(4, manufacturer);
			prepared.setInt(5, accessory);
			prepared.setInt(6, mileage);
			prepared.setInt(7, year);
			prepared.setBoolean(8, active);
			prepared.executeUpdate();
			VehicleDAOI veh = new VehicleDAOI();
			List<Vehicle> lsv = veh.getVehicleList();
			String raw_query2 = "INSERT INTO car(car_ID,doors,passenger_limit) VALUES(?,?,?)";
			PreparedStatement prepared2 = con.prepareStatement(raw_query2);	
			prepared2.setInt(1, lsv.get(lsv.size()-1).getVehicle_ID());
			prepared2.setInt(2, doors);
			prepared2.setInt(3, pass_limit);
			prepared2.executeUpdate();
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
	
	public void addTruck(String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "INSERT INTO vehicle(lisence_plate_number,color_ID,model_ID,manufacturer_ID,accessory_ID,mileage,manufacturer_year,active) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setString(1, plate);
			prepared.setInt(2, color);
			prepared.setInt(3,model);
			prepared.setInt(4, manufacturer);
			prepared.setInt(5, accessory);
			prepared.setInt(6, mileage);
			prepared.setInt(7, year);
			prepared.setBoolean(8, active);
			prepared.executeUpdate();					
			VehicleDAOI veh = new VehicleDAOI();
			List<Vehicle> lsv = veh.getVehicleList();
			String raw_query2 = "INSERT INTO truck(truck_ID, length, height, loading_limit) VALUES(?,?,?,?)";
			PreparedStatement prepared2 = con.prepareStatement(raw_query2);
			prepared2.setInt(1, lsv.get(lsv.size()-1).getVehicle_ID());
			prepared2.setInt(2,length);
			prepared2.setInt(3, height);
			prepared2.setInt(4,load_limit);
			prepared2.executeUpdate();												
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
	
	public void changeCar(int vehicle_ID,String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "UPDATE vehicle SET license_plate_number= ?, manufacturer_ID=?,color_ID=?, accessory_ID=?,mileage=?, manufacture_year=?,active=? WHERE vehicle_ID =? "; 
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setString(1, plate);
			prepared.setInt(2, manufacturer);
			prepared.setInt(3, model);
			prepared.setInt(4, color);
			prepared.setInt(5, accessory);
			prepared.setInt(6, mileage);
			prepared.setInt(7, year);
			prepared.setBoolean(8, active);
			prepared.setInt(9, vehicle_ID);						
			prepared.executeUpdate();		
			
			
			
			
			statement.setQueryTimeout(60);
			String raw_query2 = "UPDATE car SET doors=?, passenger_limit=? WHERE car_ID =?";
			PreparedStatement prepared2 = con.prepareStatement(raw_query2);
			prepared2.setInt(1, doors);
			prepared2.setInt(2, pass_limit);
			prepared2.setInt(3, vehicle_ID);
			prepared2.executeUpdate();
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
	public void changeTruck(int vehicle_ID,String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "UPDATE vehicle SET license_plate_number= ?, manufacturer_ID=?, model_ID=?, color_ID=?, accessory_ID=?,mileage=?, manufacture_year=?,active=? WHERE vehicle_ID =? "; 
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setString(1, plate);
			prepared.setInt(2, manufacturer);
			prepared.setInt(3, model);
			prepared.setInt(4, color);
			prepared.setInt(5, accessory);
			prepared.setInt(6, mileage);
			prepared.setInt(7, year);
			prepared.setBoolean(8, active);
			prepared.setInt(9, vehicle_ID);			
			prepared.executeUpdate();		
			
			
			
			statement.setQueryTimeout(60);
			String raw_query2 = "UPDATE truck SET height=?, length=?, loading_limit=? WHERE truck_ID =?";
			PreparedStatement prepared2 = con.prepareStatement(raw_query2);
			prepared2.setInt(1, height);
			prepared2.setInt(2, length);
			prepared2.setInt(3, load_limit);
			prepared2.setInt(4, vehicle_ID);
			prepared2.executeUpdate();
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
		/*	String raw_query4 ="DELETE FROM has_accessory WHERE vehicle_ID =?;";
			PreparedStatement prepared4 = con.prepareStatement(raw_query4);
			prepared4.setInt(1, vehicle_id);
			prepared4.executeUpdate(); 	*/
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "UPDATE vehicle SET active = 0 WHERE vehicle_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, vehicle_id);
			prepared.executeUpdate();
			
			 
	    	  
		/*	String raw_query1 = "DELETE FROM car WHERE car_ID = ?;";
			PreparedStatement prepared2 = con.prepareStatement(raw_query1);
			prepared2.setInt(1, vehicle_id);
			prepared2.executeUpdate();
			
			
			String raw_query3 = "DELETE FROM truck WHERE truck_ID = ?;";
			PreparedStatement prepared3 = con.prepareStatement(raw_query3);
			prepared3.setInt(1, vehicle_id);
			prepared3.executeUpdate();
			*/
	    	con.close();
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

		ManufacturerDAOI man = new ManufacturerDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		ColorDAOI col = new ColorDAOI();
		ModelDAOI mod = new ModelDAOI();
		try{ 
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle");
			
			while(result.next()){
				Color c = col.getColorById( result.getInt("color_ID"));
				List<Accessory> a= acc.getHasAccessory(result.getInt("vehicle_ID")); 
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

	public List<Vehicle> getVehicleListByAge(int age){
		Connection con = null;
		Vehicle vehic = null;

		ManufacturerDAOI man = new ManufacturerDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		ColorDAOI col = new ColorDAOI();
		ModelDAOI mod = new ModelDAOI();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM vehicle WHERE manufacturer_year = ?";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, age);
			ResultSet result = prepared.executeQuery();
			while(result.next()){
				Color c = col.getColorById( result.getInt("color_ID"));
				List<Accessory> a= acc.getHasAccessory(result.getInt("vehicle_ID")); 
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
	
	public List<Vehicle> getVehicleByModel(Model mode){
		vehicles = new ArrayList<Vehicle>();
		Connection con = null;
		ManufacturerDAOI man = new ManufacturerDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		ColorDAOI col = new ColorDAOI();
		ModelDAOI mod = new ModelDAOI();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM vehicle WHERE model_ID = ?";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, mode.getModel_ID());	 
			ResultSet result = prepared.executeQuery();
			
			while(result.next()){
				Color c = col.getColorById( result.getInt("color_ID"));
				List<Accessory> a= acc.getHasAccessory(result.getInt("vehicle_ID")); 
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
	public List<Vehicle> getTruckByLoadingLimit(int limit){
		vehicles = new ArrayList<Vehicle>();
		Connection con = null;
		VehicleDAOI v = new VehicleDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM truck WHERE loading_limit = ?";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, limit);
			ResultSet tresult = prepared.executeQuery();
			
			while(tresult.next()){
				Vehicle veh = v.getVehicleById(tresult.getInt("truck_ID"));
				List<Accessory> a= acc.getHasAccessory(veh.getVehicle_ID());
				Truck trucks = new Truck(veh.getVehicle_ID(), veh.getLicense_plate_number(), veh.getColor(), veh.getModel(), veh.getManufactur() , a, veh.getMileage(), veh.getManufacture_year(), veh.getActive(), tresult.getInt("length"),tresult.getInt("height"), tresult.getInt("loading_limit"));
				vehicles.add(trucks);
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
	
	public List<Vehicle> getVehicleByManufacturer(Manufacturer manufacturer){
		vehicles = new ArrayList<Vehicle>();
		Connection con = null;

		ManufacturerDAOI man = new ManufacturerDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		ColorDAOI col = new ColorDAOI();
		ModelDAOI mod = new ModelDAOI();
		 
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM vehicle WHERE manufacturer_ID = ?"; 
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, manufacturer.getManufacturer_ID());
			ResultSet result = prepared.executeQuery();
			
			while(result.next()){
				Color c = col.getColorById( result.getInt("color_ID"));
				List<Accessory> a= acc.getHasAccessory(result.getInt("vehicle_ID")); 
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

	public void addCar(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory,
			int mileage, int year, boolean active, int doors, int pass_limit) {
		// TODO Auto-generated method stub
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "INSERT INTO vehicle(lisence_plate_number,color_ID,model_ID,manufacturer_ID,accessory_ID,mileage,manufacturer_year,active) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setString(1, plate);
			prepared.setInt(2, color.getColor_ID());
			prepared.setInt(3,model.getModel_ID());
			prepared.setInt(4, manufacturer.getManufacturer_ID());
			prepared.setInt(5, accessory.getAccessory_ID());
			prepared.setInt(6, mileage);
			prepared.setInt(7, year);
			prepared.setBoolean(8, active);
			prepared.executeUpdate();
			VehicleDAOI veh = new VehicleDAOI();
			List<Vehicle> lsv = veh.getVehicleList();
			String raw_query2 = "INSERT INTO car(car_ID,doors,passenger_limit) VALUES(?,?,?)";
			PreparedStatement prepared2 = con.prepareStatement(raw_query2);	
			prepared2.setInt(1, lsv.get(lsv.size()-1).getVehicle_ID());
			prepared2.setInt(2, doors);
			prepared2.setInt(3, pass_limit);
			prepared2.executeUpdate();
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

	public void addTruck(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory,
			int mileage, int year, boolean active, int length, int height, int load_limit) {
		// TODO Auto-generated method stub
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "INSERT INTO vehicle(lisence_plate_number,color_ID,model_ID,manufacturer_ID,accessory_ID,mileage,manufacturer_year,active) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setString(1, plate);
			prepared.setInt(2, color.getColor_ID());
			prepared.setInt(3,model.getModel_ID());
			prepared.setInt(4, manufacturer.getManufacturer_ID());
			prepared.setInt(5, accessory.getAccessory_ID());
			prepared.setInt(6, mileage);
			prepared.setInt(7, year);
			prepared.setBoolean(8, active);
			prepared.executeUpdate();					
			VehicleDAOI veh = new VehicleDAOI();
			List<Vehicle> lsv = veh.getVehicleList();
			String raw_query2 = "INSERT INTO truck(truck_ID, length, height, loading_limit) VALUES(?,?,?,?)";
			PreparedStatement prepared2 = con.prepareStatement(raw_query2);
			prepared2.setInt(1, lsv.get(lsv.size()-1).getVehicle_ID());
			prepared2.setInt(2,length);
			prepared2.setInt(3, height);
			prepared2.setInt(4,load_limit);
			prepared2.executeUpdate();												
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
	
	public List<Vehicle> searchVehicle(int color, int model, int manufacturer, int accessory){
		Connection con = null;
		ColorDAOI col = new ColorDAOI();
		ModelDAOI mod = new ModelDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		ManufacturerDAOI man = new ManufacturerDAOI();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM vehicle WHERE ";
			
			int counter = 0;
			if (color > 0) {
				raw_query = raw_query + "color_ID = ?";
				counter++;
			}
			if (model > 0) {
				if (counter > 0)
					raw_query = raw_query + " AND ";
				raw_query = raw_query + "model_ID = ?";
				counter++;
			}
			if (manufacturer > 0) {
				if (counter > 0)
					raw_query = raw_query + " AND ";
				raw_query = raw_query + "manufacturer_ID = ?";
				counter++;
			}
			if (accessory > 0) {
				if (counter > 0)
					raw_query = raw_query + " AND ";
				raw_query = raw_query + "accessory_ID = ?";
				counter++;
			}
			counter = 0;
			PreparedStatement prepared = con.prepareStatement(raw_query);
			if (color > 0) {
				prepared.setInt(++counter, color);
			}
			if (model > 0) {
				prepared.setInt(++counter, model);
			}
			if (manufacturer > 0) {
				prepared.setInt(++counter, manufacturer);
			}
			if (accessory > 0) {
				prepared.setInt(++counter, accessory);
			}
			ResultSet result = prepared.executeQuery(raw_query);
			List<Vehicle> vehicles = new ArrayList<Vehicle>();
			while(result.next()){
				Color c = col.getColorById(result.getInt("color_ID"));
				List<Accessory> a= acc.getHasAccessory(result.getInt("vehicle_ID")); 
				Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
				Model m = mod.getModelById(result.getInt("model_id"));
				Vehicle vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
				vehicles.add(vehic);			
			}
			return vehicles;
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
}