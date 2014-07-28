<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reserva</title>
<script type="text/javascript">
jQuery(document).ready(function() {
	  
	$("#tipo").bind('change', function(self) {
		mudarTipoPagamento();
	});
    mudarTipoPagamento();	
});
function mudarTipoPagamento(){
	var tipo = $('select#tipo').val();
	
	if (tipo === "1"){
    	$("#cartao").show();
    	$("#deposito").hide();
	}else if(tipo === "2"){
		$("#cartao").hide();
    	$("#deposito").show();
	}else{
		$("#cartao").hide();
    	$("#deposito").hide();
	}
    	
}
 </script>
</head>
<body>
	<c:forEach var="error" items="${errors}">
		<div class="alert alert-danger">
			${error.message}
		</div>
	</c:forEach>
  <div class="container">
			<div class="header">
				<ul class="nav nav-pills pull-right">
					<li class="active"><a href="/">Limpar</a></li>
				</ul>
			</div>
		<fieldset>
	  	  <legend>Reserva</legend>
	  	  <div class="form-group">
				<label class="control-label col-xs-2">Responsável:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reserva.hospede.nomeCompleto}" readonly="readonly" />
				</div>
		  </div>
		   <div class="form-group">
				<label class="control-label col-xs-2">Contato:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reserva.hospede.email} - ${reserva.hospede.telefone}" readonly="readonly" />
				</div>
		  </div>
			<div class="form-group">
				<label class="control-label col-xs-2">Quarto:</label>
				<div class="col-xs-10">
					<input type="text" class="col-xs-10" value="${reserva.quarto.numero}" readonly="readonly" />
				</div>
			</div>
		<div class="form-group">
			<label class="control-label col-xs-2">Periodo:</label>
			<div class="col-xs-10">
				<input type="text" class="col-xs-10" value="De <joda:format value="${reserva.inicio}" pattern="dd/MM/yyyy"/> a <joda:format value="${reserva.fim}" pattern="dd/MM/yyyy"/>" readonly="readonly" />
			</div>
	   </div>
	    <div class="form-group">
			<label class="control-label col-xs-2">Valor Total:</label>
			<div class="col-xs-10">
				<input type="text" class="col-xs-10" value="${reserva.valorReserva}" readonly="readonly" />
			</div>
	   </div>
	   <div class="form-group">
			<label class="control-label col-xs-2">Valor em Aberto:</label>
			<div class="col-xs-10">
				<input type="text" class="col-xs-10" value="${reserva.saldoAPagar}" readonly="readonly" />
			</div>
	   </div>
	 </fieldset>
	   <c:if test="${reserva.possuiPagamentoOuPrevisao}">
	     <%@include file="fragmentos/pagamentosrealizados.jspf" %>
	   </c:if>
	    <%@include file="fragmentos/pagamentosarealizar.jspf" %>
	 
</div>
</body>
</html>