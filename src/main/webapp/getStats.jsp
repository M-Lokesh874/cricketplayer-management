<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@page import="com.playermanagement.model.CricketPlayerStats"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<%
CricketPlayerStats cricketPlayerStats = (CricketPlayerStats) request.getAttribute("cricketPlayerStats");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Display stats by id</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>

	<li><a href="index">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>
	<div align="center">

		<h1>Display stats</h1>

		<form action="getstats" method="get">
			Id:<input type="number" name="id"> <input type="submit"
				name="submit" value="display here"
				<div class="button btn-info btn-sm"></div >>

		</form>
	</div>

	<div align="center">
		<%
		if (null != cricketPlayerStats) {
		%>
		Stats Of Cricket Player
		<table class="table bg-info">

			<tr>
				<td>Id :</td>
				<td><%=cricketPlayerStats.getId()%></td>
			</tr>
			<tr>
				<td>No of match :</td>
				<td><%=cricketPlayerStats.getNoOfMatch()%></td>
			</tr>
			<tr>
				<td>Total run :</td>
				<td><%=cricketPlayerStats.getTotalRun()%></td>
			</tr>
			<tr>
				<td>Top score :</td>
				<td><%=cricketPlayerStats.getTopScore()%></td>
			</tr>
			<tr>
				<td>No of balls faced :</td>
				<td><%=cricketPlayerStats.getNoOfBallsFaced()%></td>
			</tr>
			<tr>
				<td>Batting average :</td>
				<td><%=cricketPlayerStats.getBattingAverage()%></td>
			</tr>
			<tr>
				<td>Strike rate :</td>
				<td><%=cricketPlayerStats.getStrikeRate()%></td>
			</tr>
		</table>

		<%
		}
		%>
	</div>
</body>
</html>