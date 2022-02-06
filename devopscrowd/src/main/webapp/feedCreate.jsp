<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navbar.jsp" %>

<meta charset="ISO-8859-1">
<title>Feedback</title>
<link href="css/feedback.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="FeedServlet" method="post">
	Comment:<br/> <input type="text" name="comment" class="comment">
	<br/><br/>Rating:<br/> <select name="rating">
		<option>Average</option>
		<option>Good</option>
		<option>Very Good</option>
		<option>Excellent</option>
	</select> 
	<br/> 
	<br/>  
	<input type="submit" value="Submit Feedback"/>
	
</form>



</body>

</body>
</html>