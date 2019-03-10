<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
<form name="closePostpaidAccount" action="closePostpaidAccount" method="post">
Customer ID:
<input type="text" name="customerID"><br>
Mobile number:
<input type="text" name="mobileNo"><br>
<input type="submit" name=submit>
</form>
</div>
<br>
<div align="center">
<font color=green size="10">${success}</font>
</div>
</body>
</html>