<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogIn</title>
</head>
<body>
<form id="LogIn"  action="${pageContext.request.contextPath}/LogInC" method="post">
      <h3>LogIn </h3>
      <br>
       <label><p>ID</p></label>
          <input type="text" name= "id" placeholder="xxxxxxxx-xxxx-xxx-xxx-xxxxxxxxxxxx" pattern="[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[8-9a-bA-B][0-9a-fA-F]{3}-[0-9a-fA-F]{12}" required>
        </div>
        <div>
          <label><p style="line-height:20px">Password</p></label>
          <input class="w3-input w3-border" type="password" name="password" placeholder="*******" required>
        </div>
      <p><button class="w3-btn w3-dark-grey" type=submit>Log In</button></p>
        </div>
    </form>
    </div>
   </div>
</body>
</html>