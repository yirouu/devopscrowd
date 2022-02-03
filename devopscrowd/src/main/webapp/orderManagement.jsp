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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Domine:wght@600&family=Poppins:wght@300;400;600&display=swap"
	rel="stylesheet">
	<!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Orders</title>
</head>
<style type="text/css">
<%@ include file="css/order.css"%>
</style>
<body>
	<div class="row">
		<div class="container">
			<br>
			<h3 class="text-center">Orders</h3>
			<br>
			<!-- Create a table to list out all current users information -->
			<div style="overflow-x: auto;">
				<table class="table">
					<thead>
						<tr style="height: 30px; border: 0;">
							<th>orderID</th>
							<th>orderDate</th>
							<th>product</th>
							<th>user</th>
							<th>user email</th>
							<th>delivery address</th>
							<th>total price</th>
							<th>order status</th>
						</tr>
					</thead>
					<!-- Pass in the list of users receive via the Servlet’s response to a loop
-->
					<tbody>
						<c:forEach var="order" items="${listOrders}">
							<!-- For each user in the database, display their
information accordingly -->
							<tr>

								<!-- <td><img class="image" src="${order.image}" /></td>-->
								<td><p>
										#
										<c:out value="${order.orderid}" />
									</p></td>
								<td><p>
										<c:out value="${order.orderDateTime}" />
									</p></td>
								<td><p>
										<c:out value="${order.name}" />
									</p></td>
								<td><p>
										<c:out value="${order.username}" />
									</p></td>
									<td><p>
										<c:out value="${order.email}" />
									</p></td>
								<td><p>
										<c:out value="${order.address}"/>
										<br>
										S(<c:out value="${order.postal}"/>)
									</p></td>
										<td><p>
										$SGD<c:out value="${order.price}" />
									</p></td>
								
								<c:if test="${order.orderStatus == 'pending'}">
									<td><div class="pending">
											<i class="fa fa-exclamation" aria-hidden="true"></i><span>pending</span>
										</div></td>
								</c:if>
								<c:if test="${order.orderStatus != 'pending'}">
									<td><div class="confirm">
											<i class="fa fa-check" aria-hidden="true"></i><span>approved</span>
										</div></td>
								</c:if>
								<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
								<td><a href="edit?orderid=<c:out value='${order.orderid}'/>" class="btn"><i class="fa fa-pencil"></i></a></td>	
								<td><a href="delete?orderid=<c:out value='${order.orderid}'/>" class="btn"><i class="fa fa-close" ></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>
</html>