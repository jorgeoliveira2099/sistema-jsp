
<html>
<head>
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

 
 <nav>
     <div class="nav-wrapper teal darken-3">
         <a href="acessoliberado.jsp" class="brand-logo">SistJSP</a>
         <a href="#" data-activates="menu-mobile" class="button-collapse">
             <i class="material-icons">menu</i>
         </a>
         <ul class="right hide-on-med-and-down">
              <li><a href="cadastroUsuario.jsp"> <i class="material-icons person_add">person_add</i></a></li>
       		<li><a href="#"> <i class="material-icons person_pin">person_pin</i></a></li>
       		 <li><a href="index.jsp"> <i class="material-icons power_settings_new">power_settings_new</i></a></li>
         </ul>
         <ul class="side-nav" id="menu-mobile">
             <li><a href="cadastroUsuario.jsp"> <i class="material-icons person_add">person_add</i>Adicionar Funcionário</a></li>
       		<li><a href="#"> <i class="material-icons person_pin">person_pin</i>Perfil</a></li>
       		 <li><a href="index.jsp"> <i class="material-icons power_settings_new">power_settings_new</i>Sair</a></li>
          </ul>
     </div>
 </nav>  
  
 
 
 <script>
 $(function(){
    $(".button-collapse").sideNav();
});
 </script>
</body>
</html>