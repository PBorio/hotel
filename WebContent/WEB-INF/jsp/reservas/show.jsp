<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reserva</title>
</head>
<body>
  <h3>Hóspede:&nbsp; ${reserva.hospede.nome}</h3>
  <h3>Email:&nbsp; ${reserva.hospede.email}</h3>
  <h3>Quarto:&nbsp; ${reserva.quarto.numero}</h3>
  <h3>Início:&nbsp; <joda:format value="${reserva.inicio}" pattern="dd/MM/yyyy"/></h3>
  <h3>Fim:&nbsp; <joda:format value="${reserva.fim}" pattern="dd/MM/yyyy"/></h3>
  <h3>Valor:&nbsp; ${reserva.valorReserva}</h3>
  <h3>Número de Adultos:&nbsp;${reserva.numeroAdultos}</h3>
</body>
</html>