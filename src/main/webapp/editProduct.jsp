<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Management Application</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light">
<div>
<a class="navbar-brand"> Edit Product </a>
</div>
<ul class="navbar-nav">
<li><a href="<%=request.getContextPath()%>/ProductViewServlet/dashboard"
class="nav-link">Back to Dashboard</a></li>
</ul>
</nav>
<div class="container col-md-6">
<div class="card">
<div class="card-body">
<c:if test="${product != null}">
<form action="update" method="post">
</c:if>
<c:if test="${product == null}">
<form action="insert" method="post">
</c:if>
<caption>
<h2>
<c:if test="${product != null}">
Edit Product
</c:if>
<c:if test="${product == null}">
Add New Product
</c:if>
</h2>
</caption>
<c:if test="${product != null}">
<input type="hidden" name="oriProductId" value="<c:out value='${ProductView.productId}' />" />
</c:if>
<fieldset class="form-group">
<label>ProductId</label> <input type="text" value="<c:out value='${ProductView.productId}' />" class="form-control" name="productId" required="required">
</fieldset>
<fieldset class="form-group">
<label>Name</label> <input type="text" value="<c:out value='${ProductView.name}' />" class="form-control" name="name" required="required">
</fieldset>
<fieldset class="form-group">
<label>Price</label> <input type="text" value="<c:out value='${ProductView.price}' />" class="form-control" name="price">
</fieldset>
<fieldset class="form-group">
<label>Description</label> <input type="text" value="<c:out value='${ProductView.description}' />" class="form-control" name="description">
</fieldset>
<fieldset class="form-group">
<label>Image</label> <input type="text" value="<c:out value='${ProductView.image}' />" class="form-control" name="image">
</fieldset>
<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>
</body>
</html>