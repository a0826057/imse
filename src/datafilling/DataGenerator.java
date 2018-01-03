package datagenerate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import dao.CostumerDAOI;
import model.Costumer;

public class CostumerGenerator {
	
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
	
	public static void main(String[] args) {
		
		dropDB();
		createDB();
		
		AccessoryGenerator aG = new AccessoryGenerator;
		CarGenerator carG = new CarGenerator;
		ColorGenerator colG = new Colorgenerator;
		CostumerGenerator cosG = new CostumerGenerator;
		EmployeeGenerator eG = new EmployeeGenerator;
		ManufacturerGenerator maG = new ManufacturerGenerator;
		ModelGenerator moG = new ModelGenerator;
		RentalGenerator rG = new RentalGenerator;
		TruckGenerator tG = new TruckGenerator;
		VehicleGenerator vG = new VehicleGenerator;
		
		aG.filler();
		carG.filler();
		colG.filler();
		cosG.filler();
		eG.filler();
		maGfiller();
		moGfiller();
		rGfiller();
		tGfiller();
		vGfiller();
	}
}
