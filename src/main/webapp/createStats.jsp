<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>

<li><a href = "home.jsp"> <button class="btn btn-success">Home</button></a></li><br>
<div align = "center">
<h1>Create stats</h1>
<form action="createstats" method = "post">

<table class="table bg-info">
<tr>
    <td>No of match :</td> 
    <td> <input type="number" name="noofmatch">
</tr>

<tr>
    <td>Total run :</td>
    <td> <input type="number" name="totalrun"></td>
</tr>

<tr>
    <td>Top score :</td>
    <td><input type = "number" name = "topscore"></td>
</tr>


<tr>
    <td>No of balls faced :</td>
    <td><input type = "number" name = "noofballsfaced"></td>
</tr>

<tr>
    <td>No of wickets :</td>
    <td><input type = "number" name = "noofwickets"></td>
</tr>

</table>

<input type ="submit" name = "sumbit" value = "Register" <div class="button btn-info btn-sm"></div >>
</form>
</div>
</body>
</html>