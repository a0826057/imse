package imse;

import java.util.Date;

public class Rental {
	Vehicle vehicle;
	//Customer customer;
	//Employee employee;
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
