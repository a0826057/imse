package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VehicleDAO;
import dao.VehicleDAOI;
import model.Vehicle;

@WebServlet("/ChangeVehicle")
public class ChangeVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ChangeVehicle() {
       super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VehicleDAO vdao = new VehicleDAOI();
		List<Vehicle> all = new ArrayList<Vehicle>();
		
		/*
		HttpSession session = request.getSession(true); 
		String user = session.getAttribute("currentSessionUser");
		String email = session.getAttribute("currentSessionEMail");
		String password = session.setAttribute("currentSessionPassword");
		*/
		
		String vehicleType = request.getParameter("vehicleType");
		String plate = request.getParameter("plate");
		String colorId = request.getParameter("colorId");
		int color = Integer.parseInt(colorId);
		String modelId = request.getParameter("modelId");
		int model = Integer.parseInt(modelId);
		String manufacturerId = request.getParameter("manufacturerId");
		int manufacturer = Integer.parseInt(manufacturerId);
		String accessoryId = request.getParameter("accessoryId");
		int accessory = Integer.parseInt(accessoryId);
		String mileage1 = request.getParameter("mileage");
		int mileage = Integer.parseInt(mileage1);
		String year1 = request.getParameter("year");
		int year = Integer.parseInt(year1);
		String active1 = request.getParameter("active");
		Boolean active = Boolean.parseBoolean(active1);
			     
			
		all = vdao.getVehicleList();
		
		for(Vehicle c:all){
			if(c.getActive()){
				//check if type is car or truck
				/*
				if(c.getEmail()==email && BCrypt.checkpw(password, c.getPwd_hash)){
					Costumer toAdd = new Costumer(c.getCostumer_ID(),title, fname, lname, lnum, bdate, email, pcode, 
							  street, hnum, anum, town, country, pwd_hash, c.getDrivers_licens_number(), true);
					
					cdao.changeCostumer(toAdd);
					*/
				}
			}
		}
		//send page add
	}
