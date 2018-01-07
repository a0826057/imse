package servlets;
import dao.CostumerDAO;
import dao.CostumerDAOI;
import model.Costumer;
import servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Delete() {
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
			CostumerDAO cdao = new CostumerDAOI();
			Costumer c = cdao.getCostumerById(Integer.Integer.parseInt(request.getParameter("id")));
			if(c.getActive()){
				if (request.getParameter("license") != null) {
					c.setDrivers_licens_number(request.getParameter("license"));
					cdao.changeCostumer(c);
					
				}else{
					c.setActive(false);
					cdao.changeCostumer(c);
				}
			}
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/Homepage.jsp");
			rd.forward(request,response);
			
		}
	}

}
