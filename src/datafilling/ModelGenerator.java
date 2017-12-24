package datafilling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import dao.ManufacturerDAOI;
import dao.ModelDAOI;
import imse.Manufacturer;

//written by a01349198 - IB

public class ModelGenerator {
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
	
	public static void createModelTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE model ( " +
															   "model_ID int auto_increment, " +
															   "manufacturer_ID int," +
															   "description	varchar(255) NOT NULL, " +
															   "price decimal(10,2) DEFAULT 0, " +
															   "PRIMARY KEY (model_ID, manufacturer_ID),"+
															   "FOREIGN KEY (manufacturer_ID) REFERENCES manufacturer (manufacturer_ID)," +
															   "CONSTRAINT check_price CHECK (price > 0)"+
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
		ModelDAOI ad = new ModelDAOI();
		ManufacturerDAOI m = new ManufacturerDAOI();
		List<Manufacturer> ls = m.getManufacturerList();
		Random rand = new Random(); 
		
		String[] descriptions = {"'Very nice and comfy'","'Not so great'","'Wooohoo'","'Cool thing'","'Not so bad'"};
		String[] prices = {"1.44","5.6","1.44","5.86","7.42"};
		
		int index1, index2, index3;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			index3 = rand.nextInt(5); 
			ad.addModel(ls.get(index3), descriptions[index1], Double.parseDouble(prices[index2]));
		}
	}
	
	public static void main (String [] args){
		//dropDB();
		//createDB();
		createModelTable();
		fillTable();
	}
}
