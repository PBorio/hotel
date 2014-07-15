<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reserva</title>
<script type="text/javascript" src="<c:url value='/resources/scripts/jquery.maskedinput-1.3.min.js'/>"></script>
  <script type="text/javascript">

$(function() {
	$("#cpf").mask("999.999.999-99");
});

	
</script>
</head>
<body>
  <div class="reservas-box03">
        <h3>Informações da Reserva</h3>
		<fieldset>
			  <c:forEach var="reserva" items="${reservasView.reservas}">
			   <fieldset class="molduraLegenda">
		  	   <legend>${reserva.quarto.numero}</legend>
				 <p class="half">
						<label>Início:</label>
						<input type="text" class="col-xs-10" value="<joda:format pattern='dd/MM/yyyy' value='${reserva.inicio}'/>" readonly="readonly" />
				  </p>
				  <p class="half">
						<label>Fim:</label>
						<input type="text" class="col-xs-10" value="<joda:format pattern='dd/MM/yyyy' value='${reserva.fim}'/>" readonly="readonly" />
				  </p>
				  <p class="half">
						<label>Adultos:</label>
						<input type="text" value="${reserva.numeroAdultos}" readonly="readonly" />
				  </p>
				  <p class="half">
						<label>0 a 5 anos:</label>
						<input type="text" value="${reserva.numeroCriancas0a5}" readonly="readonly" />
				  </p>
				  <p class="half">
						<label>6 a 16 anos:</label>
						<input type="text" value="${reserva.numeroCriancas6a16}" readonly="readonly" />
				  </p>
				  <p class="half">
						<label>17 a 18 anos:</label>
						<input type="text" class="col-xs-10" value="${reserva.numeroCriancas17a18}" readonly="readonly" />
				  </p>
				  <p>
						<label>Valor:</label>
						<input type="text" class="col-xs-10" value="${reserva.valorReserva}" readonly="readonly" />
				  </p>
				 </fieldset>
			  </c:forEach>
		  <fieldset class="molduraLegenda">
		  <legend>Informações do Responsável</legend>
			  <form class="form-horizontal" action='<c:url value="/reservas/confirmar"/>' method="post">
		  	   <input type="hidden" name="hospede.id" value="${hospede.id}" />
			        <p>
			        	<input id="nome" placeholder="Nome" type="text" name="hospede.nome" value="${hospede.nome}" />
			        </p>
			        <p>
			        	<input id="nome" type="text" placeholder="Sobrenome" name="hospede.sobrenome" value="${hospede.sobrenome}" />
			        </p>
			        <p>
			        	<input id="cpf" type="text" placeholder="CPF" name="hospede.cpf" value="${hospede.cpf}" />
			        </p>
			        <p>
			        	<input id="passaporte" type="text" placeholder="Passaporte" name="hospede.passaporte" value="${hospede.passaporte}" />
			        </p>
			        <p>
			        	<input id="rne" type="text" placeholder="RNE" name="hospede.rne" value="${hospede.rne}" />
			        </p>
			          <p class="half">
			        	<input id="email" type="text" placeholder="Email" name="hospede.email" value="${hospede.email}" />
			        </p>
			        <p class="half">
			        	<input id="cidade" type="text" placeholder="Cidade" name="hospede.cidade" value="${hospede.cidade}" />
			        </p>
			         <p class="half">
			        	<input id="estado" type="text" placeholder="Estado/Província/Departamento" name="hospede.estado" value="${hospede.estado}" />
			        </p>
			         <p class="half">
			        	<input id="pais" type="text" placeholder="País" name="hospede.pais" value="${hospede.pais}" />
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
		        <div class="clear"></div>
			 </form>
		 </fieldset>
	 </fieldset>
	  <div class="clear"></div>
	</div>
	<div class="nav-bar">
		<c:forEach var="error" items="${errors}">
		<div class="alert">
			${error.message}
		</div>
	</c:forEach>
    </div>
	
</body>
</html>