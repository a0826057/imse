package servlets;

import dao.EmployeeDAO;
import dao.EmployeeDAOI;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private List<Employee> employees;
    private EmployeeDAO employeeDAO = new EmployeeDAOI();

    /**
     * handles the employee use case
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeMode = request.getParameter("employeeMode");

        // get all employees and store them in a list if the request mode is valid
        if(employeeMode.equals("create") || employeeMode.equals("read") || employeeMode.equals("update") || employeeMode.equals("delete")) {
            try {
                String first_name = request.getParameter("first_name");
                String last_name = request.getParameter("last_name");
                int superior_id = -1;
                if(!request.getParameter("superior_id").isEmpty()) {
                    superior_id = Integer.parseInt(request.getParameter("superior_id"));
                }
                boolean active = true;

                try {
                    if(!first_name.isEmpty() && !last_name.isEmpty()) {
                        Employee newEmployee = new Employee(0, first_name, last_name, superior_id, active);
                        employeeDAO.addEmployee(newEmployee);
                    }
                } catch (Exception ex) {
                    //request.setAttribute("msg", "Please fill in all fields!");
                }

                if(employeeMode.equals("create")) {
                    employees = new ArrayList(employeeDAO.getEmployeeList());
                    StringBuilder superiorDropdownString = new StringBuilder();
                    superiorDropdownString.append("<select name=\"superior_id\">");
                    superiorDropdownString.append("<option value=\"-1\">None</option>");

                    for(Employee employee : employees) {
                        if(employee.isActive()) {
                            superiorDropdownString.append("<option value=\"" + employee.getEmployee_number() + "\">");
                            superiorDropdownString.append(employee.getEmployee_number() + ": " + employee.getFirst_name().replace("'", "") + " " + employee.getLast_name().replace("'", ""));
                            superiorDropdownString.append("</option>");
                        } else {
                            superiorDropdownString.append("<option value=\"none\">None</option>");
                        }
                    }
                    request.setAttribute("superiorDropdownString", superiorDropdownString.toString());
                    request.setAttribute("employeeMode", employeeMode);
                    request.getRequestDispatcher("employee.jsp").include(request, response);
                }

                if(employeeMode.equals("read")) {
                    employees = new ArrayList(employeeDAO.getEmployeeList());
                    StringBuilder employeeTableString = new StringBuilder();
                    String superiorTableString;

                    for(Employee employee : employees) {
                        if(employee.getSuperior_ID() > 0) {
                            Employee superior = employeeDAO.getEmployeeById(employee.getSuperior_ID());
                            superiorTableString = superior.getFirst_name().replace("'", "") + " " + superior.getLast_name().replace("'", "");
                        } else {
                            superiorTableString = "&nbsp;";
                        }
                        employeeTableString.append("<tr><td>");
                        employeeTableString.append(employee.getEmployee_number());
                        employeeTableString.append("</td><td>");
                        employeeTableString.append(employee.getFirst_name().replace("'", ""));
                        employeeTableString.append("</td><td>");
                        employeeTableString.append(employee.getLast_name().replace("'", ""));
                        employeeTableString.append("</td><td>");
                        employeeTableString.append(superiorTableString);
                        employeeTableString.append("</td><td>");
                        employeeTableString.append(employee.isActive());
                        employeeTableString.append("</td><td>");
                    }
                    request.setAttribute("employees", employeeTableString.toString());
                    request.setAttribute("employeeMode", employeeMode);
                    request.getRequestDispatcher("employee.jsp").include(request, response);
                }

                if(employeeMode.equals("update")) {

                    request.setAttribute("employeeMode", employeeMode);
                    request.getRequestDispatcher("employee.jsp").include(request, response);
                }

                if(employeeMode.equals("delete")) {

                }
            } catch(Exception ex) {
                response.getWriter().print(ex);
            }
        }
    }

    /**
     * redirect if someone tries to access the servlet directly
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").include(request, response);
    }

    private String generateEmployeeDropdown() {
        return null;
    }
}
