<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*, java.lang.*" %>
<%@ page language="java" import="model.*, java.lang.*" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rent Vehicle</title>
</head>
<body>
	<h2><center>Welcome to Rent Managament!</center></h2>
	<h3>View My Rents: <a href="view_rents.jsp">View</a></h3>
	<h3>Search the availability of a vehicle:</h3>
	<form id="rent"  action="${pageContext.request.contextPath}/RentVehicleServlet" method="get">
         Date From (DD.MM.YYYY): <input type = "text" name = "date_from" value=<%if(session.getAttribute("date_from") != null) out.print(session.getAttribute("date_from").toString()); %>>
         <br/>
         Date To (DD.MM.YYYY): <input type = "text" name = "date_to"  value=<%if(session.getAttribute("date_to") != null) out.print(session.getAttribute("date_to").toString()); %>>
         <br>
         <input type = "submit" value = "Search" />
     </form>
     <br>
     <% java.util.ArrayList<Vehicle> users = new ArrayList<Vehicle>();
     
    	if((session.getAttribute("list") != null) && (session.getAttribute("date_from") != null) && (session.getAttribute("date_to") != null)){
    		users = (java.util.ArrayList<Vehicle>) session.getAttribute("list");
    		out.print("The search result for " + session.getAttribute("date_from").toString() + "-" + session.getAttribute("date_to").toString() );
    	}
    		%>
    		
    <table border="1" cellpadding="5">
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
		       		<button TYPE="submit" NAME="button" VALUE=<%=veh.getVehicle_ID()%> >Book It!</button>
		       </form>
		       </td>
		    </tr>
            <%} %>
   	</table>
   	
   	<form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">      
        <div>
      <p><button type=submit>LogOut</button></p>
        </div>
    </form>   
    <a href="${pageContext.request.contextPath}/Homepage.jsp">Home</a> 
    
</body>
</html>