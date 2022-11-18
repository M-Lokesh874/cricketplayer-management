<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<div align="center">
		<h1>Cricket team....!</h1>
		<br>
		<table>
			<tr>
				<td><a href="createteam"><button type="button"
							class="btn-info btn-sm">create team</button></a><br></td>
			</tr>
			<tr>
				<td><a href="displayteams"><button type="button"
							class="btn-info btn-sm">display teams</button></a><br></td>
			</tr>
			<tr>
				<td><a href="getteam"><button type="button"
							class="btn-info btn-sm">display team by id</button></a><br></td>
			</tr>
			<tr>
				<td><a href="deleteTeam.jsp"><button type="button"
							class="btn-info btn-sm">delete team by id</button></a><br></td>
			</tr>
			<tr>
				<td><a href="updateTeam.jsp"><button type="button"
							class="btn-info btn-sm">update team by id</button></a><br></td>
			</tr>
			<tr>
				<td><a href="assignPlayer.jsp"><button type="button"
							class="btn-info btn-sm">assign player</button></a><br></td>
			</tr>
			<tr>
				<td><a href="assignCaptain.jsp"><button type="button"
							class="btn-info btn-sm">assign captain</button></a><br></td>
			</tr>
			<tr>
				<td><a href="assignWicketKeeper.jsp"><button type="button"
							class="btn-info btn-sm">assign wicketkeeper</button></a><br></td>
			</tr>
		</table>
	</div>
</body>
</html>