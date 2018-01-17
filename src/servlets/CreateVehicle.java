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


/**
 * Servlet implementation class CreateVehicle
 */
@WebServlet("/CreateVehicle")
public class CreateVehicle extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7957711074827916095L;
	
    

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
			try {
				String user = (String) request.getParameter("user");
				String password = (String)request.getParameter("password");
				if(user.equals("admin") && password.equals("admin")){
					System.out.println("here");
					String vehicleType = (String) request.getParameter("vehicleType");
					String plate = (String) request.getParameter("plate");
					String colorId = (String) request.getParameter("colorId");
					int color = Integer.parseInt(colorId);
					String modelId = (String) request.getParameter("modelId");
					int model = Integer.parseInt(modelId);
					String manufacturerId = (String) request.getParameter("manufacturerId");
					int manufacturer = Integer.parseInt(manufacturerId);
					String accessoryId = (String) request.getParameter("accessoryId");
					int accessory = Integer.parseInt(accessoryId);
					String mileage1 = (String) request.getParameter("mileage");
					int mileage = Integer.parseInt(mileage1);
					String year1 = (String) request.getParameter("year");
					int year = Integer.parseInt(year1);
					String active1 = (String) request.getParameter("active");
					Boolean active = Boolean.parseBoolean(active1);
					if(vehicleType=="CAR") {
						String doors1 = (String) request.getParameter("doors");
						int doors = Integer.parseInt(doors1);
						String pass_limit1 = (String) request.getParameter("pass_limit");
						int pass_limit = Integer.parseInt(pass_limit1);
						VehicleDAO car = new VehicleDAOI();
				    	car.addCar(plate, color, model, manufacturer, accessory, mileage, year, active, doors, pass_limit);
					}
					else {
						String length1 = (String) request.getParameter("length");
						int length = Integer.parseInt(length1);
						String height1 = (String) request.getParameter("height");
						int height = Integer.parseInt(height1);
						String load_limit1 = (String) request.getParameter("load_limit");
						int load_limit = Integer.parseInt(load_limit1);
						VehicleDAO truck = new VehicleDAOI();
				    	truck.addTruck(plate, color, model, manufacturer, accessory, mileage, year, active, length, height, load_limit);
					}
				}
				else {
					response.sendRedirect("Homepage.jsp");
				}
			}catch(Exception e) {
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("CreateVehicle.jsp");
		    dispatcher.forward(request, response);
		
	}
}
