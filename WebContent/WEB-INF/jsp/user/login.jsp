<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<h1>login</h1>
	<hr>
	<form:form action="login" method="post" commandName="userForm">
		<font color="red"><form:errors path="name"/></font>
		<font color="red"><form:errors path="password"/></font>
		<font color="red"><form:errors path="verifyCode"/></font>
		<spring:message code="user.name"/>:<form:input path="name"/><br>
		<spring:message code="user.password"/>:<form:password path="password"/><br>
		<spring:message code="user.verifyCode"/>:<form:input path="verifyCode"/>
		<img alt='<spring:message code="user.wait"/>' src="verifyCode"><br>
		<spring:message code="user.rememberMe"/><form:checkbox path="rememberMe"/><br>
		<input type="submit" value="<spring:message code='user.login'/>">
	</form:form>
</body>
</html>