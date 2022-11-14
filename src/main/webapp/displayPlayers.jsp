<%@page import="com.playermanagement.model.CricketPlayer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display players</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<h1>Display players</h1>
</head>
<body>
<c:forEach items="${cricketPlayers}" var="player">
${player.id}
</c:forEach>
  ${cricketPlayers}
</body>
</html>
    
<%-- <!DOCTYPE html>
<% List<CricketPlayer> cricketPlayers = (List<CricketPlayer>) session.getAttribute("cricketPlayers"); %>
<html>
<head>
<meta charset="UTF-8">
<title>Display players</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


</head>
<body>

<li><a href = "home.jsp"> <button class="btn btn-success">Home</button></a></li><br>
<li><a href="cricketPlayer.jsp"> <button class="btn btn-success">Back</button></a></li><br>

<div align = "center">
<h1>Display players</h1>

<form action="displayplayers" method="get">
</form>
</div>
<% if(cricketPlayers != null) { %>
<% for(CricketPlayer cricketPlayer : cricketPlayers) { %>
<table class="table bg-info bordered">

<tr><td> Id of an employee</td> <td><input readonly value=<%=cricketPlayer.getId()%>></td></tr>
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
</html> --%>