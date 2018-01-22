package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class VehicleDAOM implements VehicleDAO{
	private List<Vehicle> vehicles;
	private MongoClient mongoClient;
	private MongoClient client;
	@Override
	public List<Vehicle> getVehicleList(){
		vehicles = new ArrayList<Vehicle>();
		ManufacturerDAOI man = new ManufacturerDAOI();
		AccessoryDAOI acc = new AccessoryDAOI();
		ColorDAOI col = new ColorDAOI();
		ModelDAOI mod = new ModelDAOI();
		Connection veh = null;
		try{
			vehicles = new ArrayList<Vehicle>();
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document cols = foundDocument.get(i);
					String license_plate = (String) cols.get("license_plate_number");
					System.out.println(license_plate);
					String type = (String) cols.get("type");
					String mileage = (String) cols.get("mileage");
					System.out.println(mileage);
					String manufacturer_year = (String) cols.get("manufacturer_year");
					System.out.println(manufacturer_year);
					//get color
					Document color = (Document) cols.get("color");
					String description = (String) color.get("description");
					System.out.println(description);
					String color_code = (String) color.get("manufacturer_color_code");
					System.out.println(color_code);
					//get model
					Document model = (Document) cols.get("model");
					//get manufacturer
					Document manufacturer = (Document) model.get("manufacturer");
					String mName = (String) manufacturer.get("name");
					System.out.println(mName);
					String country = (String) manufacturer.get("country");
					System.out.println(country);
					String price = (String) model.get("price");
					System.out.println(price);
					//get accessory					
					List<Document> documentAc = (List<Document>) cols.get("accessory");
					for (int k = 0; k < documentAc.size(); k++) {
						Document colc = documentAc.get(k);
						String aName = (String) colc.get("name");
						System.out.println(aName);
						String descriptionA = (String) colc.get("description");
						System.out.println(descriptionA);
					}
					if("car".equals(type)) {
						String doors = (String) cols.get("doors");
						System.out.println(doors);
						String passenger_limit = (String) cols.get("passenger_limit");
						System.out.println(passenger_limit);
					}else {
						String length = (String) cols.get("length");
						System.out.println(length);
						String height = (String) cols.get("height");
						System.out.println(height);
						String loading_limit = (String) cols.get("loading_limit");
						System.out.println(loading_limit);
					}
					
					
					
					
					
					//Manufacturer ma= man.getManufacturerById(result.getInt("manufacturer_ID"));
					//Model m = mod.getModelById(result.getInt("model_id"));
					//Vehicle vehic = new Vehicle(result.getInt("vehicle_ID"),result.getString("lisence_plate_number"),c, m,ma, a,result.getInt("mileage"),result.getInt("manufacturer_year"),result.getBoolean("active"));
					//vehicles.add(vehic);			
				}
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			mongoClient.close();
		}
		
		return vehicles;
	}
	
	public Vehicle getVehicleById(int vehicle_ID) {
		
		return null;
	}

	public List<Vehicle> getVehicleListByType(String type){
		Connection con = null;
		List<Vehicle> ls = new ArrayList<Vehicle> ();
		VehicleDAO veh = new VehicleDAOM();
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return ls;
	}
	
	public void addCar(String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
		
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	public void addTruck(String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	public void changeCar(int vehicle_ID,String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	public void changeTruck(int vehicle_ID,String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	public void deleteVehicle(int vehicle_id){
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	public int getVehicleCount(){
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
		
		return vehicles.size();
	}

	public int getVehicleCountByType(String type) {
		ArrayList<Vehicle> ls = new ArrayList<Vehicle>();
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return ls.size();
	}
	public List<Vehicle> getVehicleListByAge(int age){
		Connection con = null;
		Vehicle vehic = null;
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
		return vehicles;
		
	}
	public List<Vehicle> getVehicleByColor(Color color){
		vehicles = new ArrayList<Vehicle>();
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
		return vehicles;
	}
	public List<Vehicle> getVehicleByAccessory(Accessory accessory){
		vehicles = new ArrayList<Vehicle>();
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
		return vehicles;
	}
	public List<Vehicle> getVehicleByModel(Model mode){
		vehicles = new ArrayList<Vehicle>();
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
		return vehicles;
	}
	public List<Vehicle> getTruckByLoadingLimit(int limit){
		vehicles = new ArrayList<Vehicle>();
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return vehicles;		
	}
	public List<Car> getCarByDoors(int doors){
		List<Car> cars = new ArrayList<Car>();
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
		return cars;		
	}
	public List<Vehicle> getVehicleByManufacturer(Manufacturer manufacturer){
		vehicles = new ArrayList<Vehicle>();
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
		return vehicles;
	}

	public void addCar(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory,
			int mileage, int year, boolean active, int doors, int pass_limit) {
		// TODO Auto-generated method stub
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}

	public void addTruck(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory,
			int mileage, int year, boolean active, int length, int height, int load_limit) {
		try{
			//Add
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
	}	
}