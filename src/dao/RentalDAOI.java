package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import imse.Rental;

//written by a01349198 - IB

public class RentalDAOI implements RentalDAO {
	private List<Rental> rentals;
	
	@Override
	public List<Rental> getRentalList(){
		rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM rental");
			
			while(result.next()){
				//Accessory ac = new Accessory(result.getInt("accessory_ID"), result.getString("name"), result.getString("description"));
				//accessories.add(ac);
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
		
		return rentals;
	}
	
	@Override
	public Rental getRentalById(int rental_id){
		Connection con = null;
		Rental ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM rental WHERE rental_ID = " + rental_id + ";");
			
			while(result.next()){
				 //ac = new Accessory(result.getInt("accessory_ID"), result.getString("name"), result.getString("description"));
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
	
	/*@Override
	public void addRental(String name, String description){
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
	}*/
	
	/*@Override
	public void changeRental(int accessory_ID, String name, String description){
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
	}*/
	
	@Override
	public void deleteRental(int rental_id){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeQuery("DELETE * FROM rental WHERE rental_ID = " + rental_id + ";");
		
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
	public int getRentalCount(){
		rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM rental");
			
			while(result.next()){
			///	Accessory ac = new Accessory(result.getInt("accessory_ID"), result.getString("name"), result.getString("description"));
			//	accessories.add(ac);
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
		
		return rentals.size();
	}
	
	/*@Override
	public List<Accessory> getRentalByCostumer(String name){
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
	}*/
	
	/*@Override
	public List<Accessory> getRentalByEmployee(String name){
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
	}*/
	
	/*@Override
	public List<Rental> getRentalByVehicle(Vehicle veh){
		rentals = new ArrayList<Rentals>();
		Connection con = null;
		Rental ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM rental WHERE name = " + name + ";");
			
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
	}*/
	
	/*@Override
	public List<Accessory> getRentalByDatePeriod(String name){
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
	}*/
}
