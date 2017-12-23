package management;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import imse.Rental;
import imse.Vehicle;

public class RentalManagement {
	List<Rental> rentals;

	public RentalManagement(){
		this.rentals = new ArrayList<Rental>();
	}
	
	public RentalManagement(List<Rental> ls){
		this.rentals = ls;
	}
	
	public List<Rental> getRentalList() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}
	
	public Rental getRentalById(int rental_id){
		return null;
	}
	
	public void deleteRental(int rental_id){
		
	}
	
	public int getRentalCount(){
		return rentals.size();
	}
	
	public Rental getRentalByVehicle(Vehicle vehicle){
		return null;
	}
	
	public Rental getRentalByDatePeriod(Date date_from, Date date_to){
		return null;
	}
}
