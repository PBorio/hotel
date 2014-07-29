<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkin - Valores</title>
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
	 
	    <c:if test="${not empty mensagem}">
		<div class="alert alert-success">
			${mensagem}
		</div>
		</c:if>
	<c:forEach var="error" items="${errors}">
		<div class="alert alert-danger">
			${error.message}
		</div>
	</c:forEach>

		<div class="container">
			<div class="header">
				<ul class="nav nav-pills pull-right">
					<li class="active"><a href="<c:url value='/'/>">Home</a></li>
				</ul>
			</div>
			<fieldset>
				<legend>Quarto</legend>
				<table class="table table-striped table-bordered" id="example"
							cellpadding="0" cellspacing="0" border="0" width="100%">
						<tfoot>
							<tr>
							   	<th>${checkin.quarto.numero}</th>
							</tr>
						</tfoot>
				</table>
			</fieldset>
			
			<fieldset>
				<legend>Hospedes Já Registrados</legend>
				<table class="table table-striped table-bordered" id="example"
						cellpadding="0" cellspacing="0" border="0" width="100%">
					<tfoot>
						<c:forEach var="hospede" items="${checkin.hospedes}">
							<tr id="hospede-${hospede.id}">
								<th>${hospede.nomeCompleto}</th>
							</tr>
						</c:forEach>
					</tfoot>
				</table>
			</fieldset>
			<fieldset>
				<legend>Hospedes Já Registrados</legend>
				<table class="table table-striped table-bordered" id="example"
						cellpadding="0" cellspacing="0" border="0" width="100%">
					<tbody>
							<tr>
							    <td>Valor Diária:</td>
								<td>${checkin.valorDiaria}</td>
							</tr>
							<tr>
							    <td>Valor Final:</td>
								<td>${checkin.valorFinal}</td>
							</tr>
							<tr>
							    <td>Valor Pago:</td>
								<td>${checkin.valorPago}</td>
							</tr>
							<tr>
							    <td>Saldo a Pagar:</td>
								<td>${checkin.saldoAPagar}</td>
							</tr>
					</tbody>
				</table>
			</fieldset>
			<div id="divHospede">
			  <fieldset> 
		<legend>Informar Pagamento</legend>
		 <form class="form-horizontal" action='<c:url value="/checkin/registrar/pagamento"/>' method="post">
		 	   <%@include file="../consultas/fragmentos/formPagamento.jspf" %>
		</form>
		</fieldset>
			</div>
		</div>
</body>
</html>