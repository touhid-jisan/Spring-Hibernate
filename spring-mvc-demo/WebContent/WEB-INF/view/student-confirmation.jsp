<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Confirmation</title>
</head>
	<body>
		The Student is confirmed : ${student.firstName} ${student.lastName} <br>
		Country : ${student.country}
		Favorite Language: ${student.favoriteLanguage}
		Operating System : 
		<ul>
			<c:forEach var="temp" items="${student.operatingSystem}"> <li>${temp}</li> </c:forEach>
		</ul>
	</body>
</html>