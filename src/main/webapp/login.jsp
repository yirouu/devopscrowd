<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navbar.jsp"%>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form action="LoginServlet" method="post">

		<h1>
			Email: <br> <input type="text" name="yourEmail" size="20">
		</h1>
		<br>
		<h1>
			Password: <br> <input type="password" name="yourPassword"
				size="20">
		</h1>
		<br>

		<!-- Implement submit button with type = submit to perform the request when clicked -->
		<div class="sbtn">
			<input type="submit" value="Login" />
		</div>
		<br>
		<h1>
			<a href="<%=request.getContextPath()%>/register.jsp" class="nav-link">Don't
				have an account? Register here!</a>
		</h1>

		<%
		String cred = "";
		%>
		<%
		cred = (String) session.getAttribute("credential");
		%>
		<%
		if (cred != null && cred.equals("incorrect")) {
		%>
		<div class="besideemailbox" style="color: red; text-align: center;">Incorrect Email or Password.</div>
		<%
		}
	
		%>

	</form>
</body>
</html>