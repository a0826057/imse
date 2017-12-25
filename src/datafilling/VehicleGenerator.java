package datafilling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		String[] names = {"'Tire'","'Seat Covers'","'LED Lights'","'Color Spray'","'Nitro'"};
		String[] descriptions = {"'Very nice and comfy'","'Not so great'","'Wooohoo'","'Cool thing'","'Not so bad'"};
		
		
		
		int index1, index2;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			ad.addCar(plate, color, model, accessory, mileage, year, active, doors, pass_limit);
			/*ad.addAccessory(names[index1], descriptions[index2]);*/
		}
	}
	public static void fillVehicleTruckTable(){
		VehicleDAOI ad = new VehicleDAOI();
		Random rand = new Random(); 
		
		String[] names = {"'Tire'","'Seat Covers'","'LED Lights'","'Color Spray'","'Nitro'"};
		String[] descriptions = {"'Very nice and comfy'","'Not so great'","'Wooohoo'","'Cool thing'","'Not so bad'"};
		/*
		"lisence_plate_number varchar(120),"+
		   "color_ID int," +
		   "model_ID int, " +
		   "accessory_ID int ,"+
		   "mileage int,"+
		   "manufacturer_year int,"+
		   "active boolean,"+
		*/
		int index1, index2;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			ad.addCar(plate, color, model, accessory, mileage, year, active, doors, pass_limit);
			/*ad.addAccessory(names[index1], descriptions[index2]);*/
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
