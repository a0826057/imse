package datagenerate;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.CostumerDAOI;
import dao.EmployeeDAOI;
import dao.RentalDAOI;
import dao.VehicleDAOI;
import model.Costumer;
import model.Employee;
import model.Rental;
import model.Vehicle;

//written by a01349198 - IB

public class RentalGenerator {
	
	public static void createRentalsTable(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE rental ( " +
															   "vehicle_ID int," +
															   "costumer_ID int," +
															   "employee_ID int,"+
															   "date_from date," +
															   "date_to date," +
															   "rating varchar(255)," +
															   "PRIMARY KEY (vehicle_ID, costumer_ID, employee_ID, date_from)," +
															   "FOREIGN KEY (vehicle_ID) REFERENCES vehicle (vehicle_ID)," +
															   "FOREIGN KEY (costumer_ID) REFERENCES costumer (costumer_ID)," +
															   "FOREIGN KEY (employee_ID) REFERENCES employee (employee_number)" +
															   ");");
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
		RentalDAOI r = new RentalDAOI();
		
		VehicleDAOI ad = new VehicleDAOI();
		CostumerDAOI col = new CostumerDAOI();
		EmployeeDAOI mod = new EmployeeDAOI();
		
		List<Vehicle> lsa = ad.getVehicleList();
		List<Costumer> lso = col.getCostumerList();
		List<Employee> lsc = mod.getEmployeeList();
		
		Random rand = new Random(); 
		String[] ratings = {"Good","Bad","Worst","Ok","Perfect"};
		
		int index1, index2, index3, index4;
		for(int i = 0; i < 1001; i ++){
			index1 = rand.nextInt(lsa.size());
			index2 = rand.nextInt(lso.size()); 
			index3 = rand.nextInt(lsc.size());  
			index4 = rand.nextInt(5); 

			Rental rent = new Rental(lsa.get(index1), lso.get(index2), lsc.get(index3), new Date(), 
					  new Date(), ratings[index4]);

			r.addRental(rent);
		}
	}
	
	public static void filler(){
		createRentalsTable();
		fillTable();
	}
}
