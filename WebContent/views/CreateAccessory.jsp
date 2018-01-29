<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Accessory</title>
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

<div class="w3-container w3-white w3-padding-16" class="w3-container w3-card-4 w3-light-grey">
	<form id="CreateAccessory"  action="${pageContext.request.contextPath}/CreateAccessory" method="post">
	      <p><h3>CreateAccessory</h3></p>
	       <p><label>Name:</label>
	        <input class="w3-input w3-border w3-round-large" style="width:30%" type="text" id="title" name="name" />
	        </p>
	      <p><label>Description:</label>
	        <input class="w3-input w3-border w3-round-large" style="width:30%" type="text" id="title" name="description"/>
	        </p>    
	        <div>
	      <button type=submit class="w3-button w3-dark-grey">Create Accessory</button>
	       </div> 
	        
	    </form>
	<form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">      
	<div><p><button type=submit class="w3-button w3-dark-grey">LogOut</button></p></div></form>    
	</div>   
</body>
</html>