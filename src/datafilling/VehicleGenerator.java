package datafilling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;


import dao.VehicleDAOI;

public class VehicleGenerator {
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
	
	public static void createVehicleTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE vehicle ("+
															   "vehicle_ID int AUTO_INCREMENT,"+
															   "lisence_plate_number varchar(120),"+
															   "color_ID int," +
															   "model_ID int, " +
															   "accessory_ID int ,"+
															   "mileage int,"+
															   "manufacturer_year int,"+
															   "active boolean,"+
															   "PRIMARY KEY (vehicle_ID)"+
															   "PRIMARY KEY (model_ID, accessory_ID,color_ID),"+
															   "FOREIGN KEY (model_ID) REFERENCES model (model_ID)," +
															   "FOREIGN KEY (accessory_ID) REFERENCES accessory (accessory_ID)," +
															   "FOREIGN KEY (color_ID) REFERENCES color (color_ID)" +
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
	
	public static void fillVehicleCarTable(){
		VehicleDAOI ad = new VehicleDAOI();
		Random rand = new Random(); 
		
		String[] plates = {"'W 3457'","'W 162 ACS'","'W 312 AA'","'W 456 VA'","'W 3454 SS1'"};
		int[] miles = {10,160,45,3,13};
		int[] manufactur= {2008,2016,2017,2018,2011};
		int[] act= {0,1,0,1,0};
		int[] doors= {4,5,4,5,4};
		int[] pass_limit= {6,4,5,8,5};
	
		
		int index1, index2, index3, index4, index5, index6;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			index3 = rand.nextInt(5); 
			index4 = rand.nextInt(5); 
			index5 = rand.nextInt(5); 
			index6 = rand.nextInt(5); 
			ad.addCar(plates[index1], miles[index2], manufactur[index3], act[index4], doors[index5], pass_limit[index6]);
		}
	}
	public static void fillVehicleTruckTable(){
		VehicleDAOI ad = new VehicleDAOI();
		Random rand = new Random(); 
		
		String[] plates = {"'W 15234'","'W 16 AEY'","'W 342 BC'","'W 90706 M'","'W 223 SS1'"};
		int[] miles = {10,160,45,3,13};
		int[] manufactur= {2008,2016,2017,2018,2011};
		int[] act= {0,1,0,1,0};
		int[] height= {200,225,300,275,350};
		int[] length= {300,450,275,450,360};
		int[] loading_limit= {300,150,100,500,450};
		
		int index1, index2, index3, index4, index5, index6, index7;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			index3 = rand.nextInt(5); 
			index4 = rand.nextInt(5); 
			index5 = rand.nextInt(5); 
			index6 = rand.nextInt(5); 
			index7 = rand.nextInt(5); 
			ad.addTruck(plates[index1], miles[index2], manufactur[index3], act[index4], height[index5], length[index6],loading_limit[index7]);
		}
	}
	
	public static void main (String [] args){
		//dropDB();
		//createDB();
		createVehicleTable();
		fillVehicleCarTable();
		fillVehicleTruckTable();

	}
}
