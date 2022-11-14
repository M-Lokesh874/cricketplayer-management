<%@page import="com.playermanagement.model.CricketTeam"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
CricketTeam cricketTeam = (CricketTeam) session.getAttribute("cricketTeam");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Assign captain</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<li><a href="home.jsp">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>
	<div align="center">
		<h1>Assign captain</h1>
		<form action="assigncaptain" method="post">

			Team Id :<input type="number" name="teamid"> Player Id :<input
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