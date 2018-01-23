<%@ page language="java" import="java.util.*, java.lang.*" %>
<%@ page language="java" import="model.*, java.lang.*" %>
<%@ page language="java" import="dao.*, java.lang.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Vehicle</title>
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
<form id="ChangeVehicle" action="${pageContext.request.contextPath}/ChangeVehicle" method="post" class="w3-container w3-card-4 w3-light-grey">
      <div><h3>Change Vehicle Information</h3></div>
    	<% //String type = (String)request.getParameter("vehicleType");
    	   //String idVehicle = (String)request.getParameter("vehicleSelectId");
    	   //int vehicleID = Integer.valueOf(idVehicle);
    	  	
    	  	int vehicleID = 5;
    	  	String type="CAR";
    	   	VehicleDAO list = new VehicleDAOI();
    	   	List<Vehicle> vehicleInfo = new ArrayList<Vehicle>();
    	   
    	   if("CAR".equals(type)){
    		   List<Vehicle> car = new ArrayList<Vehicle>();
    		   car.addAll(list.getVehicleListByType("car"));
    		   for(int i=0; i<car.size();++i){
    			
    			   if(car.get(i).getVehicle_ID()==vehicleID){
    				    vehicleInfo.add(car.get(i));
    			   }
    		   }
    	   }
    	   else{
    		  List<Vehicle> truck = new ArrayList<Vehicle>();
    		  truck.addAll(list.getVehicleListByType("truck"));
    		  for(int i=0; i<truck.size();++i){
   			  		
   			   		if(truck.get(i).getVehicle_ID()==vehicleID){
   				    	vehicleInfo.add(truck.get(i));
   			   		}
   		   		}
    	   }
    	  
    	   %> 
    	 <p>
         	<label>Vehicle ID:</label>
    		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="id" value="<%=vehicleInfo.get(0).getVehicle_ID() %>">
        </p>    
       <p>
         	<label>Plate:</label>
    		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="plate" value="<%=vehicleInfo.get(0).getLicense_plate_number()%>">
        </p>
        <p>
         <% 
          ColorDAO color = new ColorDAOI();
          java.util.ArrayList <Color> colors = new ArrayList<Color>();
          colors.addAll(color.getColorList());
  			if ( colors!= null){
  		%>
  		<label>Color:</label>
    	<select name="colorId" class="w3-select" style="width:30%">         	
  		<% for (int i = 0; i < colors.size(); ++i) { %>
        <option value="<%=colors.get(i).getColor_ID()%>" ><%=colors.get(i).getDescription()%></option>
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
  		<label>Model:</label>
    	<select name="modelId" class="w3-select" style="width:30%">         	
  		<% for (int i = 0; i < models.size(); ++i) { %>
        <option value="<%=models.get(i).getModel_ID()%>" ><%= models.get(i).getDescription() %></option>
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
  		<label>Manufacturer:</label>
    	<select name="manufacturerId" class="w3-select" style="width:30%">         	
  		<% for (int i = 0; i < manufacturers.size(); ++i) { %>
        <option value="<%=manufacturers.get(i).getManufacturer_ID()%>" ><%= manufacturers.get(i).getName() %></option>
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
  		<label>Manufacturer:</label>
    	<select name="accessoryId" class="w3-select" style="width:30%">         	
  		<% for (int i = 0; i < accessories.size(); ++i) { %>
        <option value="<%=accessories.get(i).getAccessory_ID()%>" ><%= accessories.get(i).getDescription() %></option>
  		<% }} %>
    	</select>
        </p>
        <p>
        	<label>Mileage:</label>
       		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="mileage" value="<%=vehicleInfo.get(0).getMileage() %>">
        </p>
         <p>
        	<label>Year:</label>
       		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="year" value="<%=vehicleInfo.get(0).getManufacture_year()%>">
        </p>
        <p>
        <input class="w3-check" type="checkbox" name="active" value="<%=vehicleInfo.get(0).getActive()%>" />Active<br>
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
        <%}else{ %>	
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
        <%} %>
        <div>
      	<p><button type=submit class="w3-button w3-dark-grey" name="changeVehicle" value="<%=type%>">Change Vehicle</button></p>
       	</div> 
</form>

</div>
</body>
</html>