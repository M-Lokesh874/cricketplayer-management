<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete stats</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>

	<li><a href="home.jsp">
			<button class="btn btn-success">Home</button>
	</a></li>
	<br>
	<div align="center">
		<h1>Delete stats</h1>
		<form action="deletestatsbyid" method="post">
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