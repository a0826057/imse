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
<div><h1>Create Vehicle</h1></div>
        <form action="CreateVehicle.jsp" method="post"> 
           <select name="vehicleType" required class="w3-select" style="width:30%">
           		<option value="null">Select</option>
         		<option value="CAR">Car</option>
           		<option value="TRUCK">Truck</option>         
            </select>
            <input type="submit" name="<%=request.getParameter("vehicleType")%>" value="submit" class="w3-button w3-white w3-border w3-border-red w3-round-large">
        </form> 
        <br>
        
<form id="CreateVehicle" action="${pageContext.request.contextPath}/CreateVehicle" method="post" class="w3-container w3-card-4 w3-light-grey">
      
        <% String type =(String)request.getParameter("vehicleType"); %>
         <p>
         	<label>Plate:</label>
    		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="plate" >
        </p>
        <p>
         <% 
          ColorDAO color = new ColorDAOI();
          java.util.ArrayList <Color> colors = new ArrayList<Color>();
          colors.addAll(color.getColorList());
  			if ( colors!= null){
  		%>
  		<label>Color:</label><br>
    	<select name="colorId" class="w3-select" style="width:30%">         	
  		<% for (int i = 0; i < colors.size(); ++i) { %>
        <option value="<% colors.get(i).getColor_ID();%>" ><%= colors.get(i).getDescription() %></option>
  		<% }} %>
    	</select>
        </p>
         <p>
         <% 
          ModelDAO model = new ModelDAOI();
          java.util.ArrayList <Model> models = new ArrayList<Model>();
          models.addAll(model.getModelList());
  			if ( models!= null){
  		%>
  		<label>Model:</label><br>
    	<select name="modelId" class="w3-select" style="width:30%">         	
  		<% for (int i = 0; i < models.size(); ++i) { %>
        <option value="<% models.get(i).getModel_ID();%>" ><%= models.get(i).getDescription() %></option>
  		<% }} %>
    	</select>
        </p>
        <p>
         <% 
          ManufacturerDAO manufactur = new ManufacturerDAOI();
          java.util.ArrayList <Manufacturer> manufacturers = new ArrayList<Manufacturer>();
          manufacturers.addAll(manufactur.getManufacturerList());
  			if ( manufacturers!= null){
  		%>
  		<label>Manufacturer:</label><br>
    	<select name="manufacturerId" class="w3-select" style="width:30%">         	
  		<% for (int i = 0; i < manufacturers.size(); ++i) { %>
        <option value="<% manufacturers.get(i).getManufacturer_ID();%>" ><%= manufacturers.get(i).getName() %></option>
  		<% }} %>
    	</select>
        </p>
        <p>
         <% 
          AccessoryDAO accessory = new AccessoryDAOI();
          java.util.ArrayList <Accessory> accessories = new ArrayList<Accessory>();
          accessories.addAll(accessory.getAccessoryList());
  			if ( accessories!= null){
  		%>
  		<label>Accessories:</label><br>
    	<select name="manufacturerId" class="w3-select" style="width:30%">         	
  		<% for (int i = 0; i < accessories.size(); ++i) { %>
        <option value="<% accessories.get(i).getAccessory_ID();%>" ><%= accessories.get(i).getDescription() %></option>
  		<% }} %>
    	</select>
        </p>
        <p>
        	<label>Mileage:</label>
       		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="mileage">
        </p>
         <p>
        	<label>Year:</label>
       		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="year">
        </p>
        <p>
        <input class="w3-check" type="checkbox" name="active">Active<br>
        </p>
         <%if("CAR".equals(type)){ %>   	
        		<p>
        			<label>Doors:</label>
       				<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="doors">
        		</p>
        		<p>
        			<label>Passenger Limit:</label>
       				<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="pass_limit">
       			</p>
        	<%}else if("TRUCK".equals(type)){ %>
        		<p>
        			<label>Length:</label>
       				<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="length">
        		</p>
        		<p>
        			<label>Height:</label>
       				<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="height">
       			</p>
				<p>
        			<label>Loading Limit:</label>
       				<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="load_limit">
       			</p>
        	<%}else{ %>
        		<h2>Select a type of vehicle</h2>
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