<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*, java.lang.*" %>
<%@ page language="java" import="model.*, java.lang.*" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View My Rents</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/all.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome.min.css" />
</head>
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
</style>
<body class="w3-light-grey">
	
	<!-- Navigation Bar -->
<div class="w3-bar w3-red w3-large">
  <a href="${pageContext.request.contextPath}/Homepage.jsp" class="w3-bar-item w3-button w3-left w3-red w3-mobile">YACR</a>
  <a href="${pageContext.request.contextPath}/Profil.jsp" class="w3-bar-item w3-button w3-right w3-red ">Profil</a>
  <div class="w3-right w3-dropdown-hover">
      <button class="w3-bar-item  w3-button">Managament</button>
      <div class="w3-dropdown-content w3-bar-block w3-card-4">
        <a href="${pageContext.request.contextPath}/employee.jsp" class="w3-bar-item w3-button">Employee Managament</a>
        <a href="${pageContext.request.contextPath}/CreateAccessory.jsp" class="w3-bar-item w3-button">Create Accessory</a>
        <a href="${pageContext.request.contextPath}/CreateVehicle.jsp" class="w3-bar-item w3-button">Create Vehicle</a>
        <a href="${pageContext.request.contextPath}/DeleteCustomer.jsp" class="w3-bar-item w3-button">Delete Customer</a>
      </div>
    </div>
  <a href="${pageContext.request.contextPath}/ListVehicle.jsp" class="w3-bar-item w3-button w3-right w3-red ">Vehicles</a>
  <a href="${pageContext.request.contextPath}/rent_vehicle.jsp" class="w3-bar-item w3-button w3-right w3-red">Rent</a>
</div>
   	  
 	<div class="w3-content" style="max-width:1532px;">
	 <form id="viewRents"  action="${pageContext.request.contextPath}/ViewRentsServlet" method="get">
	 	<div class="w3-center" style="padding: 10px 0px;"><button class="w3-button w3-dark-grey" type="submit">Load</button></div>
	     <% java.util.ArrayList<Rental> users = new ArrayList<Rental>();
	     
	    	if((session.getAttribute("list_rentals") != null)){
	    		users = (java.util.ArrayList<Rental>) session.getAttribute("list_rentals");
	    	}
	    		%>
	  </form>		
	    <table border="1" cellpadding="5" class="w3-table-all">
	            <tr>
	                <th>Vehicle ID</th>
	                <th>Customer ID</th>
	                <th>Employee ID</th>
	                <th>From</th>
	                <th>To</th>
	                <th>Rating</th>
	                <th></th>
	            </tr>
	            <%for(int i = 0; i < users.size(); i++){
	            	Rental ren = (Rental)users.get(i);
	            %>
	            <tr>
			       <td><% out.print(ren.getVehicle().getVehicle_ID()); %></td>
			       <td><% out.print(ren.getCostumer().getCostumer_ID()); %></td>
			       <td><% out.print(ren.getEmployee().getEmployee_number()); %></td>
			       <td><% out.print(ren.getDate_from()); %></td>
			       <td><% out.print(ren.getDate_to()); %></td>
			       <td><% out.print(ren.getRating()); %></td>
			       <td>
			       <form action="${pageContext.request.contextPath}/ViewRentsServlet" method="post">
			       		<%session.setAttribute("list_rent", users); %>
			       		<button class="w3-button w3-red" TYPE="submit" NAME="button" value=<%=i%>>Delete</button>
			       </form>
			       </td>
			    </tr>
	            <%} %>
	   	</table>
		<br>
		<form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">      
	        <div>
	      <p><button type=submit>LogOut</button></p>
	        </div>
	    </form>   
	</div>   
</body>
</html>