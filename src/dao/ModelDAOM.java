package dao;

import model.Accessory;
import model.Manufacturer;
import model.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
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
		
		MongoCollection<Document> coll = database.getCollection("imse.model");
		List<Document> mod = coll.find().into(new ArrayList<Document>());
		
		for (int i = 0; i < mod.size(); i++) {
			Document ac = mod.get(i);
			DBObject db = (DBObject) ac.get("manufacturer");
			String man_name = (String) db.get("name");
			Manufacturer manufacturer = m.getManufacturerByName(man_name);
			String description = (String) ac.get("description");
			double pr = (Double) ac.get("price");
			Model a = new Model(i, manufacturer, description, pr);
			models.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		
		return models;
	}
	
	@Override
	public Model getModelById(int model_id){
		/*Connection con = null;
		Model ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM model WHERE model_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, model_id);
			ResultSet result = prepared.executeQuery();
			
			while(result.next()){
				Manufacturer m = man.getManufacturerById( result.getInt("manufacturer_ID"));
				ac = new Model(result.getInt("model_ID"), m, result.getString("description"), result.getDouble("price"));
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
	public void addModel(Manufacturer man, String description, double price){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.model");
		
		Document document = new Document("manufacturer", man.getManufacturer_ID())
               .append("description", description)
               .append("price", price);

		coll.insertOne(document);
		
		if (mongoClient != null)
			mongoClient.close();

	}
	
	@Override
	public void changeModel(int model_ID, Manufacturer man, String description, double price){
		/*Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "UPDATE model SET manufacturer_ID = ?, description = ?, price = ? WHERE model_ID = ?;"; 
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, man.getManufacturer_ID());
			prepared.setString(2, description);
			prepared.setDouble(3, price);
			prepared.setInt(4, model_ID);
			prepared.executeUpdate();
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
	public void deleteModel(int model_id){
	/*	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.model");
		
		BasicDBObject search = new BasicDBObject();
		search.put("name", name);
		List<Document> access = coll.find(search).into(new ArrayList<Document>());
		
		for (int i = 0; i < access.size(); i++) {
			Document ac = access.get(i);
			String nam = (String) ac.get("name");
			String description = (String) ac.get("description");
			Accessory a = new Accessory(i,nam,description);
			accessories.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "DELETE * FROM model WHERE model_ID = " + model_id + ";";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, model_id);
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
	public int getModelCount(){
		models = new ArrayList<Model>();
		ManufacturerDAOM m = new ManufacturerDAOM();
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.model");
		List<Document> mod = coll.find().into(new ArrayList<Document>());
		
		for (int i = 0; i < mod.size(); i++) {
			Document ac = mod.get(i);
			DBObject db = (DBObject) ac.get("manufacturer");
			String man_name = (String) db.get("name");
			Manufacturer manufacturer = m.getManufacturerByName(man_name);
			String description = (String) ac.get("description");
			double pr = (Double) ac.get("price");
			Model a = new Model(i, manufacturer, description, pr);
			models.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		
		return models.size();
	}
	
	@Override
	public List<Model> getModelsByManufacturersName(String name){
		/*Manufacturer m = man.getManufacturerByName(name);
		models = new ArrayList<Model>();
		Connection con = null;
		Model ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
			
			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM model WHERE manufacturer_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, m.getManufacturer_ID());
			ResultSet result = prepared.executeQuery();
			
			while(result.next()){
				 ac = new Model(result.getInt("model_ID"), m, result.getString("description"), result.getDouble("description"));
				 models.add(ac);
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
