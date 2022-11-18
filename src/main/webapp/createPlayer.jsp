<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>
	<ul type="square">
		<li><a href="index">
				<button class="btn btn-success">Home</button>
		</a></li>
		<li><a href="cricketplayer">
				<button class="btn btn-success">Back</button>
		</a></li>
	</ul>
	<br>

	<div align="center">
		<h1>Player</h1>

		<form:form action="create-player" modelAttribute="cricketPlayer"
			method="post">
			<table class="table bg-info">
				<form:input type="hidden" path="id" />
				<form:input type="hidden" path="playerCode" />
				<tr>
					<td>Name</td>
					<td><form:input path="name" /></td>
				</tr>

				<tr>
					<td>Date of Birth</td>
					<td><form:input path="dateOfBirth" placeholder="dd/mm/yyyy" /></td>
				</tr>

				<tr>
					<td>Country</td>
					<td><form:input path="country" /></td>
				</tr>

				<tr>
				<tr>
					<td>Gender</td>
					<td><form:radiobuttons path="gender" /></td>
				</tr>


				<tr>
					<td>Email</td>
					<td><form:input path="email" /></td>
				</tr>

				<tr>
					<td>Delete</td>
					<td><form:input path="deleted" /></td>
				</tr>

			</table>

			<input type="submit" name="sumbit" value="Register"
				<div class="button btn-info btn-sm"></div >>

		</form:form>
	</div>
</body>
</html>