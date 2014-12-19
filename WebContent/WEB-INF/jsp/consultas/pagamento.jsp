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
					<c:if test="${not empty reserva.agrupadorReservas}">
						<li class="active"><a href="<c:url value='/pagamento/lote/${reserva.agrupadorReservas.id}'/>">Pagamentos em Lote</a></li>
					</c:if>
					<li class="active"><a href="<c:url value='/consultas'/>">Voltar</a></li>
				</ul>
			</div>
			<div class="row">
				 <div class="col-xs-12 col-md-12 col-lg-12">
                    <div class="panel panel-default height">
                        <div class="panel-heading">Reserva</div>
                        <div class="panel-body">
                            <strong>Responsável: </strong> ${reserva.hospede.nomeCompleto}<br>
                            <strong>Contato: </strong> ${reserva.hospede.email} - ${reserva.hospede.telefone}<br>
                            <strong>Quarto: </strong> ${reserva.quarto.numero}<br>
                             <strong>Periodo: </strong> De <joda:format value="${reserva.inicio}" pattern="dd/MM/yyyy"/> a <joda:format value="${reserva.fim}" pattern="dd/MM/yyyy"/><br>
                            <strong>Valor Total:</strong> <fmt:formatNumber value="${reserva.valorReserva}" type="number" pattern="#,##0.00"/><br>
                            <strong>Valor Pago:</strong> <fmt:formatNumber value="${reserva.valorPago}" type="number" pattern="#,##0.00"/> <br>
                            <strong>Valor em Aberto:</strong> <fmt:formatNumber value="${reserva.saldoAPagar}" type="number" pattern="#,##0.00"/> <br>
                        </div>
                    </div>
                </div>
			</div>
	   <div class="header">
		<ul class="nav nav-pills">
			<li class="active"><a href="<c:url value='/reservas/cancelar/${reserva.id}'/>">Cancelar Reserva</a></li>
		</ul>	
		</div>
	   <c:if test="${reserva.possuiPagamentoOuPrevisao}">
	     <%@include file="fragmentos/pagamentosrealizados.jspf" %>
	   </c:if>
	    <%@include file="fragmentos/pagamentosarealizar.jspf" %>
	 
</div>
</body>
</html>