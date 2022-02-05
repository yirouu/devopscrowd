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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Domine:wght@600&family=Poppins:wght@300;400;600&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style type="text/css">
<%@ include file="css/cart.css"%>
</style>
<body>
		<div class="row">
		<div class="container">
		<br>
			<h3 class="text-center">YOUR CART</h3>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">
				<thead>
					<tr>
						<th>item</th>
						<th>name</th>
						<th>description</th>
						<th>quantity</th>
						<th>price</th>
						<th>remove</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>

						<td><img class="img"
							src="https://theeditorsmarket.g.shopcadacdn.com/sites/files/theeditorsmarket/productimg/202201/bonita-ashgreen-2.jpg"></td>
						<td>Bonita Relaxed Shorts</td>
						<td>Fabric composition: 100% Linen</td>
						<td><form action="" method="post"
								class="form-inline">
								<input type="hidden" name="id" value="1"
									class="form-input">
								<div class="form-group d-flex justify-content-between">
									<a class="btn bnt-sm btn-incre"><i
										class="fa fa-angle-left"></i></a> <input type="text"
										name="quantity" class="form-control quantity"
										value="1" readonly> <a
										class="btn btn-sm btn-decre"><i
										class="fa fa-angle-right"></i></a>
								</div>
							</form></td>
						<td>SGD $35.00</td>
						<td><a class="btn"><i class="fa fa-close" ></i></a></td>
					<tr>
					
							<tr>

						<td><img class="img"
							src="https://theeditorsmarket.g.shopcadacdn.com/sites/files/theeditorsmarket/productimg/202201/bonita-ashgreen-2.jpg"></td>
						<td>Bonita Relaxed Shorts</td>
						<td>Fabric composition: 100% Linen</td>
						<td><form action="" method="post"
								class="form-inline">
								<input type="hidden" name="id" value="1"
									class="form-input">
								<div class="form-group d-flex justify-content-between">
									<a class="btn bnt-sm btn-incre"><i
										class="fa fa-angle-left"></i></a> <input type="text"
										name="quantity" class="form-control quantity"
										value="1" readonly> <a
										class="btn btn-sm btn-decre"><i
										class="fa fa-angle-right"></i></a>
								</div>
							</form></td>
						<td>SGD $35.00</td>
						<td><a class="btn"><i class="fa fa-close" ></i></a></td>
					<tr>
				</tbody>
			</table>
			<hr>
			<div class="container text-right">
				<!-- Add new user button redirects to the register.jsp page -->
				<h4>Total:  SGD $123.00</h4>
					<a href="<%=request.getContextPath()%>/register.jsp"
					class="btn checkout">Checkout</a>
			</div>
		</div>
	</div>

	
</body>
</html>