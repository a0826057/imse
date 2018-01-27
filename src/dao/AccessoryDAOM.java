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
		
		MongoCollection<Document> coll = database.getCollection("accessory");
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
		
		MongoCollection<Document> coll = database.getCollection("accessory");
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
		
		MongoCollection<Document> coll = database.getCollection("accessory");
		List<Document> accs = coll.find().into(new ArrayList<Document>());
		int id = 1;
		if(accs.size() != 0){
			String id_string = (String)accs.get((accs.size()-1)).get("accessory_id");
			id = Integer.parseInt(id_string) + 1;
		}
		
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
		
		MongoCollection<Document> coll = database.getCollection("accessory");
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
		
		MongoCollection<Document> coll = database.getCollection("accessory");
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
		
		MongoCollection<Document> coll = database.getCollection("accessory");
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
		
		MongoCollection<Document> coll = database.getCollection("accessory");
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
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll_acc = database.getCollection("accessory");
		MongoCollection<Document> coll_veh = database.getCollection("vehicle");
		
		List<Document> access = coll_acc.find().into(new ArrayList<Document>());
		List<Document> vehs = coll_veh.find().into(new ArrayList<Document>());
		
		for (int i = 0; i < vehs.size(); i++) {
			int veh_id = Integer.parseInt(vehs.get(i).get("vehicle_id").toString());
			if(veh_id == vehicle_ID){
				for (int j = 0; j < access.size(); j++) {
					int ac_id_TOBEAD = Integer.parseInt(access.get(j).get("accessory_id").toString());
					if(ac_id_TOBEAD == accessory_ID){
						Document accessory = access.get(j);
						List<Document> acceses_INVEHICLE = null;
						List<Document> help = null;
						Document acccess_In = null;
						boolean isArray = true;
						try{
							acceses_INVEHICLE = (List<Document>) vehs.get(i).get("accessory");
							help = acceses_INVEHICLE;
						}catch(ClassCastException e){
							isArray = false;
							acccess_In = (Document) vehs.get(i).get("accessory");
						}
						
						if(isArray){
							for(Document a : help){
								int ac_id_ALREADYIN = Integer.parseInt(a.get("accessory_id").toString());
								if(ac_id_ALREADYIN != ac_id_TOBEAD ){
									acceses_INVEHICLE.add(accessory);
									
									Document query = new Document("vehicle_id", Integer.toString(vehicle_ID));
									coll_veh.findOneAndDelete(query);

									vehs.get(i).remove("accessory");
									vehs.get(i).put("accessory", acceses_INVEHICLE);
									coll_veh.insertOne(vehs.get(i));
									break;
								}
							}
						}else{
							help = new ArrayList<Document>();
							help.add(acccess_In);
							
							int ac_id_ALREADYIN = Integer.parseInt(acccess_In.get("accessory_id").toString());
							if(ac_id_ALREADYIN != ac_id_TOBEAD ){
								help.add(accessory);
							}
							Document query = new Document("vehicle_id", Integer.toString(vehicle_ID));
							coll_veh.findOneAndDelete(query);
							
							vehs.get(i).remove("accessory");
							vehs.get(i).put("accessory", help);
							coll_veh.insertOne(vehs.get(i));
							break;
						}
					}
				}
			}
			
		}
		
		if (mongoClient != null)
			mongoClient.close();
	}
	
	public List<Accessory> getHasAccessory(int vehicle_ID){
		accessories = new ArrayList<Accessory>();
		AccessoryDAOM acc = new AccessoryDAOM();
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
		MongoDatabase database = mongoClient.getDatabase("imse"); 
		
		MongoCollection<Document> coll_veh = database.getCollection("vehicle");
		List<Document> vehs = coll_veh.find().into(new ArrayList<Document>());
		
		for (int i = 0; i < vehs.size(); i++) {
			int veh_id = Integer.parseInt(vehs.get(i).get("vehicle_id").toString());
			if(veh_id == vehicle_ID){
				List<Document> acceses = (List<Document>) vehs.get(i).get("accessory");
				for(Document a : acceses){
					int ac_id_ALREADYIN = Integer.parseInt(a.get("accessory_id").toString());
					Accessory accessory = acc.getAccessoryById(ac_id_ALREADYIN);
					accessories.add(accessory);
				}
					
			}
			
		}
		
		if (mongoClient != null)
			mongoClient.close();
		
		return accessories;
	}
}
