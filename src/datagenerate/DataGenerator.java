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
	
	public static void wait(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		dropDB();
		createDB();
		
		System.out.println("Costumer");
		CostumerGenerator.filler();		
		wait(30000);

		System.out.println("Employee");
		EmployeeGenerator.filler();
		wait(30000);
		
		System.out.println("Accessory");
		AccessoryGenerator.filler();
		wait(30000);

		System.out.println("Color");
		ColorGenerator.filler();
		wait(30000);

		System.out.println("Manufacturer");
		ManufacturerGenerator.filler();
		wait(30000);

		System.out.println("Model");
		ModelGenerator.filler();
		wait(30000);

		System.out.println("Car");
		CarGenerator.filler();
		wait(30000);

		System.out.println("Truck");
		TruckGenerator.filler();
		//wait(180000);

		System.out.println("Vehicle");
		VehicleGenerator.filler();
		//wait(60000);
		//VehicleGenerator.filler2();
		AccessoryGenerator.fillAccessoryHasTable();
		wait(60000);

		System.out.println("Rental");
		RentalGenerator.filler();

	}
}
