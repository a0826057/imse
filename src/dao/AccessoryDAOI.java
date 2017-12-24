package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import imse.Accessory;

//written by a01349198 - IB

public class AccessoryDAOI implements AccessoryDAO{
	private List<Accessory> accessories;
	
	@Override
	public List<Accessory> getAccessoryList(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/imseDB","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet rs = statement.executeQuery("SELECT * FROM Accessory");
			
		}catch(Exception e){
			
		}
		
		return null;
	}
	
	public Accessory getAccessoryById(int accessory_id){
		return null;
	}
	
	public void addAccessory(String name, String description){
		
	}
	
	public void changeAccessory(String name, String description){
		
	}
	
	public void deleteAccessory(int accessory_id){
		
	}
	
	public int getAccessoryCount(){
		return 0;
	}
	
	public Accessory getAccessoryByName(String name){
		return null;
	}
	
}
