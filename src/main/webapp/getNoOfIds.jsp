<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get no of ids</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<li> <a href = "home.jsp"> <button class = "btn btn-success"> Home</button> </a></li>
<li><a href="cricketPlayer.jsp"> <button class="btn btn-success">Back</button></a></li><br>
<div align ="center">
<h1>Display players by multiple ids</h1>
<form action = "displayMultiplePlayers.jsp" method="post">
noofids : <input type = "number" name = "noofids">
<input type="submit" name="submit" value="search" <div class="button btn-info btn-sm"></div >>
</form>
</div>

</body>
</html>