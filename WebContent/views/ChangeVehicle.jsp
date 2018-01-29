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

<form action="${pageContext.request.contextPath}/ChangeVehicle" method="post" class="w3-container w3-white w3-padding-16">
<div class="w3-container w3-card-4 w3-light-grey">
      <div><h3>Change Vehicle Information</h3></div>
    	<%    	  	
    	   java.util.ArrayList<Vehicle> vehic = new ArrayList<Vehicle>();
    		Vehicle change;
    	   String id = (String) request.getParameter("edit");
    	             	
    	   %> 
    	 <p>
           	<label>Vehicle ID:</label>
    		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="id" value="<%=id%>" disabled>
        </p>    
       <p>
         	<label>Plate:</label>
    		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="plate">
        </p>
        <p>
         <% 
          java.util.ArrayList <Color> colors = new ArrayList<Color>();
           if((session.getAttribute("colorList") != null)){
        	   colors = (java.util.ArrayList<Color>) session.getAttribute("colorList");
	       } %>
  		<label>Color:</label>
    	<select name="colorId" class="w3-select" style="width:30%">  
    	<% for(int c = 0; c < colors.size(); c++){
       		Color co = (Color)colors.get(c); %>       	
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
    	<% for(int m = 0; m < models.size(); m++){
         		Model mo = (Model)models.get(m);%>         	
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
    	<% for(int ma = 0; ma < manufacturers.size(); ma++){
        		Manufacturer man = (Manufacturer)manufacturers.get(ma);%>     	
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
    	<select name="accessoryId" class="w3-select" style="width:30%" >  
		<%  for(int a = 0; a < accessories.size(); a++){
        	Accessory ac = (Accessory)accessories.get(a);
  		%>      	
        <option value="<%=ac.getAccessory_ID()%>" ><%= ac.getDescription() %></option>
  		<% } %>
    	</select>
        </p>
        <p>
        	<label>Mileage:</label>
       		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="mileage">
        </p>
         <p>
        	<label>Year:</label>
       		<input class="w3-input w3-border w3-round-large" style="width:30%" type="text" name="year" >
        </p>
        <p>
        <input class="w3-check" type="checkbox" name="active" />Active<br>
        </p>
 
        <div>
      	<p><button type=submit class="w3-button w3-dark-grey" name="changeVehicle">Change Vehicle</button></p>
       	</div> 
       	</div>
		</form>

</body>
</html>