package servlets;

import dao.Proxy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ALogIn
 */
@WebServlet("/ALogIn")
public class ALogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ALogIn() {   super();    }

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
		try{
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			
			if(user.equals("admin") && password.equals("admin")){
				String type = "admin";
				HttpSession session = request.getSession(true); 
				session.setAttribute("currentSessionUser", type);
				session.setAttribute("currentSessionUserPassword", password);
				if(Proxy.getDbmode().equals("mongodb")) {
					request.setAttribute("dbmode","mongodb");
				} else {
					request.setAttribute("dbmode","mysql");
				}
				request.getRequestDispatcher("employee.jsp").include(request, response);;
			}else{
				response.sendRedirect("Homepage.jsp");
			}
		}catch(Exception ex){
			
		}
	}

}
