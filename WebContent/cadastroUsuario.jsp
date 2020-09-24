<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

			Código:			
			<input type="text" readonly="readonly" id="id" name="id" value="${user.id}" />

			Login:			
			<input type="text" id="login" name="login" value="${user.login}" />
		
			Senha:		
			<input type="password" id="senha" name="senha" value="${user.senha}" />
		
		<input type="submit" value="Cadastrar" />
</form>
	
	
	<table>
        <thead>
          <tr>
          <th>id</th>
              <th>Login</th>
              <th>Senha</th>
              <th>Ação</th>               
          </tr>
        </thead>

        <tbody>
        
        <c:forEach items="${usuarios}" var="user">
          <tr>
           <td><c:out value="${user.id}"></c:out> </td>
           <td><c:out value="${user.login}"></c:out> </td>
			<td><c:out value="${user.senha}"></c:out> </td>
			
			<td><a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a></td>
			<td><a href="salvarUsuario?acao=editar&user=${user.login}">Editar</a></td>
          </tr>
          </c:forEach>
          
          <tr>         
        </tbody>
      </table>

</div>
</body>
</html>