<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Spring Security tag library -->
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

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
	<div class="container">
		<div class="float-right" style="margin-right: 50px; margin-top: 20px">
			<form:form action="${pageContext.request.contextPath}/logout"
				method="POST">
				<span class="glyphicon glyphicon-log-out"></span>
				<input type="submit" class="btn btn-info btn-lg" Value="Logout">
			</form:form>
		</div>
		<hr>
		<!-- Display user name and role -->
		<h1>
			User:
			<security:authentication property="principal.username" />
			<br> Role:
			<security:authentication property="principal.authorities" />

		</h1>
		<hr>


		<p>

			<security:authorize access="hasRole('MANAGER')">
				<a href="${pageContext.request.contextPath}/leaders"
					class="btn btn-primary stretched-link">Leadership Meeting</a>
				<i class="text-danger">(Only for Manager peps)</i>
				<br>
				<br>
			</security:authorize>


			<security:authorize access="hasRole('ADMIN')">


				<a href="${pageContext.request.contextPath}/system"
					class="btn btn-primary stretched-link">IT System Meetinge</a>
				<i class="text-danger">(Only for Admin Peps)</i>
			</security:authorize>

		</p>
	</div>
</body>

</html>