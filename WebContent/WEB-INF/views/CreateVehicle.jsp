<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Vehicle</title>
</head>
<body>
<form id="CreateVehicle"  action="${pageContext.request.contextPath}/CreateVehicle" method="post">
      <div><h3>CreateVehicle</h3></div>
       <div>
       <label>Vehicle type:</label>
           <select name="vehicleType">
         		<option value="CAR">Car</option>
           		<option value="TRUCK">Truck</option>         
             </select>
          </div>
         <div>
         	<label>Plate:</label>
    		<input type="text" name="plate" >
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
        <div>
      <p><button type=submit>Create Vehicle</button></p>
       </div> 
    </form>
    <form id="DeleteVehicle"  action="${pageContext.request.contextPath}/DeleteVehicle" method="post">
      <div></div><h3>Delete a Vehicle</h3></div>
      	<div>
       <label>ID</label>
          <input type="text" name="id">
        </div>     
        
        <div>
      <p><button type=submit>Delete</button></p>
        </div>
    </form>       
</body>
</html>