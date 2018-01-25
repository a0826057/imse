package dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Employee;
import org.bson.Document;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOM implements EmployeeDAO {
	private List<Employee> allEmployee = new ArrayList<Employee>();
	Connection connection = null;
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
                Employee employee = new Employee(mongoEmployee.getString("first_name"),
                        mongoEmployee.getString("last_name"),
                        true);
                allEmployees.add(employee);
            }
        } catch(Exception ex) {

        } finally {
	        mongoClient.close();
        }
	    return allEmployees;
	}

	@Override
	public Employee getEmployeeById(int ID) {
		return null;
	}

	@Override
	public void addEmployee(Employee ToAdd) {
		//refresh();
		try {
            mongoClient = new MongoClient("localhost");System.out.println("a");
            MongoDatabase database = mongoClient.getDatabase("imse");System.out.println("b");
            MongoCollection<Document> collection = database.getCollection("employee");System.out.println("c");
            Employee superior = getEmployeeById(ToAdd.getSuperior_ID());System.out.println("d");

            Document employeeDocument = new Document();
            Document superiorDocument = new Document();
            System.out.println("e");
            superiorDocument.append("first_name", superior.getFirst_name());
            superiorDocument.append("last_name", superior.getLast_name());
            employeeDocument.append("first_name", ToAdd.getFirst_name());
            employeeDocument.append("last_name", ToAdd.getLast_name());
            //employeeDocument.append("superior", superiorDocument);
            System.out.print(employeeDocument.values());System.out.println("f");
            collection.insertOne(employeeDocument);System.out.println("g");
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

		} catch (Exception e) {
		      System.err.println(e.getMessage());
		}finally{
			try{
				
			}catch(Exception e){
				System.err.println(e);
			}
		}
		
	}

	@Override
	public void deleteEmployee(int ID) {
		try {

		} catch (Exception e) {
		      System.err.println(e.getMessage());
		}finally{
			try{
				
			}catch(Exception e){
				System.err.println(e);
			}
		}
		
	}
}
