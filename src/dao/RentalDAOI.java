package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Costumer;
import model.Employee;
import model.Rental;
import model.Vehicle;

//written by a01349198 - IB

public class RentalDAOI implements RentalDAO {
	private List<Rental> rentals;
	
	@Override
	public List<Rental> getRentalList(){
		rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM rental");
			
			VehicleDAOI ad = new VehicleDAOI();
			CostumerDAOI col = new CostumerDAOI();
			EmployeeDAOI mod = new EmployeeDAOI();
			
			Vehicle v = ad.getVehicleById(result.getInt("vehicle_ID"));
			Costumer c = col.getCostumerById(result.getInt("costumer_ID"));
			Employee e = mod.getEmployeeById(result.getInt("employee_ID"));
			while(result.next()){
				Rental r = new Rental(v, c, e, result.getDate("date_from"), result.getDate("date_to"), result.getString("rating"));
				rentals.add(r);
			}
		
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		
		return rentals;
	}

	@Override
	public void addRental(Rental r){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			java.util.Date utilDateFrom = r.getDate_from();
			java.sql.Date sqlDateFrom = new java.sql.Date(utilDateFrom.getTime());
			java.util.Date utilDateTo = r.getDate_to();
			java.sql.Date sqlDateTo = new java.sql.Date(utilDateTo.getTime());
			statement.executeUpdate("INSERT INTO rental(vehicle_ID, costumer_ID, employee_ID, date_from, date_to, rating)" + " VALUES(" 
												+ r.getVehicle().getVehicle_ID() + "," + r.getCostumer().getCostumer_ID() + ","
												+ r.getEmployee().getEmployee_number() + ",'" + sqlDateFrom + "','" + sqlDateTo + "',"
												+ r.getRating() + ");");
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	@Override
	public void changeRental(int vid, int cid, int eid, Date date_from, String rating){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeUpdate("UPDATE rental SET rating = " + rating + " WHERE vehicle_ID = " + vid + " AND costumer_ID = "
																  + cid + " AND employee_ID = " + eid + " AND date_from = " + date_from + ";");
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	@Override
	public void deleteRental(int vid, int cid, int eid, Date date_from){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeQuery("DELETE * FROM rental WHERE vehicle_ID = " + vid + " AND costumer_ID = " + cid + 
								   " AND employee_ID = " + eid + " AND date_from = " + date_from + ";");
		
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
	
	@Override
	public int getRentalCount(){
		rentals = getRentalList();
		return rentals.size();
	}
	
	@Override
	public List<Rental> getRentalByCostumer(Costumer cos){
		rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM rental WHERE costumer_ID = " + cos.getCostumer_ID() + ";");
			
			VehicleDAOI ad = new VehicleDAOI();
			CostumerDAOI col = new CostumerDAOI();
			EmployeeDAOI mod = new EmployeeDAOI();
			Vehicle v;
			Costumer c;
			Employee e;
			while(result.next()){
				v = ad.getVehicleById(result.getInt("vehicle_ID"));
				c = col.getCostumerById(result.getInt("costumer_ID"));
				e = mod.getEmployeeById(result.getInt("employee_ID"));
				Rental r = new Rental(v, c, e, result.getDate("date_from"), result.getDate("date_to"), result.getString("rating"));
				rentals.add(r);
			}
		
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		
		return rentals;
	}
	
	@Override
	public List<Rental> getRentalByEmployee(Employee em){
		rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM rental WHERE employee_ID = " + em.getEmployee_number() + ";");
			
			VehicleDAOI ad = new VehicleDAOI();
			CostumerDAOI col = new CostumerDAOI();
			EmployeeDAOI mod = new EmployeeDAOI();
			Vehicle v;
			Costumer c;
			Employee ed;
			while(result.next()){
				v = ad.getVehicleById(result.getInt("vehicle_ID"));
				c = col.getCostumerById(result.getInt("costumer_ID"));
				ed = mod.getEmployeeById(result.getInt("employee_ID"));
				Rental r = new Rental(v, c, ed, result.getDate("date_from"), result.getDate("date_to"), result.getString("rating"));
				rentals.add(r);
			}
		
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		
		return rentals;
	}
	
	@Override
	public List<Rental> getRentalByVehicle(Vehicle veh){
		rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM rental WHERE vehicle_ID = " + veh.getVehicle_ID() + ";");
			
			VehicleDAOI ad = new VehicleDAOI();
			CostumerDAOI col = new CostumerDAOI();
			EmployeeDAOI mod = new EmployeeDAOI();
			Vehicle v;
			Costumer c;
			Employee ed;
			while(result.next()){
				v = ad.getVehicleById(result.getInt("vehicle_ID"));
				c = col.getCostumerById(result.getInt("costumer_ID"));
				ed = mod.getEmployeeById(result.getInt("employee_ID"));
				Rental r = new Rental(v, c, ed, result.getDate("date_from"), result.getDate("date_to"), result.getString("rating"));
				rentals.add(r);
			}
		
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		
		return rentals;
	}
	
	@Override
	public List<Rental> getRentalByDatePeriod(Date date_from, Date date_to){
		rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			ResultSet result = statement.executeQuery("SELECT * FROM rental WHERE date_from >= " + date_from + " AND date_to <= " + date_to + ";");
			
			VehicleDAOI ad = new VehicleDAOI();
			CostumerDAOI col = new CostumerDAOI();
			EmployeeDAOI mod = new EmployeeDAOI();
			Vehicle v;
			Costumer c;
			Employee ed;
			while(result.next()){
				v = ad.getVehicleById(result.getInt("vehicle_ID"));
				c = col.getCostumerById(result.getInt("costumer_ID"));
				ed = mod.getEmployeeById(result.getInt("employee_ID"));
				Rental r = new Rental(v, c, ed, result.getDate("date_from"), result.getDate("date_to"), result.getString("rating"));
				rentals.add(r);
			}
		
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}
		
		return rentals;
	}
}
