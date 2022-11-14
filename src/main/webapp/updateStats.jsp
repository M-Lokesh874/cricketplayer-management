<%@page import="com.playermanagement.model.CricketPlayerStats"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update stats</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
</head>
<body>

	<li><a href="home.jsp">
			<button class="btn btn-success">Home</button>
	</a></li>
	<div align="center">
		<h1>Update stats</h1>
		<form action="getstatsbyid" method = "get">
			Id:<input type="number" name="id"> <input type="submit"
				name="submit" value="click here"
				<div class="button btn-info btn-sm"></div >>
		</form>
	</div>

	<form action="updatestatsbyid" method="post">
		<%
		CricketPlayerStats cricketPlayerStats = (CricketPlayerStats) session.getAttribute("cricketPlayer");
		%>
		<%
		if (null != cricketPlayerStats) {
		%>
		<table class="table bg-info">
			<tr>
				<td>Id of player</td>
				<td><input name="id" value=<%=cricketPlayerStats.getId()%>
					type="text" readonly></td>
			</tr>
			<tr>
				<td>No of match :</td>
				<td><input type="number" name="noofmatch"
					value=<%=cricketPlayerStats.getNoOfMatch()%>></td>
			</tr>

			<tr>
				<td>Total run :</td>
				<td><input type="number" name="totalrun"
					value=<%=cricketPlayerStats.getTotalRun()%>></td>
			</tr>

			<tr>
				<td>Top score :</td>
				<td><input type="number" name="topscore"
					value=<%=cricketPlayerStats.getTopScore()%>></td>
			</tr>


			<tr>
				<td>No of balls faced :</td>
				<td><input type="number" name="noofballsfaced"
					value=<%=cricketPlayerStats.getNoOfBallsFaced()%>></td>
			</tr>

			<tr>
				<td>No of wickets :</td>
				<td><input type="number" name="noofwickets"
					value=<%=cricketPlayerStats.getNoOfWicket()%>></td>
			</tr>

			<tr>
				<td>Batting average : :</td>
				<td><input type="number" name="battingaverage"
					value=<%=cricketPlayerStats.getBattingAverage()%>></td>
			</tr>

			<tr>
				<td>Strike rate :</td>
				<td><input type="number" name="strikerate"
					value=<%=cricketPlayerStats.getStrikeRate()%>></td>
			</tr>
		</table>

		<input type="submit" name="submit" value="update"
			<div class="button btn-info btn-sm"></div >>

		<%
		}
		%>

	</form>


	<%
	if (null != session.getAttribute("cricketPlayerStats")) {
	%>
	<%
	CricketPlayerStats cricketPlayerStats1 = (CricketPlayerStats) session.getAttribute("cricketPlayerStats");
	%>
	<%
	if (cricketPlayerStats1.getTopScore() != cricketPlayerStats.getTopScore()
			|| cricketPlayerStats1.getNoOfMatch() != cricketPlayerStats.getNoOfMatch()
			|| cricketPlayerStats1.getNoOfBallsFaced() != cricketPlayerStats.getNoOfBallsFaced()
			|| cricketPlayerStats1.getNoOfWicket() != cricketPlayerStats.getNoOfWicket()
			|| cricketPlayerStats1.getStrikeRate() != cricketPlayerStats.getStrikeRate()
			|| cricketPlayerStats1.getBattingAverage() != cricketPlayerStats.getBattingAverage()) {
	%>
	<%="updated successfully"%>
	<%
	} else {
	%>
	<%="not updated succesfully"%>
	<%
	}
	}
	%>
</body>
</html>