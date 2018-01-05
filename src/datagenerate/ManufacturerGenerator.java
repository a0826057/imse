package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import dao.ManufacturerDAOI;

//written by a01349198 - IB

public class ManufacturerGenerator {
	
	public static void createManufacturerTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE manufacturer ( " +
															   "manufacturer_ID int AUTO_INCREMENT," +
															   "name varchar(120) NOT NULL," +
															   "country varchar(50)," +
															   "PRIMARY KEY (manufacturer_ID)" +
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
		ManufacturerDAOI ad = new ManufacturerDAOI();
		Random rand = new Random(); 
		
		String[] names = {"'BOW'","'Continentals'","'MDs'","'Helloween'","'NDS'"};
		String[] countries = {"'Bosnia'","'Croatia'","'Serbia'","'Slovenia'","'Kosovo'"};
		
		int index1, index2;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			ad.addManufacturer(names[index1], countries[index2]);
		}
	}
	
	public static void filler(){
		createManufacturerTable();
		fillTable();
	}
}
