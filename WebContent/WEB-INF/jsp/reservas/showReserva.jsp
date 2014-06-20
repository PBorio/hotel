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
		  <c:forEach var="quarto" items="${reservasView.quartos}">
		  	<p>
		  		<label>Quarto:</label>
				<input type="text" value="${quarto.numero}" readonly="readonly" />
			</p>
		  </c:forEach>
		  <p>
				<label>Adultos:</label>
				<input type="text" value="${reservasView.numeroAdultos}" readonly="readonly" />
		  </p>
		  <p>
				<label>0 a 5 anos:</label>
				<input type="text" value="${reservasView.numeroCriancas0a5}" readonly="readonly" />
		  </p>
		  <p>
				<label>6 a 16 anos:</label>
				<input type="text" value="${reservasView.numeroCriancas6a16}" readonly="readonly" />
		  </p>
		  <p>
				<label>17 a 18 anos:</label>
				<input type="text" class="col-xs-10" value="${reservasView.numeroCriancas17a18}" readonly="readonly" />
		  </p>
		  <p>
				<label>Início:</label>
				<input type="text" value="<fmt:formatDate value='${reservasView.chegada}'/>" readonly="readonly" />
		  </p>
		  <p>
				<label>Fim:</label>
				<input type="text" value="<fmt:formatDate value='${reservasView.saida}'/>" readonly="readonly" />
		  </p>
		  <p>
				<label>Valor:</label>
				<input type="text" class="col-xs-10" value="${reservasView.valorReserva}" readonly="readonly" />
		  </p>
		  <div class="nav-bar">
			<div class="header">
				<ul class="nav nav-pills pull-right">
					<li class="active"><a href="<c:url value='/reservas/responsavelReserva'/>">Continuar</a></li>
				</ul>
				<ul class="nav nav-pills pull-right">
					<li class="active"><a href="<c:url value='/reservas/reserva'/>">Procurar Mais Quartos</a></li>
				</ul>
			</div>
		  </div>
	  </fieldset>
	  <div class="clear"></div>
	</div>
</body>
</html>