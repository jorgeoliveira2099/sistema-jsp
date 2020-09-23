<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Funcionario</title>
</head>
<body>
<%@ include file="header.jsp"  %>
<div class="container">
<h1>Novo Funcionario</h1>

<form action="salvarUsuario" method="post">

			Login:			
			<input type="text" id="login" name="login" />
		
			Senha:		
			<input type="password" id="senha" name="senha" />
		
		<input type="submit" value="Cadastrar" />
</form>

</div>
</body>
</html>