package imse;

//written by a01349198 - IB

import java.util.Date;

public class Rental {
	Vehicle vehicle;
	//Costumer costumer;
	Employee employee;
	Date date_from;
	Date date_to;
	String rating;
	
	public Rental(){
		
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Date getDate_from() {
		return date_from;
	}
	
	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}
	
	public Date getDate_to() {
		return date_to;
	}
	
	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
}
