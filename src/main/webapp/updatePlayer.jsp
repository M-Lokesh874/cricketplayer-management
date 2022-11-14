<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update player by id</title>
    <link rel="stylesheet" href=
"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<li><a href = "home.jsp"> <button class = "btn btn-success"> Home</button> </a></li>
<li><a href="cricketPlayer.jsp"> <button class="btn btn-success">Back</button></a></li><br>
<div align = "center">
<h1>Update player</h1>
<form action="getplayerbyid" method = "get">
Id:<input type = "number" name = "id">
<input type ="submit" name = "submit" value ="click here" <div class="button btn-info btn-sm"></div >>
</form>
</div>

<form action = "updateplayerbyid" method = "post">
<%CricketPlayer cricketPlayer = (CricketPlayer)session.getAttribute("cricketplayer"); %>
<%if(null != cricketPlayer)  {%>
<table class="table bg-info"> 
<tr>
<td> Id of team</td> <td><input name="id" value = <%= cricketPlayer.getId()%> type="text" readonly></td>
</tr>
<tr>
    <td>Name</td> 
    <td> <input type="text" name="name" value = <%= cricketPlayer.getName()%>></td>
</tr>

<tr>
    <td>Date of Birth</td>
    <td> <input type="date" name="dateofbirth" value = <%= cricketPlayer.getDateOfBirth()%>></td>
</tr>

<tr>
    <td>Country</td>
    <td><input type = "text" name = "country"value = <%= cricketPlayer.getCountry()%>></td>
</tr>

<tr>
<tr><td>Gender<td><td></td></tr>
    <td><input type = "radio"  name ="gender" value = "MALE" value = <%= cricketPlayer.getGender()%>></td>
    <td>Male</td>
</tr>

<tr>
    <td><input type = "radio"  name ="gender"  value = "FEMALE" value = <%= cricketPlayer.getGender()%>></td>
    <td>Female</td>
</tr>

<tr>
    <td>Email</td>
    <td><input type = "text" name = "email" value = <%= cricketPlayer.getEmail()%>></td>
</tr>

</table>

<input type ="submit" name = "submit" value ="update" <div class="button btn-info btn-sm"></div >>
<%} %>
</form>

<%if(null != session.getAttribute("cricketPlayer"))  {%>
<%CricketPlayer cricketPlayer1 = (CricketPlayer)session.getAttribute("cricketPlayer");%>
<%if(cricketPlayer1.getName() != cricketPlayer.getName() || cricketPlayer1.getCountry() != cricketPlayer.getCountry() || 
    cricketPlayer1.getDateOfBirth() != cricketPlayer.getDateOfBirth() 
    || cricketPlayer1.getGender() != cricketPlayer.getGender() || cricketPlayer1.getEmail() != cricketPlayer.getEmail())
{%>
<%= "updated successfully"%>
<%} else { %>
<%= "not updated succesfully"%>
<%} }%>
</body>
</html>