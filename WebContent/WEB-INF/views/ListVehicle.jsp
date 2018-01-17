<%@ page language="java" import="java.util.*, java.lang.*" %>
<%@ page language="java" import="model.*, java.lang.*" %>
<%@ page language="java" import="dao.*, java.lang.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Vehicle</title>
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

<div class="w3-container w3-white w3-padding-16">
	<div>
		<div>
			<h1> List of Vehicles </h1>
		</div>
		
		<div>
	<form  action="${pageContext.request.contextPath}/ListVehicle" method="get">
		
       <label>Select Vehicle Type:</label>
       <!-- <form action="ListVehicle.jsp"> -->
           <select name="vehicleType" required>
           		<option value="null">Select</option>
         		<option value="CAR">Car</option>
           		<option value="TRUCK">Truck</option>         
            </select>
            <input type="submit" name="<%=request.getParameter("vehicleType")%>" value="submit">
        <!-- </form> -->
		</div>

				
		
     		<%  //String type = (String)session.getAttribute("vehicleType");
     			String type = (String)request.getParameter("vehicleType");
     			
     			java.util.ArrayList<Car> cars = new ArrayList<Car>();
     			java.util.ArrayList<Truck> trucks = new ArrayList<Truck>();
     			
     			System.out.println(type);
		    	if(type != null){
		    		cars =  (java.util.ArrayList<Car>) session.getAttribute("list_car");
		    	 	trucks =  (java.util.ArrayList<Truck>) session.getAttribute("list_truck");
		    	}
		    	if(type.equals("CAR")){ 
		    	System.out.println("Inside the car");	
		    	%>	
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
					<th>Doors</th>
					<th>Passengers Limit</th>
								
					</tr>
					</thead>
					<tbody>
				<% for(int i = 0; i < cars.size(); i++){
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
			   <form action="${pageContext.request.contextPath}/ChangeVehicle.jsp" method="post">
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
		    else if(type=="TRUCK"){ %>
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
			   <form action="${pageContext.request.contextPath}/ChangeVehicle.jsp" method="post">
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
			<%}}
			 else{%>
			 <div><h1>Select a vehicle type</h1></div>
			 <%} %>
			</form>	
		</div>
	 <div><a href="CreateVehicle.jsp"><button name="create" class="w3-button w3-dark-grey" >Create Vehicle</button></a></div>

<form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post" >      
        <div><p><button type=submit class="w3-button w3-dark-grey">LogOut</button></p></div>
</form>    
</div>

<script>
var e = document.getElementById(vehicleType);
var strVehicle = e.options[e.selectedIndex].value;
</script>
</body>
</html>