<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CustomerServices</title>
</head>
<body>
<form id="Delete"  action="${pageContext.request.contextPath}/Delete" method="post">
      <h3>Delete a Customer</h3>
      <br>
      	<div>
       <label>ID</label>
          <input type="text" name="id" value="123">
        </div>     
        
        <div>
      <p><button type=submit>Delete</button></p>
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
      <p><button type=submit>Add</button></p>
        </div>
    </form>
    <form action="/EmployeeServlet" method="post">
        <input type="hidden" name="employeeMode" value="fillData">
        <h3>Generate Data (Data Filling)</h3>
        <br>
        <div>
            <p><button type=submit>Start Datafilling</button></p>
        </div>
    </form>
</body>
</html>