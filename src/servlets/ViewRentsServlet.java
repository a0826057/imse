package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CostumerDAOI;
import dao.RentalDAOI;
import dao.VehicleDAOI;
import model.Costumer;
import model.Rental;
import model.Vehicle;

/**
 * Servlet implementation class ViewRentsServlet
 */
@WebServlet("/ViewRentsServlet")
public class ViewRentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRentsServlet() {
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
		  		CostumerDAOI cos = new CostumerDAOI();
		  		RentalDAOI rent = new RentalDAOI();
		  		List<Costumer> list_cos = cos.getCostumerList();
		  		List<Rental> list_rentals = rent.getRentalList();
		  		String email = (String) session.getAttribute("currentSessionUserMail");
		    
				List<Rental> list = new ArrayList<Rental>();
				for(int i = 0; i < list_rentals.size(); i++){
					if(list_rentals.get(i).getCostumer().getCostumer_ID() == 1)
						list.add(list_rentals.get(i));
				}
				
		      
		        session.setAttribute("list_rentals", list);
		        
		        RequestDispatcher dispatcher = request.getRequestDispatcher("view_rents.jsp");
		        
		        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RentalDAOI rent = new RentalDAOI();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("list_rent") != null){
			List<Rental> ls = (List<Rental>) session.getAttribute("list_rent");
			String position = request.getParameter("button");
			int poss = Integer.parseInt(position);
			Rental r = ls.get(poss);
			
			rent.deleteRental(r.getVehicle().getVehicle_ID(), r.getCostumer().getCostumer_ID(),r.getEmployee().getEmployee_number(), r.getDate_from());
		}
				
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("view_rents.jsp");   
		dispatcher.forward(request, response);
	}

}
