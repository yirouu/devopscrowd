<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<%
HttpSession sessions = request.getSession();
%><%
String isLoggedIn = (String)sessions.getAttribute("isLoggedIn");
%>

<nav class="navbar navbar-expand-md navbar-light">
	<div>
		<a class="navbar-brand"> Crowds </a>
	</div>
	<ul class="navbar-nav">
		<li><a href="<%=request.getContextPath()%>/product.jsp"
			class="nav-link">Shop Here!</a></li>
	</ul>

	<%
	if (isLoggedIn != null && isLoggedIn.equals("LoggedIn")) {
	%>
	<ul class="navbar-nav ms-auto">
		<li><a href="<%=request.getContextPath()%>/register.jsp"
			class="nav-link">Logout</a></li>
	</ul>
	<%
	} 
else {
	%>
	<ul class="navbar-nav ms-auto">
		<li><a href="<%=request.getContextPath()%>/register.jsp"
			class="nav-link">Register</a></li>
		<li><a href="<%=request.getContextPath()%>/login.jsp"
			class="nav-link">Login</a></li>
	</ul>
	<%
	}
	%>
</nav>
