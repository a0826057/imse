package servlets;
import java.awt.Color;
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

import dao.AccessoryDAO;

import dao.ColorDAO;

import dao.ManufacturerDAO;

import dao.ModelDAO;

import dao.Proxy;
import dao.VehicleDAO;





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
		HttpSession session = request.getSession(); 
	    AccessoryDAO acc = Proxy.getInstance().getAccessoryDAO();
	 	ModelDAO mod = Proxy.getInstance().getModelDAO();
	 	ManufacturerDAO man = Proxy.getInstance().getManufacturerDAO();
	 	ColorDAO col = Proxy.getInstance().getColorDAO();
        session.setAttribute("colorList",col.getColorList());
   	    session.setAttribute("modelList",mod.getModelList());
   	    session.setAttribute("manufacturerList",man.getManufacturerList());
   	    session.setAttribute("accessoryList",acc.getAccessoryList());
   	    RequestDispatcher dispatcher = request.getRequestDispatcher("CreateVehicle.jsp");
   	    dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 

		String submit = (String) request.getParameter("create");
		if(submit != null) {
			System.out.println("Im"+submit);
		}
		try {
			String user = (String)session.getAttribute("currentSessionUser");
			if((session.getAttribute("currentSessionUser") != null) && user.equals("admin")){
			String vehicleType = request.getParameter("create");
			System.out.println(vehicleType);
				if(vehicleType != null) {
				String plate = request.getParameter("plate");
				System.out.println(plate);
				String colorId = request.getParameter("colorId");
				if(colorId!=null) {
					int color = Integer.parseInt(colorId);
					String modelId = request.getParameter("modelId");
						if(modelId!=null) {
							int model = Integer.parseInt(modelId);
							String manufacturerId = request.getParameter("manufacturerId");
							if(manufacturerId!=null) {
								int manufacturer = Integer.parseInt(manufacturerId);
								String accessoryId = request.getParameter("accessoryId");
								if(accessoryId!=null) {
									int accessory = Integer.parseInt(accessoryId);
									String mileage1 = request.getParameter("mileage");
									int mileage = Integer.parseInt(mileage1);
									String year1 = request.getParameter("year");
									int year = Integer.parseInt(year1);
									String active1 = request.getParameter("active");
									Boolean active = Boolean.parseBoolean(active1);
									
									if("CAR".equals(vehicleType)) {
										String doors1 = request.getParameter("doors");
										int doors = Integer.parseInt(doors1);
										String pass_limit1 = request.getParameter("pass_limit");
										int pass_limit = Integer.parseInt(pass_limit1);
										VehicleDAO car = Proxy.getInstance().getVehicleDAO();
								    	car.addCar(plate, color, model, manufacturer, accessory, mileage, year, active, doors, pass_limit);
								    	response.sendRedirect("ListVehicle.jsp");
								    
									}
									else {
										String length1 = request.getParameter("length");
										int length = Integer.parseInt(length1);
										String height1 = request.getParameter("height");
										int height = Integer.parseInt(height1);
										String load_limit1 = request.getParameter("load_limit");
										int load_limit = Integer.parseInt(load_limit1);
										VehicleDAO truck =Proxy.getInstance().getVehicleDAO();
								    	truck.addTruck(plate, color, model, manufacturer, accessory, mileage, year, active, length, height, load_limit);
								    	response.sendRedirect("ListVehicle.jsp");
								    	
									}
									
								}
							}
						}
					
					}
					
				}
				}else {
					
				}
			}catch(Exception e) {
				response.sendRedirect("Homepage.jsp");
			}
		RequestDispatcher dispatcher = request.getRequestDispatcher("CreateVehicle.jsp");
	    dispatcher.forward(request, response);
			
	}
}