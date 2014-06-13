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
  <c:forEach var="quarto" items="${reservasView.quartos}">
	<div class="form-group">
		<label class="control-label col-xs-2">Quarto:</label>
		<div class="col-xs-10">
			<input type="text" class="col-xs-10" value="${quarto.numero}" readonly="readonly" />
		</div>
	</div>
  </c:forEach>
  <h3>Número de Adultos:&nbsp;${reservasView.numeroAdultos}</h3>
  <h3>Número de Crianças de 0 a 5 anos:&nbsp;${reservasView.numeroCriancas0a5}</h3>
  <h3>Número de Crianças de 6 a 16 anos:&nbsp;${reservasView.numeroCriancas6a16}</h3>
  <h3>Número de Crianças de 17 e 18 anos:&nbsp;${reservasView.numeroCriacas17a18}</h3>
  <h3>Início:&nbsp; <fmt:formatDate value='${reservasView.chegada}'/></h3>
  <h3>Fim:&nbsp; <fmt:formatDate value='${reservasView.saida}'/></h3> 
  <h3>Valor:&nbsp; ${reservasView.valorReserva}</h3>
  <div class="nav-bar">
  	<div class="navbar-header">
		<button type="button" class="navbar-toggle"
				data-toggle="collapse" data-target=".navbar-collapse">
		</button>
		<a class="navbar-brand" href="<c:url value='/reservas/reserva'/>">Procurar Mais Quartos</a>
	</div>
	<div class="navbar-header">
		<button type="button" class="navbar-toggle"
				data-toggle="collapse" data-target=".navbar-collapse">
		</button>
		<a class="navbar-brand" href="<c:url value='/reservas/hospedeReserva'/>">Continuar</a>
	</div>
  </div>
</body>
</html>