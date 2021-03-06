package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.Proxy;
import dao.AccessoryDAO;
import dao.AccessoryDAOI;
import dao.CostumerDAO;

@WebServlet("/CreateAccessory")
public class CreateAccessory extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 959672044805514415L;

	/**
     * Default constructor. 
     */
    public CreateAccessory() {
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
				HttpSession session = request.getSession(true); 
				String user = (String)session.getAttribute("currentSessionUser");
				if((session.getAttribute("currentSessionUser") != null) && user.equals("admin")){
					String name = (String) request.getParameter("name");
					String description = (String) request.getParameter("description");
					if(name != null) {
						AccessoryDAO adao = Proxy.getInstance().getAccessoryDAO();
					    adao.addAccessory(name, description);
					    response.sendRedirect("ListVehicle.jsp");
					    return;
					}
				    
				}
				else {
					response.sendRedirect("Homepage.jsp");
					return;
				}
				
			
			}catch(Exception e) {
				
			}
		RequestDispatcher dispatcher = request.getRequestDispatcher("CreateAccessory.jsp");
		dispatcher.forward(request, response);
	
	}
	 
}
