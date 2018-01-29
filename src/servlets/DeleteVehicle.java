package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.Proxy;
import dao.VehicleDAO;
import dao.VehicleDAOI;
/**
 * Servlet implementation class Delete
 */
@WebServlet("/DeleteVehicle")
public class DeleteVehicle extends HttpServlet {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -4303654111009556058L;

	/**
     * Default constructor. 
     */
    public DeleteVehicle() {
        // TODO Auto-generated constructor stub
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
			
			HttpSession session = request.getSession(); 
			VehicleDAO veh = Proxy.getInstance().getVehicleDAO();
			String id1 = request.getParameter("delete");
			String user = (String)session.getAttribute("currentSessionUser");
			if(user.equals("admin") && user != null){
				if(id1!=null) {
					System.out.println(id1);
					int vehicle_id = Integer.parseInt(id1);
					veh.deleteVehicle(vehicle_id);
					request.getRequestDispatcher("ListVehicle.jsp").forward(request, response);
				}
				
		}			
		}catch (Exception e) {
				 
		}
		request.getRequestDispatcher("Homepage.jsp").forward(request, response);
	}
	}



