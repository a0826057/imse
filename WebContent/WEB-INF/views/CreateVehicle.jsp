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
   <%if((session.getAttribute("currentSessionUser") != null) && (session.getAttribute("currentSessionUser") == "admin")){ %>
	  
	  <div class="w3-right w3-dropdown-hover">
	      <button class="w3-bar-item  w3-button">Managament</button>
	      <div class="w3-dropdown-content w3-bar-block w3-card-4">
	        <a href="${pageContext.request.contextPath}/employee.jsp" class="w3-bar-item w3-button">Employee Managament</a>
	        <a href="${pageContext.request.contextPath}/CreateAccessory.jsp" class="w3-bar-item w3-button">Create Accessory</a>
	        <a href="${pageContext.request.contextPath}/CreateVehicle.jsp" class="w3-bar-item w3-button">Create Vehicle</a>
	        <a href="${pageContext.request.contextPath}/DeleteCustomer.jsp" class="w3-bar-item w3-button">Customer Managament</a>
	      </div>
	    </div>
	  <a href="${pageContext.request.contextPath}/ListVehicle.jsp" class="w3-bar-item w3-button w3-right w3-red ">Vehicles</a>
  <%}else if(session.getAttribute("currentSessionUser") != null){ %>
  	  <a href="${pageContext.request.contextPath}/Profil.jsp" class="w3-bar-item w3-button w3-right w3-red ">Profil</a>
	  <a href="${pageContext.request.contextPath}/rent_vehicle.jsp" class="w3-bar-item w3-button w3-right w3-red">Rent</a>
  <%} %>
</div>

<div class="w3-container w3-white w3-padding-16">
<form id="CreateVehicle" action="${pageContext.request.contextPath}/CreateVehicle" method="post">
      <div><h1>Create Vehicle</h1></div>
       <div>
       <label>Vehicle type:</label>
       <select name="vehicleType" required>
         <option value="CAR">Car</option>
          <option value="TRUCK">Truck</option>         
        </select>
          </div>
         <div>
         	<label>Plate:</label>
    		<input type="text" name="plate" >
        </div>
        <div>
         <% 
          ColorDAO color = new ColorDAOI();
          java.util.ArrayList <Color> colors = new ArrayList<Color>();
          colors.addAll(color.getColorList());
  			if ( colors!= null){
  		%>
  		<label>Color:</label>
    	<select name="colorId">         	
  		<% for (int i = 0; i < colors.size(); ++i) { %>
        <option value="<% colors.get(i).getColor_ID();%>" ><%= colors.get(i).getDescription() %></option>
  		<% }} %>
    	</select>
        </div>
         <div>
         <% 
          ModelDAO model = new ModelDAOI();
          java.util.ArrayList <Model> models = new ArrayList<Model>();
          models.addAll(model.getModelList());
  			if ( models!= null){
  		%>
  		<label>Model:</label>
    	<select name="modelId">         	
  		<% for (int i = 0; i < models.size(); ++i) { %>
        <option value="<% models.get(i).getModel_ID();%>" ><%= models.get(i).getDescription() %></option>
  		<% }} %>
    	</select>
        </div>
        <div>
         <% 
          ManufacturerDAO manufactur = new ManufacturerDAOI();
          java.util.ArrayList <Manufacturer> manufacturers = new ArrayList<Manufacturer>();
          manufacturers.addAll(manufactur.getManufacturerList());
  			if ( manufacturers!= null){
  		%>
  		<label>Manufacturer:</label>
    	<select name="manufacturerId">         	
  		<% for (int i = 0; i < manufacturers.size(); ++i) { %>
        <option value="<% manufacturers.get(i).getManufacturer_ID();%>" ><%= manufacturers.get(i).getName() %></option>
  		<% }} %>
    	</select>
        </div>
        <div>
         <% 
          AccessoryDAO accessory = new AccessoryDAOI();
          java.util.ArrayList <Accessory> accessories = new ArrayList<Accessory>();
          accessories.addAll(accessory.getAccessoryList());
  			if ( accessories!= null){
  		%>
  		<label>Manufacturer:</label>
    	<select name="manufacturerId">         	
  		<% for (int i = 0; i < accessories.size(); ++i) { %>
        <option value="<% accessories.get(i).getAccessory_ID();%>" ><%= accessories.get(i).getDescription() %></option>
  		<% }} %>
    	</select>
        </div>
        <div>
        	<label>Mileage:</label>
       		<input type="text" name="mileage">
        </div>
         <div>
        	<label>Year:</label>
       		<input type="text" name="year">
        </div>
        <div>
        <input type="checkbox" name="active">Active<br>
        </div>
        <% String type = (String)session.getValue("vehicleType"); 
        	if(type== "CAR"){%>
        		<div>
        			<label>Doors:</label>
       				<input type="text" name="doors">
        		</div>
        		<div>
        			<label>Passenger Limit:</label>
       					<input type="text" name="pass_limit">
       			</div>
        	<%}else if(type == "TRUCK"){ %>
        		<div>
        			<label>Length:</label>
       				<input type="text" name="length">
        		</div>
        		<div>
        			<label>Height:</label>
       				<input type="text" name="height">
       			</div>
				<div>
        			<label>Loading Limit:</label>
       				<input type="text" name="load_limit">
       			</div>
        	<%}else{ %>
        		<label>Select a type of vehicle</label>
        	<%} %>
        <div>
      	<p><button type=submit class="w3-button w3-dark-grey">Create Vehicle</button></p>
       	</div> 
</form>
  
<form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">      
        <div><p><button type=submit class="w3-button w3-dark-grey">LogOut</button></p></div>
</form>  
	

</div>
</body>
</html>