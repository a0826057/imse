package datafilling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import dao.AccessoryDAOI;
import dao.ManufacturerDAOI;
import dao.ModelDAOI;
import dao.VehicleDAOI;
import imse.Manufacturer;
import imse.Vehicle;

public class TruckGenerator {
	public static void createDB() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=Imse1234");
			PreparedStatement ps = connection.prepareStatement("CREATE DATABASE imsedb");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	public static void dropDB() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=Imse1234");
			PreparedStatement ps = connection.prepareStatement("DROP DATABASE imsedb");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	public static void createTruckTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE t ( " +
															   "truck_ID int"+
															   "length int,"+
															   "height int,"+
															   "loading_limit"+
															   "PRIMARY KEY (truck_ID) REFERENCES vehicle,"+
															   "CONSTRAINT check_truck CHECK (loading_limit > 0 AND length > 0 AND height > 0)"+
															   ");"
																);
		
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}	
	}
	
	public static void fillTable(){
		VehicleDAOI veh = new VehicleDAOI();
		List<Vehicle> vh= veh.getVehicleList();
		Random rand = new Random(); 
		/*
		 * 
		 * 
		ManufacturerDAOI m = new ManufacturerDAOI();
		List<Manufacturer> ls = m.getManufacturerList();
			
			ad.addModel(ls.get(index3), descriptions[index1], Double.parseDouble(prices[index2]));
		
		 */
		
		int[] height= {200,225,300,275,350};
		int[] length= {300,450,275,450,360};
		int[] loading_limit= {300,150,100,500,450};
		
		int index1, index2, index3,index4,index5,index6;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			index3 = rand.nextInt(5); 
			index4 = rand.nextInt(5); 
			index5 = rand.nextInt(5); 
			index6 = rand.nextInt(5); 
			veh.addCar(vh.get(index6), miles[index2], manufactur[index3], act[index4], doors[index5], pass_limit[index6]);
		}
	}
	public static void main (String [] args){
		//dropDB();
		//createDB();
		createTruckTable();
		fillTable();
	}
}
