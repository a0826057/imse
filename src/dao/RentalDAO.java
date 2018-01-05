package dao;

//written by a01349198 - IB

import java.util.Date;
import java.util.List;

import model.Costumer;
import model.Employee;
import model.Rental;
import model.Vehicle;

public interface RentalDAO {
	public List<Rental> getRentalList();
	public void deleteRental(int vid, int cid, int eid, Date date_from);
	public int getRentalCount();
	public List<Rental> getRentalByCostumer(Costumer cos);
	public List<Rental> getRentalByVehicle(Vehicle vehicle);
	public List<Rental> getRentalByEmployee(Employee emp);
	public List<Rental> getRentalByDatePeriod(Date date_from, Date date_to);
	void addRental(Rental r);
	void changeRental(int vid, int cid, int eid, Date date_from, String rating);
}
