<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
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
  <h3>Hóspede:&nbsp; ${reservasView.nomeHospede}</h3>
  <h3>Email:&nbsp; ${reservasView.emailHospede}</h3>
  <h3>Início:&nbsp; <joda:format value="${reserva.inicio}" pattern="dd/MM/yyyy"/></h3>
  <h3>Fim:&nbsp; <joda:format value="${reserva.fim}" pattern="dd/MM/yyyy"/></h3>
  <h3>Valor:&nbsp; ${reserva.valorReserva}</h3>
  <h3>Número de Adultos:&nbsp;${reserva.numeroAdultos}</h3>
</body>
</html>