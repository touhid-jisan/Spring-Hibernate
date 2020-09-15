<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Spring Security tag library -->
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<script
	src="//maxcdn.bootstrapcdn.com/boots trap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>touhid-jisan Company Home Page</title>

</head>

<body>
<hr>
	<!-- Display user name and role -->
	<h1>
		User: <security:authentication property="principal.username"/>
		Role: <security:authentication property="principal.authorities"/>
	
	</h1>
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST"> <span
			class="glyphicon glyphicon-log-out"></span> 
		<input type="submit" class="btn btn-info btn-lg" Value="Logout"> 
	</form:form>
	
</body>

</html>