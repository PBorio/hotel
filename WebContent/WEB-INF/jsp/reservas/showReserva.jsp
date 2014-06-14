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
  <div class="container">
			<div class="header">
				<ul class="nav nav-pills pull-right">
					<li class="active"><a href="/">Limpar</a></li>
				</ul>
			</div>
		<fieldset>
	  	  <legend>Responsável pela Reserva</legend>
		  <c:forEach var="quarto" items="${reservasView.quartos}">
			<div class="form-group">
				<label class="control-label col-xs-2">Quarto:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${quarto.numero}" readonly="readonly" />
				</div>
			</div>
		  </c:forEach>
		  <div class="form-group">
				<label class="control-label col-xs-2">Adultos:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reservasView.numeroAdultos}" readonly="readonly" />
				</div>
		  </div>
		  <div class="form-group">
				<label class="control-label col-xs-2">0 a 5 anos:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reservasView.numeroCriancas0a5}" readonly="readonly" />
				</div>
		 </div>
		 <div class="form-group">
				<label class="control-label col-xs-2">6 a 16 anos:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reservasView.numeroCriancas6a16}" readonly="readonly" />
				</div>
		 </div>
		  <div class="form-group">
				<label class="control-label col-xs-2">17 a 18 anos:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reservasView.numeroCriancas17a18}" readonly="readonly" />
				</div>
		 </div>
		 <div class="form-group">
				<label class="control-label col-xs-2">Início:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="<fmt:formatDate value='${reservasView.chegada}'/>" readonly="readonly" />
				</div>
		 </div>
		 <div class="form-group">
				<label class="control-label col-xs-2">Fim:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="<fmt:formatDate value='${reservasView.saida}'/>" readonly="readonly" />
				</div>
		 </div>
		 <div class="form-group">
				<label class="control-label col-xs-2">Valor:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reservasView.valorReserva}" readonly="readonly" />
				</div>
		 </div>
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
	</div>
</body>
</html>