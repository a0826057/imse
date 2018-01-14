<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*, java.lang.*" %>
<%@ page language="java" import="model.*, java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rent Vehicle</title>
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
  <a href="" class="w3-bar-item w3-button w3-right w3-red">Register</a>
  <a href="#contact" class="w3-bar-item w3-button w3-right w3-red ">Sign In</a>
  <a href="#rooms" class="w3-bar-item w3-button w3-right w3-red ">Cars</a>
  <a href="${pageContext.request.contextPath}/rent_vehicle.jsp" class="w3-bar-item w3-button w3-right w3-red">Rent</a>
</div>

 <div class="w3-container w3-white w3-padding-16">
   <form id="rent"  action="${pageContext.request.contextPath}/RentVehicleServlet" method="get">
     <div class="w3-row-padding" style="margin:0 -16px;">
       <div class="w3-half w3-margin-bottom">
         <label>Rent From</label>
         <input class="w3-input w3-border" type="text"  name="date_from" value=<%if(session.getAttribute("date_from") != null) out.print(session.getAttribute("date_from").toString()); %>>
       </div>
       <div class="w3-half">
         <label>Rent To</label>
         <input class="w3-input w3-border" type="text" name="date_to" value=<%if(session.getAttribute("date_to") != null) out.print(session.getAttribute("date_to").toString());%>>
       </div>
     </div>
     <button class="w3-button w3-dark-grey" type="submit">Search availability</button>
     <a href="${pageContext.request.contextPath}/view_rents.jsp" class="w3-button w3-dark-grey w3-right">View My Rents</a>
   </form>
 </div>
<!-- Page content -->
<div class="w3-content" style="max-width:1532px;">
	<br>
     <% java.util.ArrayList<Vehicle> users = new ArrayList<Vehicle>();
     
    	if((session.getAttribute("list") != null) && (session.getAttribute("date_from") != null) && (session.getAttribute("date_to") != null)){
    		users = (java.util.ArrayList<Vehicle>) session.getAttribute("list");
    	}
    		%>
    		
    <table border="1" cellpadding="5"  class="w3-table-all">
            <tr>
                <th>Vehicle ID</th>
                <th>Licence Plate Number</th>
                <th>Color</th>
                <th>Model</th>
                <th>Manufacturer</th>
                <th>Accessory</th>
                <th>Mileage</th>
                <th>Year</th>
                <th>Active</th>
                <th></th>
            </tr>
            <%for(int i = 0; i < users.size(); i++){
            	Vehicle veh = (Vehicle)users.get(i);
            %>
            <tr>
		       <td><% out.print(veh.getVehicle_ID()); %></td>
		       <td><% out.print(veh.getLicense_plate_number()); %></td>
		       <td><% out.print(veh.getColor().getDescription()); %></td>
		       <td><% out.print(veh.getModel().getDescription()); %></td>
		       <td><% out.print(veh.getManufactur().getName()); %></td>
		       <td>
		       <% for(int j = 0; j < veh.getAccessory().size(); j++)
		    	   out.print(veh.getAccessory().get(j).getName()); %>
		       </td>
		       <td><% out.print(veh.getMileage()); %></td>
		       <td><% out.print(veh.getManufacture_year()); %></td>
		       <td><% out.print(veh.getActive()); %></td>
		       <td>
		       <form action="${pageContext.request.contextPath}/RentVehicleServlet" method="post">
		       		<button class="w3-button w3-blue" TYPE="submit" NAME="button" VALUE=<%=veh.getVehicle_ID()%> >Book It!</button>
		       </form>
		       </td>
		    </tr>
            <%} %>
   	</table>
</div>
</body>
</html>