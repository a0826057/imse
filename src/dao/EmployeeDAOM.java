package dao;

import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOM implements EmployeeDAO {
	private List<Employee> allEmployee = new ArrayList<Employee>();
	Connection connection = null;
	
	private void refresh(){
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
	public List<Employee> getEmployeeList() {
		refresh();
		return this.allEmployee;
	}

	@Override
	public Employee getEmployeeById(int ID) {
		refresh();
		return null;
	}

	@Override
	public void addEmployee(Employee ToAdd) {
		refresh();
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
