package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CostumerDAOI;
import dao.EmployeeDAOI;
import dao.RentalDAOI;
import dao.VehicleDAOI;
import model.Costumer;
import model.Employee;
import model.Rental;
import model.Vehicle;

/**
 * Servlet implementation class RentVehicleServlet
 */
@WebServlet("/RentVehicleServlet")
public class RentVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentVehicleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// HERE MUST BE CHECKED IF THE USER HAS AN ACTIVE ACCOUNT AND IF HE IS LOGGED IN
		//.............
		
		HttpSession session = request.getSession();
	    String date_f = request.getParameter("date_from");
        String date_t = request.getParameter("date_to");
        
     
        List<Rental> list_rentals = new ArrayList<Rental>();
        List<Vehicle> list_vehicle = new ArrayList<Vehicle>();
        RentalDAOI rent = new RentalDAOI();
        VehicleDAOI veh = new VehicleDAOI();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
			java.util.Date DateFrom  =  df.parse(date_f);
			java.util.Date DateTo  =  df.parse(date_t);
			list_rentals = rent.getRentalByDatePeriod(DateFrom, DateTo);
			list_vehicle = veh.getVehicleList();
			
			for(Rental r : list_rentals){
				for(int i = 0; i < list_vehicle.size(); i++){
					if(list_vehicle.get(i).getVehicle_ID() == r.getVehicle().getVehicle_ID()){
						list_vehicle.remove(i);
					}
				}
			}
		} catch (ParseException e) {
		} 
        
        session.setAttribute("list", list_vehicle);
        session.setAttribute("date_from", date_f);
        session.setAttribute("date_to", date_t);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("rent_vehicle.jsp");
        
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("currentSessionUser");
		String email = (String)session.getAttribute("currentSessionUserMail");
		
		if((session.getAttribute("currentSessionUser") != null) && user.equals("customer") && (session.getAttribute("currentSessionUserMail") != null)){
			String button = request.getParameter("button");
			String date_f = (String) session.getAttribute("date_from");
	        String date_t = (String) session.getAttribute("date_to");
	        
	        CostumerDAOI c = new CostumerDAOI();
	        EmployeeDAOI e = new EmployeeDAOI();
	        VehicleDAOI v = new VehicleDAOI();
	        Random rand = new Random();
	        
	        List<Employee> employees = e.getEmployeeList();
	        List<Costumer> coss = c.getCostumerList();
	        Costumer costumer = null;
	        
	        for(Costumer cs : coss){
	        	if(cs.getEmail().equals(email)){
	        		costumer = cs;
	        		break;
	        	}
	        }
	        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			RentalDAOI rent = new RentalDAOI();
			Rental r = null;
			try {
				r = new Rental(v.getVehicleById(Integer.parseInt(button)), costumer, employees.get(rand.nextInt(employees.size())), df.parse(date_f),df.parse(date_t), "not given");
			} catch (NumberFormatException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rent.addRental(r);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("view_rents.jsp");
	        dispatcher.forward(request, response);
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Homepage.jsp");
	        dispatcher.forward(request, response);
		}
		
	}

}
