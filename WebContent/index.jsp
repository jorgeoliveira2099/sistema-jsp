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

<form action="LoginServlet" method="post">

	<label>Login:</label>
	<i class="material-icons person">person</i>
	<input type="text" id="login" name="login" placeholder="Login">
	<label>Senha:</label>
	<i class="material-icons https">https</i>
	<input type="text" id="senha" name="senha" placeholder="Senha">
	
	
	<a class="waves-effect waves-light btn"><input type="submit"  value="Entrar"/></a>
	
</form>


</div>

</body>
</html>