<%@ page import="dao.Proxy" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<% Proxy.getInstance(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CarRent</title>
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
  <%}else if(session.getAttribute("currentSessionUser") != null){ %>
  	   <div>
	    <form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">
	       <button type=submit class="w3-bar-item w3-button w3-right w3-red ">Log Out</button>
	    </form>
	  </div>
  	  <a href="${pageContext.request.contextPath}/Profil.jsp" class="w3-bar-item w3-button w3-right w3-red ">Profil</a>
	  <a href="${pageContext.request.contextPath}/rent_vehicle.jsp" class="w3-bar-item w3-button w3-right w3-red">Rent</a>
  <%} %>
</div>

<%
  if(request.getAttribute("msg") != null) {
      out.print(request.getAttribute("msg"));
  }
%>
	<div class="w3-content" style="max-width:1532px;">
		<h1>Rent a Car</h1>
		
		We are giving You the possibility to rent a car all over the world!
		Choose from thousands of different car models and explore various cities.
		<br>
		
		<%if(session.getAttribute("currentSessionUser") == null){ %>
			<a href="${pageContext.request.contextPath}/SignUp.jsp" class="w3-button w3-dark-grey">SignUp</a>
			<a href="${pageContext.request.contextPath}/LogIn.jsp" class="w3-button w3-dark-grey">LogIn</a>
			<a href="${pageContext.request.contextPath}/AdminLogIn.jsp" class="w3-button w3-dark-grey">Employee</a>
		<%}%>
	</div>
	
<div class="w3-container w3-white w3-padding-16">
<form  action="${pageContext.request.contextPath}/SearchVehicle" method="get" class="w3-container w3-card-4 w3-light-grey">
	<div><h3>Search Vehicle</h3></div>

    	<p>
        	<% 
         		java.util.ArrayList <Color> colors = new ArrayList<Color>();
           		if((session.getAttribute("colorList") != null)){
        	   		colors = (java.util.ArrayList<Color>) session.getAttribute("colorList");
	       		}
           	%>
  			<label>Color:</label>
    		<select name="colorId" class="w3-select" style="width:30%">  
    		<% 
    			for(int c = 0; c < colors.size(); c++){
       				Color co = (Color)colors.get(c); 
       		%>       	
        	<option value="<%=co.getColor_ID()%>" ><%=co.getDescription()%></option>
  			<%} %>
    		</select>
        </p>
        <p>
         	<% 
          		java.util.ArrayList <Model> models = new ArrayList<Model>();
          		if((session.getAttribute("modelList") != null)){
        	  		models = (java.util.ArrayList<Model>) session.getAttribute("modelList");
	      		}
  			%>
  			<label>Model:</label>
    		<select name="modelId" class="w3-select" style="width:30%">
    		<% 
    			for(int m = 0; m < models.size(); m++){
         			Model mo = (Model)models.get(m);
         	%>         	
  			<option value="<%=mo.getModel_ID()%>" ><%= mo.getDescription() %></option>
  			<%} %>
    		</select>
        </p>
        <p>
         	<% 
         		java.util.ArrayList <Manufacturer> manufacturers = new ArrayList<Manufacturer>();
         		if((session.getAttribute("manufacturerList") != null)){
        	 		manufacturers = (java.util.ArrayList<Manufacturer>) session.getAttribute("manufacturerList");
	      		}
  			%>
  			<label>Manufacturer:</label>
    		<select name="manufacturerId" class="w3-select" style="width:30%">    
    		<% 
    			for(int ma = 0; ma < manufacturers.size(); ma++){
        			Manufacturer man = (Manufacturer)manufacturers.get(ma);
        	%>     	
        	<option value="<%=man.getManufacturer_ID()%>" ><%=man.getName() %></option>
  			<% } %>
    		</select>
        </p>
        <p>
        	<% 
          		java.util.ArrayList <Accessory> accessories = new ArrayList<Accessory>();
         		if((session.getAttribute("accessoryList") != null)){
        	 		accessories = (java.util.ArrayList<Accessory>) session.getAttribute("accessoryList");
	      		}
       		%>
  			<label>Accessories:</label>
    		<select name="accessoryId" class="w3-select" style="width:30%">  
			<%  
				for(int a = 0; a < accessories.size(); a++){
        			Accessory ac = (Accessory)accessories.get(a);
  			%>      	
        	<option value="<%=ac.getAccessory_ID()%>" ><%= ac.getDescription() %></option>
  			<% } %>
    		</select>
        </p>
              
        <div>
        <form id="SearchVehicle" action="${pageContext.request.contextPath}/SearchVehicle" method="post">
      	<p><button type=submit class="w3-button w3-dark-grey" name="SearchVehicle">Search Vehicle</button></p>
      	</form>
       	</div> 
       	<%} %>
       	
       	<br>			
     		<% 
     			java.util.ArrayList<Vehicle> v = new ArrayList<Vehicle>();
		    	v =  (java.util.ArrayList<Vehicle>) session.getAttribute("list_vehicle");
   	
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
					</tr>
					</thead>
					<tbody>
				<% for(int i = 0; i < v.size(); i++){
            		Vehicle car = (Vehicle)v.get(i);
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
		    </tr>
			</tbody>
			</table>
</form>
</div>

</body>
</html>