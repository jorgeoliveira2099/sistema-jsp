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
        <a href="#" class="breadcrumb">Cadastrar Usuario</a>       
      </div>
    </div>
  </nav>
 
<br>
	<div class="form-group">
	<!-- <form action="salvarUsuario?acao=exportar"> -->
		<form action="#">
			<button type="submit" name="accion" value="ExportarPdf" class="btn btn-small"><i class="material-icons file_download">file_download</i>Gerar PDF</button>
		</form>
	</div>
	
<h1>Novo Funcionario</h1>

<h3>${msg}</h3>

<form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;" enctype="multipart/form-data" >
<!-- enctype="multipart/form-data" -->
			Código:			
			<input type="text" readonly="readonly" id="id" name="id" value="${user.id}" />
			
			Nome:		
			<input type="text" id="nome" name="nome" value="${user.nome}" />

			Login:			
			<input type="text" id="login" name="login" value="${user.login}" />
		
			Senha:		
			<input type="password" id="senha" name="senha" value="${user.senha}" />
			
			Telefone:		
			<input type="text" id="telefone" name="telefone" value="${user.telefone}" />
				
			Cep:		
			<input type="text" id="cep" name="cep" onblur="consultaCep()" value="${user.cep}" />			
      
      		 Rua:
       		 <input name="rua" type="text" id="rua" value="${user.rua}" />
        
       		Bairro:
       		 <input name="bairro" type="text" id="bairro" value="${user.bairro}" />
        
       		 Cidade:
       		 <input name="cidade" type="text" id="cidade" value="${user.cidade}" />
        
       		 Estado:
       		 <input name="estado" type="text" id="estado" value="${user.estado}" />
       		 
       		 Foto:
       		 <input name="foto" type="file" id="foto" />
       		 
       		  Curriculo:
       		 <input name="curriculo" type="file" id="curriculo" />
        <br> <br>
     
		
		<a class="waves-effect waves-light btn"><input type="submit" value="Salvar" /></a>
		<a class="waves-effect waves-light btn"><input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'" /></a>
		
</form>
	
	
	<table>
        <thead>
          <tr>
         	 <th>id</th>         	 
         	 <th>Foto</th>         	 
         	 <th>Curriculo</th>
         	 <th>Nome</th>         	
             <th>Delete</th>
             <th>Update</th> 
             <th>Telefones</th>       
          </tr>
        </thead>

        <tbody>
        
        <c:forEach items="${usuarios}" var="user">
          <tr>
           	<td><c:out value="${user.id}"></c:out> </td>           	
           	<td><a href="salvarUsuario?acao=download&tipo=image&user=${user.id}"><img src='<c:out value="${user.tempFotoUser}"></c:out>' width="32px" height="32px" /></a></td>
           	<td><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"> <i class="medium material-icons picture_as_pdf">picture_as_pdf</i></a></td>
           	<td><c:out value="${user.nome}"></c:out> </td>            			
			<td><a href="salvarUsuario?acao=delete&user=${user.id}"><i class="material-icons delete">delete</i></a>	</td>
					
			<td><a href="salvarUsuario?acao=editar&user=${user.id}"><i class="material-icons edit">edit</i></a>	</td>
					
			<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><i class="material-icons contact_phone">contact_phone</i></a>
			</td>
			
          
          </tr>
          </c:forEach>
          
          <tr>         
        </tbody>
      </table>

</div>

<script type="text/javascript">
	function validarCampos(){
		if(document.getElementById("nome").value == '' ){
			alert('Informe o nome!');
			return false;
		}
		else if(document.getElementById("login").value == '' ){
			alert('Informe o login!');
			return false;
		}
		else if(document.getElementById("senha").value == '' ){
			alert('Informe a senha!');
			return false;
		}
		else if(document.getElementById("telefone").value == '' ){
			alert('Informe o telefone!');
			return false;
		}
		
		return true;
	}
	
	function consultaCep(){
		var cep = $("#cep").val();
	
		
	    
        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

        	
            if (!("erro" in dados)) {
               
                 $("#rua").val(dados.logradouro);
                 $("#bairro").val(dados.bairro);
                 $("#cidade").val(dados.localidade);
                 $("#estado").val(dados.uf);
               
            }
            else {
                
                limpa_formulário_cep();
                alert("CEP não encontrado.");
            }
        });	
	}
</script>
</body>
</html>