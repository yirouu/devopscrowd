<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<%@ include file="navbar.jsp" %>

</head>
<body>
	<div class="row">
		<div class="container">


			<h3 class="text-center">My Profile</h3>
			<br>
			<br>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">



				<tr>
					<th>Your Name :</th>
					<td>${GetUser.username}</td>

				</tr>
				<tr>
					<th>Your Email :</th>
					<td>${GetUser.email}</td>

				</tr>
				<tr>
					<th>Your Address :</th>
					<td>${GetUser.address}</td>
				</tr>
				<tr>

					<th>Your Postal Code:</th>
					<td>${GetUser.postal}</td>
				</tr>
				<tr>
					<td><a href="edit?id=<c:out value='${GetUser.userid}'/>">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?id=<c:out value='${GetUser.userid}' />">Delete</a></td>
				</tr>
				

			</table>
		</div>
	</div>
</body>
</html>