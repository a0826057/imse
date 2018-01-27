package servlets;
import dao.CostumerDAO;
import dao.CostumerDAOI;
import model.Costumer;
import dao.Proxy;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangeCustomer
 */
@WebServlet("/ChangeCustomer")
public class ChangeCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ChangeCustomer() {
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
			CostumerDAO cdao = Proxy.getInstance().getCostumerDAO();
			List<Costumer> all = new ArrayList<Costumer>();
			
			HttpSession session = request.getSession(true); 
			String user = (String)session.getAttribute("currentSessionUser");
			String email = (String)session.getAttribute("currentSessionUserMail");
			String password = (String)session.getAttribute("currentSessionPassword");
			
			String title = request.getParameter("title");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String country = request.getParameter("country");
			String town = request.getParameter("town");
			String pcode = request.getParameter("pcode");
			String street = request.getParameter("steet");
			String hnum = request.getParameter("hnum");
			String anum = request.getParameter("anum");
			String bday1 = request.getParameter("bday");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");		     
			Date bdate = sdf.parse(bday1);
			
			all = cdao.getCostumerList();
			//String user = session.getAttribute("currentSessionUser");
			for(Costumer c:all){
				if(c.getActive() && user.equals("customer")){
					if(c.getEmail().equals(email) && BCrypt.checkpw(password, c.getPwd_hash())){
						Costumer toAdd = new Costumer(c.getCostumer_ID(),title, fname, lname, c.getDrivers_licens_number(), bdate, c.getEmail(), pcode, 
								  street, hnum, anum, town, country, c.getPwd_hash(), "", true);
						
						cdao.changeCostumer(toAdd);
					}
				}
			}
			response.sendRedirect("rent_vehicle.jsp");
		}catch(Exception ex){
			response.sendRedirect("Homepage.jsp");
		}
	}
}
