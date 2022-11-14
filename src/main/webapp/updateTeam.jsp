<%@page import="com.playermanagement.model.CricketTeam"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update team by id</title>
    <link rel="stylesheet" href=
"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<li><a href = "home.jsp"> <button class = "btn btn-success"> Home</button> </a></li>
<div align = "center">
<h1>Update team</h1>
<form action="getteambyid" method = "get">
Id:<input type = "number" name = "id">
<input type ="submit" name = "submit" value ="click here" <div class="button btn-info btn-sm"></div >>
</form>
</div>

<form action = "updateteambyid" method = "post">
<%CricketTeam cricketTeam = (CricketTeam)session.getAttribute("cricketteam"); %>
<%if(null != cricketTeam)  {%>
<table class="table bg-info">
<tr>
<td> Id of team</td> <td><input name="id" value=<%=  cricketTeam.getId()%> type="text" readonly></td>
</tr>
<tr>
    <td> Name:</td> 
    <td> <input type="text" name="name" value = <%= cricketTeam.getName() %>></td>
</tr>

<tr>
    <td>Total match :</td>
    <td> <input type="number" name="totalmatch" value = <%=  cricketTeam.getTotalMatch() %>></td>
</tr>

<tr>
    <td>Won :</td>
    <td><input type = "number" name = "won" value = <%=  cricketTeam.getWon()%>></td>
</tr>


<tr>
    <td>Lost :</td>
    <td><input type = "number" name = "lost" value = <%=  cricketTeam.getLost()%>></td>
</tr>

</table>

<input type ="submit" name = "submit" value ="update" <div class="button btn-info btn-sm"></div >>

<%} %>

</form>

<%if(null != session.getAttribute("cricketTeam"))  {%>
<%CricketTeam cricketTeam1 = (CricketTeam)session.getAttribute("cricketTeam");%>
<%if(cricketTeam1.getName() != cricketTeam.getName() || cricketTeam1.getTotalMatch() != cricketTeam.getTotalMatch() || cricketTeam1.getWon() != cricketTeam.getWon() ||
cricketTeam1.getLost()!= cricketTeam.getWon()){%>
<%= "updated successfully"%>
<%} else { %>
<%= "not updated succesfully"%>
<%} }%>
</body>
</html>