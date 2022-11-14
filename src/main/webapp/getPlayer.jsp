<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% CricketPlayer cricketPlayer = (CricketPlayer)session.getAttribute("cricketPlayer"); %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<li><a href = "home.jsp"> <button class = "btn btn-success"> Home</button> </a></li>
<li><a href="cricketPlayer.jsp"> <button class="btn btn-success">Back</button></a></li><br>

<div align = "center">
<h1>Display player</h1>

<form action="displayplayerbyid" method = "post">
Id:<input type = "number" name = "id">
<input type ="submit" name = "submit" value ="display here" <div class="button btn-info btn-sm"></div >>
</form>
</div>
<%if(cricketPlayer != null) { %>
<table class = "table bg-info">

<tr><td> Id : </td><td><%= cricketPlayer.getId() %></td></tr>
<tr><td> Player Code :</td><td><%=cricketPlayer.getPlayerCode() %></td></tr>
<tr><td> Name : </td><td> <%= cricketPlayer.getName() %> </td></tr>
<tr><td> Date Of birth : </td><td><%= cricketPlayer.getDateOfBirth() %></td></tr>
<tr><td> Country : </td><td><%= cricketPlayer.getCountry() %></td></tr>
<tr><td> Email : </td><td><%= cricketPlayer.getEmail() %></td></tr>

</table>

<%} %>

</body>
</html>