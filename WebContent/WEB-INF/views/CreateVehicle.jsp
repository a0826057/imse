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
  
<form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">      
        <div><p><button type=submit>LogOut</button></p></div>
    </form>   
    <a href="${pageContext.request.contextPath}/Homepage.jsp">Home</a> 
	

</body>
</html>