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
			 <form class="form-horizontal" action='<c:url value="/confirmar/reserva/interna"/>' method="post">
			<c:forEach var="reserva" items="${reservasInternasView.reservas}" varStatus="idx" >
			<div class="row">
			 <div class="col-xs-12 col-md-12 col-lg-12">
                <div class="panel panel-default height">
                   <div class="panel-heading">${reserva.quarto.descricao}</div>
                  	<div class="panel-body">
			<fieldset class="molduraLegenda">
		        		<div class="row">
			                <div class="col-sm-6">
				              <div class="form-group">
				                <label for="checkin">Check-in</label>
				                <input type="text" id="reservasInternasView.reservas[${idx.index}].inicio" name="reservasInternasView.reservas[${idx.index}].inicio" 
				                        value="<joda:format pattern='dd/MM/yyyy' value='${reserva.inicio}'/>" class="form-control" readonly="readonly"/>
				                 <input type="hidden" id="reservasInternasView.reservas[${idx.index}].quarto.id" name="reservasInternasView.reservas[${idx.index}].quarto.id" 
				                        value="${reserva.quarto.id}"/>
				              </div>
				            </div>
				            <div class="col-sm-6">
				              <div class="form-group">
				                <label for="checkin">Check-out</label>
				                 <input type="text" id="reservasInternasView.reservas[${idx.index}].fim" name="reservasInternasView.reservas[${idx.index}].fim" 
				                        class="form-control" value="<joda:format pattern='dd/MM/yyyy' value='${reserva.fim}'/>" />
				              </div>
				            </div>
				          </div>
				          <div class="row">
			              <div class="col-sm-3">
				              <div class="form-group">
				                <label>Adultos:</label>
			           			<input type="text" id="reservasInternasView.reservas[${idx.index}].numeroAdultos" name="reservasInternasView.reservas[${idx.index}].numeroAdultos" 
			           			       value="${reserva.numeroAdultos}" class="form-control" onchange="recalcular(${idx.index})" />		      
				              </div>
			              </div>
			              <div class="col-sm-3">
				              <div class="form-group">
				                <label>Crianças:</label>
			           			<input type="text" id="reservasInternasView.reservas[${idx.index}].numeroCriancas0a5" name="reservasInternasView.reservas[${idx.index}].numeroCriancas0a5" 
			           					value="${reserva.numeroCriancas0a5}" class="form-control" onchange="recalcular(${idx.index})" />		      
				              </div>
			              </div>
			               <div class="col-sm-3">
				              <div class="form-group">
				                <label>Crianças (6 a 16):</label>
			           			<input type="text" id="reservasInternasView.reservas[${idx.index}].numeroCriancas6a16" name="reservasInternasView.reservas[${idx.index}].numeroCriancas6a16" 
			           			       value="${reserva.numeroCriancas6a16}" class="form-control" onchange="recalcular(${idx.index})"/>		      
				              </div>
			              </div>
			              <div class="col-sm-3">
				              <div class="form-group">
				                <label>Crianças (17 e 18):</label>
			           			<input type="text" id="reservasInternasView.reservas[${idx.index}].numeroCriancas17a18" name="reservasInternasView.reservas[${idx.index}].numeroCriancas17a18"  
			           			       value="${reserva.numeroCriancas17a18}" class="form-control" onchange="recalcular(${idx.index})"/>		      
				              </div>
			              </div>
			              </div>
			               <div class="row">
			               <div class="col-sm-3">
				              <div class="form-group">
				                <label>Valor Diária:</label>
			           			<input type="text" id="reservasInternasView.reservas[${idx.index}].valorDiaria" name="reservasInternasView.reservas[${idx.index}].valorDiaria" value="<fmt:formatNumber value="${reserva.valorDiaria}" pattern="#,##0.00"/>" class="form-control" readonly="readonly"/>		      
				              </div>
			              </div>
			               <div class="col-sm-3">
				              <div class="form-group">
				                <label>Valor Reserva:</label>
			           			<input type="text" id="reservasInternasView.reservas[${idx.index}].valorReserva" name="reservasInternasView.reservas[${idx.index}].valorReserva" value="<fmt:formatNumber value="${reserva.valorReserva}" pattern="#,##0.00"/>" class="form-control"/>		      
				              </div>
			              </div>
			              </div>
			           </fieldset>
		          </div>
		         </div>
		       </div>
		      </div>
			</c:forEach>
			<div id="divHospede">
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
				</div>
			  </form>
		</div>
<script src="<c:url value='/resources/scripts/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/scripts/jquery.maskedinput-1.3.min.js'/>"></script>
<script type="text/javascript">
$(function() {
	 $('input[id*="\\]\\.fim"]' ).each(
				function(index, element ){
					$(element).datepicker({  
			            inline: true,  
			            showOtherMonths: true,  
			            dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],  
			            dateFormat: 'dd/mm/yy',
			            dayNames: [ "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" ],
			            dayNamesMin: [ "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" ],
			            onClose: function() {
			                propagarData(index);
			              }
			        });
			    	$(element).mask("99/99/9999");
			    }
		    );
});
function propagarData(idx){
	 var fim = $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.fim').val();
	 $('input[id*="\\]\\.fim"]' ).each(
				function(index, element ){
			    	$(element).val(fim);
			    	recalcular(index);
			    }
		    );
}
function recalcular(idx){
    var inicio = $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.inicio').val();
    var fim = $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.fim').val();
    var idQuarto = $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.quarto\\.id').val();
    var adultos = $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.numeroAdultos').val();
    var de0a5 = $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.numeroCriancas0a5').val();
    var de6a16 = $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.numeroCriancas6a16').val();
    var de17a18 = $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.numeroCriancas17a18').val();
	  $.getJSON('/hotel/reservas/recalcula/valor.json', {
	   inicio : inicio,
	   fim: fim, 
	   idQuarto: idQuarto,
	   numeroAdultos: adultos,
	   numero0a5: de0a5,
	   numero6a16: de6a16,
	   numero17a18: de17a18
		}, function(reserva) {
			 $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.valorReserva').val(reserva.valorReserva);
			 $('#reservasInternasView\\.reservas\\['+idx+'\\]\\.valorDiaria').val(reserva.valorDiaria);
		});
 }

</script>
</body>
</html>