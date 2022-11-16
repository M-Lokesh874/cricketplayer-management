<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cricket Player</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<li><a href="index">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>

	<div align="center">
		<h1>Cricket player....!</h1>
		<br>
		<table>
			<tr>
				<td><a href="createplayer"><button type="button"
							class="btn-info btn-sm">create player</button></a><br></td>
			</tr>
			<form action="displayplayers" method="get">
				<tr>
					<td><button class="btn btn-info btn-sm" type="submit">
							Display players</button></td>
				</tr>
			</form>
			<tr>
				<td><a href="getplayer"><button type="button"
							class="btn-info btn-sm">display player by id</button></a><br></td>
			</tr>
			<tr>
				<td><a href="updateplayer"><button type="button"
							class="btn-info btn-sm">update player</button></a><br></td>
			</tr>
			<tr>
				<td><a href="deleteplayer"><button type="button"
							class="btn-info btn-sm">delete player</button></a><br></td>
			</tr>
			<tr>
				<td><a href="searchPlayer.jsp"><button type="button"
							class="btn-info btn-sm">search player</button></a><br></td>
			</tr>
			<tr>
				<td><a href="displayPlayersBetweenDateOfBirth.jsp"><button
							type="button" class="btn-info btn-sm">get players
							between dates</button></a><br></td>
			</tr>
			<tr>
				<td><a href="getNoOfIds.jsp"><button type="button"
							class="btn-info btn-sm">get players by multiple ids</button></a><br></td>
			</tr>
			<tr>
				<td><a href="assignTeam.jsp"><button type="button"
							class="btn-info btn-sm">assign team</button></a><br></td>
			</tr>
			<tr>
				<td><a href="displayPlayerWithTeams.jsp"><button
							type="button" class="btn-info btn-sm">display player
							with teams</button></a><br></td>
			</tr>

		</table>
	</div>
</body>
</html>