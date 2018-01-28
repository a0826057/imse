package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.VehicleDAO;
import dao.VehicleDAOI;


@WebServlet("/SearchVehicle")
public class SearchVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchVehicle() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//String user = (String)request.getParameter("user");
		//String password = (String) request.getParameter("password");
		
		
		try {
			String user="admin";
			String password="admin";
			
			String vehicleType = (String) request.getParameter("searchVehicle");
				
			if(user.equals("admin") && password.equals("admin")){
				if(vehicleType != null) {
					String colorId = (String) request.getParameter("colorId");
					int color;
					if(colorId!=null)
						color = Integer.parseInt(colorId);
					String modelId = (String)request.getParameter("modelId");
					int model;
					if(modelId!=null)
						model = Integer.parseInt(modelId);
					String manufacturerId = (String) request.getParameter("manufacturerId");
					int manufacturer;
					if(manufacturerId!=null)
						manufacturer = Integer.parseInt(manufacturerId);
					String accessoryId = (String) request.getParameter("accessoryId");
					int accessory;
					if(accessoryId!=null)
						int accessory = Integer.parseInt(accessoryId);					
					
					VehicleDAO cdao = new VehicleDAOI();
					List <Vehicle> vlist = cdao.searchVehicle(color, model, manufacturer, accessory);
					response.sendRedirect("Homepage.jsp");
					return;
				}
			}
			else {
					response.sendRedirect("Homepage.jsp");
					return;
				}
			}catch(Exception e) {
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("SearchVehicle.jsp");
		    dispatcher.forward(request, response);
	}
}		


