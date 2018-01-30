package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDAOI implements EmployeeDAO {
	private List<Employee> allEmployee = new ArrayList<Employee>();
	Connection connection = null;
	
	private void refresh(){
		try {
		      Class.forName("com.mysql.jdbc.Driver");
		      connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

		      Statement stmt = connection.createStatement();
		      stmt.setQueryTimeout(30);

		      ResultSet epl = stmt.executeQuery("SELECT * FROM employee");
		      allEmployee.clear();
		      try {
		    	  while(epl.next()){
		    	  	Employee e1 = new Employee(	epl.getInt("employee_number"),
		    	  								epl.getString("first_name"),
		    	  								epl.getString("last_name"),
		    	  								epl.getInt("superior_ID"),
		    	  								epl.getBoolean("active"));
		    	  	this.allEmployee.add(e1);
		    	  }
		    	  	
		    	  	stmt.close();
		      } catch (Exception e) {
		        System.err.println("Fehler beim Einfuegen des Datensatzes in Person: " + e.getMessage());
		      }
		} catch (Exception e) {
		      System.err.println(e.getMessage());
		}finally{
			try{
				if(this.connection != null)
					connection.close();
				
			}catch(SQLException e){
				System.err.println(e);
			}
		}
	}
	@Override
	public List<Employee> getEmployeeList() {
		refresh();
		return this.allEmployee;
	}

	@Override
	public Employee getEmployeeById(int ID) {
		Employee e1 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");

			Statement stmt = connection.createStatement();
			stmt.setQueryTimeout(30);

			ResultSet epl = stmt.executeQuery("SELECT * FROM employee WHERE employee_number=" + ID);
			try {
				//while(epl.next()){
				epl.next();
					e1 = new Employee(	epl.getInt("employee_number"),
							epl.getString("first_name"),
							epl.getString("last_name"),
							epl.getInt("superior_ID"),
							epl.getBoolean("active"));
					//this.allEmployee.add(e1);
				//}
				stmt.close();
			} catch (Exception e) {
				System.err.println("Fehler beim Einfuegen des Datensatzes in Person: " + e.getMessage());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{
			try{
				if(this.connection != null)
					connection.close();

			}catch(SQLException e){
				System.err.println(e);
			}
		}

		return e1;
	}

	@Override
	public void addEmployee(Employee ToAdd) {
		refresh();
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
		      

		      // establish connection to database 
		      Statement stmt = connection.createStatement();
		      stmt.setQueryTimeout(30);
		      try {
		    	  String insertSql = null;
		    	  if(ToAdd.getSuperior_ID() == -1){
		    		  insertSql = "INSERT INTO employee (first_name, last_name) VALUES (?, ?)";
		    	  }else{
		    		  insertSql = "INSERT INTO employee (first_name, last_name, superior_ID) VALUES (?,?,?)";
		    	  }
		    	  PreparedStatement prepared = connection.prepareStatement(insertSql);
					
		    	  prepared.setString(1, ToAdd.getFirst_name());
		    	  prepared.setString(2, ToAdd.getLast_name());
		    	  if(ToAdd.getSuperior_ID()!=-1){
		    	  prepared.setInt(3, ToAdd.getSuperior_ID());
		    	  }
		    	  prepared.executeUpdate();
		    	  	stmt.close();
		      } catch (Exception e) {
		        System.err.println("Fehler beim Einfuegen des Datensatzes in Employee: " + e.getMessage());
		      }
		} catch (Exception e) {
		      System.err.println(e.getMessage());
		}finally{
			try{
				if(this.connection != null)
					connection.close();
				
			}catch(SQLException e){
				System.err.println(e);
			}
		}
	
		
	}

	@Override
	public void changeEmployee(Employee ToChange) {
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
		      

		      // establish connection to database 
		      Statement stmt = connection.createStatement();
		      stmt.setQueryTimeout(30);
		      try {
		    	  String insertSql = null;
		    	  
		    	 insertSql = "UPDATE employee SET first_name = ?, last_name = ?, superior_id = ?, active = ?  WHERE	employee_number = ?";
		    	  
		    	 PreparedStatement prepared = connection.prepareStatement(insertSql);
					
		    	  prepared.setString(1, ToChange.getFirst_name());
		    	  prepared.setString(2, ToChange.getLast_name());
				  prepared.setInt(3, ToChange.getSuperior_ID());
				  prepared.setBoolean(4, ToChange.isActive());
		    	  prepared.setInt(5, ToChange.getEmployee_number());
		    	  
		    	  prepared.executeUpdate();
		    		
		    	  	
		    	  	stmt.close();
		      } catch (Exception e) {
		        System.err.println("Fehler beim Einfuegen des Datensatzes in Employee: " + e.getMessage());
		      }
		} catch (Exception e) {
		      System.err.println(e.getMessage());
		}finally{
			try{
				if(this.connection != null)
					connection.close();
				
			}catch(SQLException e){
				System.err.println(e);
			}
		}
		
	}

	@Override
	public void deleteEmployee(int ID) {
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      connection = DriverManager.getConnection("jdbc:mysql://localhost/myimsedb?useSSL=false","root","MySQLrp");
		     

		      // establish connection to database 
		      Statement stmt = connection.createStatement();
		      stmt.setQueryTimeout(30);
		      try {
		    	  String insertSql = null;
		    	  
		    	 insertSql = "DELETE FROM employee  WHERE	employee_number = ?)";
		    	  
		    	 PreparedStatement prepared = connection.prepareStatement(insertSql);
					
		    	  		    	  
		    	  prepared.setInt(1, ID);
		    	  
		    	  prepared.executeUpdate();
		    		
		    	  	
		    	  	stmt.close();
		      } catch (Exception e) {
		        System.err.println("Fehler beim Einfuegen des Datensatzes in Employee: " + e.getMessage());
		      }
		} catch (Exception e) {
		      System.err.println(e.getMessage());
		}finally{
			try{
				if(this.connection != null)
					connection.close();
				
			}catch(SQLException e){
				System.err.println(e);
			}
		}
		
	}

}
