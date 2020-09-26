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
<br>
	<div class="form-group">
	<!-- <form action="salvarUsuario?acao=exportar"> -->
		<form action="#">
			<button type="submit" name="accion" value="ExportarPdf" class="btn btn-small"><i class="material-icons file_download">file_download</i>Gerar PDF</button>
		</form>
	</div>
	
<h1>Novo Funcionario</h1>

<h3>${msg}</h3>

<form action="salvarUsuario" method="post" id="formUser">

			C�digo:			
			<input type="text" readonly="readonly" id="id" name="id" value="${user.id}" />
			
			Nome:		
			<input type="text" id="nome" name="nome" value="${user.nome}" />

			Login:			
			<input type="text" id="login" name="login" value="${user.login}" />
		
			Senha:		
			<input type="password" id="senha" name="senha" value="${user.senha}" />
			
			Telefone:		
			<input type="text" id="telefone" name="telefone" value="${user.telefone}" />
				
			
		
		<a class="waves-effect waves-light btn"><input type="submit" value="Salvar" /></a>
		<a class="waves-effect waves-light btn"><input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'" /></a>
		
</form>
	
	
	<table>
        <thead>
          <tr>
         	 <th>id</th>
         	 <th>Nome</th>
         	  <th>Telefone</th>
              <th>Login</th>              
              <th>A��o</th>               
          </tr>
        </thead>

        <tbody>
        
        <c:forEach items="${usuarios}" var="user">
          <tr>
           	<td><c:out value="${user.id}"></c:out> </td>
           	<td><c:out value="${user.nome}"></c:out> </td>
           	<td><c:out value="${user.telefone}"></c:out> </td>
           	<td><c:out value="${user.login}"></c:out> </td>					
			<td><a href="salvarUsuario?acao=delete&user=${user.id}"><i class="material-icons delete">delete</i></a>
			<a href="salvarUsuario?acao=editar&user=${user.id}"><i class="material-icons edit">edit</i></a>
			</td>
          
          </tr>
          </c:forEach>
          
          <tr>         
        </tbody>
      </table>

</div>
</body>
</html>