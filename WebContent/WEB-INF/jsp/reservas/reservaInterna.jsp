<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reserva Interna</title>

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
					<li class="active"><a href="<c:url value='/consultas'/>">Voltar</a></li>
				</ul>
				<h3 class="text-muted">Reserva Interna</h3>
			</div>
		
			<c:forEach var="reserva" items="${reservasInternasView.reservas}">
			<fieldset class="molduraLegenda">
		  	   			<legend>${reserva.quarto.descricao}</legend>
		        		<div class="row">
			                <div class="col-sm-6">
				              <div class="form-group">
				                <label for="checkin">Check-in</label>
				                <input type="text" value="<joda:format pattern='dd/MM/yyyy' value='${reserva.inicio}'/>" class="form-control" readonly="readonly"/>
				              </div>
				            </div>
				            <div class="col-sm-6">
				              <div class="form-group">
				                <label for="checkin">Check-out</label>
				                 <input type="text" class="form-control" value="<joda:format pattern='dd/MM/yyyy' value='${reserva.fim}'/>" />
				              </div>
				            </div>
				          </div>
				          <div class="row">
			              <div class="col-sm-3">
				              <div class="form-group">
				                <label>Adultos:</label>
			           			<input type="text" value="${reserva.numeroAdultos}" class="form-control" />		      
				              </div>
			              </div>
			              <div class="col-sm-3">
				              <div class="form-group">
				                <label>Crianças:</label>
			           			<input type="text" value="${reserva.numeroCriancas0a5}" class="form-control" />		      
				              </div>
			              </div>
			               <div class="col-sm-3">
				              <div class="form-group">
				                <label>Crianças (6 a 16):</label>
			           			<input type="text" value="${reserva.numeroCriancas6a16}" class="form-control" />		      
				              </div>
			              </div>
			              <div class="col-sm-3">
				              <div class="form-group">
				                <label>Crianças (17 e 18):</label>
			           			<input type="text"  value="${reserva.numeroCriancas17a18}" class="form-control"/>		      
				              </div>
			              </div>
			              </div>
			               <div class="row">
			               <div class="col-sm-6">
				              <div class="form-group">
				                <label>Valor:</label>
			           			<input type="text" name="reservas[${idx.index}].valorReserva" value="<fmt:formatNumber value="0" pattern="#,##0.00"/>" class="form-control"/>		      
				              </div>
			              </div>
			              </div>
			           </fieldset>
			</c:forEach>
			<div id="divHospede">
			  <form class="form-horizontal" action='<c:url value="/checkin/novo/hospede"/>' method="post">
			  	<fieldset>
					<legend>Responsável</legend>
					<div class="form-group">
						<label class="control-label col-xs-2" >Nome:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.nome" id="hospede.nome" value="${hospede.nome}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Email:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.email" id="hospede.email" value="${hospede.email}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">CPF:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.cpf" id="hospede.cpf" value="${hospede.cpf}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">RG:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.rg" id="hospede.rg" value="${hospede.rg}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Endereço:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.endereco" id="hospede.endereco" value="${hospede.endereco}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Telefone:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.telefone" id="hospede.telefone" value="${hospede.telefone}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Celular:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.celular" id="hospede.celular" value="${hospede.celular}" />
						</div>
					</div>
					 <div class="form-group">
						<label class="control-label col-xs-2" for="singlebutton"></label>
						<div class="col-xs-10">
							<button type="submit" id="concluir" name="concluir" class="btn btn-primary">
								Salvar
							</button>
						</div>
					 </div>
				</fieldset>
			  </form>
			</div>
		</div>
</body>
</html>