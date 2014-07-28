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
</head>
<body>
	 
	    <c:if test="${not empty mensagem}">
		<div class="alert alert-success">
			${mensagem}
		</div>
		</c:if>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">
				${erro}
			</div>
		</c:if>

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
			<c:if test="${ not empty checkin.hospedes}">
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
			</c:if>
			<div id="divHospede">
			  <form class="form-horizontal" method="post">
			  	<fieldset>
					<legend>Checkin</legend>
					<div class="form-group">
						<label class="control-label col-xs-2">Chegada:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="checkinLocal.dataCheckin" value="<fmt:formatDate value='${checkin.dataCheckin}' pattern='dd/MM/yyyy'/>" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Saída:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="checkinLocal.dataCheckout" value="<fmt:formatDate value='${checkin.dataCheckout}' pattern='dd/MM/yyyy'/>" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2" >Valor Diária:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" value="${checkin.valorDiaria}" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2" >Valor da Reserva:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" value="${checkin.reserva.valorReserva}" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Valor Pago:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" value="${checkin.reserva.valorPago}" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Desconto em %:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="checkinLocal.desconto" value="${checkin.desconto}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Valor Final:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" value="${checkin.valorFinal}" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Saldo A Pagar:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" value="${checkin.saldoAPagar}" readonly="readonly"/>
						</div>
					</div>
					 <div class="form-group">
						<label class="control-label col-xs-2" for="singlebutton"></label>
						<div class="col-xs-10">
							<button type="button" class="btn btn-primary" onClick="this.form.action='<c:url value="/checkin/recalcula/valores/"/>';this.form.submit()">
								Realcular Valor Da Diária
							</button>
							<button type="submit" class="btn btn-primary" onClick="this.form.action='<c:url value="/checkin/confirma/"/>';this.form.submit()">
								Concluir Checkin
							</button>
						</div>
					 </div>
					 <label class="control-label col-xs-2" for="singlebutton"></label>
				</fieldset>
			  </form>
			</div>
		</div>
</body>
</html>