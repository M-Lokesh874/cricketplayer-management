<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create team</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<li><a href="index">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>
	<div align="center">
		<h1>Create team</h1>

		<form:form action="create-team" modelAttribute="cricketTeam" method="post">

			<table class="table bg-info">
			    <form:input type="hidden" path="id" />
				<tr>
					<td>Name</td>
					<td><form:input path = "name"/></td>
				</tr>

				<tr>
					<td>Total match :</td>
					<td><form:input path = "totalMatch"/></td>
				</tr>

				<tr>
					<td>Won :</td>
					<td><form:input path = "won"/></td>
				</tr>

				<tr>
					<td>Lost :</td>
					<td><form:input path = "lost"/></td>
				</tr>

			</table>

			<input type="submit" name="sumbit" value="Register">
				<div class="button btn-info btn-sm"></div >
		</form:form>
	</div>
</body>
</html>