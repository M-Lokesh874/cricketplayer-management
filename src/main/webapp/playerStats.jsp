<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>player stats</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
</head>
<body>

	<li><a href="index">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>

	<div align="center">
		<h1>Player stats....!</h1>
		<br>
		<table>
			<tr>
				<td><a href="createStats.jsp"><button type="button"
							class="btn-info btn-sm">create stats</button></a><br></td>
			</tr>
			<tr>
				<td><a href="getStats.jsp"><button type="button"
							class="btn-info btn-sm">display stats by id</button></a><br></td>
			</tr>
			<tr>
				<td><a href="deleteStats.jsp"><button type="button"
							class="btn-info btn-sm">delete stats by id</button></a><br></td>
			</tr>
			<tr>
				<td><a href="updateStats.jsp"><button type="button"
							class="btn-info btn-sm">update stats by id</button></a><br></td>
			</tr>
			<tr>
				<td><a href="assignPlayerForStats.jsp"><button
							type="button" class="btn-info btn-sm">assign player</button></a><br></td>
			</tr>
		</table>
	</div>

</body>
</html>