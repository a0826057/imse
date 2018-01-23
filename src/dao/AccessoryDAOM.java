package dao;

import model.Accessory;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//written by a01349198 - IB

//HASACCESSORY PART NOT COMPLETED AS IT IS DEPENDING ON VEHICLE FROM OTHER TEAMMATE!!!

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
			String id_str = (String) ac.get("accessory_id");
			int id = Integer.parseInt(id_str);
			String name = (String) ac.get("name");
			String description = (String) ac.get("description");
			Accessory a = new Accessory(id,name,description);
			accessories.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		
		return accessories;
	}	
	
	@Override
	public Accessory getAccessoryById(int accessory_id){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.accessory");
		Document searchQuery = new Document("accessory_id", Integer.toString(accessory_id));
		Document result = null;
		
		for(Document ac : coll.find(searchQuery))
			result = ac;
		
		String id_str = (String) result.get("accessory_id");
		int id = Integer.parseInt(id_str);
		String name = (String) result.get("name");
		String description = (String) result.get("description");
		Accessory accessory = new Accessory(id,name,description);
		
		if (mongoClient != null)
			mongoClient.close();
		
		return accessory;
	}
	
	@Override
	public void addAccessory(String name, String description){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.accessory");
		List<Document> accs = coll.find().into(new ArrayList<Document>());
		String id_string = (String)accs.get((accs.size()-1)).get("accessory_id");
		int id = Integer.parseInt(id_string) + 1;
		
		Document doc = new Document();
		doc.put("accessory_id", Integer.toString(id));
		doc.put("name", name);
		doc.put("description", description );

		coll.insertOne(doc);
		
		if (mongoClient != null)
			mongoClient.close();
	}	
	
	@Override
	public void changeAccessory(int accessory_ID, String name, String description){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.accessory");
		Document searchQuery = new Document("accessory_id", Integer.toString(accessory_ID));
		Document newValues = new Document();
		newValues.put("name", name);
		newValues.put("description", description);
		
		Document update = new Document("$set", newValues);
		coll.updateOne(searchQuery, update);
		
		if (mongoClient != null)
			mongoClient.close();
	}
	
	@Override
	public void deleteAccessory(int accessory_id){
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll = database.getCollection("imse.accessory");
		Document query = new Document("accessory_id", Integer.toString(accessory_id));
		
		coll.findOneAndDelete(query);
			
		if (mongoClient != null)
			mongoClient.close();
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
			String id_str = (String) ac.get("accessory_id");
			int id = Integer.parseInt(id_str);
			String name = (String) ac.get("name");
			String description = (String) ac.get("description");
			Accessory a = new Accessory(id,name,description);
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
		Document query = new Document("name",name);
		List<Document> access = coll.find(query).into(new ArrayList<Document>());
		
		for (int i = 0; i < access.size(); i++) {
			Document ac = access.get(i);
			String id_str = (String) ac.get("accessory_id");
			int id = Integer.parseInt(id_str);
			String nam = (String) ac.get("name");
			String description = (String) ac.get("description");
			Accessory a = new Accessory(id,nam,description);
			accessories.add(a);
		}
			
		if (mongoClient != null)
			mongoClient.close();
		
		return accessories;
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
