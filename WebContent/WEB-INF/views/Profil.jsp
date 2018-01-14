<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Profile</title>
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
	Change information here!
			<form id="ChangeCustomer"  action="${pageContext.request.contextPath}/ChangeCustomer" method="post">
	<div>
	     <label>Title</label>
	        <input type="text" name="title" value="Doctor">
	      </div>
	      <div>
	     <label>First Name</label>
	        <input type="text" name="fname" value="MyName">
	      </div>
	      <div>
	     <label>Last Name</label>
	        <input type="text" name="lname" value="MyName">
	      </div>
	      <div>
	     <label>Birthday</label>
	        <input type="text" name="bday" value="dd-mm-yyyy">
	      </div>
	      <div>
	     <label>Country</label>
	        <input type="text" name="country" value="Austria">
	      </div>
	      <div>
	     <label>Town</label>
	        <input type="text" name="town" value="atown">
	      </div>
	      <div>
	     <label>Post Code</label>
	        <input type="text" name="pcode" value="1010">
	      </div>
	      <div>
	     <label>Street</label>
	        <input type="text" name="street" value="astreet">
	      </div>
	      <div>
	     <label>House number</label>
	        <input type="text" name="hnum" value="10">
	      </div>
	      <div>
	     <label>Apartment number</label>
	        <input type="text" name="anum" value="">
	      </div>    
	      <div>
	     <label>EMail</label>
	        To change your email please contact admin@admin.mail.
	      </div>      
	      <div>
	        <label>Password</label>
	        To change your password please contact admin@admin.mail.
	      </div>
	      <div>
	       <label>Drivers License</label>
	       To change your license please contact admin@admin.mail.
	      </div>
	      <div>
	    <p><button type=submit class="w3-button w3-dark-grey">Change</button></p>
	     </div>         
	  </form> 
	  
	  Your current Profile:
	  <% String user=(String)session.getAttribute("currentSessionUserString"); %>
	<%=user%>
	<form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">      
	       <div>
	     <p><button type=submit class="w3-button w3-dark-grey">LogOut</button></p>
	       </div>
	</form>
</div>
</body>
</html>