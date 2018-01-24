package dao;


import model.Color;


import java.util.ArrayList;

import java.util.List;


import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;




//MongoDB
public class ColorDAOM implements ColorDAO{
	private List<Color> colors;
	private MongoClient client;
	
	@Override
	public List<Color> getColorList(){
		try{
			colors = new ArrayList<Color>();
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("color");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document col = foundDocument.get(i);
					String idMongo = (String) col.get("color_id");
					int id = Integer.valueOf(idMongo);
					String description = (String) col.get("description");
					String color_code = (String) col.get("manufacturer_color_code");
					Color data = new Color(id,description,color_code);
					colors.add(data);
				}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
			return colors;
	}
	
	@Override
	public Color getColorById(int color_id){
		Color colorId = null;
		try{
			colors = new ArrayList<Color>();
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("color");
			Document query = new Document("color_id",Integer.toString(color_id));
			Document result = null;
			for(Document color : collection.find(query)) {
				result = color;
			}
			String idMongo = (String) result.get("color_id");
			int id = Integer.valueOf(idMongo);
			String description = (String) result.get("description");
			String color_code = (String) result.get("manufacturer_color_code");	
			colorId = new Color(id, description, color_code);
			
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return colorId;
	
	}
	
	@Override
	public void addColor(String description, String manufacturer_color_code){
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse");
			MongoCollection<Document> collection = database.getCollection("color");
			List<Document> col = collection.find().into(new ArrayList<Document>());
			String id_string = (String)col.get((col.size()-1)).get("color_id");
			int id = Integer.parseInt(id_string) + 1;
			
			Document documents = new Document();
			documents.put("color_id", Integer.toString(id));
			documents.put("desciption",description);
			documents.put("manufacturer_color_code", manufacturer_color_code);
			collection.insertOne(documents);
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		
	}
	
	@Override
	public void changeColor(int color_ID, String description, String manufacturer_color_code){
		Bson filter ;
		Bson newValue;
		try{
			
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("color");
			filter = new Document("color_id", Integer.toString(color_ID));
			newValue = new Document("description", description);
			//newValue = new Document("manufacturer_color_code", manufacturer_color_code);
			Bson update = new Document("$set", newValue);
			collection.updateOne(filter, update);
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	@Override
	public void deleteColor(int color_id){
		try{		
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("color");
			Document query = new Document("color_id", Integer.toString(color_id));
			collection.findOneAndDelete(query);
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	@Override
	public int getColorCount(){
		try{
			colors = new ArrayList<Color>();
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("color");
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document col = foundDocument.get(i);
					String id_str = (String) col.get("color_id");
					int id = Integer.parseInt(id_str);
					String description = (String) col.get("description");
					String color_code = (String) col.get("manufacturer_color_code");
					Color data = new Color(id,description,color_code);
					colors.add(data);
				}
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}		
		return colors.size();
	}
	
	@Override
	public List<Color> getColorsByDescription(String description){
		colors = new ArrayList<Color>();
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("color");
			Document query = new Document("description",description);
			List<Document> foundDocument = collection.find(query).into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document col = foundDocument.get(i);
					String id_str = (String) col.get("color_id");
					int id = Integer.parseInt(id_str);
					String descriptiond = (String) col.get("description");
					String color_code = (String) col.get("manufacturer_color_code");
					Color data = new Color(id,descriptiond,color_code);
					colors.add(data);
				}
			
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return colors;
	}
}
