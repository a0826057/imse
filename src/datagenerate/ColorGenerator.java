package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import dao.ColorDAOI;

//written by a01349198 - IB

public class ColorGenerator {
	
	public static void createColorTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE color (" +
															   "color_ID int AUTO_INCREMENT," +
															   "description varchar(255) NOT NULL, " +
															   "manufacturer_color_code	varchar(100)," +
															   "PRIMARY KEY (color_ID)" +
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
	
	private static void fillTable() {
		ColorDAOI ad = new ColorDAOI();
		Random rand = new Random(); 
		
		String[] descriptions = {"'red'","'blue'","'green'","'black'","'white'"};
		String[] colornumber = {"'39042'","'39040'","'39041'","'39001'","'39234'"};
		
		int index1, index2;
		for(int i = 0; i < 10; i ++){
			index1 = rand.nextInt(5);
			index2 = rand.nextInt(5); 
			String s = colornumber[index2];
			ad.addColor(descriptions[index1], s);
		}
	}
	
	public static void filler(){
		createColorTable();
		fillTable();
	}
}
