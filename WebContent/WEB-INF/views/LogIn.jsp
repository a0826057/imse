<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogIn</title>
</head>
<body>
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
      <p><button type=submit>LogIn</button></p>
        </div>
    </form>    
    
    <a href="${pageContext.request.contextPath}/Profil.jsp">Profile</a>
</body>
</html>