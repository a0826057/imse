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



//written by a01349198 - IB
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
					String description = (String) col.get("description");
					String color_code = (String) col.get("manufacturer_color_code");
					Color data = new Color(i,description,color_code);
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
		//manufacturer_ID not applicable for mongoDB
		return null;
	}
	
	@Override
	public void addColor(String description, String manufacturer_color_code){
		try{
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse");
			MongoCollection<Document>  collection = database.getCollection("color");
			Document documents = new Document();
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
		Bson filter;
		Bson newValue;
		try{		
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("color");
			filter = new Document("manufacturer_color_code", manufacturer_color_code);
			newValue = new Document("description", description);
			Bson updateOperationDocument = new Document("$set", newValue);
			collection.updateOne(filter, updateOperationDocument);
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
	}
	
	@Override
	public void deleteColor(int color_id){
		//color_ID not applicable for mongoDB
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
					String description = (String) col.get("description");
					String color_code = (String) col.get("manufacturer_color_code");
					Color data = new Color(i,description,color_code);
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
			List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
				for (int i = 0; i < foundDocument.size(); i++) {
					Document col = foundDocument.get(i);
					String descriptiond = (String) col.get("description");
					if(descriptiond.equals(description)) {
						String color_code = (String) col.get("manufacturer_color_code");
						Color data = new Color(i,descriptiond,color_code);
						colors.add(data);
						
					}
				}
			
		}catch (MongoException e){
		    System.out.println(e.getClass().getCanonicalName());
		}finally {
			client.close();
		}
		return colors;
	}
}
