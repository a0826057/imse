package dao;

import model.Manufacturer;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
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
				String id_str = col.getString("manufacturer_id");
				int id = Integer.parseInt(id_str);
				String name = (String) col.get("name");
				String country = (String) col.get("country");
				Manufacturer data = new Manufacturer(id,name,country);
				manufacturers.add(data);
			}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			if(client != null)
				client.close();
		}
		
		return manufacturers;
	}

	@Override
	public Manufacturer getManufacturerById(int manufacturer_id){
		manufacturers = new ArrayList<Manufacturer>();
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("manufacturer");
		Document searchQuery = new Document("manufacturer_id", Integer.toString(manufacturer_id));
		Document result = null;
		
		for(Document ac : coll.find(searchQuery))
			result = ac;
		
		String id_str = (String) result.get("manufacturer_id");
		int id = Integer.parseInt(id_str);
		String name = (String) result.get("name");
		String country = (String) result.get("country");
		Manufacturer m = new Manufacturer(id,name,country);
		
		if (mongoClient != null)
			mongoClient.close();
		
		return m;
	}

	@Override
	public void addManufacturer(String name, String country){
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse");
			MongoCollection<Document>  collection = database.getCollection("manufacturer");
			List<Document> accs = collection.find().into(new ArrayList<Document>());
			
			int id = 1;
			if(accs.size() != 0){
				String id_string = (String)accs.get((accs.size()-1)).get("manufacturer_id");
				id = Integer.parseInt(id_string) + 1;
			}

			Document documents = new Document();
			documents.put("manufacturer_id", Integer.toString(id));
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
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("manufacturer");
			
			Document searchQuery = new Document("manufacturer_id", Integer.toString(manufacturer_ID));
			Document newValues = new Document();
			newValues.put("name", name);
			newValues.put("country", country);
			
			Document update = new Document("$set", newValues);
			collection.updateOne(searchQuery, update);
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}

	@Override
	public void deleteManufacturer(int manufacturer_ID){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("manufacturer");
		Document query = new Document("manufacturer_id", Integer.toString(manufacturer_ID));
		
		coll.findOneAndDelete(query);
			
		if (mongoClient != null)
			mongoClient.close();
	}
	
	@Override
	public int getManufacturerCount(){
		try{
			manufacturers = new ArrayList<Manufacturer>();
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("manufacturer");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
			
			for (int i = 0; i < foundDocument.size(); i++) {
				Document col = foundDocument.get(i);
				String id_str = col.getString("manufacturer_id");
				int id = Integer.parseInt(id_str);
				String name = (String) col.get("name");
				String country = (String) col.get("country");
				Manufacturer data = new Manufacturer(id,name,country);
				manufacturers.add(data);
			}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			if(client != null)
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
				String id_str = (String)col.getString("manufacturer_id");
				int id = Integer.parseInt(id_str);
				String names = (String) col.get("name");
				if(names.equals(name)) {
					String country = (String) col.get("country");
					ac = new Manufacturer(id,names,country);
				}
			}		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			if(client != null)
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
				String id_str = (String)col.getString("manufacturer_id");
				int id = Integer.parseInt(id_str);
				String name = (String) col.get("name");
				String countries = (String) col.get("country");
				if(countries.equals(country)) {
					ac = new Manufacturer(id,name,country);
					manufacturers.add(ac);
				}
			}		
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			if(client != null)
				client.close();
		}	
			
		return manufacturers;
	}
}
