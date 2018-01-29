package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccessoryDAO;
import dao.AccessoryDAOI;
import dao.ColorDAO;
import dao.ColorDAOI;
import dao.ManufacturerDAO;
import dao.ManufacturerDAOI;
import dao.ModelDAO;
import dao.ModelDAOI;
import dao.Proxy;
import dao.VehicleDAO;
import dao.VehicleDAOI;
import model.Car;
import model.Truck;
import model.Vehicle;


@WebServlet("/ChangeVehicle")
public class ChangeVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String cid;
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
		System.out.println("My id");
		HttpSession session = request.getSession(); 
		AccessoryDAO acc = Proxy.getInstance().getAccessoryDAO();
	 	ModelDAO mod = Proxy.getInstance().getModelDAO();
	 	ManufacturerDAO man = Proxy.getInstance().getManufacturerDAO();
	 	ColorDAO col = Proxy.getInstance().getColorDAO();
		VehicleDAO vehi = Proxy.getInstance().getVehicleDAO();
		cid = (String) request.getParameter("id");
		int changeId = Integer.parseInt(cid);
		System.out.println("My id" + cid);
		session.setAttribute("changeList",vehi.getVehicleById(changeId));
        session.setAttribute("colorList",col.getColorList());
        System.out.println(col.getColorList().toString());
   	    session.setAttribute("modelList",mod.getModelList());
   	    session.setAttribute("manufacturerList",man.getManufacturerList());
   	    session.setAttribute("accessoryList",acc.getAccessoryList());
   	    RequestDispatcher dispatcher = request.getRequestDispatcher("ChangeVehicle.jsp");
   	    dispatcher.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		cid = (String)session.getAttribute("edit");
		System.out.println("Im in post");
		int changeId = Integer.parseInt(cid);
		VehicleDAO chVe = Proxy.getInstance().getVehicleDAO();
		String user = (String)session.getAttribute("currentSessionUser");
		System.out.println(user);
		try {
			if((session.getAttribute("currentSessionUser") != null) && user.equals("admin")){
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
										VehicleDAO cdao = Proxy.getInstance().getVehicleDAO();
										ArrayList<Car> carss = new ArrayList<Car>();
										carss.add((Car) chVe.getVehicleListByType("Car"));
										for(Car c: carss) {
											if(changeId==c.getVehicle_ID()) {
												cdao.changeCar(vehicleId, plate, color, model, manufacturer, accessory, mileage, year, active,c.getDoors(),c.getPassenger_limit());
												response.sendRedirect("ListVehicle.jsp");
												return;
											}
										}
									}else {
										ArrayList<Truck> truckss = new ArrayList<Truck>();
										truckss.add((Truck) chVe.getVehicleListByType("Truck"));
										for(Truck c: truckss) {
											if(changeId==c.getVehicle_ID()) {
												VehicleDAO tdao = Proxy.getInstance().getVehicleDAO();
												tdao.changeTruck(vehicleId, plate, color, model, manufacturer, accessory, mileage, year, active, c.getLenght(), c.getHeight(), c.getLoading_limit());
												response.sendRedirect("ListVehicle.jsp");
												return;
											}
										
										}								
								}
							}
						}
					
					}
					}
				}}
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


