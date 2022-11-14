<%@page import="java.util.List"%>
<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search player by id</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<li><a href = "home.jsp"> <button class="btn btn-success">Home</button></a></li><br>
<li><a href="cricketPlayer.jsp"> <button class="btn btn-success">Back</button></a></li><br>
<div align = "center">
<h1> Search player</h1>

<form action="searchplayerbyid" method="post">
Name:<input type = "text" name = "name">
<input type="submit" name="submit" value="search" <div class="button btn-info btn-sm"></div >>
</form>
</div>

<%List<CricketPlayer> cricketPlayers = (List<CricketPlayer>)session.getAttribute("cricketPlayers"); %>
<% if(cricketPlayers != null) { %>
<% for(CricketPlayer cricketPlayer : cricketPlayers) { %>
<table class="table bg-info">

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