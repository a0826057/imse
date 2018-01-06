<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Profile</title>
</head>
<body>
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
      <p><button type=submit>Change</button></p>
       </div> 
        

        
    </form>    
</body>
</html>