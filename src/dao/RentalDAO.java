package dao;

//written by a01349198 - IB

import java.util.Date;
import java.util.List;

import imse.Rental;
import imse.Vehicle;

public interface RentalDAO {
	public List<Rental> getRentalList();
	public Rental getRentalById(int rental_id);
	public void deleteRental(int rental_id);
	public int getRentalCount();
	public Rental getRentalByVehicle(Vehicle vehicle);
	public Rental getRentalByDatePeriod(Date date_from, Date date_to);
}
