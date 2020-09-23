<jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp"  %>
<div class="container">
<h1> chegou do outro lado</h1>

Nome: ${param.nome}
<br/>
Ano: ${param.ano}
<br/>
${sessionScope.user}

<br/>

</div>
</body>
</html>