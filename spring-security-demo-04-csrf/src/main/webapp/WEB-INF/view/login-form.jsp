<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>touhid-jisan Company Home Page</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<link href="//githubpictures.000webhostapp.com/pictures/style-login.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/boots trap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>

<body>
	<div class="wrapper">
		<div id="formContent">
			<!-- Tabs Titles -->

			<form
				action="${pageContext.request.contextPath }/authenticateTheUser"
				method="POST">


				<c:if test="${ param.error != null}">
					<div class="alert alert-danger" role="alert">Sorry! you
						entered wrong username or password</div>
				</c:if>
				<c:if test="${param.logout != null}">

					<div class="alert alert-success col-xs-offset-1 col-xs-10">
						You have been logged out.</div>

				</c:if>

				<input type="text" id="login" class="fadeIn second" name="username" placeholder="login"> 
				<input type="text" id="password"
					class="fadeIn third" name="password" placeholder="password">
				<input type="submit" class="fadeIn fourth" value="Log In"> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

			</form>
			<div id="formFooter">
				<a class="underlineHover" href="#">Forgot Password?</a>
			</div>
		</div>
	</div>
</body>

</html>