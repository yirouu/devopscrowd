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

</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new user button redirects to the register.jsp page -->
				<a href="<%=request.getContextPath()%>/register.jsp"
					class="btn btnsuccess">Add New User</a>
			</div>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Email</th>
						<th>Password</th>
						<th>Role</th>
						<th>Address</th>
						<th>Postal</th>

					</tr>
				</thead>
				<!-- Pass in the list of users receive via the Servlet’s response to a loop -->
				<tbody>
					<c:forEach var="user" items="${GetUser}">
						<!-- For each user in the database, display their information accordingly -->
						<tr>
							<td><c:out value="${user.username}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.password}" /></td>
							<td><c:out value="${user.role}" /></td>
							<td><c:out value="${user.address}" /></td>
							<td><c:out value="${user.postal}" /></td>
							<!-- For each user in the database, Edit/Delete buttons
								which invokes the edit/delete functions -->
							<td><a href="edit?name=<c:out value='${user.username}'/>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?name=<c:out value='${user.username}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>