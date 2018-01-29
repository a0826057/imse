<%@ page language="java" import="java.util.*, java.lang.*" %>
<%@ page language="java" import="model.*, java.lang.*" %>
<%@ page language="java" import="dao.*, java.lang.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Vehicles</title>
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
	<div>
		<div>
			<h3> List of Vehicles </h3>
		</div>
		
		<div>
<form  action="${pageContext.request.contextPath}/ListVehicle" method="get">
		
       <label>Select Vehicle Type:</label>
       <!-- <form action="ListVehicle.jsp"> -->
           <select name="vehicleType" required class="w3-select" style="width:30%">
           		<option value="null">Select</option>
         		<option value="CAR">Car</option>
           		<option value="TRUCK">Truck</option>         
            </select>
            <input type="submit" name="<%=request.getParameter("vehicleType")%>" value="submit" class="w3-button w3-white w3-border w3-border-red w3-round-large">
        <!-- </form> -->
		</div>
			<br>			
     		<%  //String type = (String)session.getAttribute("vehicleType");
     			String type = (String)request.getParameter("vehicleType");
     			String sid;
     			java.util.ArrayList<Car> cars = new ArrayList<Car>();
     			java.util.ArrayList<Truck> trucks = new ArrayList<Truck>();
     			
		    	if((type != null)){
		    		cars =  (java.util.ArrayList<Car>) session.getAttribute("list_car");
		    	 	trucks =  (java.util.ArrayList<Truck>) session.getAttribute("list_truck");
		    	}
		    		    	
		    	if("CAR".equals(type)){    	
		    	%>	
				<table class="w3-table-all">
					<thead>
					<tr class="w3-red">
					<th>Vehicle ID</th>
					<th>License Plate Number</th>
					<th>Color </th>
					<th>Model </th>
					<th>Manufacturer </th>
					<th>Accessory </th>
					<th>Mileage</th>
					<th>Manufacturer Year</th>
					<th>Active</th>
					<th>Doors</th>
					<th>Passengers Limit</th>
					<th></th>
					<th></th>			
					</tr>
					</thead>
					<tbody>
				<% for(int i = 0; i < cars.size(); i++){
            		Car car = (Car)cars.get(i);
            	%>
            <tr>
		       <td><% out.print(car.getVehicle_ID()); 
		       		 sid =  String.valueOf(car.getVehicle_ID());%>
		       <td><% out.print(car.getLicense_plate_number()); %></td>
		       <td><% out.print(car.getColor().getDescription()); %></td>
		       <td><% out.print(car.getModel().getDescription()); %></td>
		       <td><% out.print(car.getManufactur().getName()); %></td>
		       <td><% for(int j = 0; j < car.getAccessory().size(); j++)
		    	  	 out.print(car.getAccessory().get(j).getName()); %> </td>
		       <td><% out.print(car.getMileage()); %></td>
		       <td><% out.print(car.getManufacture_year()); %></td>
		       <td><% out.print(car.getActive()); %></td>
		       <td><% out.print(car.getDoors()); %></td>
		       <td><% out.print(car.getPassenger_limit()); %></td>
		       <td> <%System.out.println(sid); %>
			   <form action="ChangeVehicle.jsp" method="post">
    				<button NAME="edit" type="submit" value="<%=sid%>">Edit</button>
				</form>
		       </td>
		        <td>
		        <form action="${pageContext.request.contextPath}/DeleteVehicle" method="post">
			       		<button name="delete" type="submit" value=<%=sid%>>Delete</button>
			    </form>       
		       </td>
		    </tr>
		    <%} %>
			</tbody>
			</table>
			<%
			}
		    else if("TRUCK".equals(type)){ %>
				<table class="w3-table-all">
			<thead>
				<tr class="w3-red">
					<th>Vehicle ID</th>
					<th>License Plate Number</th>
					<th>Color </th>
					<th>Model </th>
					<th>Manufacturer</th>
					<th>Accessory </th>
					<th>Mileage</th>
					<th>Manufacturer Year</th>
					<th>Active</th>
					<th>Length</th>
					<th>Height</th>
					<th>Load Limit</th>
					<th></th>
					<th></th>			
				</tr>
			</thead>
			<tbody>
				<%for(int i = 0; i < trucks.size(); i++){
            		Truck truck = (Truck)trucks.get(i);
            	%>
            <tr>
		       <td><% out.print(truck.getVehicle_ID());
		       	 sid =  String.valueOf(truck.getVehicle_ID());%></td>
		       <td><% out.print(truck.getLicense_plate_number()); %></td>
		       <td><% out.print(truck.getColor().getDescription()); %></td>
		       <td><% out.print(truck.getModel().getDescription()); %></td>
		       <td><% out.print(truck.getManufactur().getName()); %></td>
		       <td>
		       <% for(int j = 0; j < truck.getAccessory().size(); j++)
		    	  out.print(truck.getAccessory().get(j).getName()); %></td>
		       <td><% out.print(truck.getMileage()); %></td>
		       <td><% out.print(truck.getManufacture_year()); %></td>
		       <td><% out.print(truck.getActive()); %></td>
		       <td><% out.print(truck.getLenght()); %></td>
		       <td><% out.print(truck.getHeight()); %></td>
		       <td><% out.print(truck.getLoading_limit()); %></td>
		       <td>
			   <form action="ChangeVehicle.jsp" method="post">
    				<button NAME="edit" type="submit" value="<%=sid%>">Edit</button>
				</form>
		       </td>
		       <td>
			       <form action="${pageContext.request.contextPath}/DeleteVehicle" method="post">
			       		<button NAME="delete" type="submit" value=<%=sid%>>Delete</button>
			       </form>
		       </td>
		    </tr>
		    <%} %>
			</tbody>
			</table>
			<%} 
			 else{%>
			 <div align="center"><h4>You have to select a vehicle type for display</h4></div>
			 <%} %>
			</form>	
		</div>
	 <div><a href="CreateVehicle.jsp"><button name="create" class="w3-button w3-dark-grey" >Create Vehicle</button></a></div>
</div>


</body>
</html>