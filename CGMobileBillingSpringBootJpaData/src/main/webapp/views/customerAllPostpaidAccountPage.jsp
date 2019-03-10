<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer All Postpaid Account Details</title>
</head>
<body>
<div class="form">
		<form action="customerAllAccount" method="post">
			Customer ID: <input type="text" name="customerId">  
				<input type="submit" name="submit">
		</form>
	</div>
	<div>
	<s:forEach  var="comm" items="${accounts}">
	<table>
	<tr>
	<th>MobileNo</th>
	<th>PlanID</th>
	</tr>
	<tr>
	<td>${comm.mobileNo}</td>
	<td>${comm.plan.planID}</td>	
	</tr>
	</table>
	</s:forEach>
	</div>
</body>
</html>