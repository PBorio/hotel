<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reserva</title>
</head>
<body>
  <div class="reservas-box03">
        <h3>Informações da Reserva</h3>
		<fieldset>
		  <fieldset>
		  	  <legend>Quartos Selecionados</legend>
			  <c:forEach var="quarto" items="${reservasView.quartos}">
			  	<p>
					<input type="text" value="${quarto.numero}" readonly="readonly" />
				</p>
			  </c:forEach>
		  </fieldset>
		   <p class="half">
				<label>Início:</label>
				<input type="text" value="<fmt:formatDate value='${reservasView.chegada}'/>" readonly="readonly" />
		  </p>
		  <p class="half">
				<label>Fim:</label>
				<input type="text" value="<fmt:formatDate value='${reservasView.saida}'/>" readonly="readonly" />
		  </p>
		  <p class="half">
				<label>Adultos:</label>
				<input type="text" value="${reservasView.numeroAdultos}" readonly="readonly" />
		  </p>
		  <p class="half">
				<label>0 a 5 anos:</label>
				<input type="text" value="${reservasView.numeroCriancas0a5}" readonly="readonly" />
		  </p>
		  <p class="half">
				<label>6 a 16 anos:</label>
				<input type="text" value="${reservasView.numeroCriancas6a16}" readonly="readonly" />
		  </p>
		  <p class="half">
				<label>17 a 18 anos:</label>
				<input type="text" class="col-xs-10" value="${reservasView.numeroCriancas17a18}" readonly="readonly" />
		  </p>
		  <p>
				<label>Valor:</label>
				<input type="text" class="col-xs-10" value="${reservasView.valorReserva}" readonly="readonly" />
		  </p>
		  <fieldset>
		  <legend>Informações do Responsável</legend>
			  <form class="form-horizontal" action='<c:url value="/reservas/confirmar"/>' method="post">
		  	   <input type="hidden" name="hospede.id" value="${hospede.id}" />
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