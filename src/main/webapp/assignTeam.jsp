<%@page import="java.util.List"%>
<%@page import="com.playermanagement.model.CricketTeam"%>
<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<%
int id = 0;
%>
<%
CricketPlayer cricketPlayer = (CricketPlayer) request.getAttribute("cricketPlayer");
%>
<%
List<CricketTeam> cricketTeams = (List<CricketTeam>) request.getAttribute("cricketTeams");
%>
<%
CricketTeam cricketTeam = (CricketTeam) request.getAttribute("cricketTeam");
%>
<%
if (null != cricketPlayer) {
%>
<%
id = cricketPlayer.getId();
%>
<%
}
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

<title>Assign team</title>
</head>
<body>

	<li><a href="index">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>
	<li><a href="cricketplayer">
			<button class="btn btn-success">Back</button>
	</a></li>
	<br>

	<div align="center">
		<h1>Assign team</h1>
		<form action="assign-team" method="post">

			Player id:<input type="number" name="playerid">
			<!-- Team id :<input type="number" name="teamid"> -->
			<input type="submit" name="submit" value="assign"
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
		if (null != cricketTeams) {
		%>

		<form action="after-assign" method="post">

			Player id:<input name="playerid" value="<%=id%>" readonly>
			Team id :<input type="number" name="teamid"> <input
				type="submit" name="submit" value="assign"
				<div class = "button btn-info btn-sm"</div>>
		</form>
		Cricket team
		<%
		for (CricketTeam team : cricketTeams) {
		%>
		<table class="table bg-info">

			<tr>
				<td>Id :</td>
				<td><%=team.getId()%></td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><%=team.getName()%></td>
			</tr>
		</table>
		<%
		}
		}
		%>

		<%
		if (null != cricketTeams && null != cricketTeam) {
		%>
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
		<div align="center">${status}</div>
	</div>
</body>
</html>