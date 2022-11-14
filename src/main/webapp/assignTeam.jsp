<%@page import="com.playermanagement.model.CricketTeam"%>
<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
CricketPlayer cricketPlayer = (CricketPlayer) session.getAttribute("cricketPlayer");
%>
<%
CricketTeam cricketTeam = (CricketTeam) session.getAttribute("cricketTeam");
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

<title>Assign team</title>
</head>
<body>

	<li><a href="home.jsp">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>
	<li><a href="cricketPlayer.jsp">
			<button class="btn btn-success">Back</button>
	</a></li>
	<br>

	<div align="center">
		<h1>Assign team</h1>
		<form action="assignteam" method="post">

			Player id:<input type="number" name="playerid"> Team id :<input
				type="number" name="teamid"> <input type="submit"
				name="submit" value="assign"
				<div class = "button btn-info btn-sm"</div>>
		</form>

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
		if (null != cricketTeam) {
		%>
		Cricket team
		<table class="table bg-info">

			<tr>
				<td>Id :</td>
				<td><%=cricketTeam.getId()%></td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><%=cricketTeam.getName()%></td>
			</tr>
			<tr>
				<td>Total match played :</td>
				<td><%=cricketTeam.getTotalMatch()%></td>
			</tr>
			<tr>
				<td>Won :</td>
				<td><%=cricketTeam.getWon()%></td>
			</tr>
			<tr>
				<td>Lost :</td>
				<td><%=cricketTeam.getLost()%></td>
			</tr>

		</table>
		<%
		}
		%>
		<div align="center">
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
		</div>
	</div>
</body>
</html>