<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navbar.jsp" %>
<meta charset="ISO-8859-1">
<title>Crowd</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Domine:wght@600&family=Poppins:wght@300;400;600&display=swap"
	rel="stylesheet">
</head>
<style type="text/css">
<%@include file="css/product.css"%>
</style>
<body>
	<div class="row">
		<div class="column">
			<img src="${productdetail.image}">
		</div>
		<div class="column">
		<br><br>
			<div class="contents">
				<h2>
					<c:out value="${productdetail.name}" />
				</h2>
				<h4>SGD $<c:out value="${productdetail.price}" /></h4>
				<br>
				<h4><c:out value="${productdetail.description}" /></h4>
				<br><br>
				<a href="http://localhost:8090/devopscrowd/addToCartServlet?productid=<c:out value='${productdetail.productid}'/>"class="btn btn-secondary btn-lg">Checkout</a>
			</div>
			
		</div>
	</div>
</body>
</html>