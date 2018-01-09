package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import dao.AccessoryDAO;
import dao.AccessoryDAOI;

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
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			if(user.equals("admin") && password.equals("admin")){
				String name = request.getParameter("name");
				String description = request.getParameter("description");
			    AccessoryDAO adao = new AccessoryDAOI();
			    adao.addAccessory(name, description);
			}
			else {
				response.sendRedirect("Homepage.jsp");
			}
			response.sendRedirect("CreateVehicle.jsp");
			
			}catch(Exception e) {
				
			}
	}
}
