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
 <nav>
    <div class="nav-wrapper teal darken-3">
      <div class="col s12">
        <a href="acessoliberado.jsp" class="breadcrumb">Inicío</a>
        <a href="#" class="breadcrumb">Cadastrar Telefone</a>       
      </div>
    </div>
  </nav>
 
<br>

	
<h1>Novo Telefone</h1>

<h3>${msg}</h3>

<form action="salvarTelefones" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;">

			User:
			<input type="text" id="user" name="user" value="${usua.id}">
     		Nome:
			<input type="text" id="nome" name="nome" value="${usua.nome}">
			
			Número:
			<input type="text" id="numero" name="numero">
			
			
			
			Tipo:
		  <div class="input-field col s12">
    <select id="tipo" name="tipo">
      <option value="" disabled selected>Choose your option</option>
      <option value="Casa">Casa</option>
      <option value="Celular">Celular</option>
      <option value="Comercial">Comercial</option>
    </select>
    <label>Materialize Select</label>
  </div>
			
		
		<a class="waves-effect waves-light btn"><input type="submit" value="Salvar" /></a>
	
		
</form>
	
	
	<table>
        <thead>
          <tr>
         	 <th>id</th>
         	 <th>Número</th>
         	 <th>Tipo</th>
         	 <th>Excluir</th>                         
          </tr>
        </thead>

        <tbody>
        
        <c:forEach items="${telefones}" var="fone">
          <tr>
           	<td><c:out value="${fone.id}"></c:out> </td>
           	<td><c:out value="${fone.numero}"></c:out> </td>
           	<td><c:out value="${fone.tipo}"></c:out> </td>
           
          				
			<td><a href="salvarTelefone?acao=delete&user=${fone.id}"><i class="material-icons delete">delete</i></a>
			
					</td>
					
					
          
          </tr>
          </c:forEach>
          
          <tr>         
        </tbody>
      </table>

</div>

<script type="text/javascript">
	function validarCampos(){
		if(document.getElementById("numero").value == '' ){
			alert('Informe o numero!');
			return false;
		}
		
		
		return true;
	}
	
	

		  // Or with jQuery

		 $(document).ready(function() {
    $('select').material_select();
});
	
	
</script>
</body>
</html>