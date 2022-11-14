<%@page import="com.playermanagement.model.CricketTeam"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%List<CricketTeam> cricketTeams = (List<CricketTeam>) session.getAttribute("cricketTeams"); %>
<html>
<head>
<meta charset="UTF-8">
<title>Display team</title>
    <link rel="stylesheet" href=
"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>

<li><a href = "home.jsp"> <button class="btn btn-success">Home</button></a></li><br>
<div align = "center">
<h1>Display team</h1>
<form action="displayteams" method = "get">
<input type="submit" name="submit" value="display here" <div class="button btn-info btn-sm"></div >>
</form>
</div>
<% if(cricketTeams != null) { %>
<% for(CricketTeam cricketTeam : cricketTeams) { %>
<table class="table bg-info">

<tr><td> Id : </td><td><%= cricketTeam.getId() %></td></tr>
<tr><td> Name :</td><td><%=cricketTeam.getName() %></td></tr>
<tr><td> Total match played : </td><td> <%= cricketTeam.getTotalMatch() %> </td></tr>
<tr><td> Won : </td><td><%= cricketTeam.getWon() %></td></tr>
<tr><td> Lost : </td><td><%= cricketTeam.getLost() %></td></tr>

</table>
<%} %>
<%} %>
</body>
</html>