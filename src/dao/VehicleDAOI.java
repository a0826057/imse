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
import imse.Model;
import imse.Truck;

public class VehicleDAOI implements VehicleDAO{
	private List<Vehicle> vehicles;
	
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
				Vehicle ac = new Vehicle(result.getString("accessory_ID"), result.getString("name"), result.getString("description"));
				String plate, Color color2, Model model2, Accessory accessory2, int mileage2, int year,
				Boolean active2, int doors, int pass_limit
				vehicles.add(ac);
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
	public Vehicle getVehicleListById(int vehicle_ID);
	public List<Vehicle> getVehicleListByType(String type);
	public void addCar(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit);
	public void addTruck(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit);
	public void changeCar(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int doors, int pass_limit);
	public void changeTruck(String plate, Color color, Model model, Accessory accessory, int mileage, int year, Boolean active, int length, int height, int load_limit);
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
	
	
	
	
	@Override
	public Accessory getAccessoryById(int accessory_id){
		Connection con = null;
		Accessory ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM accessory WHERE accessory_ID = " + accessory_id + ";");
			
			while(result.next()){
				 ac = new Accessory(result.getInt("accessory_ID"), result.getString("name"), result.getString("description"));
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
		return ac;
	}
	
	@Override
	public void addAccessory(String name, String description){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("INSERT INTO accessory(name,description) VALUES(" +
													  name + "," +
													  description +
													  ");" 
													  );
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
	
	@Override
	public void changeAccessory(int accessory_ID, String name, String description){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("UPDATE accessory SET name = " + name + 
														  ", description=" + description +
													      " WHERE accessory_ID = " + accessory_ID + ";" 
													      );
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
	
	@Override
	public void deleteAccessory(int accessory_id){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeQuery("DELETE * FROM accessory WHERE accessory_ID = " + accessory_id + ";");
		
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
	
	@Override
	public int getAccessoryCount(){
		accessories = new ArrayList<Accessory>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM accessory");
			
			while(result.next()){
				Accessory ac = new Accessory(result.getInt("accessory_ID"), result.getString("name"), result.getString("description"));
				accessories.add(ac);
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
		
		return accessories.size();
	}
	
	@Override
	public List<Accessory> getAccessoriesByName(String name){
		accessories = new ArrayList<Accessory>();
		Connection con = null;
		Accessory ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM accessory WHERE name = " + name + ";");
			
			while(result.next()){
				 ac = new Accessory(result.getInt("accessory_ID"), result.getString("name"), result.getString("description"));
				 accessories.add(ac);
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
		return accessories;
	}
}
