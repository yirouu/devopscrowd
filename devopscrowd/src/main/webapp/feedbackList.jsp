<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
<div class="container">
<h3 class="text-center">Feedbacks</h3>
<hr>
<div class="container text-left">
<!-- Add new user button redirects to the register.jsp page -->
<a href="<%=request.getContextPath()%>/feedCreate.jsp" class="btn btnsuccess">Add New User</a>
</div>
<br>
<!-- Create a table to list out all current users information -->
<table class="table">
<thead>
<tr>
<th>Feedback ID</th>
<th>Rating</th>
<th>Comment</th>
</tr>
</thead>
<!-- Pass in the list of users receive via the Servlet’s response to a loop
-->
<tbody>
<c:forEach var="feedback" items="${listFeedbacks}">
<!-- For each user in the database, display their
information accordingly -->
<tr>
<td>
<c:out value="${feedback.feedbackId}" />
</td>
<td>
<c:out value="${feedback.rating}" />
</td>
<td>
<c:out value="${feedback.comment}" />s
</td>
<td>
<!-- For each user in the database, Edit/Delete
buttons which invokes the edit/delete functions -->
<td>
<a href="edit?feedbackId=<c:out value='${feedback.feedbackId}'
/>">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="delete?feedbackId=<c:out
value='${feedback.feedbackId}' />">Delete</a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
</body>
</html>