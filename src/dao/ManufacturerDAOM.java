package dao;


import model.Manufacturer;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//written by a01349198 - IB

public class ManufacturerDAOM implements ManufacturerDAO{
	private List<Manufacturer> manufacturers;
	private MongoClient mongoClient;
	private MongoClient client;
	@Override
	public List<Manufacturer> getManufacturerList(){
		try{
			manufacturers = new ArrayList<Manufacturer>();
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("manufacturer");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document col = foundDocument.get(i);
					String name = (String) col.get("name");
					String country = (String) col.get("country");
					Manufacturer data = new Manufacturer(i,name,country);
					manufacturers.add(data);
				}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
		return manufacturers;
	}
	
	@Override
	public Manufacturer getManufacturerById(int manufacturer_id){
		//manufacturer_ID not applicable for mongoDB
		return null;
	}
	
	@Override
	public void addManufacturer(String name, String country){
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse");
			MongoCollection<Document>  collection = database.getCollection("manufacturer");
			Document documents = new Document();
			documents.put("name",name);
			documents.put("country", country);
			collection.insertOne(documents);
			
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	@Override
	public void changeManufacturer(int manufacturer_ID, String name, String country){
		Bson filter;
		Bson newValue;
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("manufacturer");
			if(manufacturer_ID==1) {
				//if id==1 change by name
				 filter = new Document("name", name);
				 newValue = new Document("country", country);
				
			}else {
				//if id!=1 change by country
				 filter = new Document("country", country);
				 newValue = new Document("name", name);			
			}
			Bson updateOperationDocument = new Document("$set", newValue);
			collection.updateOne(filter, updateOperationDocument);
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	@Override
	public void deleteManufacturer(int manufacturer_ID){
		//manufacturer_ID not applicable for mongoDB
	}
	
		

	@Override
	public int getManufacturerCount(){
		try{
			manufacturers = new ArrayList<Manufacturer>();
			//db.runCommand({whatsmyuri : 1})
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("manufacturer");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
			System.out.println(foundDocument.size());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document col = foundDocument.get(i);
					String name = (String) col.get("name");
					String country = (String) col.get("country");
					Manufacturer data = new Manufacturer(i,name,country);
					manufacturers.add(data);
		
				}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return manufacturers.size();
	}
	
	@Override
	public Manufacturer getManufacturerByName(String name){
		Manufacturer ac = null;
		try{
			manufacturers = new ArrayList<Manufacturer>();
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("manufacturer");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
			
			for (int i = 0; i < foundDocument.size(); i++) {
				Document col = foundDocument.get(i);
				String names = (String) col.get("name");
				if(names.equals(name)) {
					String country = (String) col.get("country");
					ac = new Manufacturer(i,names,country);
				}
			}		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}	
			
		return ac;
	}
	
	public List<Manufacturer> getManufacturersByCountry(String country){
		manufacturers = new ArrayList<Manufacturer>();
		Manufacturer ac = null;
		try{
			manufacturers = new ArrayList<Manufacturer>();
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("manufacturer");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
			
			for (int i = 0; i < foundDocument.size(); i++) {
				Document col = foundDocument.get(i);
				String name = (String) col.get("name");
				String countries = (String) col.get("country");
				if(countries.equals(country)) {
					ac = new Manufacturer(i,name,country);
					manufacturers.add(ac);
				}
			}		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}	
			
		return manufacturers;
	}
}
