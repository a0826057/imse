package dao;

import java.util.List;

import imse.Employee;

public interface EmployeeDAO {
	public List<Employee> getEmployeeList();
	public Employee getEmployeeById(int ID);
	public void addEmployee(Employee ToAdd);
	public void changeEmployee(Employee ToChange);
	public void deleteEmployee(int ID);
	
}
