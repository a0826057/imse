<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogIn</title>
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
  <a href="${pageContext.request.contextPath}/rent_vehicle.jsp" class="w3-bar-item w3-button w3-right w3-red">Rent</a>
</div>

<div class="w3-container w3-white w3-padding-16">
	<form id="LogIn"  action="${pageContext.request.contextPath}/LogIn" method="post">
	      <h3>LogIn</h3>
	      <br>
	      	<div>
	       <label>EMail</label>
	          <input type="text" name="email" value="a@b.mail">
	        </div>
	        
	        <div>
	          <label>Password</label>
	          <input type="password" name="password" value="*******">
	        </div>
	        <div>
	      <p><button type=submit class="w3-button w3-dark-grey">LogIn</button></p>
	        </div>
	</form>
</div>
</body>
</html>