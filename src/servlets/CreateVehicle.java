package servlets;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import dao.CostumerDAO;
import dao.CostumerDAOI;
import dao.VehicleDAO;
import dao.VehicleDAOI;
import model.Costumer;
import model.Employee;
import model.Vehicle;

/**
 * Servlet implementation class CreateVehicle
 */
@WebServlet("/CreateVehicle")
public class CreateVehicle extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7957711074827916095L;
	private List<Vehicle> vehicle;
    private VehicleDAO employeeDAO = new VehicleDAOI();
    

    /**
     * Default constructor. 
     */
    public CreateVehicle() {
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
		try{
			
			String vehicleType = request.getParameter("vehicleType");
			if(vehicleType=="CAR") {
				
			}
			String plate = request.getParameter("plate");
			String colorId = request.getParameter("colorId");
			String modelId = request.getParameter("modelId");
			String manufacturerId = request.getParameter("manufacturerId");
			String accessoryId = request.getParameter("accessoryId");
			String mileage = request.getParameter("mileage");
			String year = request.getParameter("year");
			String active = request.getParameter("active");
					
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");		     
			Date bdate = sdf.parse(bday1);
			 
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
		    String pwd_hash = BCrypt.hashpw(password, BCrypt.gensalt(4));

		    Vehicle toAdd = new Vehicle(0,title, fname, lname, lnum, bdate, email, pcode, 
		    							  street, hnum, anum, town, country, pwd_hash, "", true);
		    CostumerDAO cdao = new CostumerDAOI();
		    cdao.addCostumer(toAdd);
		    
		    HttpSession session = request.getSession(true); 
			session.setAttribute("currentSessionUser", "customer");
			session.setAttribute("currentSessionEMail", "email");
			session.setAttribute("currentSessionPassword", "password");
			
			response.sendRedirect("rent_vehicle.jsp");
		}
	}
}
