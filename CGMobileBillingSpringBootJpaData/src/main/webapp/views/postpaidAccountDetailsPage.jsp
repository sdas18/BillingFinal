<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div class="form">
<form name="postpaidAccountDetail" action="postpaidAccountDetail" method="post">
Customer ID:
<input type="text" name="customerId"><br>
Mobile number:
<input type="text" name="mobileNumber"><br>
<input type="submit" name=submit>
</form>
</div>
<div>
${postpaidAccount }
</div>
</body>
</html>