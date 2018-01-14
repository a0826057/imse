<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	  <a href="${pageContext.request.contextPath}/Profil.jsp" class="w3-bar-item w3-button w3-right w3-red ">Profil</a>
	  <a href="${pageContext.request.contextPath}/rent_vehicle.jsp" class="w3-bar-item w3-button w3-right w3-red">Rent</a>
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
		<a href="${pageContext.request.contextPath}/SignUp.jsp" class="w3-button w3-dark-grey">SignUp</a>
		<a href="${pageContext.request.contextPath}/LogIn.jsp" class="w3-button w3-dark-grey">LogIn</a>
		<a href="${pageContext.request.contextPath}/AdminLogIn.jsp" class="w3-button w3-dark-grey">Employee</a>
	</div>
</body>
</html>