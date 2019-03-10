<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div class="form">
<form name="customerDetails" action=customerDetails method=post>
Customer ID:
<input type="text" name="customerId"/><br>
<input type="submit" name="submit"><br><br>
</form>
</div>
<div>
${customer }
</div>
</body>
</html>