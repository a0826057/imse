package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import dao.AccessoryDAOI;
import dao.VehicleDAOI;
import model.Accessory;
import model.Vehicle;

//written by a01349198 - IB

public class AccessoryGenerator {
	
	public static void createAccessoryTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
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
		AccessoryDAOI ad = new AccessoryDAOI();
		VehicleDAOI veh = new VehicleDAOI();
		Random rand = new Random();
		
		List<Accessory> ac = ad.getAccessoryList();
		List<Vehicle> ah = veh.getVehicleList();
		
		System.out.println(ah.size());
		
		int index1, index2;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			ad.addHasAccessory(ac.get(index1).getAccessory_ID(), ah.get(index2).getVehicle_ID());
		}
		
	}

	public static void filler(){
		createAccessoryTable();
		fillAccessoryTable();
	}
}
