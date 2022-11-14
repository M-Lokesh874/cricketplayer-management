<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create team</title>
    <link rel="stylesheet" href=
"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<li><a href = "home.jsp"> <button class="btn btn-success">Home</button></a></li><br>
<div align = "center">
<h1>Create team</h1>

<form action="createteam" method = "post">

<table class="table bg-info">
<tr>
    <td>Name</td> 
    <td> <input type="text" name="name">
</tr>

<tr>
    <td>Total match :</td> 
    <td> <input type="number" name="totalmatch">
</tr>

<tr>
    <td>Won :</td>
    <td> <input type="number" name="won"></td>
</tr>

<tr>
    <td>Lost :</td>
    <td><input type = "number" name = "lost"></td>
</tr>

</table>

<input type ="submit" name = "sumbit" value = "Register" <div class="button btn-info btn-sm"></div >>
</form>
</div>
</body>
</html>