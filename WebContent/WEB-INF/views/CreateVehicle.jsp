<%@ page language="java" import="java.util.*, java.lang.*" %>
<%@ page language="java" import="model.*, java.lang.*" %>
<%@ page language="java" import="dao.*, java.lang.*" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Vehicle</title>
</head>
<body>

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
        <option value="<% colors.get(i);%>" ><%= colors.get(i)%></option>
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
        <option value="<% models.get(i).getModel_ID();%>" ><%= models.get(i)%></option>
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
        <option value="<% manufacturers.get(i).getManufacturer_ID();%>" ><%= manufacturers.get(i)%></option>
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
        <option value="<% accessories.get(i).getAccessory_ID();%>" ><%= accessories.get(i)%></option>
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
        <c:choose>
        	<c:when test="${session.getAttribute("vehicleType") == 'CAR'}">
       			<div>
        			<label>Doors:</label>
       				<input type="text" name="doors">
        		</div>
        		<div>
        			<label>Passenger Limit:</label>
       					<input type="text" name="pass_limit">
       			</div>
        </c:when>
   		<c:when test="${session.getAttribute("vehicleType") == 'TRUCK'}">
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
		</c:when> 
   		<c:otherwise>
   			<label>Select a type of vehicle</label>
   		</c:otherwise>    
		</c:choose>
        <div>
      	<p><button type=submit>Create Vehicle</button></p>
       	</div> 
</form>
  
<form id="ChangeVehicle" action="${pageContext.request.contextPath}/ChangeVehicle" method="post">
      <div><h1>Change Vehicle</h1></div>
       <div>
       <label>Vehicle type:</label>
           <select name="vehicleType" required>
         		<option value="CAR">Car</option>
           		<option value="TRUCK">Truck</option>         
            </select>
          </div>
       	<div>
         <% VehicleDAO vehicle = new VehicleDAOI();
          java.util.ArrayList <Vehicle> vehicles = new ArrayList<Vehicle>();
          vehicles.addAll(vehicle.getVehicleList());
  			if ( vehicles!= null){
  		%>
  		<label>Select Vehicle ID:</label>
    	<select name="vehicle_id">         	
  		<% for (int i = 0; i < vehicles.size(); ++i) { %>
        	<option value="<%= vehicles.get(i).getVehicle_ID()%>" ><%= vehicles.get(i).getVehicle_ID() %></option>
  		<% }}%>
  		<% String id = session.getAttribute(vehicles.get(i).getVehicle_ID());
  		   int vid= Integer.parseInt(id);
  		   VehicleDAO veh = new VehicleDAOI();
  		   Vehicle vehic;
  		   vehic = veh.getVehicleById(vid);
  		   %>
    	</select>
        </div>
        
         <div>
         	<label>Plate:</label>
    		<input type="text" name="plate" value="<% vehic.getLicense_plate_number(); %>" >
        </div>
        <div>
         <% 
          colors.addAll(color.getColorList());
  			if ( colors!= null){
  		%>
  		<label>Color:</label>
    	<select name="colorId">         	
  		<% for (int i = 0; i < colors.size(); ++i) { %>
        	<option value="<% colors.get(i);%>" <%if(colors.get(i).getColor_ID()== vehic.getColor().getColor_ID()){ SELECTED="SELECTED";} %>><%= colors.get(i)%></option>
  		<% }} %>
    	</select>
        </div>
         <div>
         <% 
          models.addAll(model.getModelList());
  			if ( models!= null){
  		%>
  		<label>Model:</label>
    	<select name="modelId">         	
  		<% for (int i = 0; i < models.size(); ++i) { %>
        <option value="<% models.get(i).getModel_ID();%>" <%if(models.get(i).getModel_ID()== vehic.getModel().getModel_ID()){ SELECTED="SELECTED";} %>><%= models.get(i)%></option>
  		<% }} %>
    	</select>
        </div>
        <div>
         <% 
          manufacturers.addAll(manufactur.getManufacturerList());
  			if ( manufacturers!= null){
  		%>
  		<label>Manufacturer:</label>
    	<select name="manufacturerId">         	
  		<% for (int i = 0; i < manufacturers.size(); ++i) { %>
        <option value="<% manufacturers.get(i).getManufacturer_ID();%>" <%if(manufacturers.get(i).getManufacturer_ID()== vehic.getManufactur().getManufacturer_ID()){ SELECTED="SELECTED";} %>><%= manufacturers.get(i)%></option>
  		<% }} %>
    	</select>
        </div>
        <div>
         <% 
         	accessories.addAll(accessory.getAccessoryList());
  			if ( accessories!= null){
  		%>
  		<label>Manufacturer:</label>
    	<select name="manufacturerId">         	
  		<% for (int i = 0; i < accessories.size(); ++i) { %>
        <option value="<% accessories.get(i).getAccessory_ID();%>" <%if(accessories.get(i).getAccessory_ID()== vehic.getAccessory().get(i).getAccessory_ID()){ SELECTED="SELECTED";} %>><%= accessories.get(i)%></option>
  		<% } %>
    	</select>
        </div>
        <div>
        	<label>Mileage:</label>
       		<input type="text" name="mileage" value="<% vehic.getMileage(); %>">
        </div>
         <div>
        	<label>Year:</label>
       		<input type="text" name="year" value="<% vehic.getManufacture_year();%>">
        </div>
        <div>
        <input type="checkbox" name="active" value="<%vehic.getActive();%>">Active<br>
        </div>
        <c:choose>
        	<c:when test="${session.getAttribute("vehicleType") == 'CAR'}">
       			<div>
        			<label>Doors:</label>
       				<input type="text" name="doors">
        		</div>
        		<div>
        			<label>Passenger Limit:</label>
       					<input type="text" name="pass_limit">
       			</div>
        </c:when>
   		<c:when test="${session.getAttribute("vehicleType") == 'TRUCK'}">
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
		</c:when> 
   		<c:otherwise>
   			<label>Select a type of vehicle</label>
   		</c:otherwise>    
		</c:choose>
        <div>
      	<p><button type=submit>Create Vehicle</button></p>
       	</div> 
</form>
	
<form id="DeleteVehicle"  action="${pageContext.request.contextPath}/DeleteVehicle" method="post">
      <div><h1>Delete a Vehicle</h1></div>
      	<div>
        <% 
  		 if(vehicles!= null){
  		%>
  		<label>Select the Vehicle ID you want to delete:</label>
    	<select name="vehicle_id" required>         	
  		<% for (int i = 0; i < vehicles.size(); ++i) { %>
       		 <option value="<% vehicles.get(i).getVehicle_ID();%>" ><%= vehicles.get(i).getVehicle_ID() %></option>
  		<%}}%>
  		
    	</select>
        </div>     
        
        <div>
      		<p><button type=submit>Delete Vehicle</button></p>
        </div>
 </form>       
</body>
</html>