package dao;

import model.Manufacturer;
import model.Model;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//written by a01349198 - IB

public class ModelDAOM implements ModelDAO {
	
	ManufacturerDAOI man = new ManufacturerDAOI();
	private List<Model> models;
	
	@Override
	public List<Model> getModelList(){
		models = new ArrayList<Model>();
		ManufacturerDAOM m = new ManufacturerDAOM();
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("model");
		List<Document> mod = coll.find().into(new ArrayList<Document>());
		
		for (int i = 0; i < mod.size(); i++) {
			Document ac = mod.get(i);
			String id_str = (String) ac.get("model_id");
			int id = Integer.parseInt(id_str);
			
			Document db = (Document) ac.get("manufacturer");
			String id_str1 = (String) db.get("manufacturer_id");
			int id1 = Integer.parseInt(id_str1);
			Manufacturer manufacturer = m.getManufacturerById(id1);
			
			String description = (String) ac.get("description");
			String p = (String) ac.get("price");
			double pr = Double.parseDouble(p); 
			Model a = new Model(id, manufacturer, description, pr);
			models.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		
		return models;
	}
	
	@Override
	public Model getModelById(int model_id){
		ManufacturerDAOM m = new ManufacturerDAOM();
		Model model = null;
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("model");
		Document query = new Document("model_id",Integer.toString(model_id));
		Document result = null;
		
		for(Document ac : coll.find(query))
			result = ac;
		
		String id_str = (String) result.get("model_id");
		int id = Integer.parseInt(id_str);
		Document db = (Document) result.get("manufacturer");	
		String id_str1 = (String) db.get("manufacturer_id");
		int id1 = Integer.parseInt(id_str1);
		Manufacturer manufacturer = m.getManufacturerById(id1);
		String description = (String) result.get("description");
		String price_string = (String)result.get("price");
		double pr = Double.parseDouble(price_string);
	
		model = new Model(id, manufacturer, description, pr);
		
		if (mongoClient != null)
			mongoClient.close();
	
		return model;
	}
	
	@Override
	public void addModel(Manufacturer man, String description, double price){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("model");
		List<Document> mod = coll.find().into(new ArrayList<Document>());
		
		int id = 1;
		if(mod.size() != 0){
			String id_string = (String)mod.get((mod.size()-1)).get("model_id");
			id = Integer.parseInt(id_string) + 1;
		}
	
		Document doc = new Document();
		doc.put("model_id", Integer.toString(id));
		Document subdoc = new Document();
		subdoc.put("manufacturer_id", Integer.toString(man.getManufacturer_ID()));
		subdoc.put("name", man.getName());
		subdoc.put("country", man.getCountry());
		doc.append("manufacturer", subdoc);
		doc.put("description", description);
		String pr = Double.toString(price);
		doc.put("price", pr );

		coll.insertOne(doc);
		
		if (mongoClient != null)
			mongoClient.close();

	}

	@Override
	public void changeModel(int model_ID, Manufacturer man, String description, double price){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("model");
		Document searchQuery = new Document("model_id", Integer.toString(model_ID));
		Document newValues = new Document();
		Document subdoc = new Document();
		subdoc.put("manufacturer_id", Integer.toString(man.getManufacturer_ID()));
		subdoc.put("name", man.getName());
		subdoc.put("country", man.getCountry());
		newValues.put("manufacturer", subdoc);
		newValues.put("description", description);
		newValues.put("price", Double.toString(price));
		
		Document update = new Document("$set", newValues);
		coll.updateOne(searchQuery, update);
		
		if (mongoClient != null)
			mongoClient.close();
	}
	
	@Override
	public void deleteModel(int model_id){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("model");
		Document query = new Document("model_id", Integer.toString(model_id));
		
		coll.findOneAndDelete(query);
			
		if (mongoClient != null)
			mongoClient.close();
	}
	
	@Override
	public int getModelCount(){
		models = new ArrayList<Model>();
		ManufacturerDAOM m = new ManufacturerDAOM();
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("model");
		List<Document> mod = coll.find().into(new ArrayList<Document>());
		
		for (int i = 0; i < mod.size(); i++) {
			Document ac = mod.get(i);
			String id_str = (String) ac.get("model_id");
			int id = Integer.parseInt(id_str);
			
			Document db = (Document) ac.get("manufacturer");
			String id_str1 = (String) db.get("manufacturer_id");
			int id1 = Integer.parseInt(id_str1);
			Manufacturer manufacturer = m.getManufacturerById(id1);
			
			String description = (String) ac.get("description");
			String p = (String) ac.get("price");
			double pr = Double.parseDouble(p); 
			Model a = new Model(id, manufacturer, description, pr);
			models.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		
		return models.size();
	}	
	
	@Override
	public List<Model> getModelsByManufacturersName(String name){
		models = new ArrayList<Model>();
		ManufacturerDAOM m = new ManufacturerDAOM();
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		MongoCollection<Document> coll = database.getCollection("model");
		
		Document query = new Document("manufacturer.name",name);
		List<Document> mod = coll.find(query).into(new ArrayList<Document>());
		
		for (int i = 0; i < mod.size(); i++) {
			Document ac = mod.get(i);
			String id_str = (String) ac.get("model_id");
			int id = Integer.parseInt(id_str);
			
			Document db = (Document) ac.get("manufacturer");
			String id_str1 = (String) db.get("manufacturer_id");
			int id1 = Integer.parseInt(id_str1);
			Manufacturer manufacturer = m.getManufacturerById(id1);
			String description = (String) ac.get("description");
			String p = (String) ac.get("price");
			double pr = Double.parseDouble(p); 
			Model a = new Model(id, manufacturer, description, pr);
			models.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		
		return models;
	}
}
