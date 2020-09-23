<jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page" />



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp"  %>
<div class="container">
<h1> Login</h1>

<c:out value="${'bem vindo'}"></c:out>

<form action="LoginServlet" method="post">

	<input type="text" id="login" name="login">
	
	<input type="text" id="senha" name="senha">
	
	<input type="submit"  value="Entrar"/>
</form>


</div>

</body>
</html>