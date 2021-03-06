package servlets;
import model.Costumer;
import dao.CostumerDAO;
import dao.CostumerDAOI;
import dao.Proxy;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SignUp() {
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
		try{
			String title = request.getParameter("title");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String country = request.getParameter("country");
			String town = request.getParameter("town");
			String pcode = request.getParameter("pcode");
			String street = request.getParameter("street");
			String hnum = request.getParameter("hnum");
			String anum = request.getParameter("anum");
			String bday1 = request.getParameter("bday");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");		     
			Date bdate = sdf.parse(bday1);
			 
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
		    String pwd_hash = BCrypt.hashpw(password, BCrypt.gensalt(4));

		    Costumer toAdd = new Costumer(0,title, fname, lname, "", bdate, email, pcode, 
		    							  street, hnum, anum, town, country, pwd_hash, password, true);
		    CostumerDAO cdao = Proxy.getInstance().getCostumerDAO();
		    cdao.addCostumer(toAdd);
		    
		    HttpSession session = request.getSession(true); 
			session.setAttribute("currentSessionUser", "customer");
			session.setAttribute("currentSessionUserMail", email);
			session.setAttribute("currentSessionPassword", password);
			session.setAttribute("currentSessionUserString", toAdd.toString());
			
			response.sendRedirect("rent_vehicle.jsp");
		}catch(Exception ex){
			response.sendRedirect("Homepage.jsp");
		}
	}

}
