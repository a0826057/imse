package dao;

import model.Costumer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CostumerDAOM implements CostumerDAO {

	@Override
	public List<Costumer> getCostumerList() {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("Customer");

			List<Document> cursor = collection.find().into(new ArrayList<Document>());
			List<Costumer> cList = new ArrayList<Costumer>();
			for (Document obj:cursor) {
				
				int costumer_ID = Integer.parseInt(obj.get("costumer_ID").toString());
				String title = obj.get("title").toString();
				String first_name = obj.get("first_name").toString();
				String last_name = obj.get("last_name").toString();
				String drivers_license_number = "";
				if(obj.get("drivers_license_number") != null) {
					drivers_license_number = obj.get("drivers_license_number").toString();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date birth_date = sdf.parse(obj.get("birth_date").toString());

				String email = obj.get("email").toString();
				Document d = (Document) obj.get("address");
				String post_code = d.get("post_code").toString();
				String street = d.get("street").toString();
				String house_number = d.get("house_number").toString();
				String appartment_number = "";
				if(d.get("appartment_number") != null) {
					appartment_number = d.get("appartment_number").toString();
				}
				String town = d.get("town").toString();
				String country = d.get("country").toString();
				String pwd_hash = obj.get("pwd_hash").toString();
				String salt = obj.get("salt").toString();
				Costumer c = new Costumer(	costumer_ID, title, first_name, last_name, drivers_license_number,
											birth_date, email, post_code, street, house_number, appartment_number,
											town, country, pwd_hash, salt, true);
				cList.add(c);
			}
			return cList;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}finally{
        	if (mongoClient != null)
        		mongoClient.close();
        }
		return null;
	}

	@Override
	public Costumer getCostumerById(int ID) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("Customer");

			List<Document> cursor = collection.find().into(new ArrayList<Document>());
			//List<Costumer> cList = new ArrayList<Costumer>();
			for (Document obj:cursor) {
				
				int costumer_ID = Integer.parseInt(obj.get("costumer_ID").toString());
				String title = obj.get("title").toString();
				String first_name = obj.get("first_name").toString();
				String last_name = obj.get("last_name").toString();
				String drivers_license_number = "";
				if(obj.get("drivers_license_number") != null) {
					drivers_license_number = obj.get("drivers_license_number").toString();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date birth_date = sdf.parse(obj.get("birth_date").toString());

				String email = obj.get("email").toString();
				Document d = (Document) obj.get("address");
				String post_code = d.get("post_code").toString();
				String street = d.get("street").toString();
				String house_number = d.get("house_number").toString();
				String appartment_number = "";
				if(d.get("appartment_number") != null) {
					appartment_number = d.get("appartment_number").toString();
				}
				String town = d.get("town").toString();
				String country = d.get("country").toString();
				String pwd_hash = obj.get("pwd_hash").toString();
				String salt = obj.get("salt").toString();
				Costumer c = new Costumer(	costumer_ID, title, first_name, last_name, drivers_license_number,
											birth_date, email, post_code, street, house_number, appartment_number,
											town, country, pwd_hash, salt, true);
				return c;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}finally{
        	if (mongoClient != null)
    			mongoClient.close();
        }
		return null;
	}
	
	@Override
	public void addCostumer(Costumer c) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("Customer");

			Document toInsert = new Document();
			Document address = new Document();
            toInsert.put("costumer_ID", c.getCostumer_ID());
            toInsert.put("title", c.getTitle());
            toInsert.put("first_name", c.getFirst_name());
            toInsert.put("last_name", c.getLast_name());
            toInsert.put("drivers_license_number", c.getDrivers_licens_number());
            toInsert.put("birth_date", c.getBirth_date());
            toInsert.put("email", c.getEmail());
            address.put("post_code", c.getPost_code());
            address.put("street", c.getStreet());
            address.put("house_number", c.getHouse_number());
            address.put("appartment_number", c.getAppartment_number());
            address.put("town", c.getTown());
            address.put("country", c.getCountry());
            toInsert.put("pwd_hash", c.getPwd_hash());
            toInsert.put("address", address);

			collection.insertOne(toInsert);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	if (mongoClient != null)
    			mongoClient.close();
        }
	}

	@Override
	public void changeCostumer(Costumer c) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("Customer");

            Document update = new Document();
            Document whatToUpdate = new Document();
            whatToUpdate.append("costumer_ID", c.getCostumer_ID());
            whatToUpdate.append("title", c.getTitle());
            whatToUpdate.append("first_name", c.getFirst_name());
            whatToUpdate.append("last_name", c.getLast_name());
            whatToUpdate.append("drivers_license_number", c.getDrivers_licens_number());
            whatToUpdate.append("birth_date", c.getBirth_date());
            whatToUpdate.append("email", c.getEmail());
            whatToUpdate.append("post_code", c.getPost_code());
            whatToUpdate.append("street", c.getStreet());
            whatToUpdate.append("house_number", c.getHouse_number());
            whatToUpdate.append("appartment_number", c.getAppartment_number());
            whatToUpdate.append("town", c.getTown());
            whatToUpdate.append("country", c.getCountry());
            whatToUpdate.append("pwd_hash", c.getPwd_hash());
            whatToUpdate.append("salt", c.getSalt());
            update.append("$set", whatToUpdate);


            Document searchQuery = new Document().append("costumer_ID", c.getCostumer_ID());

            collection.updateOne(searchQuery, update);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	if (mongoClient != null)
    			mongoClient.close();
        }
	}

	@Override
	public void deleteCostumer(int ID) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
			MongoDatabase database = mongoClient.getDatabase("imse"); 
			MongoCollection<Document> collection = database.getCollection("Customer");

            collection.findOneAndDelete(new Document().append("costumer_ID", ID));

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	if (mongoClient != null)
    			mongoClient.close();
        }
	}
}
