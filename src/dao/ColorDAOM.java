package dao;

import model.Accessory;
import model.Color;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import jdk.nashorn.internal.ir.Flags;

//written by a01349198 - IB
//MongoDB
public class ColorDAOM implements ColorDAO{
	private List<Color> colors;
	private MongoClient client;
	
	@Override
	public List<Color> getColorList(){
		colors = new ArrayList<Color>();
		client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = client.getDatabase("imse"); 
		MongoCollection<Document> collection = database.getCollection("Color");
		System.out.println("before find");
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		System.out.println("after find");
		if(foundDocument==null) {
			System.out.println("it is empty");
		}
		Boolean flag=false;
			for (int i = 0; i < foundDocument.size(); i++) {
				System.out.println("inside the loop size");
				Document col = foundDocument.get(i);
				String description = (String) col.get("description");
				String color_code = (String) col.get("manufacturer_color_code");
				Color data = new Color(i,description,color_code);
				colors.add(data);
				flag=true;
			}
			
		if(flag!=false) {
			client.close();		
		} 
			return colors;
	}
	@Override
	public Color getColorById(int color_id){
		/*	Color co = null;
		try{
		
		client = new MongoClient("localhost", 27017);
		MongoDatabase database = client.getDatabase("imse");
		DBCollection collection = (DBCollection) database.getCollection("Color");
		BasicDBObject query = new BasicDBObject();
		String id= String.valueOf(color_id);
		query.put(id, 1);
		DBCursor cursor = collection.find(query);
		while (cursor.hasNext()) {
			colors.add((Color) cursor.next());
		}
		
		}catch(Exception e){
			System.err.println(e);
		}finally {
			
		}
		*/
		return null;
	}
	
	@Override
	public void addColor(String description, String manufacturer_color_code){
	
	
		client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse");
			MongoCollection<Document>  collection = database.getCollection("Color");
			Document documents = new Document();
			documents.put("desciption",description);
			documents.put("manufacturer_color_code", manufacturer_color_code);
			collection.insertOne(documents);
			client.close();
		
	}
	
	@Override
	public void changeColor(int color_ID, String description, String manufacturer_color_code){
				
			client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = client.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("Color");
			Bson filter = new Document("manufacturer_color_code", manufacturer_color_code);
			Bson newValue = new Document("description", description);
			Bson updateOperationDocument = new Document("$set", newValue);
			collection.updateOne(filter, updateOperationDocument);
			client.close();
				
	}
	
	@Override
	public void deleteColor(int color_id){
		/*Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			statement.executeQuery("DELETE * FROM color WHERE color_ID = " + color_id + ";");
		
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
	public int getColorCount(){
		colors = new ArrayList<Color>();
		client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = client.getDatabase("imse"); 
		MongoCollection<Document> collection = database.getCollection("Color");
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		if(foundDocument==null) {
			System.out.println("it is empty");
		}
		Boolean flag=false;
			for (int i = 0; i < foundDocument.size(); i++) {
				Document col = foundDocument.get(i);
				String description = (String) col.get("description");
				String color_code = (String) col.get("manufacturer_color_code");
				Color data = new Color(i,description,color_code);
				colors.add(data);
				flag=true;
			}
			
		if(flag!=false) {
			client.close();		
		} 
		
		return colors.size();
	}
	
	@Override
	public List<Color> getColorsByDescription(String description){
		colors = new ArrayList<Color>();
		client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = client.getDatabase("imse"); 
		MongoCollection<Document> collection = database.getCollection("Color");
		
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		
		if(foundDocument==null) {
			System.out.println("it is empty");
		}
		Boolean flag=false;
			for (int i = 0; i < foundDocument.size(); i++) {
				Document col = foundDocument.get(i);
				String descriptiond = (String) col.get("description");
				if(descriptiond.equals(description)) {
					String color_code = (String) col.get("manufacturer_color_code");
					Color data = new Color(i,descriptiond,color_code);
					colors.add(data);
					flag=true;
				}
				
			}
			
		if(flag!=false) {
			client.close();		
		} 
		return colors;
	}
}
