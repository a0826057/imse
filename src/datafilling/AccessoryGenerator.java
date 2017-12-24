package datafilling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import dao.AccessoryDAOI;

//written by a01349198 - IB

public class AccessoryGenerator {
	
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
	
	public static void createAccessoryTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE accessory ("+
															   "accessory_ID int AUTO_INCREMENT,"+
															   "name varchar(120) NOT NULL,"+
															   "description	varchar(255),"+
															   "PRIMARY KEY (accessory_ID)"+
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
	
	public static void createAccessoryHasTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE has_accessory (" +
															   "accessory_ID int," +
															   "vehicle_ID int," +
															   "PRIMARY KEY (accessory_ID, vehicle_ID)," +
															   "FOREIGN KEY (accessory_ID) REFERENCES accessory (accessory_ID)," +
															   "FOREIGN KEY (vehicle_ID) REFERENCES vehicle (vehicle_ID)" +
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
	
	public static void fillAccessoryTable(){
		AccessoryDAOI ad = new AccessoryDAOI();
		Random rand = new Random(); 
		
		String[] names = {"'Tire'","'Seat Covers'","'LED Lights'","'Color Spray'","'Nitro'"};
		String[] descriptions = {"'Very nice and comfy'","'Not so great'","'Wooohoo'","'Cool thing'","'Not so bad'"};
		
		int index1, index2;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			ad.addAccessory(names[index1], descriptions[index2]);
		}
	}
	
	public static void fillAccessoryHasTable(){
		
	}

	public static void main (String [] args){
		//dropDB();
		//createDB();
		//createAccessoryTable();
		//createAccessoryHasTable();
		fillAccessoryTable();
		//fillAccessoryHasTable();
	}
}