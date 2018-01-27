package dao;

import model.Accessory;
import model.Costumer;
import model.Employee;
import model.Rental;
import model.Vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		rentals = new ArrayList<Rental>();
		CostumerDAOM cost = new CostumerDAOM();
		VehicleDAOM ve = new VehicleDAOM();
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.Customer");
		List<Document> cursor = coll.find().into(new ArrayList<Document>());
		
		for (Document obj:cursor) {
			if(obj.get("rental") != null){
				int costumer_ID = Integer.parseInt(obj.get("costumer_ID").toString());
				Costumer costumer = cost.getCostumerById(costumer_ID);
				
				List<Document> rents = (List<Document>) obj.get("rental");
				for (Document r : rents) {
					Document veh = (Document) r.get("vehicle");
					int id_veh = Integer.parseInt(veh.get("vehicle_id").toString());
					Vehicle vehicle = ve.getVehicleById(id_veh);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date date_from = null;
					java.util.Date date_to = null;
					try {
						date_from = sdf.parse(r.get("date_from").toString());
						date_to = sdf.parse(r.get("date_to").toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
					String rat = r.get("rating").toString();
					//EMPLOYEE MISSING
					Rental re = new Rental(vehicle, costumer, null, date_from, date_to, rat);
					rentals.add(re);
				}
				
			}
			
		}
		
		if (mongoClient != null)
			mongoClient.close();
		

		return rentals;
	}
	

	public static void main (String [] args){
		RentalDAOM acc = new RentalDAOM();
		System.out.println(acc.getRentalList().size());
	}

	@Override
	public void addRental(Rental r){
		
		CostumerDAOM cost = new CostumerDAOM();
		VehicleDAOM ve = new VehicleDAOM();
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.Customer");
		List<Document> cursor = coll.find().into(new ArrayList<Document>());
		
		for (Document obj:cursor) {
			int costumer_ID = Integer.parseInt(obj.get("costumer_ID").toString());
			if(costumer_ID == r.getCostumer().getCostumer_ID()){
				Costumer costumer = cost.getCostumerById(costumer_ID);
				
				if(obj.get("rental") != null){
					
					List<Document> rents = (List<Document>) obj.get("rental");
					Document rent = new Document();
					
					
				}else{
					List<Document> rents = new ArrayList<Document>();
					Document rent = new Document();
				}
			}
		}
		
		if (mongoClient != null)
			mongoClient.close();
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
		
		rentals = new ArrayList<Rental>();
		CostumerDAOM cost = new CostumerDAOM();
		VehicleDAOM ve = new VehicleDAOM();
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.Customer");
		List<Document> cursor = coll.find().into(new ArrayList<Document>());
		
		for (Document obj:cursor) {
			int costumer_ID = Integer.parseInt(obj.get("costumer_ID").toString());
			
			if(costumer_ID == cid){
				
				if(obj.get("rental") != null){
					Costumer costumer = cost.getCostumerById(costumer_ID);
					
					List<Document> rents = (List<Document>) obj.get("rental");
					for (Document r : rents) {
						
						Document veh = (Document) r.get("vehicle");
						int id_veh = Integer.parseInt(veh.get("vehicle_id").toString());
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						java.util.Date date_from_r = null;
							try {
								date_from_r = sdf.parse(r.get("date_from").toString());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						if((id_veh == vid) &&  (date_from.equals(date_from_r))){
							
							//coll.findOneAndDelete(arg0);
							
						}
					}
				}
		
			}
			
		}
		
		if (mongoClient != null)
			mongoClient.close();
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
