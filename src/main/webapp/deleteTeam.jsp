<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete team by id</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>

	<li><a href="index">
			<button class="btn btn-success">Home</button>
	</a></li>
		<br>
	<li><a href="cricketteams">
			<button class="btn btn-success">Back</button>
	</a></li>
	<br>
	<div align="center">
		<h1>Delete team</h1>
		<form action="delete-team" method="post">
			Id:<input type="number" name="id"> <input type="submit"
				name="submit" value="click here"
				<div class="button btn-info btn-sm"></div >>

		</form>
	</div>
	${isDeleted }
	
</body>
</html>