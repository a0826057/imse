package imse;

public class Employee {
 private int employee_number;
 private String first_name;
 private String last_name;
 private int superior_ID = -1;
 private boolean active;
 
 public Employee(int employee_number, String first_name, String last_name, int superior_ID, boolean active) {
	super();
	this.employee_number = employee_number;
	this.first_name = first_name;
	this.last_name = last_name;
	this.superior_ID = superior_ID;
	this.active = active;
}
public Employee(int employee_number, String first_name, String last_name, boolean active) {
	super();
	this.employee_number = employee_number;
	this.first_name = first_name;
	this.last_name = last_name;
	this.active = active;
}
public Employee(String first_name, String last_name, boolean active) {
	super();
	this.first_name = first_name;
	this.last_name = last_name;
	this.active = active;
}
public int getEmployee_number() {
	return employee_number;
}
public void setEmployee_number(int employee_number) {
	this.employee_number = employee_number;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public int getSuperior_ID() {
	return superior_ID;
}
public void setSuperior_ID(int superior_ID) {
	this.superior_ID = superior_ID;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}

 
}
