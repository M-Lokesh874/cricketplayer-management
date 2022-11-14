<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@page import="com.playermanagement.model.CricketPlayerStats"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%CricketPlayerStats cricketPlayerStats = (CricketPlayerStats)session.getAttribute("cricketPlayerStats"); %>

<html>
<head>
<meta charset="UTF-8">
<title>Display stats by id</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<li><a href = "home.jsp"> <button class="btn btn-success">Home</button></a></li><br>
<div align = "center">
<h1>Display stats by id</h1>
<form action="displaystatsbyid" method = "post">
Id:<input type = "number" name = "id">
<input type ="submit" name = "submit" value ="display here" <div class="button btn-info btn-sm"></div >>

</form>
</div>


<%if(null != cricketPlayerStats) {%>
Stats Of Cricket Player
<table class = "table bg-info">

<tr> <td> Id :</td><td><%= cricketPlayerStats.getId() %></td></tr>
<tr> <td> No of match :</td><td><%= cricketPlayerStats.getNoOfMatch() %></td></tr>
<tr> <td> Total run :</td><td><%= cricketPlayerStats.getTotalRun() %></td></tr>
<tr> <td> Top score :</td><td><%= cricketPlayerStats.getTopScore() %></td></tr>
<tr> <td> No of balls faced :</td><td><%= cricketPlayerStats.getNoOfBallsFaced() %></td></tr>
<tr> <td> Batting average :</td><td><%= cricketPlayerStats.getBattingAverage() %></td></tr>
<tr> <td> Strike rate :</td><td><%= cricketPlayerStats.getStrikeRate() %></td></tr>
</table>

<% CricketPlayer cricketPlayer = cricketPlayerStats.getCricketPlayer();%>


<table class = "table bg-info">
<%if(null != cricketPlayer)  {%>
Details of Cricket Player
<tr><td> Id : </td><td><%= cricketPlayer.getId() %></td></tr>
<tr><td> Player Code :</td><td><%=cricketPlayer.getPlayerCode() %></td></tr>
<tr><td> Name : </td><td> <%= cricketPlayer.getName() %> </td></tr>
<tr><td> Date Of birth : </td><td><%= cricketPlayer.getDateOfBirth() %></td></tr>
<tr><td> Country : </td><td><%= cricketPlayer.getCountry() %></td></tr>
<tr><td> Email : </td><td><%= cricketPlayer.getEmail() %></td></tr>
</table>
<%} %>
<%} %>

</body>
</html>