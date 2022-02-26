<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navbar.jsp"%>
<meta charset="ISO-8859-1">
<title>crowd</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<style type="text/css">
<%@include file="css/index.css"%>
</style>
<body>
	<div class="row">
		<div class="column" style="padding-left: 80px;">
			<img
				src="https://i.pinimg.com/564x/c1/5f/a9/c15fa9fe9a7a150eafe36bb9f1ea4f8e.jpg">
		</div>
		<div class="column" style="padding-right: 50px;">
			<img
				src="https://i.pinimg.com/564x/04/ce/83/04ce83802d5a7c9a9740cf41a5de517f.jpg">
		</div>
	</div>
	<div class="contents">
		<h1>new collection</h1>
		<p>Give your outfits-on-rotation an update with elevated staples for every occasion</p>
		<div class="container text-center">
		<!-- Add new user button redirects to the register.jsp page -->
				<a href="<%=request.getContextPath()%>/product.jsp"
					class="btn btn-outline-light">shop now</a>
		</div>
		
	</div>
</body>
</html>