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

import dao.VehicleDAO;
import dao.VehicleDAOI;
import model.Car;
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
	
		//String user = (String)request.getParameter("user");
		//String password = (String) request.getParameter("password");
		/*String vehicleSelectId= (String) request.getParameter("edit");   
	    int vehicleIdSelect = Integer.parseInt(vehicleSelectId);
		String vehicleType=null;
	    HttpSession session = request.getSession();
		session.setAttribute("vehicleSelectId",vehicleSelectId);
	
        VehicleDAO veh = new VehicleDAOI();
        List<Vehicle> list_car = new ArrayList<Vehicle>();
        list_car.addAll(veh.getVehicleListByType("car"));
		for(Vehicle c:list_car){
			if(c.getVehicle_ID()== vehicleIdSelect){
				vehicleType="CAR";
			}
		}
		if(vehicleType == null) {
			vehicleType="TRUCK";
		}
		
		session.setAttribute("vehicleType",vehicleType);   
	    */
		String page;
		try {
		String vehicleSelectId= (String) request.getParameter("id"); 
		if(vehicleSelectId!=null) {
			System.out.println("Inside the select");
			int vehicleIdSelect= Integer.valueOf(vehicleSelectId);
			System.out.println(vehicleSelectId);
	   // if(user.equals("admin") && password.equals("admin")){
			VehicleDAO vdao = new VehicleDAOI();
			List<Vehicle> all = new ArrayList<Vehicle>();	
			String vId = (String) request.getParameter("id");
			int vehicleId = Integer.valueOf(vId);
			String plate = (String) request.getParameter("plate");
			String colorId = (String) request.getParameter("colorId");
			int color = Integer.valueOf(colorId);
			String modelId = (String) request.getParameter("modelId");
			int model = Integer.valueOf(modelId);
			String manufacturerId = (String) request.getParameter("manufacturerId");
			int manufacturer = Integer.valueOf(manufacturerId);
			String accessoryId = (String) request.getParameter("accessoryId");
			int accessory = Integer.valueOf(accessoryId);
			String mileage1 = (String) request.getParameter("mileage");
			int mileage = Integer.valueOf(mileage1);
			String year1 = (String) request.getParameter("year");
			int year = Integer.valueOf(year1);
			String active1 = (String) request.getParameter("active");
			Boolean active = Boolean.parseBoolean(active1);
			System.out.println("here");
			all = vdao.getVehicleListByType("car");
			
			System.out.println("Before the loop");
			for(Vehicle c:all){
				System.out.println("Inside the loop");
				if(c.getVehicle_ID()== vehicleIdSelect){
					System.out.println("Inside the car");
					String doors1 = (String) request.getParameter("doors");
					int doors = Integer.valueOf(doors1);
					String pass_limit1 = (String) request.getParameter("pass_limit");
					int pass_limit = Integer.valueOf(pass_limit1);					
					VehicleDAO cdao = new VehicleDAOI();
					cdao.changeCar(vehicleId, plate, color, model, manufacturer, accessory, mileage, year, active, doors, pass_limit);
				}
			}
			
			all = vdao.getVehicleListByType("truck");
			
			for(Vehicle c:all){
				if(c.getVehicle_ID()== vehicleIdSelect){
					
					String length1 = (String) request.getParameter("length");
					int length = Integer.valueOf(length1);
					String height1 = (String) request.getParameter("height");
					int height = Integer.valueOf(height1);
					String load_limit1 = (String) request.getParameter("load_limit");
					int load_limit = Integer.valueOf(load_limit1);
					VehicleDAO tdao = new VehicleDAOI();
					tdao.changeTruck(vehicleId, plate, color, model, manufacturer, accessory, mileage, year, active, length, height, load_limit);
				}
			}	
			response.sendRedirect("ListVehicle.jsp");
			
	   // }
	        
		}
	}catch(NumberFormatException ex){
		
	}	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ChangeVehicle.jsp");
	    dispatcher.forward(request, response);
			
	}
}		


