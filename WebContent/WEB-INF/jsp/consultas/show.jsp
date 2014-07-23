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
	 <c:choose>
	   <c:when test="${reserva.possuiPagamento}">
	   		<legend>Pagamentos</legend>
		  	 <c:forEach var="pg" items="${reserva.pagamentos}">
			  	 <div class="form-group">
			  	  	<label class="control-label col-xs-2">Data:</label>
				    <div class="col-xs-10">
				 	  <input type="text" class="col-xs-10" value="<fmt:formatDate value='${pg.dataPagamento}' pattern='dd/MM/yyyy'/>" readonly="readonly" />
					</div>
				</div>
				<div class="form-group">
			  	  	<label class="control-label col-xs-2">Valor:</label>
				    <div class="col-xs-10">
				 	  <input type="text" class="col-xs-10" value="${pg.valor}" readonly="readonly" />
					</div>
				</div>
			</c:forEach>
	   </c:when>
	   <c:otherwise> 
	   <form class="form-horizontal" action='<c:url value="/pagamentos/registrar"/>' method="post">
		<legend>Informar Pagamento</legend>
		  	 <div class="form-group">
				<label class="control-label col-xs-2">Tipos de Pagamento:</label>
				<div class="col-xs-10">
					<select id="tipo" class="col-xs-10" >   
		                <option> Tipos...</option>
		                <option value="1"> Cartão </option>
		                <option value="2"> Depósito </option>  
		            </select>
				</div>
			 </div>
		  	 <div id="cartao">
		  	 	<input type="hidden" value="${reserva.id}" name="pagamentoReserva.reserva.id"/>
		  	 	<input type="hidden" value="1" name="pagamentoReserva.tipoPagamento"/>
			  	 <div class="form-group">
			  	  	<label class="control-label col-xs-2">Data Pagamento:</label>
				    <div class="col-xs-10">
				 	  <input type="text" class="col-xs-10" value="<fmt:formatDate value='${pagamentoReserva.dataPagamento}' pattern='dd/MM/yyyy'/>" name="pagamentoReserva.dataPagamento" />
					</div>
				 </div>
			  	 
			  	 <div class="form-group">
			  	  	<label class="control-label col-xs-2">Valor:</label>
				    <div class="col-xs-10">
				 	  <input type="text" class="col-xs-10" value="${pagamentoReserva.valor}" name="pagamentoReserva.valor" />
					</div>
				 </div>
				 <div class="form-group">
					  <label class="control-label col-xs-2" for="singlebutton"></label>
					  <div class="col-xs-10">
					    <button id="singlebutton" name="singlebutton" class="btn btn-primary btn-lg">
						 Salvar
					    </button>
					  </div>
				    </div>
			</div>
			<div id="deposito">
			     <input type="hidden" value="${reserva.id}" name="pagamentoReserva.reserva.id"/>
			      <input type="hidden" value="2" name="pagamentoReserva.tipoPagamento"/>
				 <div class="form-group">
				   <label class="control-label col-xs-2">Banco:</label>
				   <div class="col-xs-10">
					 <select id="banco" name="pagamentoReserva.banco" class="col-xs-10" >   
		                <option> Tipos...</option>
		                <option value="Itau"> Itau </option>
		                <option value="Banco do Brasil"> Banco do Brasil </option>  
		             </select>
				   </div>
			 	</div>
				
				<div class="form-group">
			  	  	<label class="control-label col-xs-2">Data Previsão:</label>
				    <div class="col-xs-10">
				 	  <input type="text" class="col-xs-10" value="<fmt:formatDate value='${pagamentoReserva.dataPrevisao}' pattern='dd/MM/yyyy'/>" name="pagamentoReserva.dataPrevisao" />
					</div>
				 </div>
				<div class="form-group">
			  	  	<label class="control-label col-xs-2">Data Pagamento:</label>
				    <div class="col-xs-10">
				 	  <input type="text" class="col-xs-10" value="<fmt:formatDate value='${pagamentoReserva.dataDeposito}' pattern='dd/MM/yyyy'/>" name="pagamentoReserva.dataDeposito" />
					</div>
				 </div>
				 <div class="form-group">
			  	  	<label class="control-label col-xs-2">Valor:</label>
				    <div class="col-xs-10">
				 	  <input type="text" class="col-xs-10" value="${pagamentoReserva.valor}" name="pagamentoReserva.valor" />
					</div>
				 </div>
				 <div class="form-group">
					  <label class="control-label col-xs-2" for="singlebutton"></label>
					  <div class="col-xs-10">
					    <button id="singlebutton" name="singlebutton" class="btn btn-primary btn-lg">
						 Salvar
					    </button>
					  </div>
				  </div>
				
			</div> 
		</form>
	   </c:otherwise>
	 </c:choose>
	 </fieldset>
</div>
</body>
</html>