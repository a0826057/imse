package servlets;

import java.io.IOException;


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
import dao.VehicleDAOI;


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
		HttpSession session = request.getSession(); 
	    AccessoryDAO acc = Proxy.getInstance().getAccessoryDAO();
	 	ModelDAO mod = Proxy.getInstance().getModelDAO();
	 	ManufacturerDAO man = Proxy.getInstance().getManufacturerDAO();
	 	ColorDAO col = Proxy.getInstance().getColorDAO();
	 	String cid = (String)session.getAttribute("edit");
		int changeId = Integer.parseInt(cid);
		System.out.println("My id" + cid);
		VehicleDAO vehi = Proxy.getInstance().getVehicleDAO();
		
	    /*AccessoryDAOI acc = new AccessoryDAOI();
	 	ModelDAOI mod = new ModelDAOI();
	 	ManufacturerDAOI man = new ManufacturerDAOI();
	 	ColorDAOI col = new ColorDAOI();*/
		session.setAttribute("colorList",vehi.getVehicleById(changeId));
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
		HttpSession session = request.getSession(true); 
		try {
			
			String user = (String)session.getAttribute("currentSessionUser");
			if(user.equals("admin") && user != null){
				String vehicleType = (String) request.getParameter("changeVehicle");
				if(vehicleType != null) {
				String IDvehicle = (String) request.getParameter("id");
				int vehicleId = Integer.parseInt(IDvehicle);
				String plate = (String) request.getParameter("plate");
				String colorId = (String) request.getParameter("colorId");
				if(colorId!=null) {
					int color = Integer.parseInt(colorId);
					String modelId = (String)request.getParameter("modelId");
						if(modelId!=null) {
							int model = Integer.parseInt(modelId);
							String manufacturerId = (String) request.getParameter("manufacturerId");
							if(manufacturerId!=null) {
								int manufacturer = Integer.parseInt(manufacturerId);
								String accessoryId = (String) request.getParameter("accessoryId");
								if(accessoryId!=null) {
								
									int accessory = Integer.parseInt(accessoryId);
									String mileage1 = (String) request.getParameter("mileage");
									int mileage = Integer.parseInt(mileage1);
									String year1 = (String) request.getParameter("year");
									int year = Integer.parseInt(year1);
									String active1 = (String) request.getParameter("active");
									Boolean active = Boolean.parseBoolean(active1);
									
									if("CAR".equals(vehicleType)) {
										
										String doors1 = (String) request.getParameter("doors");
										int doors = Integer.parseInt(doors1);
										String pass_limit1 = (String) request.getParameter("pass_limit");
										int pass_limit = Integer.parseInt(pass_limit1);
										VehicleDAO cdao = new VehicleDAOI();
										cdao.changeCar(vehicleId, plate, color, model, manufacturer, accessory, mileage, year, active, doors, pass_limit);
										response.sendRedirect("ListVehicle.jsp");
										return;
									}else {
										String length1 = request.getParameter("length");
										int length = Integer.parseInt(length1);
										String height1 = request.getParameter("height");
										int height = Integer.parseInt(height1);
										String load_limit1 = request.getParameter("load_limit");
										int load_limit = Integer.parseInt(load_limit1);
										VehicleDAO tdao = new VehicleDAOI();
										tdao.changeTruck(vehicleId, plate, color, model, manufacturer, accessory, mileage, year, active, length, height, load_limit);
										response.sendRedirect("ListVehicle.jsp");
										return;
										}
									
								}
							}
						}
					
					}
					}
				}
				else {
					response.sendRedirect("Homepage.jsp");
					return;
				}
			}catch(Exception e) {
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ChangeVehicle.jsp");
		    dispatcher.forward(request, response);
	}
}		


