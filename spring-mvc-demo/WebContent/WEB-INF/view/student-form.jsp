<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration Form</title>
</head>
<body>
	<form:form action="processForm" modelAttribute="student" method="post">
		First Name : <form:input path="firstName"/> <br><br>
		Last Name : <form:input path="lastName"/> <br><br>
		Country : 
		<form:select path="country">
			 
<%-- 		<form:option value="Brazil" label="Brazil"></form:option> --%>
<%-- 		<form:option value="France" label="France"></form:option> --%>
<%-- 		<form:option value="Germany" label="Germany"></form:option> --%>
<%-- 		<form:option value="Bangladesh" label="Bangladesh"></form:option> --%>
			<form:options items="${student.countryOptions}"/>	
			
		</form:select>
		<br><br>
		Favorite Programming Language : 
		Java <form:radiobutton path="favoriteLanguage" value="Java"/>
		C# <form:radiobutton path="favoriteLanguage" value="C#"/>
		Python <form:radiobutton path="favoriteLanguage" value="Python"/>
		Ruby <form:radiobutton path="favoriteLanguage" value="Ruby"/>
		
		<br><br>		
		Operating Systems : 
		Linux <form:checkbox path="operatingSystem" value="Linux"/>
		Mac OS <form:checkbox path="operatingSystem" value="Mac OS"/>
		Windows <form:checkbox path="operatingSystem" value="Windows"/>
		<br><br>	
		<input type="submit">
	</form:form>
</body>
</html>