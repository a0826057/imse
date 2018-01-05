package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import dao.ManufacturerDAOI;
import dao.ModelDAOI;
import model.Manufacturer;

//written by a01349198 - IB

public class ModelGenerator {
		
	public static void createModelTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
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
		
		String[] descriptions = {"'X360'","'Panda'","'Polo'","'Golf'","'Karl'"};
		String[] prices = {"1044","5006","1004","5086","7042"};
		
		int index1, index2, index3;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			index3 = rand.nextInt(5); 
			ad.addModel(ls.get(index3), descriptions[index1], Double.parseDouble(prices[index2]));
		}
	}
	
	public static void filler(){
		createModelTable();
		fillTable();
	}
}
