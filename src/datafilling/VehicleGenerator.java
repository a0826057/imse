package datafilling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import dao.AccessoryDAOI;
import dao.ColorDAOI;
import dao.ManufacturerDAOI;
import dao.ModelDAOI;
import dao.VehicleDAOI;
import imse.Accessory;
import imse.Color;
import imse.Manufacturer;
import imse.Model;

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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=MySQLrp");
			PreparedStatement ps = connection.prepareStatement("CREATE DATABASE myimsedb");
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=MySQLrp");
			PreparedStatement ps = connection.prepareStatement("DROP DATABASE myimsedb");
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb","root","MySQLrp");
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
		ManufacturerDAOI man = new ManufacturerDAOI();
		ModelDAOI mod = new ModelDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		
		List<Accessory> lsa = acc.getAccessoryList();
		List<Model> lso = mod.getModelList();
		List<Manufacturer> lsm = man.getManufacturerList();
		List<Color> lsc = col.getColorList();
		
		Random rand = new Random(); 
		
		String[] plates = {"'W 3457'","'W 162 ACS'","'W 312 AA'","'W 456 VA'","'W 3454 SS1'"};
		int[] miles = {10,160,45,3,13};
		int[] manufactur= {2008,2016,2017,2018,2011};
		int[] doors= {4,5,4,5,4};
		int[] pass_limit= {6,4,5,8,5};
		int[] length= {200,250,160,150,255};
		int[] height= {200,250,260,240,255};
		int[] load_limit= {100,175,160,150,255};
		int index1, index2, index3, index4, index5, index6;
		for(int i = 0; i < 20; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			index3 = rand.nextInt(5); 
			index4 = rand.nextInt(5); 
			index5 = rand.nextInt(5); 
			index6 = rand.nextInt(5); 
			ad.addCar(plates[index1], lsc.get(index2), lso.get(index3), lsm.get(index4), lsa.get(index4), miles[index5], manufactur[index3], true, doors[index5], pass_limit[index6]);
		}
		
		for(int i = 0; i < 20; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			index3 = rand.nextInt(5); 
			index4 = rand.nextInt(5); 
			index5 = rand.nextInt(5); 
			index6 = rand.nextInt(5); 
			ad.addTruck(plates[index1], lsc.get(index2), lso.get(index3), lsm.get(index4),  lsa.get(index4), miles[index5], manufactur[index3],true, length[index6], height[index3], load_limit[index5]);
		}
	}
		
	public static void filler(){
		//dropDB();
		//createDB();
		//createVehicleTable();
		fillVehicleTable();
	}
}