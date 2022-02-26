<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navbar.jsp" %>
<meta charset="ISO-8859-1">
<title>Products</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
crossorigin="anonymous">
<style>
.item1 { grid-area: name; }
.item2 { grid-area: image; }
.item3 { grid-area: description; }
.item4 { grid-area: price; }
.item5 { grid-area: feedback; }
.item6 { grid-area: purchase; }

.grid-container {
  display: grid;
  grid-template-areas:
    'name name name name name name'
    'image image image description description description'
    'image image image feedback feedback feedback'
    'image image image price price purchase';
  grid-gap: 10px;
  padding: 10px;
}

.grid-container > div {
  background-color: rgba(255, 255, 255, 0.8);
  text-align: center;
  padding: 20px 0;
  font-size: 30px;
}
</style>
</head>
<body>
<div class="grid-container">
  <div class="item1"><c:out value="${product.name}" /></div>
  <div class="item2"><c:out value="${product.image}" /></div>
  <div class="item3"><c:out value="${product.description}" /></div>  
  <div class="item4"><c:out value="${product.price}" /></div>
  <div class="item5">Feedback</div>
  <div class="item6">purchase</div>
</div>

</body>
</html>