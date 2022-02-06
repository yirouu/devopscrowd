<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="navbar.jsp" %>
<meta charset="ISO-8859-1">
<title>Products</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<form action="ProductViewServlet" method="post">
    ProductId: <input type="text" name="productId">
    Name: <input type="text" name="name">
    Price: <input type="text" name="price">
    description: <input type="text" name="description">
    Image: <input type="image" name="image">
    <input type="submit" value="Create" />
</form>

</body>
</html>