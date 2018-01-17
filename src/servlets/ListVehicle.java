package servlets;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VehicleDAO;
import dao.VehicleDAOI;

import model.Vehicle;


/**
 * Servlet implementation class CreateVehicle
 */
@WebServlet("/ListVehicle")
public class ListVehicle extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7957711074827916095L;
	
    

    /**
     * Default constructor. 
     */
    public ListVehicle() {
       super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
			HttpSession session = request.getSession();
	        VehicleDAO veh = new VehicleDAOI();
	        List<Vehicle> list_car = new ArrayList<Vehicle>();
	        List<Vehicle> list_truck = new ArrayList<Vehicle>();
	        String type = (String) request.getParameter("vehicleType");
	        try {
				if(type.equalsIgnoreCase("CAR")) {
					list_car.addAll(veh.getVehicleListByType("car"));
				}else {
					list_truck.addAll(veh.getVehicleListByType("truck"));
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			} 
	        
	        
	        session.setAttribute("list_car", list_car);
	   	    session.setAttribute("list_truck",list_truck);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("ListVehicle.jsp");
	        
	        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
