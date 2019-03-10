<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
	padding: 9px;
}
.formWrapper{
padding:12px;
font-size:20px;
margin-right:500px;
}
</style>
<meta charset="ISO-8859-1">
<title>AccountAllBill</title>
</head>
<body>
	<div class="formWrapper">
		<form action="accountAllBills" method="post">
			Customer ID: <input type="text" name="customerId"> MobileNo :<input
				type="text" name="mobileNo"> <input type="submit"
				name="submit">
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>billID</th>
				<th>noOfLocalSMS</th>
				<th>noOfStdSMS</th>
				<th>noOfLocalCalls</th>
				<th>noOfStdCalls</th>
				<th>internetDataUsageUnits</th>
				<th>billMonth</th>
				<th>localSMSAmount</th>
				<th>stdSMSAmount</th>
				<th>localCallAmount</th>
				<th>stdCallAmount</th>
				<th>internetDataUsageAmount</th>
				<th>stateGST</th>
				<th>centralGST</th>
				<th>totalBillAmount</th>
			</tr>
			<s:forEach items="${bills}" var="entry">
				<tr>
					<td>${entry.billID}</td>
					<td>${entry.noOfLocalSMS}</td>
					<td>${entry.noOfStdSMS}</td>
					<td>${entry.noOfLocalCalls}</td>
					<td>${entry.noOfStdCalls}</td>
					<td>${entry.internetDataUsageUnits}</td>
					<td>${entry.billMonth}</td>
					<td>${entry.localSMSAmount}</td>
					<td>${entry.stdSMSAmount}</td>
					<td>${entry.localSMSRate}</td>
					<td>${entry.stdSMSRate}</td>
					<td>${entry.localCallAmount}</td>
					<td>${entry.stdCallAmount}</td>
					<td>${entry.internetDataUsageAmount}</td>
					<td>${entry.stateGST}</td>
					<td>${entry.centralGST}</td>
					<td>${entry.totalBillAmount}</td>
				</tr>
			</s:forEach>
		</table>
	</div>
</body>
</html>