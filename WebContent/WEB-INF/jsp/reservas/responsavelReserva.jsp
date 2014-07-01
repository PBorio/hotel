<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Políticas de Preços</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/datepicker.css'/>" />
</head>
<body>
  <form class="form-horizontal" action='<c:url value="/reservas/confirmar"/>' method="post">
  	   <input type="hidden" name="hospede.id" value="${hospede.id}" />
	  <div class="reservas-box04">
        <h3>Informações da Reserva</h3>
        <fieldset>
	        <p>
	        	<input id="nome" placeholder="Nome" type="text" name="hospede.nome" value="${hospede.nome}" />
	        </p>
	        <p>
	        	<input id="nome" type="text" placeholder="Sobrenome" name="hospede.sobrenome" value="${hospede.sobrenome}" />
	        </p>
	         <p class="half">
	        	<input id="email" type="text" placeholder="Email" name="hospede.email" value="${hospede.email}" />
	        </p>
	        <p class="half">
	        	<input id="cidade" type="text" placeholder="Cidade" name="hospede.cidade" value="${hospede.cidade}" />
	        </p>
	        <p class="half">
	        	<input id="telefone" type="text" placeholder="Telefone" name="hospede.telefone" value="${hospede.telefone}" />
	        </p>
	        <p class="half">
	        	<input id="celular" type="text" placeholder="Celular" name="hospede.celular" value="${hospede.celular}" />
	        </p>
	        <p>
	        	<input type="submit" name="Salvar" value="Salvar"/>
	        </p>
        </fieldset>
        <div class="clear"></div>
      </div>
		  
	 </form>
</body>
</html>