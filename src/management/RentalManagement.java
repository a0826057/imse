package management;

//written by a01349198 - IB

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Rental;
import model.Vehicle;

public class RentalManagement {
	private List<Rental> rentals;
	private static RentalManagement rentalInstance = null;
	
	public RentalManagement(){
		this.rentals = new ArrayList<Rental>();
	}
	
	public RentalManagement getInstance(){
		if(rentalInstance == null){
			rentalInstance = new RentalManagement();
		}
		
		return rentalInstance;
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
