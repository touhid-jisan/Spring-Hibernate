<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>


<%--  <link type="text/css" 
 		rel="styleshet"
 		href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
  <link type="text/css" 
 		rel="styleshet"
 		href="${pageContext.request.contextPath}/js/bootstrap.min.js"/> --%>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="customer" items="${customers}">

					<c:url var="updateLink" value="showFormForUpdate">
						<c:param name="customerId" value="${customer.id }" />
					</c:url>

					<c:url var="deleteLink" value="deleteCustomer">
						<c:param name="customerId" value="${customer.id }" />
					</c:url>

					<tr>
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.email}</td>
						<td><a href="${updateLink}" class="btn btn-primary mr-1">Update</a>
							| <a href="${deleteLink }" class="btn btn-danger" onclick="if(!(confirm('Confirm to delete the user?'))) return false">Delete</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="new-customer" class="btn btn-success">Add New Customer</a>
	</div>



	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>




</body>
</html>