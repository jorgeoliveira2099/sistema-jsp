<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Funcionario</title>

 <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

 <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- Compiled and minified JavaScript -->
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
 <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script>

</head>
<body>


 <div class="container">
 <br>

 
<br>

<h1>Criar login</h1>

<h3>${msg}</h3>

<form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false;">

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
        
     
		
		<a class="waves-effect waves-light btn"><input type="submit" value="Salvar" /></a>
		<a class="waves-effect waves-light btn"><input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'" /></a>
		<a href="index.jsp" class="waves-effect waves-light btn"><input type="submit" value="Voltar" /></a>
</form>
	</div>
<br>
<script type="text/javascript">
	function validarCampos(){
		if(document.getElementById("nome").value == '' ){
			
			return false;
		}
		else if(document.getElementById("login").value == '' ){
			
			return false;
		}
		else if(document.getElementById("senha").value == '' ){
			
			return false;
		}
		else if(document.getElementById("telefone").value == '' ){
			
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