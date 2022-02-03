<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="FeedbackServlet" method="post">
	Comment: <input type="text" name="comment">
	Rating: <select name="rating">
		<option>Average</option>
		<option>Good</option>
		<option>Very Good</option>
		<option>Excellent</option>
	</select>
	<input type="submit" value="Call Servlet"/>
	
</form>

</body>

</body>
</html>