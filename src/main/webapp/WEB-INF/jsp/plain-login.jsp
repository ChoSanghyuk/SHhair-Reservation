<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Custom Login Page</title>
	<style>
		.failed{
			color: red;
		}
	</style>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>

<body>
<div class="d-flex flex-column justify-content-center align-items-center" style="height:50%">
<h3>My Custom Login Page</h3>
	<form:form action ="${pageContext.request.contextPath}/authenticateUser" method="POST">
	
		<!-- Check for login error -->
		<c:if test="${param.error != null }">
			<i class="failed">Invalid username/password</i>
		</c:if>
		<!-- Check for logout -->
		<c:if test="${param.logout != null }">
			<i class="failed">You have been logged out</i>
		</c:if>
		
		<p>
			User name: <input type="text" name="username" />
		</p>
		
		<p>
			Password: <input type="password" name="password" />
		</p>
		<input type="submit" value="Login" />
	</form:form>
</div>
</body>
</html>