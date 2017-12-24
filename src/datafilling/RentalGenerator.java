package datafilling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//written by a01349198 - IB

public class RentalGenerator {
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
	
	public static void createRentalsTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/imsedb","root","Imse1234");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE rents ( " +
															   "vehicle_ID int," +
															   "customer_ID int," +
															   "employee_number int,"+
															   "date_from date," +
															   "date_to date," +
															   "rating varchar(255)," +
															   "PRIMARY KEY (vehicle_ID, customer_ID, employee_number, date_from)," +
															   "CONSTRAINT check_date CHECK (date_from <= date_to)," + 
															   "FOREIGN KEY (vehicle_ID) REFERENCES vehicle (vehicle_ID)," +
															   "FOREIGN KEY (customer_ID) REFERENCES customer (customer_ID)," +
															   "FOREIGN KEY (employee_number) REFERENCES employee (employee_number)" +
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
		
	}
	
	public static void main (String [] args){
		//dropDB();
		//createDB();
		createRentalsTable();
		fillTable();
	}
}
