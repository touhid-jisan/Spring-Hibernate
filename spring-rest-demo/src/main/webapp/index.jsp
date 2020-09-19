<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REST Service</title>
</head>
<body>

Default Page: index.jsp

<a href="${pageContext.request.contextPath}/test/hello">Hello</a> 

<a href="${pageContext.request.contextPath}/api/students">Get All Students</a> 
</body>
</html>