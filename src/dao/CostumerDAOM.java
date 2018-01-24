package dao;

import model.Costumer;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class CostumerDAOM implements CostumerDAO {

	@Override
	public List<Costumer> getCostumerList() {

		try {
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("imse");
			DBCollection collection = db.getCollection("Customer");

			DBCursor cursor = collection.find();
			List<Costumer> cList = new ArrayList<Costumer>();
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				int costumer_ID = Integer.parseInt(obj.get("costumer_ID"));
				String title = obj.get("title").toString();
				String first_name = obj.get("first_name").toString();
				String last_name = obj.get("last_name").toString();
				String drivers_license_number = "";
				if(obj.containsField("drivers_license_number")) {
					drivers_license_number = obj.get("drivers_license_number").toString();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date birth_date = sdf.parse(obj.get("birth_date").toString());

				String email = obj.get("email").toString();
				String post_code = obj.get("post_code").toString();
				String street = obj.get("street").toString();
				String house_number = obj.get("house_number").toString();
				String appartment_number = "";
				if(obj.containsField("appartment_number")) {
					appartment_number = obj.get("appartment_number").toString();
				}
				String town = obj.get("town").toString();
				String country = obj.get("country").toString();
				String pwd_hash = obj.get("pwd_hash").toString();
				String salt = obj.get("salt").toString();
				Costumer c = new Costumer(	costumer_ID, title, first_name, last_name, drivers_license_number,
											birth_date, email, post_code, street, house_number, appartment_number,
											town, country, pwd_hash, salt, true);
				cList.add(c);
			}
			return cList;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Costumer getCostumerById(int ID) {

		try {
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("imse");
			DBCollection collection = db.getCollection("Customer");

			BasicDBObject query = new BasicDBObject();
			query.put("costumer_ID", ID);
			DBCursor cursor = collection.find(query);

			if (cursor.next()) {
				int costumer_ID = Integer.parseInt(obj.get("costumer_ID"));
				String title = obj.get("title").toString();
				String first_name = obj.get("first_name").toString();
				String last_name = obj.get("last_name").toString();
				String drivers_license_number = "";
				if(obj.containsField("drivers_license_number")) {
					drivers_license_number = obj.get("drivers_license_number").toString();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date birth_date = sdf.parse(obj.get("birth_date").toString());

				String email = obj.get("email").toString();
				String post_code = obj.get("post_code").toString();
				String street = obj.get("street").toString();
				String house_number = obj.get("house_number").toString();
				String appartment_number = "";
				if(obj.containsField("appartment_number")) {
					appartment_number = obj.get("appartment_number").toString();
				}
				String town = obj.get("town").toString();
				String country = obj.get("country").toString();
				String pwd_hash = obj.get("pwd_hash").toString();
				String salt = obj.get("salt").toString();
				Costumer c = new Costumer(	costumer_ID, title, first_name, last_name, drivers_license_number,
						birth_date, email, post_code, street, house_number, appartment_number,
						town, country, pwd_hash, salt, true);
				return c;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addCostumer(Costumer c) {

		try {
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("imse");
			DBCollection collection = db.getCollection("Customer");

			BasicDBObject toInsert = new BasicDBObject();
			BasicDBObject address = new BasicDBObject();
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

			collection.insert(toInsert);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void changeCostumer(Costumer c) {
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("imse");
            DBCollection collection = db.getCollection("Customer");

            BasicDBObject update = new BasicDBObject();
            BasicDBObject whatToUpdate = new BasicDBObject();
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


            BasicDBObject searchQuery = new BasicDBObject().append("costumer_ID", c.getCostumer_ID());

            collection.update(searchQuery, update);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteCostumer(int ID) {
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("imse");
            DBCollection collection = db.getCollection("Customer");

            collection.remove(new BasicDBObject().append("costumer_ID", ID));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
	}
}
