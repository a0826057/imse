<%@ page language="java" import="java.util.*, java.lang.*" %>
<%@ page language="java" import="model.*, java.lang.*" %>
<%@ page language="java" import="dao.*, java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Vehicle</title>
</head>
<body>

	<div>
		<div>
			<h1> List of Vehicles </h1>
		</div>
		<div>
       <label>Select Vehicle Type:</label>
           <select name="vehicleType" required>
         		<option value="CAR">Car</option>
           		<option value="TRUCK">Truck</option>         
            </select>
		</div>
		<form  action="${pageContext.request.contextPath}/ListVehicle" method="get">
     		<%  String type = (String)session.getValue("vehicleType");
     			java.util.ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
     			java.util.ArrayList<Vehicle> trucks = new ArrayList<Vehicle>();
     			
		    	if((type != null)){
		    		if(type.equalsIgnoreCase("CAR")){
		    			 cars =  (java.util.ArrayList<Vehicle>) session.getAttribute("carList");
		    		}else{
			    		 trucks =  (java.util.ArrayList<Vehicle>) session.getAttribute("truckList");}
		    		if(type=="CAR"){  %>	
		<table>
			<thead>
				<tr>
					<th>Vehicle ID</th>
					<th>License Plate Number</th>
					<th>Color ID</th>
					<th>Model ID</th>
					<th>Manufacturer ID</th>
					<th>Acessory ID</th>
					<th>Mileage</th>
					<th>Manufacturer Year</th>
					<th>Active</th>
					<th>Add</th>
					<th>Add</th>
								
				</tr>
			</thead>
			<tbody>
				<%for(int i = 0; i < cars.size(); i++){
            		Car car = (Car)cars.get(i);
            		
            	%>
            <tr>
		       <td><% out.print(car.getVehicle_ID()); %></td>
		       <td><% out.print(car.getLicense_plate_number()); %></td>
		       <td><% out.print(car.getColor().getColor_ID()); %></td>
		       <td><% out.print(car.getModel().getModel_ID()); %></td>
		       <td><% out.print(car.getManufactur().getManufacturer_ID()); %></td>
		       <td><% out.print(car.getAccessory()); %></td>
		       <td><% out.print(car.getMileage()); %></td>
		       <td><% out.print(car.getManufacture_year()); %></td>
		       <td><% out.print(car.getActive()); %></td>
		       <td><% out.print(car.getDoors()); %></td>
		       <td><% out.print(car.getPassenger_limit()); %></td>
		       <td>
			   <form action="${pageContext.request.contextPath}/ChangeVehicle" method="post">
    				<button name="edit" value="<% car.getVehicle_ID();%>">Edit</button>
				</form>
			       
		       </td>
		       <td>
			       <form action="${pageContext.request.contextPath}/DeleteVehicle" method="post">
			       		<button name="delete" value="<% car.getVehicle_ID();%>">Delete</button>
			       </form>
		       </td>
		    </tr>
			</tbody>
			</table>
			<%
			}} 
			else{ %>
				<table>
			<thead>
				<tr>
					<th>Vehicle ID</th>
					<th>License Plate Number</th>
					<th>Color ID</th>
					<th>Model ID</th>
					<th>Manufacturer ID</th>
					<th>Acessory ID</th>
					<th>Mileage</th>
					<th>Manufacturer Year</th>
					<th>Active</th>
					<th>Lengt</th>
					<th>Height</th>
					<th>Load Limit</th>
								
				</tr>
			</thead>
			<tbody>
				<%for(int i = 0; i < trucks.size(); i++){
            		Truck truck = (Truck)trucks.get(i);
            	%>
            <tr>
		       <td><% out.print(truck.getVehicle_ID()); %></td>
		       <td><% out.print(truck.getLicense_plate_number()); %></td>
		       <td><% out.print(truck.getColor().getColor_ID()); %></td>
		       <td><% out.print(truck.getModel().getModel_ID()); %></td>
		       <td><% out.print(truck.getManufactur().getManufacturer_ID()); %></td>
		       <td><% out.print(truck.getAccessory()); %></td>
		       <td><% out.print(truck.getMileage()); %></td>
		       <td><% out.print(truck.getManufacture_year()); %></td>
		       <td><% out.print(truck.getActive()); %></td>
		       <td><% out.print(truck.getLenght()); %></td>
		       <td><% out.print(truck.getHeight()); %></td>
		       <td><% out.print(truck.getLoading_limit()); %></td>
		       <td>
			   <form action="${pageContext.request.contextPath}/ChangeVehicle" method="post">
    				<button name="edit" value="<% truck.getVehicle_ID();%>">Edit</button>
				</form>
			       
		       </td>
		       <td>
			       <form action="${pageContext.request.contextPath}/DeleteVehicle" method="post">
			       		<button name="delete" value="<% truck.getVehicle_ID();%>">Delete</button>
			       </form>
		       </td>
		    </tr>
			</tbody>
			</table>
			<%}}}
			 else{%>
			 <div><h1>Select a vehicle type</h1></div>
			 <%} %>
			</form>	
		</div>
	 <div><a href="CreateVehicle.jsp"><button name="create" >Create Vehicle</button></a></div>

<form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">      
        <div><p><button type=submit>LogOut</button></p></div></form>   
    <a href="${pageContext.request.contextPath}/Homepage.jsp">Home</a> 	   
</body>
</html>