package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import dao.AccessoryDAOI;
import dao.ColorDAOI;
import dao.ModelDAOI;
import dao.VehicleDAOI;
import model.Accessory;
import model.Color;
import model.Model;

public class VehicleGenerator {
		
	public static void createVehicleTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE vehicle ("+
															   "vehicle_ID int AUTO_INCREMENT,"+
															   "lisence_plate_number varchar(15) NOT NULL UNIQUE,"+
															   "color_ID int," +
															   "model_ID int, " +
															   "manufacturer_ID int,"+
															   "accessory_ID int ,"+
															   "mileage int DEFAULT 0,"+
															   "manufacturer_year smallint,"+
															   "active boolean DEFAULT true,"+
															   "PRIMARY KEY (vehicle_ID),"+
															   "FOREIGN KEY (color_ID) REFERENCES color (color_ID),"+
															   "FOREIGN KEY (model_ID) REFERENCES model (model_ID)," +
															   "FOREIGN KEY (accessory_ID) REFERENCES accessory (accessory_ID)," +
															   "FOREIGN KEY (model_ID, manufacturer_ID) REFERENCES model (model_ID, manufacturer_ID)," +
															   "CONSTRAINT check_vehicle CHECK (mileage >= 0 AND manufacture_year > 1940)" +
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
	
	public static void fillVehicleTable(){
		VehicleDAOI ad = new VehicleDAOI();
		ColorDAOI col = new ColorDAOI();
		ModelDAOI mod = new ModelDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		
		List<Accessory> lsa = acc.getAccessoryList();
		List<Model> lso = mod.getModelList();
		List<Color> lsc = col.getColorList();
		Random rand = new Random(); 
		
		String[] plates = {"'W 3457","'W 162 ACS","'W 312 AA","'W 456 VA","'W 3454 SS1"};
		int[] miles = {10,160,45,3,13};
		int[] manufactur= {2008,2016,2017,2018,2011};
		int[] doors= {4,5,4,5,4};
		int[] pass_limit= {6,4,5,8,5};
		int[] length= {200,250,160,150,255};
		int[] height= {200,250,260,240,255};
		int[] load_limit= {100,175,160,150,255};
		int index1, index2, index3, index5, index6, index7, index8, index9, index10;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(lsc.size()); 
			index3 = rand.nextInt(lso.size());  
			index5 = rand.nextInt(lsa.size()); 
			index6 = rand.nextInt(5); 
			index7 = rand.nextInt(5);
			index8 = rand.nextInt(5);
			index9 = rand.nextInt(5);
			ad.addCar(plates[index1] + " " + i + index1 + "'", lsc.get(index2), lso.get(index3), lso.get(index3).getManufacturer(), lsa.get(index5), miles[index6], manufactur[index7], true, doors[index8], pass_limit[index9]);
		}
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(lsc.size()); 
			index3 = rand.nextInt(lso.size());  
			index5 = rand.nextInt(lsa.size()); 
			index6 = rand.nextInt(5); 
			index7 = rand.nextInt(5);
			index8 = rand.nextInt(5);
			index9 = rand.nextInt(5);
			index10 = rand.nextInt(5);
			ad.addTruck(plates[index1] + " " + i*3 + index1 + "'", lsc.get(index2), lso.get(index3), lso.get(index3).getManufacturer(), lsa.get(index5), miles[index6], manufactur[index7],true, length[index8], height[index9], load_limit[index10]);
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
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
	
	public static void filler(){
		createVehicleTable();
		createAccessoryHasTable();
		fillVehicleTable();
	}
}