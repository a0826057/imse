package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.CostumerDAOI;
import dao.EmployeeDAOI;
import dao.RentalDAOI;
import model.Costumer;
import model.Employee;
import model.Rental;

public class DataGenerator {
	
	public static void createDB() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/?useSSL=false&user=root&password=MySQLrp");
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/?useSSL=false&user=root&password=MySQLrp");
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
	
	public static void main(String[] args) {
		
		dropDB();
		createDB();
		
		System.out.println("Costumer");
		CostumerGenerator.filler();		
		
		System.out.println("Employee");
		EmployeeGenerator.filler();
		
		System.out.println("Accessory");
		AccessoryGenerator.filler();
		
		System.out.println("Color");
		ColorGenerator.filler();
		
		System.out.println("Manufacturer");
		ManufacturerGenerator.filler();
		
		System.out.println("Model");
		ModelGenerator.filler();
		
		System.out.println("Car");
		CarGenerator.filler();
		
		System.out.println("Truck");
		TruckGenerator.filler();
		
		System.out.println("Vehicle");
		VehicleGenerator.filler();
		
		System.out.println("Rental");
		RentalGenerator.filler();
		
		/*EmployeeDAOI cost = new EmployeeDAOI();
		Employee c = cost.getEmployeeById(10);
		System.out.println(c.getEmployee_number());
		RentalDAOI rent = new RentalDAOI();
		List<Rental> r = rent.getRentalByEmployee(c);
		System.out.print(r);*/
	}
}
