<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página Inicial</title>
</head>
<body>
<%@ include file="header.jsp"  %>
<div class="container">
<h1>Bem vindo</h1>

<a href="salvarUsuario?acao=listartodos"><i class="small material-icons person_add">person_add</i>Cadastrar Funcionario</a>

<a href="salvarProduto?acao=listartodos"><i class="material-icons person_add">person_add</i>Cadastrar Produto</a>

</div>
</body>
</html>