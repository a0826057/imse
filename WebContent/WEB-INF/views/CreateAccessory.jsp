<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Accessory</title>
</head>
<body>
<form id="CreateAccessory"  action="${pageContext.request.contextPath}/CreateAccessory" method="post">
      <h3>CreateAccessory</h3>
       <div><label>Name:</label>
        <input type="text" id="title" name="name" />
        </div>
      <div><label>Description:</label>
        <input type="text" id="title" name="description"/>
        </div>
    
        <div>
      <p><button type=submit>Create Accessory</button></p>
       </div> 

        
    </form>    
</body>
</html>