<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>

	<li><a href="index">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>
	<div align="center">
		<h1>Create stats</h1>

		<form action="get-player-for-assign" method="get">
			Id:<input type="number" name="id"> <input type="submit"
				name="submit" value="display here">
			<div class="button btn-info btn-sm"></div>
		</form>
		<%if (null != request.getAttribute("cricketPlayer")) {%>
<%-- 		<%
		CricketPlayer cricketPlayer = (CricketPlayer) request.getAttribute("cricketPlayer");
		%>
		<table class="table bg-info">
			<tr>
				<td>Id :</td>
				<td>${cricketPlayer.getId()}</td>
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

		</table>
		<%
		}
		%>
		${cricketPlayer} 
		<br> --%>
		${status}
		
		Now you can create stats for this player...!

 		<form:form action="create-stats" modelAttribute="stats"
			method="post">
			<table class="table bg-info">
				<tr>
					<td>No of match :</td>
					<td><form:input path="noOfMatch" />
				</tr>

				<tr>
					<td>Total run :</td>
					<td><form:input path="totalRun" /></td>
				</tr>

				<tr>
					<td>Top score :</td>
					<td><form:input path="topScore" /></td>
				</tr>


				<tr>
					<td>No of balls faced :</td>
					<td><form:input path="noOfBallsFaced" /></td>
				</tr>

				<tr>
					<td>No of wickets :</td>
					<td><form:input path="noOfWicket" /></td>
				</tr>

			</table>

			<input type="submit" name="sumbit" value="Register">
			<div class="button btn-info btn-sm"></div>
		</form:form> 
		
		<%} %>
		
	</div>
	${created}
</body>
</html>