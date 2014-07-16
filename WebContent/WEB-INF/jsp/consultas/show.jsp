<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	  	  <legend>Reserva</legend>
	  	  <div class="form-group">
				<label class="control-label col-xs-2">Hóspede:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reserva.hospede.nome}" readonly="readonly" />
				</div>
		  </div>
		   <div class="form-group">
				<label class="control-label col-xs-2">Email:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reserva.hospede.email}" readonly="readonly" />
				</div>
		  </div>
			<div class="form-group">
				<label class="control-label col-xs-2">Quarto:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reserva.quarto.numero}" readonly="readonly" />
				</div>
			</div>
		<div class="form-group">
			<label class="control-label col-xs-2">Início:</label>
			<div class="col-xs-10">
				<input type="text" class="col-xs-10" value="<joda:format value="${reserva.inicio}" pattern="dd/MM/yyyy"/>" readonly="readonly" />
			</div>
	   </div>
	   <div class="form-group">
			<label class="control-label col-xs-2">Fim:</label>
			<div class="col-xs-10">
				<input type="text" class="col-xs-10" value="<joda:format value="${reserva.fim}" pattern="dd/MM/yyyy"/>" readonly="readonly" />
			</div>
	   </div>
	    <div class="form-group">
			<label class="control-label col-xs-2">Valor:</label>
			<div class="col-xs-10">
				<input type="text" class="col-xs-10" value="${reserva.valorReserva}" readonly="readonly" />
			</div>
	   </div>
	    <div class="form-group">
			<label class="control-label col-xs-2">Adultos:</label>
			<div class="col-xs-10">
				<input type="text" class="col-xs-10" value="${reserva.numeroAdultos}" readonly="readonly" />
			</div>
	   </div>
	 </fieldset>
</div>
</body>
</html>