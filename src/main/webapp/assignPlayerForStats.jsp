<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@page import="java.util.List"%>
<%@page import="com.playermanagement.model.CricketPlayerStats"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
CricketPlayerStats cricketPlayerStats = (CricketPlayerStats) session.getAttribute("cricketPlayerStats");
%>
<%
CricketPlayer cricketPlayer = (CricketPlayer) session.getAttribute("cricketPlayer");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Assign player for stats</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>

	<li><a href="home.jsp">
			<button class="btn btn-success">Home</button>
	</a></li>
	<div align="center">
		<h1>Assign player for stats</h1>

		<form action="assignplayerforstats" method="post">
			Player id to assign stats:<input type="number" name="enterplayerid">
			Stats Id :<input type="number" name="statsid"> <input
				type="submit" name="submit" value="assign"
				<div class="button btn-info btn-sm"</div>>
		</form>
	</div>

	<div align="center">

		<%
		if (cricketPlayer != null) {
		%>
		Cricket player
		<table class="table bg-info">

			<tr>
				<td>Id :</td>
				<td><%=cricketPlayer.getId()%></td>
			</tr>
			<tr>
				<td>Player Code :</td>
				<td><%=cricketPlayer.getPlayerCode()%></td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><%=cricketPlayer.getName()%></td>
			</tr>
			<tr>
				<td>Date Of birth :</td>
				<td><%=cricketPlayer.getDateOfBirth()%></td>
			</tr>
			<tr>
				<td>Country :</td>
				<td><%=cricketPlayer.getCountry()%></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><%=cricketPlayer.getEmail()%></td>
			</tr>

		</table>

		<%
		}
		%>

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

	<%
	if (null != session.getAttribute("found")) {
	%>
	<%
	boolean found = (boolean) session.getAttribute("found");
	%>
	<%
	if (found) {
	%>
	Assigned succesfully
	<%
	} else {
	%>
	not assigned successfully
	<%
	}
	}
	%>

</body>
</html>