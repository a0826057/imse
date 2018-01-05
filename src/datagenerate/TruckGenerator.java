package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TruckGenerator {
	
	public static void createTruckTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE truck (" +
															   "truck_ID int REFERENCES vehicle,"+
															   "length int,"+
															   "height int,"+
															   "loading_limit int,"+
															   "PRIMARY KEY (truck_ID),"+
															   "CONSTRAINT check_truck CHECK (loading_limit > 0 AND length > 0 AND height > 0)"+
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
		createTruckTable();
	}
}