package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Manufacturer;
import model.Model;

//written by a01349198 - IB

public class ModelDAOI implements ModelDAO {
	
	ManufacturerDAOI man = new ManufacturerDAOI();
	private List<Model> models;
	
	@Override
	public List<Model> getModelList(){
		models = new ArrayList<Model>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM model");
			
			while(result.next()){
				Manufacturer m = man.getManufacturerById( result.getInt("manufacturer_ID"));
				Model ac = new Model(result.getInt("model_ID"), m, result.getString("description"), result.getDouble("price"));
				models.add(ac);
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
		
		return models;
	}
	
	@Override
	public Model getModelById(int model_id){
		Connection con = null;
		Model ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM model WHERE model_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, model_id);
			ResultSet result = prepared.executeQuery();
			
			while(result.next()){
				Manufacturer m = man.getManufacturerById( result.getInt("manufacturer_ID"));
				ac = new Model(result.getInt("model_ID"), m, result.getString("description"), result.getDouble("price"));
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
	public void addModel(Manufacturer man, String description, double price){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "INSERT INTO model(manufacturer_ID,description,price) VALUES(?,?,?)";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, man.getManufacturer_ID());
			prepared.setString(2, description);
			prepared.setDouble(3, price);
			prepared.executeUpdate();
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
	public void changeModel(int model_ID, Manufacturer man, String description, double price){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "UPDATE model SET manufacturer_ID = ?, description = ?, price = ? WHERE model_ID = ?;"; 
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, man.getManufacturer_ID());
			prepared.setString(2, description);
			prepared.setDouble(3, price);
			prepared.setInt(4, model_ID);
			prepared.executeUpdate();
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
	public void deleteModel(int model_id){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "DELETE * FROM model WHERE model_ID = " + model_id + ";";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, model_id);
			prepared.executeQuery();
		
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
	public int getModelCount(){
		models = new ArrayList<Model>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM model");
			
			while(result.next()){
				Manufacturer m = man.getManufacturerById( result.getInt("manufacturer_ID"));
				Model ac = new Model(result.getInt("model_ID"), m, result.getString("description"), result.getDouble("price"));
				models.add(ac);
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
		
		return models.size();
	}
	
	@Override
	public List<Model> getModelsByManufacturersName(String name){
		Manufacturer m = man.getManufacturerByName(name);
		models = new ArrayList<Model>();
		Connection con = null;
		Model ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM model WHERE manufacturer_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, m.getManufacturer_ID());
			ResultSet result = prepared.executeQuery();
			
			while(result.next()){
				 ac = new Model(result.getInt("model_ID"), m, result.getString("description"), result.getDouble("description"));
				 models.add(ac);
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
		return models;
	}
}
