package dao;

import model.Color;
import model.Manufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//written by a01349198 - IB

public class ManufacturerDAOM implements ManufacturerDAO{
	private List<Manufacturer> manufacturers;
	private MongoClient client;
	@Override
	public List<Manufacturer> getManufacturerList(){
		manufacturers = new ArrayList<Manufacturer>();
		client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = client.getDatabase("imse"); 
		MongoCollection<Document> collection = database.getCollection("Manufacturer");
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		
		if(foundDocument==null) {
			System.out.println("it is empty");
		}
		Boolean flag=false;
			for (int i = 0; i < foundDocument.size(); i++) {
			
				Document col = foundDocument.get(i);
				String name = (String) col.get("name");
				String country = (String) col.get("country");
				Manufacturer data = new Manufacturer(i,name,country);
				manufacturers.add(data);
				flag=true;
			}
			
		if(flag!=false) {
			client.close();		
		} 
		
		return manufacturers;
	}
	
	@Override
	public Manufacturer getManufacturerById(int manufacturer_id){
		/*Connection con = null;
		Manufacturer ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM manufacturer WHERE manufacturer_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, manufacturer_id);
			ResultSet result = prepared.executeQuery();
			
			while(result.next()){
				 ac = new Manufacturer(result.getInt("manufacturer_ID"), result.getString("name"), result.getString("country"));
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
	public void addManufacturer(String name, String country){
		client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = client.getDatabase("imse");
		MongoCollection<Document>  collection = database.getCollection("Manufacturer");
		Document documents = new Document();
		documents.put("name",name);
		documents.put("country", country);
		collection.insertOne(documents);
		client.close();
	}
	
	@Override
	public void changeManufacturer(int manufacturer_ID, String name, String country){
		client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = client.getDatabase("imse"); 
		MongoCollection<Document> collection = database.getCollection("Manufacturer");
		Bson filter = new Document("name", name);
		Bson newValue = new Document("country", country);
		Bson updateOperationDocument = new Document("$set", newValue);
		collection.updateOne(filter, updateOperationDocument);
		client.close();
	}
	
	@Override
	public void deleteManufacturer(int manufacturer_ID){
		/*Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "DELETE * FROM manufacturer WHERE manufacturer_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, manufacturer_ID);
			prepared.executeQuery();
		
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
	public int getManufacturerCount(){
		manufacturers = new ArrayList<Manufacturer>();
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
				String name = (String) col.get("name");
				String country = (String) col.get("country");
				Manufacturer data = new Manufacturer(i,name,country);
				manufacturers.add(data);
				flag=true;
			}
			
		if(flag!=false) {
			client.close();		
		} 
		return manufacturers.size();
	}
	
	@Override
	public Manufacturer getManufacturerByName(String name){
		Manufacturer ac = null;
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
				String names = (String) col.get("name");
				if(names.equals(name)) {
					String country = (String) col.get("country");
					ac = new Manufacturer(i,names,country);
				}
							
				flag=true;
			}
			
		if(flag!=false) {
			client.close();		
		} 
		return ac;
	}
	
	public List<Manufacturer> getManufacturersByCountry(String country){
		manufacturers = new ArrayList<Manufacturer>();
		Manufacturer ac = null;
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
				String name = (String) col.get("name");
				String countries = (String) col.get("country");
				if(countries.equals(country)) {
					ac = new Manufacturer(i,name,country);
					manufacturers.add(ac);
				}
							
				flag=true;
			}
			
		if(flag!=false) {
			client.close();		
		} 
		return manufacturers;
	}
}
