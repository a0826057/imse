<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*, java.lang.*" %>
<%@ page language="java" import="model.*, java.lang.*" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View My Rents</title>
</head>
<body>
	<h2><center>Welcome to Rent Managament!</center></h2>

	<form id="rent"  action="${pageContext.request.contextPath}/ViewRentsServlet" method="get">
   	<h3>View My Rents:<button type="submit" value="" >Load</button></h3>
     <br>
     <% java.util.ArrayList<Rental> users = new ArrayList<Rental>();
     
    	if((session.getAttribute("list") != null)){
    		users = (java.util.ArrayList<Rental>) session.getAttribute("list");
    	}
    		%>
    		
    <table border="1" cellpadding="5">
            <tr>
                <th>Vehicle ID</th>
                <th>Customer ID</th>
                <th>Employee ID</th>
                <th>From</th>
                <th>To</th>
                <th>Rating</th>
                <th></th>
                <th></th>
            </tr>
            <%for(int i = 0; i < users.size(); i++){
            	Rental ren = (Rental)users.get(i);
            %>
            <tr>
		       <td><% out.print(ren.getVehicle().getVehicle_ID()); %></td>
		       <td><% out.print(ren.getCostumer().getCostumer_ID()); %></td>
		       <td><% out.print(ren.getEmployee().getEmployee_number()); %></td>
		       <td><% out.print(ren.getDate_from()); %></td>
		       <td><% out.print(ren.getDate_to()); %></td>
		       <td><% out.print(ren.getRating()); %></td>
		       <td>
		       <form action="${pageContext.request.contextPath}/ViewRentsServlet" method="post">
		       		<button TYPE="submit" NAME="button" VALUE=<%=ren.getVehicle().getVehicle_ID()%> >Change</button>
		       </form>
		       </td>
		       <td>
		       <form action="${pageContext.request.contextPath}/ViewRentsServlet" method="post">
		       		<button TYPE="submit" NAME="button" VALUE=<%=ren.getVehicle().getVehicle_ID()%> >Delete</button>
		       </form>
		       </td>
		    </tr>
            <%} %>
   	</table>
   	</form>
   	  
   	<form id="LogOut"  action="${pageContext.request.contextPath}/LogOut" method="post">      
        <div>
      <p><button type=submit>LogOut</button></p>
        </div>
    </form>   
    <a href="${pageContext.request.contextPath}/Homepage.jsp">Home</a> 
    
</body>
</html>