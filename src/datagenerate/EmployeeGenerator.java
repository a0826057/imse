package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import dao.EmployeeDAOI;
import dao.EmployeeDAO;
import model.Employee;

public class EmployeeGenerator {

	public static void createEmployee() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
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
	
	public static void fillEmployee() {
		EmployeeDAO empl = new EmployeeDAOI();
		String[] firstname = {"'Franz'", "'Hans'", "'Sieglinde'", "'Manuela'", "'Karl'","'Anna'", "'Bernd'", "'Christian'", 
							  "'Diana'", "'Erich'", "'Fred'", "'Georg'", "'Hannah'", "'Ingrid'", "'Johann'", "'Kevin'", "'Lara'"};
	    String[] lastname = {"'Markart'", "'Pliger'", "'Stuffer'","'Mair'","'Sauermoser'","'Becker'", "'Gruber'", "'Baumgartner'", 
	    					 "'Huber'", "'Brunner'", "'Wagner'", "'Schmidt'", "'Pichler'", "'Auer'", "'Mueller'"};
	    int numEmployees = 10;
	    
	    int fi = 0;
	    int li = 0;
	    
	    
	    for (int i = 0; i < numEmployees; i++) {
			
			
			fi = (int)((Math.random()) * 17);
			li = (int)((Math.random()) * 15);
			
			
			Employee e = new Employee( firstname[fi], lastname[li], true);
			
			empl.addEmployee(e);
		}

		/*for(int i = 0; i < numEmployees; i++) {

		}*/
	}
	
	public static void filler() {
		createEmployee();
		fillEmployee();
	}

}
