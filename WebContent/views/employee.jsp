<%@ page import="model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
   	if(session.getAttribute("currentSessionUser") == null || (!session.getAttribute("currentSessionUser").equals("admin") && !session.getAttribute("currentSessionUserPassword").equals("admin"))) {
       response.sendRedirect("Homepage.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/all.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome.min.css" />
    <title>Employee Administration</title>
</head>
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
</style>
<body class="w3-light-grey">

<!-- Navigation Bar -->
<div class="w3-bar w3-red w3-large">
  <a href="${pageContext.request.contextPath}/Homepage.jsp" class="w3-bar-item w3-button w3-left w3-red w3-mobile">YACR</a>
   <%if((session.getAttribute("currentSessionUser") != null) && (session.getAttribute("currentSessionUser") == "admin")){ %>
	  
	  
	  <div>
	    <form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">
	       <button type=submit class="w3-bar-item w3-button w3-right w3-red ">Log Out</button>
	    </form>
	  </div>
	  <div class="w3-right w3-dropdown-hover">
	      <button class="w3-bar-item  w3-button">Managament</button>
	      <div class="w3-dropdown-content w3-bar-block w3-card-4">
	        <a href="${pageContext.request.contextPath}/employee.jsp" class="w3-bar-item w3-button">Employee Managament</a>
	        <a href="${pageContext.request.contextPath}/CreateAccessory.jsp" class="w3-bar-item w3-button">Create Accessory</a>
	        <a href="${pageContext.request.contextPath}/CreateVehicle.jsp" class="w3-bar-item w3-button">Create Vehicle</a>
	        <a href="${pageContext.request.contextPath}/ChangeVehicle.jsp" class="w3-bar-item w3-button">Change Vehicle</a>
	        <a href="${pageContext.request.contextPath}/DeleteCustomer.jsp" class="w3-bar-item w3-button">Customer Managament</a>
	      </div>
	    </div>
	  <a href="${pageContext.request.contextPath}/ListVehicle.jsp" class="w3-bar-item w3-button w3-right w3-red ">Vehicles</a>
  <%}%>
</div>

<div class="w3-container w3-white w3-padding-16">
<h2>Employee Management</h2>
<table>
    <tr>
        <td>
            <form class="" action="EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="create">
                <input type="hidden" name="first_name" value="">
                <input type="hidden" name="last_name" value="">
                <input type="hidden" name="superior_id" value="">
                <input type="hidden" name="active" value="">
                <input type="submit" class="w3-button w3-dark-grey" value="Create">
            </form>
        </td>
        <td>
            <form class="" action="EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="read">
                <input type="hidden" name="first_name" value="">
                <input type="hidden" name="last_name" value="">
                <input type="hidden" name="superior_id" value="">
                <input type="hidden" name="active" value="">
                <input type="submit" class="w3-button w3-dark-grey" value="Read">
            </form>
        </td>
        <td>
            <form class="" action="EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="update">
                <input type="hidden" name="employee_number" value="-1">
                <input type="hidden" name="save" value="0">
                <input type="hidden" name="first_name" value="">
                <input type="hidden" name="last_name" value="">
                <input type="hidden" name="superior_id" value="0">
                <input type="hidden" name="active" value="">
                <input type="submit" class="w3-button w3-dark-grey" value="Update">
            </form>
        </td>
        <td>
            <form class="" action="EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="delete">
                <input type="hidden" name="first_name" value="">
                <input type="hidden" name="last_name" value="">
                <input type="hidden" name="superior_id" value="">
                <input type="hidden" name="active" value="">
                <input type="submit" class="w3-button w3-dark-grey" value="Change Customer Data">
            </form>
        </td>
        <td>
	        <form action="/EmployeeServlet" method="post">
		        <input type="hidden" name="employeeMode" value="fillData">
		        <div>
		            <p><button type=submit class="w3-button w3-dark-grey">Start Datafilling</button></p>
		        </div>
    		</form>
        </td>
        <td>
            <form action="/EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="migrate">
                <div>
                    <p><button type=submit class="w3-button w3-dark-grey">Migrate to MongoDB</button></p>
                </div>
            </form>
        </td>
        <td>
            <form action="/EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="loadProxy">
                <input type="hidden" name="dbmode" value="<% if(request.getAttribute("dbmode").equals("mongodb")) {out.print("mysql");} else {out.print("mongodb");} %>">
                <div>
                    <p><button type=submit class="w3-button w3-dark-grey">Switch to <% if(request.getAttribute("dbmode").equals("mongodb")) {out.print("MySQL");} else {out.print("MongoDB");} %></button></p>
                </div>
            </form>
        </td>
    </tr>
</table>

<% try {
    if(request.getAttribute("employeeMode").equals("create")) {
        //out.print((String)request.getAttribute("msg"));
%>
<p>&nbsp;</p>
<form action="EmployeeServlet" method="post">
    <input type="hidden" name="employeeMode" value="create">
    <table>
        <tr><td><label for="first_name">First Name: </label><input id="first_name" type="text" name="first_name"></td></tr>
        <tr><td><label for="last_name">Last Name: </label><input id="last_name" type="text" name="last_name"></td></tr>
        <tr><td><label for="superior_id">Superior: </label><% out.print((String)request.getAttribute("superiorDropdownString")); %></td></tr>
        <tr><td><button type="submit">Create Employee</button></td></tr>
    </table>
</form>

<% }
} catch (Exception e) {

} %>

<% try {
    if(request.getAttribute("employeeMode").equals("read")) {
%>
<table>
    <tr>
        <td>ID</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Superior</td>
        <td>Active</td>
    </tr>
<%
    out.print((String)request.getAttribute("employees"));
%>
</table>
<%
    }
} catch (Exception e) {

} %>

<% try {
    if(request.getAttribute("employeeMode").equals("update")) {
        //out.print((String)request.getAttribute("msg"));
%>
<p>&nbsp;</p>
<form action="EmployeeServlet" method="post">
    <input type="hidden" name="employeeMode" value="update">
    <input type="hidden" name="save" value="0">
    <input type="hidden" name="first_name" value="">
    <input type="hidden" name="last_name" value="">
    <input type="hidden" name="superior_id" value="0">
    <input type="hidden" name="active" value="">
    <table>
        <tr><td><label for="employee_number">Employee: </label><% out.print((String)request.getAttribute("employeeDropdownString")); %></td><td><button type="submit">Load Employee</button></td></tr>
    </table>
</form>

    <% if(!request.getAttribute("employee_number").equals("-1")) {
            //out.print((String)request.getAttribute("save"));
    %>
    <p>&nbsp;</p>
    <form action="EmployeeServlet" method="post">
        <input type="hidden" name="employeeMode" value="update">
        <input type="hidden" name="save" value="1">
        <input type="hidden" name="employee_number" value="<% out.print((String)request.getAttribute("employee_number").toString().replace("'", "")); %>">
        <table>
            <tr><td><label for="first_name">First Name: </label><input id="first_name" type="text" name="first_name" value="<% out.print((String)request.getAttribute("first_name").toString().replace("'", "")); %>"></td></tr>
            <tr><td><label for="last_name">Last Name: </label><input id="last_name" type="text" name="last_name" value="<% out.print((String)request.getAttribute("last_name").toString().replace("'", "")); %>"></td></tr>
            <tr><td><label for="superior_id">Superior: </label><% out.print((String)request.getAttribute("superiorDropdownString")); %></td></tr>
            <tr><td><label for="active"></label>Active: <input type="checkbox" name="active" id="active" <% if((Boolean)request.getAttribute("active")) { out.print("value=\"true\" checked"); } else { out.print("value=\"true\""); } %>></td></tr>
            <tr><td><button type="submit">Update Employee</button></td></tr>
        </table>
    </form>

    <% } %>

<% }
} catch (Exception e) {

} %>


</div>
</body>
</html>
