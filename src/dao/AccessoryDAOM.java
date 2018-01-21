package dao;

import model.Accessory;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//written by a01349198 - IB

public class AccessoryDAOM implements AccessoryDAO{
	private List<Accessory> accessories;
	
	@Override
	public List<Accessory> getAccessoryList(){
		accessories = new ArrayList<Accessory>();
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.accessory");
		List<Document> access = coll.find().into(new ArrayList<Document>());
		
		for (int i = 0; i < access.size(); i++) {
			Document ac = access.get(i);
			String name = (String) ac.get("name");
			String description = (String) ac.get("description");
			Accessory a = new Accessory(i,name,description);
			accessories.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		
		return accessories;
	}
	
	@Override
	public Accessory getAccessoryById(int accessory_id){
		/*Connection con = null;
		Accessory ac = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "SELECT * FROM accessory WHERE accessory_id = ?";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, accessory_id);
			ResultSet result = prepared.executeQuery();

			while(result.next()){
				 ac = new Accessory(result.getInt("accessory_ID"), result.getString("name"), result.getString("description"));
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
	public void addAccessory(String name, String description){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.accessory");
		
		if((name instanceof String) && (description instanceof String)){
			Document document = new Document("name", name)
	               .append("description", description);

			coll.insertOne(document);
		}
		
		
		if (mongoClient != null)
			mongoClient.close();
	}
	
	@Override
	public void changeAccessory(int accessory_ID, String name, String description){
		/*MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.accessory");
		
		if((name instanceof String) && (description instanceof String)){
			Document document = new Document("name", name)
	               .append("description", description);

			coll.insertOne(document);
		}
		
		
		if (mongoClient != null)
			mongoClient.close();
		
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "UPDATE accessory SET name = ?, description = ? WHERE accessory_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setString(1, name);
			prepared.setString(2, description);
			prepared.setInt(3, accessory_ID);
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
	public void deleteAccessory(int accessory_id){
		/*Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement statement = con.createStatement();
			statement.setQueryTimeout(60);
			String raw_query = "DELETE * FROM accessory WHERE accessory_ID = ?;";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, accessory_id);
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
	public int getAccessoryCount(){
		accessories = new ArrayList<Accessory>();
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.accessory");
		List<Document> access = coll.find().into(new ArrayList<Document>());
		
		for (int i = 0; i < access.size(); i++) {
			Document ac = access.get(i);
			String name = (String) ac.get("name");
			String description = (String) ac.get("description");
			Accessory a = new Accessory(i,name,description);
			accessories.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		
		return accessories.size();
	}
	
	@Override
	public List<Accessory> getAccessoriesByName(String name){
		accessories = new ArrayList<Accessory>();
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.accessory");
		
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
		
		return accessories;
	}
	
	public static void main(String[] args){
		AccessoryDAOM a = new AccessoryDAOM();
		a.getAccessoriesByName("Navi");
	}
	
	
	public void addHasAccessory(int accessory_ID, int vehicle_ID){
		/*Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root", "MySQLrp");
			
			Statement stat = con.createStatement();
			stat.setQueryTimeout(60);
			String raw_query = "INSERT INTO has_accessory(accessory_ID, vehicle_ID) VALUES(?,?);";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, accessory_ID);
			prepared.setInt(2, vehicle_ID);
			prepared.executeUpdate();
		}catch(Exception e){
			System.err.println(e);
		}finally{
			try{
				if(con != null)
					con.close();
			}catch(SQLException e){
				System.err.println(e);
			}
		}*/
		
	}
	
	public List<Accessory> getHasAccessory(int vehicle_ID){
	/*	Connection con = null;
		AccessoryDAOM ac = new AccessoryDAOM();
		List<Accessory> list = new ArrayList<Accessory>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root", "MySQLrp");
			
			Statement stat = con.createStatement();
			stat.setQueryTimeout(60);
			String raw_query = "SELECT * FROM has_accessory WHERE vehicle_ID = ?";
			PreparedStatement prepared = con.prepareStatement(raw_query);
			prepared.setInt(1, vehicle_ID);
			ResultSet res = prepared.executeQuery();
			
			while(res.next()){
				 Accessory acc = ac.getAccessoryById(res.getInt("accessory_ID"));
				 list.add(acc);
			}
			
		}catch(Exception e){
			System.err.println(e);
		}finally{
			try{
				if(con != null)
					con.close();
			}catch(SQLException e){
				System.err.println(e);
			}
		}*/
		
		return null;
	}
}
