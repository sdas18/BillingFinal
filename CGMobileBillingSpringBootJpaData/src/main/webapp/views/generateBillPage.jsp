<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div id="form">
<form name="billForm" action=generateBill method="post">
Customer ID:
<input type="text" name="customerId"><br>
Mobile Number:
<input type="text" name="mobileNumber"><br>
Bill Month:
<input type="text" name="billMonth"><br>
Number of local sms:
<input type="text" name="noOfLocalSMS"><br>
Number of STD sms:
<input type="text" name="noOfStdSMS"><br>
Number of local calls:
<input type="text" name="noOfLocalCalls"><br>
Number of STD calls:
<input type="text" name="noOfStdCalls"><br>
Internet Data Usage units:
<input type="text" name="internetDataUsageUnits"><br>
<input type="submit">
</form>
<div>
Your bill amount : ${bill }
</div>
</div>
</body>
</html>