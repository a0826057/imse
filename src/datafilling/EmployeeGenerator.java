package datafilling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import dao.EmployeeDAOI;
import dao.EmployeeDAO;
import imse.Employee;

public class EmployeeGenerator {

	public static void createDB() {
		Connection connection = null;
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
	
	
	public static void createCostumer() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb","root","MySQLrp");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String raw_query = "CREATE TABLE employee (employee_number			int AUTO_INCREMENT,"+
					  				"first_name				varchar(120) NOT NULL,"+
					  				"last_name				varchar(120) NOT NULL,"+
					  				"superior_ID			int,"+
					  				"active					boolean DEFAULT true,"+
					  				"PRIMARY KEY (employee_number),"+
					  				"FOREIGN KEY (superior_ID) REFERENCES employee (employee_number)"+
					  				");";
			statement.executeUpdate(raw_query);
		}catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
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
	
	public static void fillCostumer() {
		EmployeeDAO empl = new EmployeeDAOI();
		String[] firstname = {"'Franz'", "'Hans'", "'Sieglinde'", "'Manuela'", "'Karl'","'Anna'", "'Bernd'", "'Christian'", 
							  "'Diana'", "'Erich'", "'Fred'", "'Georg'", "'Hannah'", "'Ingrid'", "'Johann'", "'Kevin'", "'Lara'"};
	    String[] lastname = {"'Markart'", "'Pliger'", "'Stuffer'","'Mair'","'Sauermoser'","'Becker'", "'Gruber'", "'Baumgartner'", 
	    					 "'Huber'", "'Brunner'", "'Wagner'", "'Schmidt'", "'Pichler'", "'Auer'", "'Mueller'"};
	    
	    int fi = 0;
	    int li = 0;
	    
	    
	    for (int i = 0; i < 10; i++) {
			
			
			fi = (int)((Math.random()) * 17);
			li = (int)((Math.random()) * 15);
			
			
			Employee e = new Employee( firstname[fi], lastname[li], true);
			
			empl.addEmployee(e);
		}
	}
	
	public static void main(String[] args) {
		dropDB();
		createDB();
		createCostumer();
		fillCostumer();
		EmployeeDAO empl = new EmployeeDAOI();
		System.out.println(empl.getEmployeeList().toString());
	}

}