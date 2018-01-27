package servlets;

import dao.EmployeeDAO;
import dao.Proxy;
import datagenerate.DataGenerator;
import datagenerate.DataGeneratorM;
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
    private EmployeeDAO employeeDAO;

    /**
     * handles the employee use case
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeDAO = Proxy.getInstance().getEmployeeDAO();
        String employeeMode = request.getParameter("employeeMode");
        request.setAttribute("dbmode", Proxy.getDbmode());

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
                    if(!first_name.isEmpty() && !last_name.isEmpty() && employeeMode.equals("create")) {
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
                    superiorDropdownString.append("</select>");
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
                    employees = new ArrayList(employeeDAO.getEmployeeList());
                    StringBuilder employeeDropdownString = new StringBuilder();
                    employeeDropdownString.append("<select name=\"employee_number\">");
                    employeeDropdownString.append("<option value=\"-1\">None</option>");

                    for(Employee employee : employees) {
                        //if(employee.isActive()) {
                            employeeDropdownString.append("<option value=\"" + employee.getEmployee_number() + "\">");
                            employeeDropdownString.append(employee.getEmployee_number() + ": " + employee.getFirst_name().replace("'", "") + " " + employee.getLast_name().replace("'", ""));
                            employeeDropdownString.append("</option>");
                        //} else {
                        //    employeeDropdownString.append("<option value=\"none\">None</option>");
                        //}
                    }
                    employeeDropdownString.append("</select>");
                    superior_id = Integer.parseInt(request.getParameter("superior_id"));

                    if(!request.getParameter("employee_number").equals("-1")) {
                        Employee employee = employeeDAO.getEmployeeById(Integer.parseInt(request.getParameter("employee_number")));
                        request.setAttribute("employee_number", employee.getEmployee_number());
                        request.setAttribute("first_name", employee.getFirst_name());
                        request.setAttribute("last_name", employee.getLast_name());
                        superior_id = employee.getSuperior_ID();
                        request.setAttribute("superior_id", superior_id);
                        request.setAttribute("active", employee.isActive());
                    }

                    StringBuilder superiorDropdownString = new StringBuilder();
                    superiorDropdownString.append("<select name=\"superior_id\">");
                    superiorDropdownString.append("<option value=\"-1\">None</option>");

                    for(Employee employee : employees) {
                        if(employee.isActive()) {
                            superiorDropdownString.append("<option ");
                            if(employee.getEmployee_number() == superior_id) {
                                superiorDropdownString.append("selected ");
                            }
                            superiorDropdownString.append("value=\"" + employee.getEmployee_number() + "\">");
                            superiorDropdownString.append(employee.getEmployee_number() + ": " + employee.getFirst_name().replace("'", "") + " " + employee.getLast_name().replace("'", ""));
                            superiorDropdownString.append("</option>");
                        } else {
                            //superiorDropdownString.append("<option value=\"none\">None</option>");
                        }
                    }
                    superiorDropdownString.append("</select>");
                    request.setAttribute("employee_number", request.getParameter("employee_number"));
                    if(request.getParameter("save").equals("1")) {
                        Employee updateEmployee = new Employee(Integer.parseInt(request.getParameter("employee_number")), request.getParameter("first_name"), request.getParameter("last_name"), Integer.parseInt(request.getParameter("superior_id")), Boolean.parseBoolean(request.getParameter(("active"))));
                        employeeDAO.changeEmployee(updateEmployee);
                        request.setAttribute("employee_number", "-1");
                        request.setAttribute("msg", "Employee updated!");
                    }
                    request.setAttribute("superiorDropdownString", superiorDropdownString.toString());
                    request.setAttribute("employeeDropdownString", employeeDropdownString.toString());
                    request.setAttribute("employeeMode", employeeMode);
                    request.setAttribute("save", "0");
                    request.getRequestDispatcher("employee.jsp").include(request, response);
                }

                if(employeeMode.equals("delete")) {
                    request.getRequestDispatcher("DeleteCustomer.jsp").include(request, response);
                }
            } catch(Exception ex) {
                response.getWriter().print(ex);
            }
        }

        if (request.getParameter("employeeMode").equals("fillData")) {
            DataGenerator.main(new String[0]);
            request.setAttribute("msg", "Data loading complete!");
            request.getRequestDispatcher("Homepage.jsp").include(request, response);
        }

        if (request.getParameter("employeeMode").equals("loadProxy")) {
            Proxy.getInstance(request.getParameter("dbmode"));
            request.setAttribute("dbmode", Proxy.getDbmode());
            DataGeneratorM.main(new String[0]);
            request.getRequestDispatcher("Homepage.jsp").include(request, response);
        }
        if (request.getParameter("employeeMode").equals("migrate")) {
            DataGeneratorM.main(new String[0]);
            request.setAttribute("msg", "Data migration complete!");
            request.getRequestDispatcher("Homepage.jsp").include(request, response);
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
}
