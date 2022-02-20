<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navbar.jsp"%>
<meta charset="ISO-8859-1">
<title>Register</title>
<link href="css/register.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<form action="RegisterServlet" method="post">

		<h1>
			Name: <br> <input type="text" name="yourName" size="20">
		</h1>
		<h1>
			Email: <br> <input type="text" name="yourEmail" size="20">
		</h1>
		<h1>
			Password: <br> <input type="password" name="yourPassword"
				size="20">
		</h1>
		<h1>
			Address: <br> <input type="text" name="yourAddress" size="20">
		</h1>
		<h1>
			Postal: <br> <input type="number" name="yourPostal" size="20"
				min="100000" max="999999">
		</h1>
		<br>
		<br>
		<!-- Implement submit button with type = submit to perform the request when clicked -->
		<div class="sbtn">
			<input type="submit" value="Register" />
		</div>
		<br>
		<h1>
			<a href="<%=request.getContextPath()%>/login.jsp" class="nav-link">Already
				have an account? Login here!</a>
		</h1>
		<%String s1 = "";%>
		<%
		s1 = (String) session.getAttribute("email");
		%>
		<%
		if (s1 != null && s1.equals("exist")) {
		%>
		<div class="besideemailbox" style="color: red; text-align : center;">Email Already
			exist, please enter another email.</div>
		<%
		
		}
		%>
	</form>

</body>
</html>