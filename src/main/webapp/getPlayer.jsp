<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>get-player</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>

	<li><a href="home">
			<button class="btn btn-success">Home</button>
	</a></li>
	<li><a href="cricketPlayer">
			<button class="btn btn-success">Back</button>
	</a></li>
	<br>

	<div align="center">
		<h1>Player</h1>

		<form action="getplayer" method="get">
			Id:<input type="number" name="id"> <input type="submit"
				name="submit" value="display here">
			<div class="button btn-info btn-sm"></div>
		</form>
	</div>
	<%
	if (request.getAttribute("cricketPlayer") != null) {
	%>
	<%
	CricketPlayer cricketPlayer = (CricketPlayer) request.getAttribute("cricketPlayer");
	%>
	<table class="table bg-info">
		<tr>
			<td>Id :</td>
			<td><%=cricketPlayer.getId()%></td>
		</tr>
		<tr>
			<td>Player Code :</td>
			<td>${cricketPlayer.getPlayerCode()}</td>
		</tr>
		<tr>
			<td>Name :</td>
			<td>${cricketPlayer.getName()}</td>
		</tr>
		<tr>
			<td>Date Of birth :</td>
			<td>${cricketPlayer.getDateOfBirth()}</td>
		</tr>
		<tr>
			<td>Country :</td>
			<td>${cricketPlayer.getCountry()}</td>
		</tr>
		<tr>
			<td>Email :</td>
			<td>${cricketPlayer.getEmail()}</td>
		</tr>
		<%
		}
		%>

	</table>
</body>
</html>