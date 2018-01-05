package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Accessory;

//written by a01349198 - IB

public class AccessoryDAOI implements AccessoryDAO{
	private List<Accessory> accessories;
	
	@Override
	public List<Accessory> getAccessoryList(){
		accessories = new ArrayList<Accessory>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
		
		return accessories;
	}
	
	@Override
	public Accessory getAccessoryById(int accessory_id){
		Connection con = null;
		Accessory ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
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
	
	public void addHasAccessory(int accessory_ID, int vehicle_ID){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root", "MySQLrp");
			
			Statement stat = con.createStatement();
			stat.setQueryTimeout(60);
			stat.executeUpdate("INSERT INTO has_accessory(accessory_ID, vehicle_ID) VALUES(" +
																	accessory_ID + "," +
																	vehicle_ID +
																	");"
																	);
		}catch(Exception e){
			System.err.println(e);
		}finally{
			try{
				if(con != null)
					con.close();
			}catch(SQLException e){
				System.err.println(e);
			}
		}
		
	}
	
	public List<Accessory> getHasAccessory(int vehicle_ID){
		Connection con = null;
		AccessoryDAOI ac = new AccessoryDAOI();
		List<Accessory> list = new ArrayList<Accessory>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root", "MySQLrp");
			
			Statement stat = con.createStatement();
			stat.setQueryTimeout(60);
			ResultSet res = stat.executeQuery("SELECT * FROM has_accessory WHERE vehicle_ID = " + vehicle_ID + ";");
			
			while(res.next()){
				 Accessory acc = ac.getAccessoryById(res.getInt("accessory_ID"));
				 list.add(acc);
			}
			
		}catch(Exception e){
			System.err.println(e);
		}finally{
			try{
				if(con != null)
					con.close();
			}catch(SQLException e){
				System.err.println(e);
			}
		}
		
		return list;
	}
}
