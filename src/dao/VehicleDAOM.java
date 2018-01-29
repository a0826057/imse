package dao;

import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.Document;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class VehicleDAOM implements VehicleDAO{
	public List<Vehicle> vehicles;
	private MongoClient mongoClient;
	private MongoClient client;
	public String type;
	
	@Override
	public List<Vehicle> getVehicleList(){
		vehicles = new ArrayList<Vehicle>();
		List<Vehicle> ls = new ArrayList<Vehicle> ();
		//ManufacturerDAOM m = new ManufacturerDAOM();
		List<Accessory> acc = new ArrayList<Accessory>();
		try{
			vehicles = new ArrayList<Vehicle>();
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document cols = foundDocument.get(i);
					String id_str = (String) cols.get("vehicle_id");
					int id = Integer.parseInt(id_str);
					String license_plate = (String) cols.get("license_plate_number");
					String type = (String) cols.get("type");
					
					String mil = (String) cols.get("mileage");
					int mileage = Integer.parseInt(mil);
					
					String year = (String) cols.get("manufacturer_year");
					int manufacturer_year = Integer.parseInt(year);
					
					String activ = (String) cols.get("active");
					Boolean active = Boolean.parseBoolean(activ);
					
					//get color
					Document color = (Document) cols.get("color");
					String mId = (String) color.get("color_id");
					int cId = Integer.parseInt(mId);
					String description = (String) color.get("description");
					String color_code = (String) color.get("manufacturer_color_code");
					Color colD = new Color(cId, description, color_code);
				
					//get model
					Document model = (Document) cols.get("model");
					String idM = (String) model.get("model_id");
					int idModel = Integer.parseInt(idM);
					Document manu = (Document) model.get("manufacturer");
					
					//create manufacturer id
					String id_str1 = (String) manu.get("manufacturer_id");
					int id1 = Integer.parseInt(id_str1);

					String des = (String) model.get("description");
					String p = (String) model.get("price");
					double pr = Double.parseDouble(p); 
					
					//get manufacturer and model
					Document manufactu = (Document) model.get("manufacturer");
					String mName = (String) manufactu.get("name");
					String country = (String) manufactu.get("country");
					Manufacturer manufa = new Manufacturer(id1, mName, country);
					Model mo = new Model(id,manufa, des, pr);
					
					//get accessory
					List<Document> acFount = new ArrayList<Document>();
					acFount.addAll((Collection<? extends Document>) cols.get("accessory"));
					for (int k = 0; k < acFount.size(); k++) {
						Document colc = acFount.get(k);
						String a_id = (String) colc.get("accessory_id");
						int ida = Integer.parseInt(a_id);
						String aName = (String) colc.get("name");
						String descriptionA = (String) colc.get("description");
						Accessory a = new Accessory(ida, aName, descriptionA);
						acc.add(a);
					}
				
					if("car".equals(type)) {
						String doo = (String) cols.get("doors");
						int doors = Integer.parseInt(doo);
						String limit = (String) cols.get("passenger_limit");
						int passenger_limit = Integer.parseInt(limit);
						Car car = new Car(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, doors, passenger_limit);
						ls.add(car);
					}else {
						String len = (String) cols.get("length");
						int length = Integer.parseInt(len);
						String he = (String) cols.get("height");
						int height = Integer.parseInt(he);
						String loading = (String) cols.get("loading_limit");
						int loading_limit = Integer.parseInt(loading);
						Truck truck = new Truck(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, length, height, loading_limit);
						ls.add(truck);
					}
					
				}
				
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			mongoClient.close();
		}
		
		return ls;
	}
	
	public Vehicle getVehicleById(int vehicle_ID) {
		Vehicle vehicl = null;
		List<Accessory> acc = new ArrayList<Accessory>();
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			Document query = new Document("vehicle_id",Integer.toString(vehicle_ID));
			Document result = null;
			for(Document vehic : collection.find(query)) {
				result = vehic;
			}
			//vehicle id
			String id_str = (String) result.get("vehicle_id");
			int id = Integer.parseInt(id_str);
			String license_plate = (String) result.get("license_plate_number");
			String type = (String) result.get("type");
			
			String mil = (String) result.get("mileage");
			int mileage = Integer.parseInt(mil);
			
			String year = (String) result.get("manufacturer_year");
			int manufacturer_year = Integer.parseInt(year);
			
			String activ = (String) result.get("active");
			Boolean active = Boolean.parseBoolean(activ);
			
			//get color
			Document color = (Document) result.get("color");
			String mId = (String) color.get("color_id");
			int cId = Integer.parseInt(mId);
			String description = (String) color.get("description");
			String color_code = (String) color.get("manufacturer_color_code");
			Color colD = new Color(cId, description, color_code);
		
			//get model
			Document model = (Document) result.get("model");
			String idM = (String) model.get("model_id");
			int idModel = Integer.parseInt(idM);
			Document manu = (Document) model.get("manufacturer");
			
			//create manufacturer id
			String id_str1 = (String) manu.get("manufacturer_id");
			int id1 = Integer.parseInt(id_str1);

			String des = (String) model.get("description");
			String p = (String) model.get("price");
			double pr = Double.parseDouble(p); 
			
			Document manufactu = (Document) model.get("manufacturer");
			String mName = (String) manufactu.get("name");
			String country = (String) manufactu.get("country");
			Manufacturer manufa = new Manufacturer(id1, mName, country);
			Model mo = new Model(id,manufa, des, pr);
														
			//get accessory
			List<Document> acFount = new ArrayList<Document>();
			acFount.addAll((Collection<? extends Document>) result.get("accessory"));
			for (int k = 0; k < acFount.size(); k++) {
				Document colc = acFount.get(k);
				String a_id = (String) colc.get("accessory_id");
				int ida = Integer.parseInt(a_id);
				String aName = (String) colc.get("name");
				String descriptionA = (String) colc.get("description");
				Accessory a = new Accessory(ida, aName, descriptionA);
				acc.add(a);
			}
		
			vehicl = new Vehicle(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active);
			
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return vehicl;
	}

	public List<Vehicle> getVehicleListByType(String type){
		List<Vehicle> ls = new ArrayList<Vehicle> ();
		List<Accessory> acc = new ArrayList<Accessory>();
		try{
		client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = client.getDatabase("imse"); 
		MongoCollection<Document> collection = database.getCollection("vehicle");
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		for (int i = 0; i < foundDocument.size(); i++) {
			Document cols = foundDocument.get(i);
			String id_str = (String) cols.get("vehicle_id");
			int id = Integer.parseInt(id_str);
			String license_plate = (String) cols.get("license_plate_number");
			String type1 = (String) cols.get("type");
			
			String mil = (String) cols.get("mileage");
			int mileage = Integer.parseInt(mil);
			
			String year = (String) cols.get("manufacturer_year");
			int manufacturer_year = Integer.parseInt(year);
			
			String activ = (String) cols.get("active");
			Boolean active = Boolean.parseBoolean(activ);
			
			//get color
			Document color = (Document) cols.get("color");
			String mId = (String) color.get("color_id");
			int cId = Integer.parseInt(mId);
			String description = (String) color.get("description");
			String color_code = (String) color.get("manufacturer_color_code");
			Color colD = new Color(cId, description, color_code);
		
			//get model
			Document model = (Document) cols.get("model");
			String idM = (String) model.get("model_id");
			int idModel = Integer.parseInt(idM);
			Document manu = (Document) model.get("manufacturer");
			
			//create manufacturer id
			String id_str1 = (String) manu.get("manufacturer_id");
			int id1 = Integer.parseInt(id_str1);

			String des = (String) model.get("description");
			String p = (String) model.get("price");
			double pr = Double.parseDouble(p); 
			
			//get manufacturer and model
			Document manufactu = (Document) model.get("manufacturer");
			String mName = (String) manufactu.get("name");
			String country = (String) manufactu.get("country");
			Manufacturer manufa = new Manufacturer(id1, mName, country);
			Model mo = new Model(id,manufa, des, pr);
						
			//get accessory
			List<Document> acFount = new ArrayList<Document>();
			acFount.addAll((Collection<? extends Document>) cols.get("accessory"));
			for (int k = 0; k < acFount.size(); k++) {
				Document colc = acFount.get(k);
				String a_id = (String) colc.get("accessory_id");
				int ida = Integer.parseInt(a_id);
				String aName = (String) colc.get("name");
				String descriptionA = (String) colc.get("description");
				Accessory a = new Accessory(ida, aName, descriptionA);
				acc.add(a);
			}
				
			if(type1.equals(type)) {
				String doo = (String) cols.get("doors");
				int doors = Integer.parseInt(doo);
				String limit = (String) cols.get("passenger_limit");
				int passenger_limit = Integer.parseInt(limit);
				Car car = new Car(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, doors, passenger_limit);
				ls.add(car);
			}else {
				String len = (String) cols.get("length");
				int length = Integer.parseInt(len);
				String he = (String) cols.get("height");
				int height = Integer.parseInt(he);
				String loading = (String) cols.get("loading_limit");
				int loading_limit = Integer.parseInt(loading);
				Truck truck = new Truck(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, length, height, loading_limit);
				ls.add(truck);
			}
		}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return ls;
	}
	
	public void addCar(String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
			ColorDAOM cols = new ColorDAOM();
			ArrayList<Color> co = new ArrayList<Color>();
			ModelDAOM mods = new ModelDAOM();
			ManufacturerDAOM mans = new ManufacturerDAOM();
			AccessoryDAOM acce = new AccessoryDAOM();
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
			
			Document documents = new Document();
			Document mod = new Document();
			Document col = new Document();
			Document man = new Document();
			Document acc = new Document();
			String id_veh = (String)foundDocument.get((foundDocument.size()-1)).get("vehicle_id");
			int idv = Integer.parseInt(id_veh) + 1;
			documents.put("vehicle_id", Integer.toString(idv));
			documents.put("license_plate_number", plate);
			//color
			String mId = Integer.toString(cols.getColorById(color).getColor_ID());
			col.put("color_id", mId); 
			col.put("description", cols.getColorById(color).getDescription());
			col.put("manufacturer_color_code", cols.getColorById(color).getManufacturer_color_code());
			documents.put("color", col);
		
			//model
			String moId = Integer.toString(mods.getModelById(model).getModel_ID());
			mod.put("model_id", moId);
			//manufacturer
			String id_str1 =Integer.toString(mans.getManufacturerById(manufacturer).getManufacturer_ID());
			man.put("manufacturer_id", id_str1); 
			man.put("name",mans.getManufacturerById(manufacturer).getName());
			man.put("country",mans.getManufacturerById(manufacturer).getCountry());
			mod.put("manufacturer", man);
			String mop =Double.toString(mods.getModelById(model).getPrice());
			mod.put("price",mop);
			documents.put("model", mod);
			//accessory
			String ida =Integer.toString(acce.getAccessoryById(accessory).getAccessory_ID());
			acc.put("accessory_id", ida);
			acc.put("name",acce.getAccessoryById(accessory).getName());
			acc.put("name",acce.getAccessoryById(accessory).getDescription());
			documents.put("accessory", acc);
			String mil = Integer.toString(mileage);
			documents.put("mileage", mil);
			String myear = Integer.toString(year);
			documents.put("manufacturer_year", myear);
			documents.put("type", "car");
			String bool = Boolean.toString(active);
			documents.put("active", bool);
			String dd = Integer.toString(doors);
			documents.put("doors", dd);
			String pp = Integer.toString(pass_limit);
			documents.put("passenger_limit", pp);
			collection.insertOne(documents);			
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	public void addTruck(String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
		ColorDAOM cols = new ColorDAOM();
		ArrayList<Color> co = new ArrayList<Color>();
		ModelDAOM mods = new ModelDAOM();
		ManufacturerDAOM mans = new ManufacturerDAOM();
		AccessoryDAOM acce = new AccessoryDAOM();
	try{
		client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = client.getDatabase("imse"); 
		MongoCollection<Document> collection = database.getCollection("vehicle");
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		
		Document documents = new Document();
		Document mod = new Document();
		Document col = new Document();
		Document man = new Document();
		Document acc = new Document();
		String id_veh = (String)foundDocument.get((foundDocument.size()-1)).get("vehicle_id");
		int idv = Integer.parseInt(id_veh) + 1;
		documents.put("vehicle_id", Integer.toString(idv));
		documents.put("license_plate_number", plate);
		//color
		String mId = Integer.toString(cols.getColorById(color).getColor_ID());
		col.put("color_id", mId); 
		col.put("description", cols.getColorById(color).getDescription());
		col.put("manufacturer_color_code", cols.getColorById(color).getManufacturer_color_code());
		documents.put("color", col);
	
		//model
		String moId = Integer.toString(mods.getModelById(model).getModel_ID());
		mod.put("model_id", moId);
		//manufacturer
		String id_str1 =Integer.toString(mans.getManufacturerById(manufacturer).getManufacturer_ID());
		man.put("manufacturer_id", id_str1); 
		man.put("name",mans.getManufacturerById(manufacturer).getName());
		man.put("country",mans.getManufacturerById(manufacturer).getCountry());
		mod.put("manufacturer", man);
		String mop =Double.toString(mods.getModelById(model).getPrice());
		mod.put("price",mop);
		documents.put("model", mod);
		//accessory
		String ida =Integer.toString(acce.getAccessoryById(accessory).getAccessory_ID());
		acc.put("accessory_id", ida);
		acc.put("name",acce.getAccessoryById(accessory).getName());
		acc.put("name",acce.getAccessoryById(accessory).getDescription());
		documents.put("accessory", acc);
		String mil = Integer.toString(mileage);
		documents.put("mileage", mil);
		String myear = Integer.toString(year);
		documents.put("manufacturer_year", myear);
		documents.put("type", "truck");
		String bool = Boolean.toString(active);
		documents.put("active", bool);
		String len = Integer.toString(length);
		documents.put("length", len);
		String hei = Integer.toString(height);
		documents.put("height", hei);
		String loa = Integer.toString(load_limit);
		documents.put("loading_limit", loa);
		collection.insertOne(documents);			

	}catch (MongoException e){
	    System.out.println(e.getClass().getCanonicalName());
	}finally {
		client.close();
	}
	}
	
	public void changeCar(int vehicle_ID,String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int doors, int pass_limit){
		Document filter = new Document();
		Document newValue = new Document();
		Document update = new Document();
		ColorDAOM cols = new ColorDAOM();
		ArrayList<Color> co = new ArrayList<Color>();
		ModelDAOM mods = new ModelDAOM();
		ManufacturerDAOM mans = new ManufacturerDAOM();
		AccessoryDAOM acce = new AccessoryDAOM();
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");

			filter.append("vehicle_id", Integer.toString(vehicle_ID));
			Document mod = new Document();
			Document col = new Document();
			Document man = new Document();
			Document acc = new Document();
			newValue.put("license_plate_number", plate);
			//color
			String mId = Integer.toString(cols.getColorById(color).getColor_ID());
			col.put("color_id", mId); 
			col.put("description", cols.getColorById(color).getDescription());
			col.put("manufacturer_color_code", cols.getColorById(color).getManufacturer_color_code());
			newValue.put("color", col);
			//model
			String moId = Integer.toString(mods.getModelById(model).getModel_ID());
			mod.put("model_id", moId);
			//manufacturer
			String id_str1 =Integer.toString(mans.getManufacturerById(manufacturer).getManufacturer_ID());
			man.put("manufacturer_id", id_str1); 
			man.put("name",mans.getManufacturerById(manufacturer).getName());
			man.put("country",mans.getManufacturerById(manufacturer).getCountry());
			mod.put("manufacturer", man);
			String mop =Double.toString(mods.getModelById(model).getPrice());
			mod.put("price",mop);
			newValue.put("model", mod);
			//accessory
			String ida =Integer.toString(acce.getAccessoryById(accessory).getAccessory_ID());
			acc.put("accessory_id", ida);
			acc.put("name",acce.getAccessoryById(accessory).getName());
			acc.put("name",acce.getAccessoryById(accessory).getDescription());
			newValue.put("accessory", acc);
			String mil = Integer.toString(mileage);
			newValue.put("mileage", mil);
			String myear = Integer.toString(year);
			newValue.put("manufacturer_year", myear);
			newValue.put("type", "car");
			String bool = Boolean.toString(active);
			newValue.put("active", bool);
			String dd = Integer.toString(doors);
			newValue.put("doors", dd);
			String pp = Integer.toString(pass_limit);
			newValue.put("passenger_limit", pp);
			update.append("$set", newValue);
			collection.updateOne(filter, update);
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	public void changeTruck(int vehicle_ID,String plate, int color, int model, int manufacturer, int accessory, int mileage, int year, Boolean active, int length, int height, int load_limit){
		Document filter = new Document();
		Document newValue = new Document();
		Document update = new Document();
		ColorDAOM cols = new ColorDAOM();
		ArrayList<Color> co = new ArrayList<Color>();
		ModelDAOM mods = new ModelDAOM();
		ManufacturerDAOM mans = new ManufacturerDAOM();
		AccessoryDAOM acce = new AccessoryDAOM();
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");

			filter.append("vehicle_id", Integer.toString(vehicle_ID));
			Document mod = new Document();
			Document col = new Document();
			Document man = new Document();
			Document acc = new Document();
			newValue.put("license_plate_number", plate);
			//color
			String mId = Integer.toString(cols.getColorById(color).getColor_ID());
			col.put("color_id", mId); 
			col.put("description", cols.getColorById(color).getDescription());
			col.put("manufacturer_color_code", cols.getColorById(color).getManufacturer_color_code());
			newValue.put("color", col);
			//model
			String moId = Integer.toString(mods.getModelById(model).getModel_ID());
			mod.put("model_id", moId);
			//manufacturer
			String id_str1 =Integer.toString(mans.getManufacturerById(manufacturer).getManufacturer_ID());
			man.put("manufacturer_id", id_str1); 
			man.put("name",mans.getManufacturerById(manufacturer).getName());
			man.put("country",mans.getManufacturerById(manufacturer).getCountry());
			mod.put("manufacturer", man);
			String mop =Double.toString(mods.getModelById(model).getPrice());
			mod.put("price",mop);
			newValue.put("model", mod);
			//accessory
			String ida =Integer.toString(acce.getAccessoryById(accessory).getAccessory_ID());
			acc.put("accessory_id", ida);
			acc.put("name",acce.getAccessoryById(accessory).getName());
			acc.put("name",acce.getAccessoryById(accessory).getDescription());
			newValue.put("accessory", acc);
			String mil = Integer.toString(mileage);
			newValue.put("mileage", mil);
			String myear = Integer.toString(year);
			newValue.put("manufacturer_year", myear);
			newValue.put("type", "truck");
			String bool = Boolean.toString(active);
			newValue.put("active", bool);
			String len = Integer.toString(length);
			newValue.put("length", len);
			String hei = Integer.toString(height);
			newValue.put("height", hei);
			String loa = Integer.toString(load_limit);
			newValue.put("loading_limit", loa);
			update.append("$set", newValue);
			collection.updateOne(filter, update);
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	public void deleteVehicle(int vehicle_id){
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			Document query = new Document("vehicle_id", Integer.toString(vehicle_id));
			collection.findOneAndDelete(query);
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	public int getVehicleCount(){
		int count = 0;
		try{
		
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
			count = foundDocument.size();
				
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			mongoClient.close();
		}
		
		
		return count;
	}

	public List<Vehicle> getVehicleListByAge(int age){
		vehicles = new ArrayList<Vehicle>();
		List<Vehicle> ls = new ArrayList<Vehicle>();
		List<Accessory> acc = new ArrayList<Accessory>();
		try{
			vehicles = new ArrayList<Vehicle>();
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document cols = foundDocument.get(i);
					String id_str = (String) cols.get("vehicle_id");
					int id = Integer.parseInt(id_str);
					String license_plate = (String) cols.get("license_plate_number");
					String type = (String) cols.get("type");
					
					String mil = (String) cols.get("mileage");
					int mileage = Integer.parseInt(mil);
					
					String year = (String) cols.get("manufacturer_year");
					int manufacturer_year = Integer.parseInt(year);
					
					String activ = (String) cols.get("active");
					Boolean active = Boolean.parseBoolean(activ);
					
					//get color
					Document color = (Document) cols.get("color");
					String mId = (String) color.get("color_id");
					int cId = Integer.parseInt(mId);
					String description = (String) color.get("description");
					String color_code = (String) color.get("manufacturer_color_code");
					Color colD = new Color(cId, description, color_code);
				
					//get model
					Document model = (Document) cols.get("model");
					String idM = (String) model.get("model_id");
					int idModel = Integer.parseInt(idM);
					Document manu = (Document) model.get("manufacturer");
					
					//create manufacturer id
					String id_str1 = (String) manu.get("manufacturer_id");
					int id1 = Integer.parseInt(id_str1);

					String des = (String) model.get("description");
					String p = (String) model.get("price");
					double pr = Double.parseDouble(p); 
					
					//get manufacturer and model
					Document manufactu = (Document) model.get("manufacturer");
					String mName = (String) manufactu.get("name");
					String country = (String) manufactu.get("country");
					Manufacturer manufa = new Manufacturer(id1, mName, country);
					Model mo = new Model(id,manufa, des, pr);
								
					//get accessory
					List<Document> acFount = new ArrayList<Document>();
					acFount.addAll((Collection<? extends Document>) cols.get("accessory"));
					for (int k = 0; k < acFount.size(); k++) {
						Document colc = acFount.get(k);
						String a_id = (String) colc.get("accessory_id");
						int ida = Integer.parseInt(a_id);
						String aName = (String) colc.get("name");
						String descriptionA = (String) colc.get("description");
						Accessory a = new Accessory(ida, aName, descriptionA);
						acc.add(a);
					}
						
					if("car".equals(type)) {
						String doo = (String) cols.get("doors");
						int doors = Integer.parseInt(doo);
						String limit = (String) cols.get("passenger_limit");
						int passenger_limit = Integer.parseInt(limit);
						if(manufacturer_year == age) {
							Car car = new Car(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, doors, passenger_limit);
							ls.add(car);
						}
						
					}else {
						String len = (String) cols.get("length");
						int length = Integer.parseInt(len);
						String he = (String) cols.get("height");
						int height = Integer.parseInt(he);
						String loading = (String) cols.get("loading_limit");
						int loading_limit = Integer.parseInt(loading);
						if(manufacturer_year == age) {
							Truck truck = new Truck(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, length, height, loading_limit);
							ls.add(truck);
						}
					}
					
				}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return ls;
	}
	
	public List<Vehicle> getVehicleByModel(Model mode){
		List<Vehicle> ls = new ArrayList<Vehicle>();
		List<Accessory> acc = new ArrayList<Accessory>();
		int searchId = mode.getModel_ID();
		try{
			vehicles = new ArrayList<Vehicle>();
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document cols = foundDocument.get(i);
					String id_str = (String) cols.get("vehicle_id");
					int id = Integer.parseInt(id_str);
					String license_plate = (String) cols.get("license_plate_number");
					String type = (String) cols.get("type");
					
					String mil = (String) cols.get("mileage");
					int mileage = Integer.parseInt(mil);
					
					String year = (String) cols.get("manufacturer_year");
					int manufacturer_year = Integer.parseInt(year);
					
					String activ = (String) cols.get("active");
					Boolean active = Boolean.parseBoolean(activ);
					
					//get color
					Document colorss = (Document) cols.get("color");
					String mId = (String) colorss.get("color_id");
					int cId = Integer.parseInt(mId);
					String description = (String) colorss.get("description");
					String color_code = (String) colorss.get("manufacturer_color_code");
					Color colD = new Color(cId, description, color_code);
				
					//get model
					Document model = (Document) cols.get("model");
					String idM = (String) model.get("model_id");
					int idModel = Integer.parseInt(idM);
					Document manu = (Document) model.get("manufacturer");
					
					//create manufacturer id
					String id_str1 = (String) manu.get("manufacturer_id");
					int id1 = Integer.parseInt(id_str1);

					String des = (String) model.get("description");
					String p = (String) model.get("price");
					double pr = Double.parseDouble(p); 
					
					//get manufacturer and model
					Document manufactu = (Document) model.get("manufacturer");
					String mName = (String) manufactu.get("name");
					String country = (String) manufactu.get("country");
					Manufacturer manufa = new Manufacturer(id1, mName, country);
					Model mo = new Model(id,manufa, des, pr);
								
					//get accessory
					List<Document> acFount = new ArrayList<Document>();
					acFount.addAll((Collection<? extends Document>) cols.get("accessory"));
					for (int k = 0; k < acFount.size(); k++) {
						Document colc = acFount.get(k);
						String a_id = (String) colc.get("accessory_id");
						int ida = Integer.parseInt(a_id);
						String aName = (String) colc.get("name");
						String descriptionA = (String) colc.get("description");
						Accessory a = new Accessory(ida, aName, descriptionA);
						acc.add(a);
					}
						
					if("car".equals(type)) {
						String doo = (String) cols.get("doors");
						int doors = Integer.parseInt(doo);
						String limit = (String) cols.get("passenger_limit");
						int passenger_limit = Integer.parseInt(limit);
						if(idModel == searchId) {
							Car car = new Car(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, doors, passenger_limit);
							ls.add(car);
						}
						
					}else {
						String len = (String) cols.get("length");
						int length = Integer.parseInt(len);
						String he = (String) cols.get("height");
						int height = Integer.parseInt(he);
						String loading = (String) cols.get("loading_limit");
						int loading_limit = Integer.parseInt(loading);
						if(idModel == searchId) {
							Truck truck = new Truck(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, length, height, loading_limit);
							ls.add(truck);
						}
					}
					
				}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return ls;
	}
	
	public List<Vehicle> getTruckByLoadingLimit(int lim){
		List<Vehicle> ls = new ArrayList<Vehicle>();
		List<Accessory> acc = new ArrayList<Accessory>();
		int searchId = lim;
		try{
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document cols = foundDocument.get(i);
					String id_str = (String) cols.get("vehicle_id");
					int id = Integer.parseInt(id_str);
					String license_plate = (String) cols.get("license_plate_number");
					type = (String) cols.get("type");
					
					String mil = (String) cols.get("mileage");
					int mileage = Integer.parseInt(mil);
					
					String year = (String) cols.get("manufacturer_year");
					int manufacturer_year = Integer.parseInt(year);
					
					String activ = (String) cols.get("active");
					Boolean active = Boolean.parseBoolean(activ);
					
					//get color
					Document colorss = (Document) cols.get("color");
					String mId = (String) colorss.get("color_id");
					int cId = Integer.parseInt(mId);
					String description = (String) colorss.get("description");
					String color_code = (String) colorss.get("manufacturer_color_code");
					Color colD = new Color(cId, description, color_code);
				
					//get model
					Document model = (Document) cols.get("model");
					String idM = (String) model.get("model_id");
					int idModel = Integer.parseInt(idM);
					Document manu = (Document) model.get("manufacturer");
					
					//create manufacturer id
					String id_str1 = (String) manu.get("manufacturer_id");
					int id1 = Integer.parseInt(id_str1);

					String des = (String) model.get("description");
					String p = (String) model.get("price");
					double pr = Double.parseDouble(p); 
					
					//get manufacturer and model
					Document manufactu = (Document) model.get("manufacturer");
					String mName = (String) manufactu.get("name");
					String country = (String) manufactu.get("country");
					Manufacturer manufa = new Manufacturer(id1, mName, country);
					Model mo = new Model(id,manufa, des, pr);
								
					//get accessory
					List<Document> acFount = new ArrayList<Document>();
					acFount.addAll((Collection<? extends Document>) cols.get("accessory"));
					for (int k = 0; k < acFount.size(); k++) {
						Document colc = acFount.get(k);
						String a_id = (String) colc.get("accessory_id");
						int ida = Integer.parseInt(a_id);
						String aName = (String) colc.get("name");
						String descriptionA = (String) colc.get("description");
						Accessory a = new Accessory(ida, aName, descriptionA);
						acc.add(a);
					}
					//Just search truck	
					String len = (String) cols.get("length");
					int length = Integer.parseInt(len);
					String he = (String) cols.get("height");
					int height = Integer.parseInt(he);
					String loading = (String) cols.get("loading_limit");
					int loading_limit = Integer.parseInt(loading);
					if(loading_limit == searchId) {
						Truck truck = new Truck(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, length, height, loading_limit);
						ls.add(truck);
					}
				}
					
				
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return ls;
	}
		
	public List<Vehicle> getVehicleByManufacturer(Manufacturer manufacturer){
		List<Vehicle> ls = new ArrayList<Vehicle>();
		List<Accessory> acc = new ArrayList<Accessory>();
		Integer searchId = manufacturer.getManufacturer_ID();
		try{
			vehicles = new ArrayList<Vehicle>();
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document cols = foundDocument.get(i);
					String id_str = (String) cols.get("vehicle_id");
					int id = Integer.parseInt(id_str);
					String license_plate = (String) cols.get("license_plate_number");
					String type = (String) cols.get("type");
					
					String mil = (String) cols.get("mileage");
					int mileage = Integer.parseInt(mil);
					
					String year = (String) cols.get("manufacturer_year");
					int manufacturer_year = Integer.parseInt(year);
					
					String activ = (String) cols.get("active");
					Boolean active = Boolean.parseBoolean(activ);
					
					//get color
					Document color = (Document) cols.get("color");
					String mId = (String) color.get("color_id");
					int cId = Integer.parseInt(mId);
					String description = (String) color.get("description");
					String color_code = (String) color.get("manufacturer_color_code");
					Color colD = new Color(cId, description, color_code);
				
					//get model
					Document model = (Document) cols.get("model");
					String idM = (String) model.get("model_id");
					int idModel = Integer.parseInt(idM);
					Document manu = (Document) model.get("manufacturer");
					
					//create manufacturer id
					String id_str1 = (String) manu.get("manufacturer_id");
					int id1 = Integer.parseInt(id_str1);

					String des = (String) model.get("description");
					String p = (String) model.get("price");
					double pr = Double.parseDouble(p); 
					
					//get manufacturer and model
					Document manufactu = (Document) model.get("manufacturer");
					String mName = (String) manufactu.get("name");
					String country = (String) manufactu.get("country");
					Manufacturer manufa = new Manufacturer(id1, mName, country);
					Model mo = new Model(id,manufa, des, pr);
								
					//get accessory
					List<Document> acFount = new ArrayList<Document>();
					acFount.addAll((Collection<? extends Document>) cols.get("accessory"));
					for (int k = 0; k < acFount.size(); k++) {
						Document colc = acFount.get(k);
						String a_id = (String) colc.get("accessory_id");
						int ida = Integer.parseInt(a_id);
						String aName = (String) colc.get("name");
						String descriptionA = (String) colc.get("description");
						Accessory a = new Accessory(ida, aName, descriptionA);
						acc.add(a);
						if("car".equals(type)) {
							String doo = (String) cols.get("doors");
							int doors = Integer.parseInt(doo);
							String limit = (String) cols.get("passenger_limit");
							int passenger_limit = Integer.parseInt(limit);
							if(id1 == searchId) {
								Car car = new Car(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, doors, passenger_limit);
								ls.add(car);
							}
							
						}else {
							String len = (String) cols.get("length");
							int length = Integer.parseInt(len);
							String he = (String) cols.get("height");
							int height = Integer.parseInt(he);
							String loading = (String) cols.get("loading_limit");
							int loading_limit = Integer.parseInt(loading);
							if(id1 == searchId) {
								Truck truck = new Truck(id, license_plate, colD, mo, manufa, acc, mileage, manufacturer_year, active, length, height, loading_limit);
								ls.add(truck);
							}
						}
					}
									
				}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return ls;
	}

	public void addCar(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory,
			int mileage, int year, boolean active, int doors, int pass_limit) {
	
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
			
			Document documents = new Document();
			Document mod = new Document();
			Document col = new Document();
			Document man = new Document();
			Document acc = new Document();
			
			int idv = 1;
			if(foundDocument.size() != 0){
				String id_veh = (String)foundDocument.get((foundDocument.size()-1)).get("vehicle_id");
				idv = Integer.parseInt(id_veh) + 1;
			}
			
			documents.put("vehicle_id", Integer.toString(idv));
			documents.put("license_plate_number", plate);
			//color
			String mId = Integer.toString(color.getColor_ID());
			col.put("color_id", mId); 
			col.put("description", color.getDescription());
			col.put("manufacturer_color_code",color.getManufacturer_color_code());
			documents.put("color", col);
			//model
			String moId = Integer.toString(model.getModel_ID());
			mod.put("model_id", moId);
			//manufacturer
			String id_str1 =Integer.toString(manufacturer.getManufacturer_ID());
			man.put("manufacturer_id", id_str1); 
			man.put("name",manufacturer.getName());
			man.put("country",manufacturer.getCountry());
			mod.put("manufacturer", man);
			String mop =Double.toString(model.getPrice());
			mod.put("price",mop);
			documents.put("model", mod);
			//accessory
			String ida =Integer.toString(accessory.getAccessory_ID());
			acc.put("accessory_id", ida);
			acc.put("name",accessory.getName());
			acc.put("name",accessory.getDescription());
			documents.put("accessory", acc);
			String mil = Integer.toString(mileage);
			documents.put("mileage", mil);
			String myear = Integer.toString(year);
			documents.put("manufacturer_year", myear);
			documents.put("type", "car");
			String bool = Boolean.toString(active);
			documents.put("active", bool);
			String dd = Integer.toString(doors);
			documents.put("doors", dd);
			String pp = Integer.toString(pass_limit);
			documents.put("passenger_limit", pp);
			collection.insertOne(documents);			
		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}

	public void addTruck(String plate, Color color, Model model, Manufacturer manufacturer, Accessory accessory,
			int mileage, int year, boolean active, int length, int height, int load_limit){ 
		try{
			
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("vehicle");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
			Document documents = new Document();
			Document mod = new Document();
			Document col = new Document();
			Document man = new Document();
			Document acc = new Document();
			
			int idv = 1;
			if(foundDocument.size() != 0){
				String id_veh = (String)foundDocument.get((foundDocument.size()-1)).get("vehicle_id");
				idv = Integer.parseInt(id_veh) + 1;
			}
			
			documents.put("vehicle_id", Integer.toString(idv));
			documents.put("license_plate_number", plate);
			//color
			String mId = Integer.toString(color.getColor_ID());
			col.put("color_id", mId); 
			col.put("description", color.getDescription());
			col.put("manufacturer_color_code",color.getManufacturer_color_code());
			documents.put("color", col);
			//model
			String moId = Integer.toString(model.getModel_ID());
			mod.put("model_id", moId);
			//manufacturer
			String id_str1 =Integer.toString(manufacturer.getManufacturer_ID());
			man.put("manufacturer_id", id_str1); 
			man.put("name",manufacturer.getName());
			man.put("country",manufacturer.getCountry());
			mod.put("manufacturer", man);
			String mop =Double.toString(model.getPrice());
			mod.put("price",mop);
			documents.put("model", mod);
			//accessory
			String ida =Integer.toString(accessory.getAccessory_ID());
			acc.put("accessory_id", ida);
			acc.put("name",accessory.getName());
			acc.put("name",accessory.getDescription());
			documents.put("accessory", acc);
			String mil = Integer.toString(mileage);
			documents.put("mileage", mil);
			String myear = Integer.toString(year);
			documents.put("manufacturer_year", myear);
			documents.put("type", "truck");
			String bool = Boolean.toString(active);
			documents.put("active", bool);
			String len = Integer.toString(length);
			documents.put("length", len);
			String hei = Integer.toString(height);
			documents.put("height", hei);
			String loa = Integer.toString(load_limit);
			documents.put("loading_limit", loa);
			collection.insertOne(documents);			
	}catch (MongoException e){
	    System.out.println(e.getClass().getCanonicalName());
	}finally {
		client.close();	
	}	
}
	
	public List<Vehicle> getVehicleList(int color, int model, int manufacturer, int accessory){ return null;}

	@Override
	public List<Vehicle> searchVehicle(int color, int model, int manufacturer, int accessory) {
		// TODO Auto-generated method stub
		return null;
	}
}