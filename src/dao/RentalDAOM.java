package dao;

import model.Accessory;
import model.Costumer;
import model.Employee;
import model.Rental;
import model.Vehicle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//written by a01349198 - IB

//FUNCTIONS NOT DATE AS THEY ARE DEPENDING ON CUSTOMER FROM OTHER TEAMMATE!!!
public class RentalDAOM implements RentalDAO {
	private List<Rental> rentals;

	@Override
	public List<Rental> getRentalList(){
	/*	rentals = new ArrayList<Rental>();
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.customer");
		List<Document> access = coll.find().into(new ArrayList<Document>());
		
		for (int i = 0; i < access.size(); i++) {
			Document ac = access.get(i);
			List<Rental> rentals = (List<Rental>) ac.get("rental");
			String description = (String) ac.get("description");
			Accessory a = new Accessory(i,name,description);
			accessories.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		

			VehicleDAOI ad = new VehicleDAOI();
			CostumerDAOI col = new CostumerDAOI();
			EmployeeDAOI mod = new EmployeeDAOI();

			while(result.next()){
				Vehicle v = ad.getVehicleById(result.getInt("vehicle_ID"));
				Costumer c = col.getCostumerById(result.getInt("costumer_ID"));
				Employee e = mod.getEmployeeById(result.getInt("employee_ID"));
				Rental r = new Rental(v, c, e, result.getDate("date_from"), result.getDate("date_to"), result.getString("rating"));
				rentals.add(r);
			}*/

		return null;
	}
	

	public static void main (String [] args){
	//	AccessoryDAOM acc = new AccessoryDAOM();
		//acc.getAccessoriesByName("Paint");
	}

	@Override
	public void addRental(Rental r){
	/*	Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "INSERT INTO rental(vehicle_ID, costumer_ID, employee_ID, date_from, date_to, rating) VALUES(?,?,?,?,?,?);";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			Date utilDateFrom = r.getDate_from();
			java.sql.Date sqlDateFrom = new java.sql.Date(utilDateFrom.getTime());
			Date utilDateTo = r.getDate_to();
			java.sql.Date sqlDateTo = new java.sql.Date(utilDateTo.getTime());
			prepared.setInt(1, r.getVehicle().getVehicle_ID());
			prepared.setInt(2, r.getCostumer().getCostumer_ID());
			prepared.setInt(3, r.getEmployee().getEmployee_number());
			prepared.setDate(4, sqlDateFrom);
			prepared.setDate(5, sqlDateTo);
			prepared.setString(6, r.getRating());
			prepared.executeUpdate();
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}*/
	}

	@Override
	public void changeRental(int vid, int cid, int eid, Date date_from, String rating){
		/*Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "UPDATE rental SET rating = ? WHERE vehicle_ID = ? AND costumer_ID = ? AND employee_ID = ? AND date_from = ?";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setString(1, rating);
			prepared.setInt(2, vid);
			prepared.setInt(3, cid);
			prepared.setInt(4,eid);
			Date utilDateTo = date_from;
			java.sql.Date sqlDateTo = new java.sql.Date(utilDateTo.getTime());
			prepared.setDate(5, sqlDateTo);

			prepared.executeUpdate();
		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}*/
	}

	@Override
	public void deleteRental(int vid, int cid, int eid, Date date_from){
	/*	Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "DELETE FROM rental WHERE vehicle_ID = ? AND costumer_ID = ? AND employee_ID = ? AND date_from = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, vid);
			prepared.setInt(2, cid);
			prepared.setInt(3,eid);
			Date utilDateTo = date_from;
			java.sql.Date sqlDateTo = new java.sql.Date(utilDateTo.getTime());
			prepared.setDate(4, sqlDateTo);

			prepared.executeUpdate();

		}catch(Exception e){
			System.err.println(e);
		}finally {
			try {
				if (con != null)
					con.close();
			}catch (SQLException e) {
				System.err.println(e);
			}
		}*/
	}

	@Override
	public int getRentalCount(){
		rentals = getRentalList();
		return rentals.size();
	}

	@Override
	public List<Rental> getRentalByCostumer(Costumer cos){
		/*rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM rental WHERE costumer_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, cos.getCostumer_ID());
			ResultSet result = prepared.executeQuery();

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
		}*/

		return rentals;
	}

	@Override
	public List<Rental> getRentalByEmployee(Employee em){
	/*	rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM rental WHERE employee_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, em.getEmployee_number());
			ResultSet result = prepared.executeQuery();

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
		}*/

		return null;
	}

	@Override
	public List<Rental> getRentalByVehicle(Vehicle veh){
	/*	rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM rental WHERE vehicle_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, veh.getVehicle_ID());
			ResultSet result = prepared.executeQuery();

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
		}*/

		return rentals;
	}

	@Override
	public List<Rental> getRentalByDatePeriod(Date date_from, Date date_to){
		/*rentals = new ArrayList<Rental>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM rental WHERE date_from >= ? AND date_to <= ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			Date utilDateTo = date_to;
			java.sql.Date sqlDateTo = new java.sql.Date(utilDateTo.getTime());
			Date utilDateFrom = date_from;
			java.sql.Date sqlDateFrom = new java.sql.Date(utilDateFrom.getTime());
			prepared.setDate(1, sqlDateFrom);
			prepared.setDate(2, sqlDateTo);
			
			ResultSet result = prepared.executeQuery();
			
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
		}*/
		
		return null;
	}
}
