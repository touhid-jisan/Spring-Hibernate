<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yahoo from jsp</title>
</head>
<body>
<form action="/hello" method="post">
	${errorMessage}
	<br><br>
	Enter name <input type="text" name="user_name"/><br><br>
	Enter password <input type="password" name="user_password"/><br><br>
	<input type="submit"value="Login"/>
</form>

</body>
</html>