package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import imse.Manufacturer;
import imse.Model;

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
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM model WHERE model_ID = " + model_id + ";");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("INSERT INTO model(manufacturer_ID,description,price) VALUES(" +
													  man.getManufacturer_ID() + "," +
													  description + "," +
													  price +
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
	public void changeModel(int model_ID, Manufacturer man, String description, double price){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("UPDATE model SET manufacturer_ID = " + man.getManufacturer_ID() + 
														  ", description=" + description +
														  ", price=" + price +
													      " WHERE model_ID = " + model_ID + ";" 
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
	public void deleteModel(int model_id){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeQuery("DELETE * FROM model WHERE model_ID = " + model_id + ";");
		
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM model WHERE manufacturer_ID = " + m.getManufacturer_ID() + ";");
			
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
