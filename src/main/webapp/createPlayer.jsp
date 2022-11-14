<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<li><a href="home.jsp">
			<button class="btn btn-success">Home</button>
	</a></li>
	<li><a href="cricketPlayer.jsp">
			<button class="btn btn-success">Back</button>
	</a></li>
	</ul>
	<br>

	<div align="center">
		<h1>Add new player</h1>

		<form action="createplayer" method="post">
			<table class="table bg-info">
				<tr>
					<td>Name</td>
					<td><input type="text" name="name">
				</tr>

				<tr>
					<td>Date of Birth</td>
					<td><input type="date" name="dateofbirth"></td>
				</tr>

				<tr>
					<td>Country</td>
					<td><input type="text" name="country"></td>
				</tr>

				<tr>
				<tr>
					<td>Gender</td>
					<td><input type="radio" name="gender" value="FEMALE" id="female"> <label for="female">FEMALE</label>
						<input type="radio" name="gender" value="MALE" id="male"> MALE</td>
				</tr>


				<tr>
					<td>Email</td>
					<td><input type="text" name="email"></td>
				</tr>

			</table>

			<input type="submit" name="sumbit" value="Register"
				<div class="button btn-info btn-sm"></div >>

		</form>
	</div>
</body>
</html>