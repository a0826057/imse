package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Manufacturer;

//written by a01349198 - IB

public class ManufacturerDAOI implements ManufacturerDAO{
	private List<Manufacturer> manufacturers;
	
	@Override
	public List<Manufacturer> getManufacturerList(){
		manufacturers = new ArrayList<Manufacturer>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM manufacturer");
			
			while(result.next()){
				Manufacturer ac = new Manufacturer(result.getInt("manufacturer_ID"), result.getString("name"), result.getString("country"));
				manufacturers.add(ac);
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
		
		return manufacturers;
	}
	
	@Override
	public Manufacturer getManufacturerById(int manufacturer_id){
		Connection con = null;
		Manufacturer ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM manufacturer WHERE manufacturer_ID = " + manufacturer_id + ";");
			
			while(result.next()){
				 ac = new Manufacturer(result.getInt("manufacturer_ID"), result.getString("name"), result.getString("country"));
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
	public void addManufacturer(String name, String country){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("INSERT INTO manufacturer(name,country) VALUES(" +
													  name + "," +
													  country +
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
	public void changeManufacturer(int manufacturer_ID, String name, String country){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("UPDATE manufacturer SET name = " + name + 
														  ", description=" + country +
													      " WHERE manufacturer_ID = " + manufacturer_ID + ";" 
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
	public void deleteManufacturer(int manufacturer_ID){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeQuery("DELETE * FROM manufacturer WHERE manufacturer_ID = " + manufacturer_ID + ";");
		
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
	public int getManufacturerCount(){
		manufacturers = new ArrayList<Manufacturer>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM accessory");
			
			while(result.next()){
				Manufacturer ac = new Manufacturer(result.getInt("manufacturer_ID"), result.getString("name"), result.getString("country"));
				manufacturers.add(ac);
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
		
		return manufacturers.size();
	}
	
	@Override
	public Manufacturer getManufacturerByName(String name){
		Connection con = null;
		Manufacturer ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM manufacturer WHERE name = " + name + ";");
			
			while(result.next()){
				 ac = new Manufacturer(result.getInt("manufacturer_ID"), result.getString("name"), result.getString("country"));
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
	
	public List<Manufacturer> getManufacturersByCountry(String country){
		manufacturers = new ArrayList<Manufacturer>();
		Connection con = null;
		Manufacturer ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM manufacturer WHERE name = " + country + ";");
			
			while(result.next()){
				 ac = new Manufacturer(result.getInt("manufacturer_ID"), result.getString("name"), result.getString("country"));
				 manufacturers.add(ac);
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
		return manufacturers;
	}
}
