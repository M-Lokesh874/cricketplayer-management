<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>
	<li><a href="index">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>
	<li><a href="cricketplayer">
			<button class="btn btn-success">Back</button>
	</a></li>
	<br>
	<div align="center">
		<h1>Delete player</h1>
		<form action="delete-player" method="post">
			Id:<input type="number" name="id"> <input type="submit"
				name="submit" value="click here">
			<div class="button btn-info btn-sm"></div>
		</form>
	</div>

	${isDeleted }

</body>
</html>