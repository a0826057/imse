
import model.Costumer;
import dao.CostumerDAO;
import dao.CostumerDAOI;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			String street = request.getParameter("steet");
			String hnum = request.getParameter("hnum");
			String anum = request.getParameter("anum");
			String bday = request.getParameter("bday");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			//Todo licence
		    String pwd_hash = BCrypt.hashpw(password, BCrypt.gensalt(4));

		    Costumer toAdd = new Costumer(0,title, fname, lname, lnum, bdate, email, pcode, 
		    							  street, hnum, anum, town, country, pwd_hash, "", true);
		    CostumerDAO cdao = new CostumerDAOI();
		    cdao.addCostumer(toAdd);
		}
	}

}
