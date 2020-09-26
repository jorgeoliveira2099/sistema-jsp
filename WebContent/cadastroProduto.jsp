<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Produto</title>
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
	<h3>${msg}</h3>
<h1>Novo Produto</h1>


<form action="salvarProduto" method="post" id="formProduto">

			Código:			
			<input type="text" readonly="readonly" id="id" name="id" value="${produto.id}" />
			
			Nome:		
			<input type="text" id="nome" name="nome" value="${produto.nome}" />

			Valor:			
			<input type="text" id="valor" name="valor" value="${produto.valor}" />
	
			
			Quantidade:		
			<input type="text" id="quantidade" name="quantidade" value="${produto.quantidade}" />
				
			
		
		<a class="waves-effect waves-light btn"><input type="submit" value="Salvar" /></a>
		<a class="waves-effect waves-light btn"><input type="submit" value="Cancelar" onclick="document.getElementById('formProduto').action='salvarProduto?acao=reset'" /></a>
		
</form>
	
	
	<table>
        <thead>
          <tr>
         	 <th>id</th>
         	 <th>Nome</th>
         	  <th>Valor</th>
              <th>Quantidade</th>              
                          
          </tr>
        </thead>

        <tbody>
        
        <c:forEach items="${produtos}" var="produto">
          <tr>
           	<td><c:out value="${produto.id}"></c:out> </td>
           	<td><c:out value="${produto.nome}"></c:out> </td>
           	<td><c:out value="${produto.valor}"></c:out> </td>
           	<td><c:out value="${produto.quantidade}"></c:out> </td>					
			<td><a href="salvarProduto?acao=delete&produto=${produto.id}"><i class="material-icons delete">delete</i></a>
			<a href="salvarProduto?acao=editar&produto=${produto.id}"><i class="material-icons edit">edit</i></a>
			</td>
          
          </tr>
          </c:forEach>
          
          <tr>         
        </tbody>
      </table>

</div>
</body>
</html>