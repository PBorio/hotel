<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reserva</title>
<script type="text/javascript" src="<c:url value='/resources/scripts/jquery.maskedinput-1.3.min.js'/>"></script>
  <script type="text/javascript">

$(function() {
	$("#cpf").mask("999.999.999-99");
});

	
</script>
</head>
<body>
<section id="reservation-form" class="mt100">
    <div class="container">
    
	    <div class="row">
	        <div class="col-md-12">
	          <div class="form-inline reservation-horizontal clearfix">
	            <fieldset>
		        	<h2 class="lined-heading"><span>Informações da Reserva</span></h2>
		        	<c:forEach var="reserva" items="${reservasView.reservas}">
		        	  <fieldset class="molduraLegenda">
		  	   			<legend>${reserva.quarto.descricao}</legend>
		        		<div class="row">
			                <div class="col-sm-3">
				              <div class="form-group">
				                <label for="checkin">Check-in</label>
				                <input type="text" value="<joda:format pattern='dd/MM/yyyy' value='${reserva.inicio}'/>" class="form-control" readonly="readonly"/>
				              </div>
				            </div>
				            <div class="col-sm-3">
				              <div class="form-group">
				                <label for="checkin">Check-out</label>
				                 <input type="text" class="form-control" value="<joda:format pattern='dd/MM/yyyy' value='${reserva.fim}'/>" readonly="readonly"/>
				              </div>
				            </div>
			              <div class="col-sm-3">
				              <div class="form-group">
				                <label>Adultos:</label>
			           			<input type="text" value="${reserva.numeroAdultos}" class="form-control" readonly="readonly"/>		      
				              </div>
			              </div>
			              <div class="col-sm-3">
				              <div class="form-group">
				                <label>Crianças:</label>
			           			<input type="text" value="${reserva.numeroCriancas0a5}" class="form-control" readonly="readonly"/>		      
				              </div>
			              </div>
			            </div>
			           </fieldset>
		        	</c:forEach>
		        </fieldset>
	          </div>
	        </div>
	    </div>
	</div>
</section>

<section class="mt100">
    <div class="container">
    
	    <div class="row">
	        <div class="col-md-12">
	          <fieldset>
		         <h2 class="lined-heading"><span>Informações do Responsável</span></h2>
	          	 <form class="reservation-horizontal clearfix" id="form-reservas" action='<c:url value="/reservas/confirmar"/>' method="post">
	          	   <input type="hidden" name="hospede.id" value="${hospede.id}" />
	          	   <div id="message">
		            	<c:forEach var="error" items="${errors}">
							<div class="alert alert-danger">
								${error.message}
							</div>
						</c:forEach>
		            </div><!-- Error message display -->
		            <div class="row">
		                <div class="col-sm-12">
			              <div class="form-group">
			                <label>Nome</label>
			                <input id="nome" type="text" name="hospede.nome" value="${hospede.nome}" class="form-control" />
			              </div>
			            </div>
			        </div>
			        <div class="row">
		                <div class="col-sm-12">
			              <div class="form-group">
			                <label>Sobrenome</label>
			                <input id="nome" type="text" name="hospede.sobrenome" value="${hospede.sobrenome}" class="form-control" />
			              </div>
			            </div>
			        </div>
			        <div class="row">
		                <div class="col-sm-12">
			              <div class="form-group">
			                <label>CPF</label>
			                <input id="cpf" type="text" name="hospede.cpf" value="${hospede.cpf}" class="form-control" />
			              </div>
			            </div>
			        </div>
			        <div class="row">
		                <div class="col-sm-12">
			              <div class="form-group">
			                <label>Email</label>
			                <input id="email" type="text" name="hospede.email" value="${hospede.email}" class="form-control" />
			              </div>
			            </div>
			        </div>
			        <div class="row">
		                <div class="col-sm-6">
			              <div class="form-group">
			                <label>Cidade</label>
			                <input id="cidade" type="text" name="hospede.cidade" value="${hospede.cidade}" class="form-control" />
			              </div>
			            </div>
			            <div class="col-sm-6">
			              <div class="form-group">
			                <label>Estado/Província/Departamento</label>
			                <input id="estado" type="text" name="hospede.estado" value="${hospede.estado}" class="form-control" />
			              </div>
			            </div>
			        </div>
			        <div class="row">
		                <div class="col-sm-12">
			              <div class="form-group">
			                <label>País</label>
			                <input id="pais" type="text" name="hospede.pais" value="${hospede.pais}" class="form-control"/>
			              </div>
			            </div>
			        </div>
			        <div class="row">
		                <div class="col-sm-6">
			              <div class="form-group">
			                <label>Fone</label>
			                <input id="telefone" type="text" placeholder="Telefone" name="hospede.telefone" value="${hospede.telefone}" class="form-control" />
			              </div>
			            </div>
			            <div class="col-sm-6">
			              <div class="form-group">
			                <label>Celular</label>
			                <input id="celular" type="text" placeholder="Celular" name="hospede.celular" value="${hospede.celular}" class="form-control" />
			              </div>
			            </div>
			        </div>
			        <div class="row">
			         <div class="col-sm-2">
	              		<button type="submit" class="btn btn-primary btn-block">Confirmar Reserva</button>
	            	</div>
	            	</div>
	          	 </form>
	          </fieldset>
	        </div>
	    </div>
	 </div>
</section>
	            
		        	

</body>
</html>
