<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete team by id</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>

	<li><a href="home.jsp">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>
	<div align="center">
		<h1>Delete team</h1>
		<form action="deleteteambyid" method="post">
			Id:<input type="number" name="id"> <input type="submit"
				name="submit" value="click here"
				<div class="button btn-info btn-sm"></div >>

		</form>
	</div>

	<%
	if (null != session.getAttribute("found")) {
	%>
	<%
	boolean found = (boolean) session.getAttribute("found");
	%>
	<%
	if (found)
	%>
	deleted successfully
	<%
	}
	%>
</body>
</html>