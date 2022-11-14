<%@page import="com.playermanagement.model.CricketTeam"%>
<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
CricketTeam cricketTeam = (CricketTeam) session.getAttribute("cricketTeam");
%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

<title>Assign wicketkeeper</title>
</head>
<body>

	<div align="center">
		<h1>Assign wicketkeeper</h1>
		<form action="assignwicketkeeper" method="post">

			Team id :<input type="number" name="teamid"> Player id :<input
				type="number" name="playerid"> <input type="submit"
				name="submit" value="assign"
				<div class="button btn-info btn-sm"</div>>
		</form>
	</div>

	<%
	if (null != cricketTeam) {
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
</body>
</html>