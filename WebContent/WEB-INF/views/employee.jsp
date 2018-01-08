<%@ page import="model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  // if not logged in, redirect to login page
    //if(session.getAttribute("auth") == null)
        //response.sendRedirect("login.jsp");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Employee Administration</title>
</head>

<body>

<table>
    <tr>
        <td>
            <form class="" action="EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="create">
                <input type="hidden" name="first_name" value="">
                <input type="hidden" name="last_name" value="">
                <input type="hidden" name="superior_id" value="">
                <input type="hidden" name="active" value="">
                <input type="submit" value="Create">
            </form>
        </td>
        <td>
            <form class="" action="EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="read">
                <input type="hidden" name="first_name" value="">
                <input type="hidden" name="last_name" value="">
                <input type="hidden" name="superior_id" value="">
                <input type="hidden" name="active" value="">
                <input type="submit" value="Read">
            </form>
        </td>
        <td>
            <form class="" action="EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="update">
                <input type="hidden" name="employee_number" value="-1">
                <input type="hidden" name="first_name" value="">
                <input type="hidden" name="last_name" value="">
                <input type="hidden" name="superior_id" value="">
                <input type="hidden" name="active" value="">
                <input type="submit" value="Update">
            </form>
        </td>
        <td>
            <form class="" action="EmployeeServlet" method="post">
                <input type="hidden" name="employeeMode" value="delete">
                <input type="hidden" name="first_name" value="">
                <input type="hidden" name="last_name" value="">
                <input type="hidden" name="superior_id" value="">
                <input type="hidden" name="active" value="">
                <input type="submit" value="Delete">
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
    <input type="hidden" name="first_name" value="">
    <input type="hidden" name="last_name" value="">
    <input type="hidden" name="superior_id" value="">
    <input type="hidden" name="active" value="">
    <table>
        <tr><td><label for="employee_number">Employee: </label><% out.print((String)request.getAttribute("employeeDropdownString")); %></td><td><button type="submit">Load Employee</button></td></tr>
    </table>
</form>

    <% if(!request.getAttribute("employee_number").equals("-1")) {
            //out.print((String)request.getAttribute("msg"));
    %>
    <p>&nbsp;</p>
    <form action="EmployeeServlet" method="post">
        <input type="hidden" name="employeeMode" value="update">
        <table>
            <tr><td><label for="first_name">First Name: </label><input id="first_name" type="text" name="first_name" value="<% out.print((String)request.getAttribute("first_name").toString().replace("'", "")); %>"></td></tr>
            <tr><td><label for="last_name">Last Name: </label><input id="last_name" type="text" name="last_name" value="<% out.print((String)request.getAttribute("last_name").toString().replace("'", "")); %>"></td></tr>
            <tr><td><label for="superior_id">Superior: </label><% out.print((String)request.getAttribute("superiorDropdownString")); %></td></tr>
            <tr><td><button type="submit">Update Employee</button></td></tr>
        </table>
    </form>

    <% } %>

<% }
} catch (Exception e) {

} %>

</body>
</html>
