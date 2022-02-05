<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navbar.jsp" %>
<meta charset="ISO-8859-1">
<title>Crowds</title>
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
<%@ include file="css/product.css"%>
</style>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">Products</h3>
			<hr>
			<br>
			<div class="row">
				<c:forEach var="products" items="${listProduct}">
					<div class="col-sm-4">
						<div class="card" style="width: 23rem;">
							<img class="card-img-top" src="${products.image}"
								alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title">
									<c:out value="${products.name}" />
								</h5>
								<p class="card-text">
									SGD $<c:out value="${products.price}" />
									<br>
									<c:out value="${products.description}" />
								</p>
								<a
									href="details?productid=<c:out value='${products.productid}'/>"
									class="btn details">View details</a>
							</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>