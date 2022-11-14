<%@page import="com.playermanagement.model.CricketTeam"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%CricketTeam cricketTeam = (CricketTeam) session.getAttribute("cricketTeam"); %>
<html>
<head>
<meta charset="UTF-8">
<title>Display team by id</title>
    <link rel="stylesheet" href=
"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>

<li><a href = "home.jsp"> <button class="btn btn-success">Home</button></a></li><br>
<div align = "center">

<h1>Display team by id</h1>
<form action="displayteambyid" method = "post">
Id:<input type = "number" name = "id">
<input type ="submit" name = "submit" value ="display here" <div class="button btn-info btn-sm"></div >>

</form>
</div>
</div>

<% if(null != cricketTeam) { %>
<table class="table bg-info">

<tr><td> Id : </td><td><%= cricketTeam.getId() %></td></tr>
<tr><td> Name :</td><td><%=cricketTeam.getName() %></td></tr>
<tr><td> Total match played : </td><td> <%= cricketTeam.getTotalMatch() %> </td></tr>
<tr><td> Won : </td><td><%= cricketTeam.getWon() %></td></tr>
<tr><td> Lost : </td><td><%= cricketTeam.getLost() %></td></tr>

</table>
<%} %>

<% if (null != session.getAttribute("found")) {%>
<%boolean found = (boolean) session.getAttribute("found"); %>
<%if(found) {%>
Assigned succesfully
<% } else { %>
not assigned successfully
<% }} %>

</body>
</html>