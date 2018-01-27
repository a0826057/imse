package datagenerate;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarGenerator {

	public static void createCarTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE car ( " +
															   "car_ID int REFERENCES vehicle,"+
															   "doors int,"+
															   "passenger_limit smallint DEFAULT 4,"+
															   "PRIMARY KEY (car_ID)"+
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
		createCarTable();
	}
}