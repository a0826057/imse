package dao;

import model.Accessory;
import model.Car;
import model.Costumer;
import model.Employee;
import model.Rental;
import model.Truck;
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
		
		MongoCollection<Document> coll = database.getCollection("Customer");
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

	@Override
	public void addRental(Rental r){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("Customer");
		Document query = new Document("costumer_ID", Integer.toString(r.getCostumer().getCostumer_ID()));
		List<Document> cursor = coll.find(query).into(new ArrayList<Document>());
		
		for (Document obj : coll.find(query)) {
			boolean found = false;
			int costumer_ID = Integer.parseInt(obj.get("costumer_ID").toString());
			if(costumer_ID == r.getCostumer().getCostumer_ID()){	
				List<Document> rents = null;
				found = true;

				coll.findOneAndDelete(obj);
			
				if(obj.get("rental") != null){
					rents = (List<Document>) obj.get("rental");
				}else{
					rents = new ArrayList<Document>();
				}
					Document rent = new Document();
					Document veh = new Document();
					Document emp = new Document();
					rent.put("date_from", r.getDate_from().toString());
					rent.put("date_to", r.getDate_from().toString());
					
					veh.put("vehicle_id", r.getVehicle().getVehicle_ID());
					veh.put("license_plate_number", r.getVehicle().getLicense_plate_number());
					veh.put("mileage", r.getVehicle().getMileage());
					veh.put("manufacturer_year", r.getVehicle().getManufacture_year());

					Document col = new Document();
					col.put("color_id", r.getVehicle().getColor().getColor_ID());
					col.put("description", r.getVehicle().getColor().getDescription());
					col.put("manufacturer_color_code", r.getVehicle().getColor().getManufacturer_color_code());
					veh.put("color", col);
					Document mod = new Document();
					mod.put("model_id", r.getVehicle().getModel().getModel_ID());
					Document man = new Document();
					man.put("manufacturer_id", r.getVehicle().getManufactur().getManufacturer_ID());
					man.put("name", r.getVehicle().getManufactur().getName());
					man.put("country", r.getVehicle().getManufactur().getCountry());
					mod.put("price", r.getVehicle().getModel().getPrice());
					veh.put("model", mod);
					
					List<Document> accs = null;
					
					if(obj.get("accessory") != null){
						accs = (List<Document>) obj.get("accessory");
					}else{
						accs = new ArrayList<Document>();
					}
					
					Document accessory = null;
					
					for(Accessory ac : r.getVehicle().getAccessory()){
						accessory = new Document();
						accessory.put("accessory_id", ac.getAccessory_ID());
						accessory.put("name", ac.getName());
						accessory.put("description", ac.getDescription());
						accs.add(accessory);
					}
					
					
					if(r.getVehicle() instanceof Truck){
						Truck truck = (Truck)r.getVehicle();
						veh.put("type", "truck");
						veh.put("length", truck.getLenght());
						veh.put("height", truck.getHeight());
						veh.put("loading_limit", truck.getLoading_limit());
					}else if(r.getVehicle() instanceof Car){
						Car car = (Car)r.getVehicle();
						veh.put("type", "car");
						veh.put("doors", car.getDoors());
						veh.put("passenger_limit", car.getPassenger_limit());
					}
					rent.put("vehicle", veh);
					
					emp.put("first_name", r.getEmployee().getFirst_name());
					emp.put("last_name", r.getEmployee().getLast_name());
					rent.put("employee", emp);
					
					rent.put("rating", r.getRating());
					rents.add(rent);
					
				
					
					obj.remove("rental");
					obj.put("rental", rents);
				
					coll.insertOne(obj);
					System.out.println("DONE");
					break;
			}
			
			if(found)
				break;
			
		}
		
		if (mongoClient != null)
			mongoClient.close();
	}

	@Override
	public void changeRental(int vid, int cid, int eid, Date date_from, String rating){
	}

	

	public static void main (String [] args){
		RentalDAOM acc = new RentalDAOM();
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	 String dateInString = "2018-01-28";

	 Date date =  null;
	try {
		date = formatter.parse(dateInString);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		acc.deleteRental(46, 14, 0, date);
	}

	
	
	@Override
	public void deleteRental(int vid, int cid, int eid, Date date_from){
		
		rentals = new ArrayList<Rental>();
		CostumerDAOM cost = new CostumerDAOM();
		VehicleDAOM ve = new VehicleDAOM();
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("Customer");
		Document doc = new Document("costumer_ID", Integer.toString(cid));
		List<Document> cursor = coll.find(doc).into(new ArrayList<Document>());
		
		for (Document o : cursor) {
			Document obj = o;
				if(obj.get("rental") != null){
					List<Document> rents = (List<Document>) obj.get("rental");
					for (Document r : rents) {
						
						Document veh = (Document) r.get("vehicle");
						int id_veh = Integer.parseInt(veh.get("vehicle_id").toString());

						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date_from_r = null;
							try {
								date_from_r = sdf.parse(r.get("date_from").toString());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						if((id_veh == vid) &&  (date_from.equals(date_from_r))){
							
							coll.findOneAndDelete(obj);
							
							List<Document> help = (List<Document>) obj.get("rental");
							help.remove(r);
							obj.remove("rental");
							obj.put("rental", help);
							coll.insertOne(obj);
							
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

		return null;
	}

	@Override
	public List<Rental> getRentalByVehicle(Vehicle veh){
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
