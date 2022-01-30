<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> User Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/OrderServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${order != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${order == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${order != null}">
Edit User
</c:if>
						<c:if test="${order == null}">
Add New User
</c:if>
					</h2>
				</caption>
				<c:if test="${order != null}">
					<input type="hidden" name="orderid"
						value="<c:out
value='${order.orderid}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>OrderID</label> <input type="text"
						value="<c:out
value='${order.orderid}' />" class="form-control"
						name="orderid" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Order Status</label> <input type="text"
						value="<c:out
value='${order.orderStatus}' />" class="form-control"
						name="orderStatus" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>