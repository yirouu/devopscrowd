<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Management Application</title>
 <link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">

<nav class="navbar navbar-expand-md navbar-light">
<div>
<a class="navbar-brand"> Feedback </a> 
</div>
<ul class="navbar-nav">
<li><a href="<%=request.getContextPath()%>/FeedbackServlet/dashboard"
class="nav-link">Back to Dashboard</a></li>
</ul>
</nav>
<div class="container col-md-6">
<div class="card">
<div class="card-body">
<c:if test="${feedbacks != null}">
<form action="update" method="post">
</c:if>
<c:if test="${feedbacks == null}">
<form action="insert" method="post">
</c:if>
<caption>
<h2>
<c:if test="${feedbacks != null}">
Edit Comment
</c:if>
<c:if test="${feedbacks == null}">
Add New Comment
</c:if>
</h2>
</caption>

<c:if test="${feedbacks != null}">
                    <input type="hidden" name="orifeedbackId" value="<c:out value= '${feedbacks.feedbackId}'/>" >
                </c:if>
<fieldset class="form-group">
<label>Comment:</label> <input type="text" value="<c:out
value='${feedbacks.comment}' />" class="form-control" name="comment" required="required">
</fieldset>
</br>
<fieldset class="form-group">
<label> Rating:</label> <input type="text" value="<c:out
value='${feedbacks.rating}' />" class="form-control" name="rating" required="required">
</fieldset>


<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>

</html>
