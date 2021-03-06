package servlets;
import dao.CostumerDAO;
import dao.CostumerDAOI;
import model.Costumer;
import dao.Proxy;
import javax.servlet.RequestDispatcher;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LogIn() {
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
		try{			
			CostumerDAO cdao = Proxy.getInstance().getCostumerDAO();
			List<Costumer> all = cdao.getCostumerList();
			List<Costumer> buff = new ArrayList<Costumer>();
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			for(Costumer c:all){
				if(c.getEmail().equals(email) && c.getActive()){
					buff.add(c);
				}
			}
			
			for(Costumer c:buff){
				if(BCrypt.checkpw(password, c.getPwd_hash())){
					HttpSession session = request.getSession(true); 
					session.setAttribute("currentSessionUser", "customer");
					session.setAttribute("currentSessionUserPassword", password);
					session.setAttribute("currentSessionUserString", c.toString());
					session.setAttribute("currentSessionUserMail", c.getEmail());
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/Profil.jsp");
					rd.forward(request,response);
				}
			}
				
			}catch(Exception ex){
			}
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/LogIn.jsp");
		rd.forward(request,response);
	}

}
