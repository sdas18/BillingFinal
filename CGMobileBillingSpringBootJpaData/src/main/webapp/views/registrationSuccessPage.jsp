<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<div align="center">
		<table>
			<tr style="font:20px">
				<th>FirstName</th>
				<th>LastName</th>
			</tr>
			<tr>
				<td>${customer.firstName}</td>
				<td>${customer.lastName}</td>
			</tr>
		</table>
		<h2>Registration done successfully</h2>
		<h2>Customer ID: ${customer.customerID}</h2>
	</div>
</body>
</html>