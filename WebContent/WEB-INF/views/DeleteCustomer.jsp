<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CustomerServices</title>
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
<form id="Delete"  action="${pageContext.request.contextPath}/Delete" method="post">
      <h3>Delete a Customer</h3>
      <br>
      	<div>
       <label>ID</label>
          <input type="text" name="id" value="123">
        </div>     
        
        <div>
      <p><button type=submit class="w3-button w3-dark-grey">Delete</button></p>
        </div>
    </form>   
    
    <form id="license"  action="${pageContext.request.contextPath}/Delete" method="post">
      
      
      <h3>Add License</h3>
      <br>
      <div>
       <label>ID</label>
          <input type="text" name="id" value="123">
        </div>  
      	<div>
       <label>License</label>
          <input type="text" name="license" value="AB1234">
        </div>        
        
        <div>
      <p><button type=submit class="w3-button w3-dark-grey">Add</button></p>
        </div>
    </form>
   </div>
</body>
</html>