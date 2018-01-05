package dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle;");
			
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
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle WHERE vehicle_ID = " + vehicle_ID + ";");
			
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
				ResultSet cresult = statement.executeQuery("SELECT * FROM car ;");
				
				while(cresult.next()) {
					Vehicle v = veh.getVehicleById(cresult.getInt("car_ID"));
					Car car = new Car(v.getVehicle_ID(), v.getLicense_plate_number(), v.getColor(), v.getModel(), v.getManufactur(), v.getAccessory(), v.getMileage() ,v.getManufacture_year(), v.getActive() , cresult.getInt("doors"), cresult.getInt("passenger_limit"));
					ls.add(car);
				}
			}
			else if(type.equals("truck")){
				ResultSet tresult = statement.executeQuery("SELECT * FROM truck ;");
				
				while(tresult.next()) {
					Vehicle v = veh.getVehicleById(tresult.getInt("truck_ID"));
					Truck truck = new Truck(v.getVehicle_ID(), v.getLicense_plate_number(), v.getColor(), v.getModel(), v.getManufactur() , v.getAccessory(), v.getMileage(), v.getManufacture_year(), v.getActive(), tresult.getInt("lenght"),tresult.getInt("height"), tresult.getInt("load_limit"));
					ls.add(truck);
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
	
	public void addCar(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
			statement.executeUpdate("INSERT INTO truck(truck_ID, length, height, loading_limit) VALUES(" +
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeQuery("DELETE * FROM vehicle WHERE vehicle_id = " + vehicle_id + ";");
			Statement statement2 = con.createStatement();
			statement2.setQueryTimeout(60);
			statement2.executeQuery("DELETE * FROM car WHERE car_ID = " + vehicle_id + ";");
			Statement statement3 = con.createStatement();
			statement3.setQueryTimeout(60);
			statement3.executeQuery("DELETE * FROM truck WHERE truck_ID = " + vehicle_id + ";");
			statement3.executeQuery("DELETE * FROM has_accessory WHERE vehicle_ID = " + vehicle_id + ";");
		
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

	public int getVehicleCountByType(String type) {
		Connection con = null;
		List<Vehicle> ls = new ArrayList<Vehicle> ();
		VehicleDAOI veh = new VehicleDAOI();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			
			if(type.equals("car")) {
				ResultSet cresult = statement.executeQuery("SELECT * FROM car ;");
				
				while(cresult.next()) {
					Vehicle v = veh.getVehicleById(cresult.getInt("car_ID"));
					Car car = new Car(v.getVehicle_ID(), v.getLicense_plate_number(), v.getColor(), v.getModel(), v.getManufactur(), v.getAccessory(), v.getMileage() ,v.getManufacture_year(), v.getActive() , cresult.getInt("doors"), cresult.getInt("passenger_limit"));
					ls.add(car);
				}
			}
			else if(type.equals("truck")){
				ResultSet tresult = statement.executeQuery("SELECT * FROM truck ;");
				
				while(tresult.next()) {
					Vehicle v = veh.getVehicleById(tresult.getInt("truck_ID"));
					Truck truck = new Truck(v.getVehicle_ID(), v.getLicense_plate_number(), v.getColor(), v.getModel(), v.getManufactur() , v.getAccessory(), v.getMileage(), v.getManufacture_year(), v.getActive(), tresult.getInt("lenght"),tresult.getInt("height"), tresult.getInt("load_limit"));
					ls.add(truck);
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
		return ls.size();
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
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle WHERE manufacturer_year = " + age + ";");
			
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
	public List<Vehicle> getVehicleByColor(Color color){
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
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle WHERE color_ID = "+ color.getColor_ID()+ ";");
			
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
	public List<Vehicle> getVehicleByAccessory(Accessory accessory){
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
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle WHERE accessory_ID = "+ accessory.getAccessory_ID()+ ";");
			
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
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle WHERE model_ID = "+ mode.getModel_ID()+ ";");
			
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
			ResultSet tresult = statement.executeQuery("SELECT * FROM truck WHERE loading_limit = "+limit+";");
			while(tresult.next()){
				Vehicle veh = v.getVehicleById(tresult.getInt("truck_ID"));
				List<Accessory> a= acc.getHasAccessory(veh.getVehicle_ID());
				Truck trucks = new Truck(veh.getVehicle_ID(), veh.getLicense_plate_number(), veh.getColor(), veh.getModel(), veh.getManufactur() , a, veh.getMileage(), veh.getManufacture_year(), veh.getActive(), tresult.getInt("lenght"),tresult.getInt("height"), tresult.getInt("load_limit"));
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
	public List<Car> getCarByDoors(int doors){
		List<Car> cars = new ArrayList<Car>();
		Connection con = null;
		VehicleDAOI veh = new VehicleDAOI();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet tresult = statement.executeQuery("SELECT * FROM car WHERE doors = "+doors+";");
			
			while(tresult.next()){
				Vehicle v = veh.getVehicleById(tresult.getInt("car_ID"));
				Car car = new Car(v.getVehicle_ID(), v.getLicense_plate_number(), v.getColor(), v.getModel(), v.getManufactur(), v.getAccessory(), v.getMileage() ,v.getManufacture_year(), v.getActive() , tresult.getInt("doors"),tresult.getInt("passenger_limit"));
				cars.add(car);
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
		
		return cars;		
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
			ResultSet result = statement.executeQuery("SELECT * FROM vehicle WHERE manufacturer_ID ="+ manufacturer.getManufacturer_ID()+ ";");
			
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
}