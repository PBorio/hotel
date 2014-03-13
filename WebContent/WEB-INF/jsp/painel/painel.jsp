<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
   <title>Painel</title>        
   <script src=" <c:url value='/resources/scripts/jquery-1.7.1.min.js'/>"></script> 
		<script type="text/javascript">                                         
		   jQuery(document).ready(function() {
				jQuery('.correcao').hover(mouseOver, mouseOut);
				jQuery('.melhoria').hover(mouseOver, mouseOut);
				jQuery('.duvida').hover(mouseOver, mouseOut);
		   });                   
		
		   function mouseOver(){
			 jQuery(this).addClass('over');
		   }   
		   
		   function mouseOut(){
		     jQuery(this).removeClass('over');
		   }
 	</script> 
</head>
<body>
<div id="container1">

    <div id="colEntrada">
		<h4>Entrada</h4>
		<div class="correcao"> 
		  <span>105105</span></br>
		  <span>Chamado de Correção do IPTU de Guaratuba</span>
		</div>
		<div class="melhoria"> 
		  <span>105106</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="duvida"> 
		  <span>105107</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
	</div>
    <div id="colSuporte">
	  <h4>Suporte</h4>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="duvida"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
	</div>
    <div id="colPrioridade">
	   <h4>Priorização</h4>
	   <div class="correcao"> 
		  <span>105111</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="correcao"> 
		  <span>105112</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="melhoria"> 
		  <span>105113</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
	</div>
	<div id="colDesenv">
	  <h4>Desenvolvimento</h4>
	  <div class="melhoria"> 
		  <span>105111</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="correcao"> 
		  <span>105112</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="melhoria"> 
		  <span>105113</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="duvida"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="melhoria"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
	</div>
	<div id="colTeste">
	  <h4>Testes</h4>
	    <div class="melhoria"> 
		  <span>105111</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="correcao"> 
		  <span>105112</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="melhoria"> 
		  <span>105113</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105111</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="correcao"> 
		  <span>105112</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="melhoria"> 
		  <span>105113</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105111</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="correcao"> 
		  <span>105112</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="melhoria"> 
		  <span>105113</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
	</div>
	<div id="colVersao">
	  <h4>Aguardando Versão</h4>
	   <div class="melhoria"> 
		  <span>105111</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="correcao"> 
		  <span>105112</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="melhoria"> 
		  <span>105113</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105108</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="melhoria"> 
		  <span>105109</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="correcao"> 
		  <span>105110</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>
		<div class="melhoria"> 
		  <span>105111</span></br>
		  <span>bla bla bla bla bla bla</span>
		</div>
		<div class="correcao"> 
		  <span>105112</span></br>
		  <span>Novo formato de Relatorio de Empresas</span>
		</div>
		<div class="melhoria"> 
		  <span>105113</span></br>
		  <span>Usuário não consegue realizar o fechamento mensal</span>
		</div>  
	</div>
</div>
 
</body>
</html>