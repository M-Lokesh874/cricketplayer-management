<%@page import="java.util.List"%>
<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search player by id</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
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
		<h1>Search player</h1>

		<form action="search-player" method="get">
			Name:<input type="text" name="name"> <input type="submit"
				name="submit" value="search">
			<div class="button btn-info btn-sm"></div>
		</form>
	</div>

	${cricketPlayer}
<%-- 	<%
	if (request.getAttribute("cricketPlayer") != null) {
	%>
	<%
	List<CricketPlayer> cricketPlayers = (List<CricketPlayer>) request.getAttribute("cricketPlayer");
	%> --%>
<%-- 	<%
	for (CricketPlayer cricketPlayer : cricketPlayers) {
	%> --%>
	
<%-- 	<table class="table bg-info">
<c:forEach var = "player" items = "${cricketPlayer}">
		<tr>
			<td>Id :</td>
			<td>${player.id()}</td>
		</tr>
		<tr>
			<td>Player Code :</td>
			<td>${Player.playerCode()}</td>
		</tr>
 		<tr>
			<td>Name :</td>
			<td>${player.name()}</td>
		</tr>
		<tr>
			<td>Date Of birth :</td>
			<td>${player.dateOfBirth()}</td>
		</tr>
		<tr>
			<td>Country :</td>
			<td>${player.country()}</td>
		</tr>
				<tr>
			<td>Gender :</td>
			<td>${player.gender()}</td>
		</tr>
		<tr>
			<td>Email :</td>
			<td>${player.email()}</td>
		</tr>
		</c:forEach>

	</table> --%>
	
<%-- 	<%
	}
	%> --%>
<%-- 	<%
	}
	%> --%>
	
	<%
	if (request.getAttribute("cricketPlayers") != null) {
	%>
	<%
	List<CricketPlayer> cricketPlayers = (List<CricketPlayer>) request.getAttribute("cricketPlayers");
	%>
	<%
	if (cricketPlayers != null) {
	%>
	<%
	for (CricketPlayer cricketPlayer : cricketPlayers) {
	%>
	<table class="table bg-info bordered">

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
	}
	%>
	<%
	}
	%>
</body>
</html>