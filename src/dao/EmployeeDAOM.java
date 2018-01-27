package dao;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.Employee;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOM implements EmployeeDAO {
	private MongoClient mongoClient;

	@Override
	public List<Employee> getEmployeeList() {
        List<Employee> allEmployees = new ArrayList<>();
	    try {
            mongoClient = new MongoClient("localhost");
            MongoDatabase database = mongoClient.getDatabase("imse");
            MongoCollection<Document> collection = database.getCollection("employee");
            List<Document> mongoEmployees = collection.find().into(new ArrayList<>());
            for(Document mongoEmployee : mongoEmployees) {
                Document superior = mongoEmployee.get("superior", Document.class);
                Employee employee = new Employee(mongoEmployee.getInteger("_id"),
                        mongoEmployee.getString("first_name"),
                        mongoEmployee.getString("last_name"),
                        superior.getInteger("_id"),
                        mongoEmployee.getBoolean("active"));
                allEmployees.add(employee);
            }
        } catch(Exception ex) {

        } finally {
            try{
                mongoClient.close();
            }catch(Exception e){
                System.err.println(e);
            }
        }
	    return allEmployees;
	}

	@Override
	public Employee getEmployeeById(int ID) {
        Employee employee = null;
        try {
            mongoClient = new MongoClient("localhost");
            MongoDatabase database = mongoClient.getDatabase("imse");
            MongoCollection<Document> collection = database.getCollection("employee");

            FindIterable<Document> mongoEmployee = collection.find(new BasicDBObject("_id", ID)).limit(1);
            MongoCursor<Document> cursor = mongoEmployee.iterator();
            while(cursor.hasNext()) {
                Document mongoEmployeeDoc = cursor.next();
                Document superior = mongoEmployeeDoc.get("superior", Document.class);
                employee = new Employee(mongoEmployeeDoc.getInteger("_id"),
                        mongoEmployeeDoc.getString("first_name"),
                        mongoEmployeeDoc.getString("last_name"),
                        superior.getInteger("_id"),
                        mongoEmployeeDoc.getBoolean("active"));
            }
        } catch(Exception ex) {

        } finally {
            try{
                mongoClient.close();
            }catch(Exception e){
                System.err.println(e);
            }
        }
        return employee;
	}

	@Override
	public void addEmployee(Employee ToAdd) {
		try {
            mongoClient = new MongoClient("localhost");
            MongoDatabase database = mongoClient.getDatabase("imse");
            MongoCollection<Document> collection = database.getCollection("employee");

            // find highest id in collection to increment for the next document
            FindIterable<Document> maxEntry = collection.find().sort(new BasicDBObject("_id",-1)).limit(1);
            MongoCursor<Document> cursor = maxEntry.iterator();
            int maxId = 0;
            while(cursor.hasNext()) {
                Document max = cursor.next();
                maxId = (Integer)max.get("_id");
            }

            Document employeeDocument = new Document();
            employeeDocument.append("_id", maxId+1);
            employeeDocument.append("first_name", ToAdd.getFirst_name());
            employeeDocument.append("last_name", ToAdd.getLast_name());

            Employee superior = getEmployeeById(ToAdd.getSuperior_ID());
            if(ToAdd.getSuperior_ID() > 0) {
                Document superiorDoc = new Document();
                superiorDoc.append("_id", superior.getEmployee_number());
                superiorDoc.append("first_name", superior.getFirst_name());
                superiorDoc.append("last_name", superior.getLast_name());
                employeeDocument.append("superior", superiorDoc);
            } else {
                employeeDocument.append("superior", new Document("_id", -1));
            }
            employeeDocument.append("active", true);
            collection.insertOne(employeeDocument);
		} catch (Exception e) {
		      System.err.println(e.getMessage());
		}finally{
			try{
				mongoClient.close();
			}catch(Exception e){
				System.err.println(e);
			}
		}
	}

	@Override
	public void changeEmployee(Employee ToChange) {
        try {
            mongoClient = new MongoClient("localhost");
            MongoDatabase database = mongoClient.getDatabase("imse");
            MongoCollection<Document> collection = database.getCollection("employee");

            FindIterable<Document> oldEmployee = collection.find(new BasicDBObject("_id", ToChange.getEmployee_number())).limit(1);
            MongoCursor<Document> cursor = oldEmployee.iterator();
            Document oldEmployeeDoc = cursor.next();

            Document employeeDocument = new Document();
            employeeDocument.append("_id", ToChange.getEmployee_number());
            employeeDocument.append("first_name", ToChange.getFirst_name());
            employeeDocument.append("last_name", ToChange.getLast_name());

            Employee superior = getEmployeeById(ToChange.getSuperior_ID());
            if(ToChange.getSuperior_ID() > 0) {
                Document superiorDoc = new Document();
                superiorDoc.append("_id", superior.getEmployee_number());
                superiorDoc.append("first_name", superior.getFirst_name());
                superiorDoc.append("last_name", superior.getLast_name());
                employeeDocument.append("superior", superiorDoc);
            } else {
                employeeDocument.append("superior", new Document("_id", -1));
            }
            employeeDocument.append("active", ToChange.isActive());
            collection.replaceOne(oldEmployeeDoc, employeeDocument);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally{
            try{
                mongoClient.close();
            }catch(Exception e){
                System.err.println(e);
            }
        }
	}

	@Override
	public void deleteEmployee(int ID) {

	}
}
