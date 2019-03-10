<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
table, th, td {
	border: 1px solid black;
	padding: 9px;
}
</style>
</head>
<body>
	<table>
		<tr>
			<th>PlanId</th>
			<th>monthlyRental</th>
			<th>freeLocalCalls</th>
			<th>freeStdCalls</th>
			<th>freeLocalSMS</th>
			<th>freeStdSMS</th>
			<th>freeInternetDataUsageUnits</th>
			<th>localCallRate</th>
			<th>stdCallRate</th>
			<th>localSMSRate</th>
			<th>stdSMSRate</th>
			<th>internetDataUsageRate</th>
			<th>planCircle</th>
			<th>planName</th>
		</tr>
		<s:forEach items="${plans}" var="entry">
			<tr>
				<td>${entry.planID}</td>
				<td>${entry.monthlyRental}</td>
				<td>${entry.freeLocalCalls}</td>
				<td>${entry.freeStdCalls}</td>
				<td>${entry.freeLocalSMS}</td>
				<td>${entry.freeStdSMS}</td>
				<td>${entry.freeInternetDataUsageUnits}</td>
				<td>${entry.localCallRate}</td>
				<td>${entry.stdCallRate}</td>
				<td>${entry.localSMSRate}</td>
				<td>${entry.stdSMSRate}</td>
				<td>${entry.internetDataUsageRate}</td>
				<td>${entry.planCircle}</td>
				<td>${entry.planName}</td>
			</tr>
			</s:forEach>
	</table>


	<div class="form">
		<form action="postpaidAccountOpen" method="post">
			Customer ID: <input type="text" name="customerId"> Plan ID: <input
				type="text" name="planId"> <input type="submit"
				name="submit">
		</form>
	</div>
	<div align="center">
		Your system generated new Mobile Number: <font color=Blue size="18px">${mobileNumber }</font>
	</div>
</body>
</html>