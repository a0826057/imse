package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CostumerDAO;
import dao.Proxy;
import dao.RentalDAO;
import model.Costumer;
import model.Rental;

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
				
  		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("currentSessionUser");
  		String email = (String) session.getAttribute("currentSessionUserMail");
  		CostumerDAO cos = Proxy.getInstance("mongodb").getCostumerDAO();
  		RentalDAO rent = Proxy.getInstance("mongodb").getRentalDAO();
  		
		if((session.getAttribute("currentSessionUser") != null) && user.equals("customer") && (session.getAttribute("currentSessionUserMail") != null)){
	  		List<Rental> list_rentals = rent.getRentalList();
	  		List<Costumer> coss = cos.getCostumerList();
	        Costumer costumer = null;
	        
	        for(Costumer cs : coss){
	        	if(cs.getEmail().equals(email)){
	        		costumer = cs;
	        		break;
	        	}
	        }
	  		
			List<Rental> list = new ArrayList<Rental>();
			for(int i = 0; i < list_rentals.size(); i++){
				if(list_rentals.get(i).getCostumer().getCostumer_ID() == costumer.getCostumer_ID())
					list.add(list_rentals.get(i));
			}
			
	      
	        session.setAttribute("list_rentals", list);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("view_rents.jsp");
	        dispatcher.forward(request, response);
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("Homepage.jsp");
	        dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RentalDAO rent = Proxy.getInstance("mongodb").getRentalDAO();
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
