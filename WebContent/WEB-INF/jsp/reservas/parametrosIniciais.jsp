<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <script type="text/javascript" src="<c:url value='/resources/scripts/jquery-1.7.1.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/scripts/jquery.maskedinput-1.3.min.js'/>"></script>
  <script type="text/javascript">
  jQuery(document).ready(function() {
	  
	$("#numeroQuartos").bind('change', function(self) {
		maisParametrosDeSelecao();
	});
});

function maisParametrosDeSelecao() {
	var num = $('select#numeroQuartos').val(); 
	$('#reservas').empty();
	addParametrosDeReservas(0);
	
	for (i = 0; i < num; i++){
		if (i === 0)
			continue;
		addParametrosDeReservas(i);
	}
}

$(function() {
	$('#chegada').datepicker({  
         inline: true,  
         showOtherMonths: true,  
         dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],  
         dateFormat: 'dd/mm/yy',
         dayNames: [ "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" ],
         dayNamesMin: [ "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" ],
     });
	$("#chegada").mask("99/99/9999");
	$('#saida').datepicker({  
        inline: true,  
        showOtherMonths: true,  
        dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        dateFormat: 'dd/mm/yy',
        dayNames: [ "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" ],
        dayNamesMin: [ "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" ],
    });
	$("#saida").mask("99/99/9999");
});

function addParametrosDeReservas(indiceDosParametros){
	
	var num = indiceDosParametros + 1;
	cHtml = "<fieldset class='molduraLegenda'>"+
			"<legend>Reserva N. "+num+"</legend>"+
			"<div class='row'>"+
			"<div class='col-sm-6'>"+
            "<div class='form-group'>"+
            "<label>Adultos</label>"+
			"	<select name='parametrosReserva.detalhes["+indiceDosParametros+"].numeroAdultos' class='form-control'>"+
			"		<option value=''>Nº de adultos</option> "+
			"		<option value='0'>0</option> "+
			"		<option value='1'>1</option>"+
			"		<option value='2'>2</option>"+
			"		<option value='3'>3</option>"+
			"		<option value='4'>4</option>"+
			"	    <option value='5'>5</option>"+
			"		<option value='6'>6</option>"+
			"		<option value='7'>7</option>"+
			"		<option value='8'>8</option>"+
			"		<option value='9'>9</option>"+
			"		<option value='10'>10</option>"+
			"	</select>"+
			"</div>"+
			"</div>"+
			"<div class='col-sm-3'>"+
            "<div class='form-group'>"+
            "<label>Crianças</label>"+
			"	<select name='parametrosReserva.detalhes["+indiceDosParametros+"].numeroCriancas0a5' class='form-control'>"+
			"		<option value=''>Nº de crianças (0-5)</option>"+
			"		<option value='0'>0</option>"+
			"		<option value='1'>1</option>"+
			"		<option value='2'>2</option>"+
			"		<option value='3'>3</option>"+
			"		<option value='4'>4</option>"+
			"	    <option value='5'>5</option>"+
			"		<option value='6'>6</option>"+
			"		<option value='7'>7</option>"+
			"		<option value='8'>8</option>"+
			"		<option value='9'>9</option>"+
			"		<option value='10'>10</option>"+
			"	</select>"+
			"</div>"+
			"</div>"+
			"</div>"+
			"<div class='row'>"+
			"<div class='col-sm-6'>"+
            "<div class='form-group'>"+
            "<label>Crianças de 6 a 16</label>"+
			"	<select name='parametrosReserva.detalhes["+indiceDosParametros+"].numeroCriancas6a16' class='form-control'>"+
			"		<option value=''>Crianças de 6 a 16</option> "+
			"		<option value='0'>0</option> "+
			"		<option value='1'>1</option>"+
			"		<option value='2'>2</option>"+
			"		<option value='3'>3</option>"+
			"		<option value='4'>4</option>"+
			"	    <option value='5'>5</option>"+
			"		<option value='6'>6</option>"+
			"		<option value='7'>7</option>"+
			"		<option value='8'>8</option>"+
			"		<option value='9'>9</option>"+
			"		<option value='10'>10</option>"+
			"	</select>"+
			"</div>"+
			"</div>"+
			"<div class='col-sm-3'>"+
            "<div class='form-group'>"+
            "<label>Crianças de 17 e 18</label>"+
			"	<select name='parametrosReserva.detalhes["+indiceDosParametros+"].numeroCriancas17a18' class='form-control'>"+
			"		<option value=''>Crianças de 17 e 18</option>"+
			"		<option value='0'>0</option>"+
			"		<option value='1'>1</option>"+
			"		<option value='2'>2</option>"+
			"		<option value='3'>3</option>"+
			"		<option value='4'>4</option>"+
			"	    <option value='5'>5</option>"+
			"		<option value='6'>6</option>"+
			"		<option value='7'>7</option>"+
			"		<option value='8'>8</option>"+
			"		<option value='9'>9</option>"+
			"		<option value='10'>10</option>"+
			"	</select>"+
			"</div>"+
			"</div>"+
			"</div>"+
			"</fieldset>";

	$("#reservas").append(cHtml);
}
	
</script>
</head>
<body>


<section id="reservation-form">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <form class="reservation-horizontal clearfix" id="form-reservas" action='<c:url value="/reservas/parametrosDetalhes"/>' method="post">
            <div id="message">
            	<c:forEach var="error" items="${errors}">
					<div class="alert alert-danger">
						${error.message}
					</div>
				</c:forEach>
            </div><!-- Error message display -->
            <div class="row">
	            <div class="col-sm-3">
		              <div class="form-group">
		                <label for="checkin">Check-in</label>
		                <div class="popover-icon" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Check-In is from 11:00"> <i class="fa fa-info-circle fa-lg"> </i> </div>
		                <i class="fa fa-calendar infield"></i>
		                <input name="parametrosReserva.chegada" type="text" id="checkin" value="<fmt:formatDate value='${reservasView.chegada}' pattern='dd/MM/yyyy'/>" class="form-control" placeholder="Check-in"/>
		              </div>
		            </div>
		            <div class="col-sm-3">
		              <div class="form-group">
		                <label for="checkout">Check-out</label>
		                <div class="popover-icon" data-container="body" data-toggle="popover" data-trigger="hover" data-placement="right" data-content="Check-out is from 12:00"> <i class="fa fa-info-circle fa-lg"> </i> </div>
		                <i class="fa fa-calendar infield"></i>
		                <input name="parametrosReserva.saida" type="text" id="checkout" class="form-control" placeholder="Check-out" value="<fmt:formatDate value='${reservasView.saida}' pattern='dd/MM/yyyy'/>"/>
		              </div>
		            </div>
		            <div class="col-sm-6">
		              <div class="form-group">
		                <label for="numeroQuartos" accesskey="N">Número de Quartos</label>
		            	 <select name="parametrosReserva.numeroDeQuartos" id="numeroQuartos" class="form-control">
							<option value="1" <c:if test="${1 == reservasView.numeroDeQuartos}">selected="true"</c:if>>1</option>
							<option value="2" <c:if test="${2 == reservasView.numeroDeQuartos}">selected="true"</c:if>>2</option>
							<option value="3" <c:if test="${3 == reservasView.numeroDeQuartos}">selected="true"</c:if>>3</option>
							<option value="4" <c:if test="${4 == reservasView.numeroDeQuartos}">selected="true"</c:if>>4</option>
		                    <option value="5" <c:if test="${5 == reservasView.numeroDeQuartos}">selected="true"</c:if>>5</option>
							<option value="6" <c:if test="${6 == reservasView.numeroDeQuartos}">selected="true"</c:if>>6</option>
							<option value="7" <c:if test="${7 == reservasView.numeroDeQuartos}">selected="true"</c:if>>7</option>
							<option value="8" <c:if test="${8 == reservasView.numeroDeQuartos}">selected="true"</c:if>>8</option>
							<option value="9" <c:if test="${9 == reservasView.numeroDeQuartos}">selected="true"</c:if>>9</option>
							<option value="10" <c:if test="${10 == reservasView.numeroDeQuartos}">selected="true"</c:if>>10</option>
						 </select>
		              </div>
		           </div>
		         </div>
		        <div class="row">
		            <div id="reservas">
		              <c:choose>
	           			<c:when test="${empty reservasView.parametrosReserva || empty reservasView.parametrosReserva.detalhes }">
	           			   
				          <fieldset class="molduraLegenda">
				          	  <legend>Reserva N. 1</legend>
				          	  <div class="row">
					          <div class="col-sm-4">
					              <div class="form-group">
					                <label>Adultos</label>
					            	 <select name="parametrosReserva.detalhes[${idx.index}].numeroAdultos" class="form-control">
										<option value="">Nº de adultos</option>
										<option value="1" <c:if test="${1 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>1</option>
										<option value="2" <c:if test="${2 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>2</option>
										<option value="3" <c:if test="${3 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>3</option>
										<option value="4" <c:if test="${4 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>4</option>
					                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>5</option>
										<option value="6" <c:if test="${6 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>6</option>
										<option value="7" <c:if test="${7 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>7</option>
										<option value="8" <c:if test="${8 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>8</option>
										<option value="9" <c:if test="${9 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>9</option>
										<option value="10" <c:if test="${10 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>10</option>
									</select>
					              </div>
					           </div>
					          <div class="col-sm-2">
				              	<div class="form-group">
				              	   <label>Crianças</label>
				                   <select name="parametrosReserva.detalhes[0].numeroCriancas0a5" class="form-control">
										<option value="">Nº de crianças (0-5)</option>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
					                    <option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
									</select>
				                </div>
				            	</div>
				            </div>
				            <div class="row">
					          <div class="col-sm-4">
					              <div class="form-group">
					                <label>Crianças de 6 a 16</label>
					            	 <select name="parametrosReserva.detalhes[${idx.index}].numeroCriancas6a16" class="form-control">
										<option value="">Crianças de 6 a 16</option>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
					                    <option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
									</select>
					              </div>
					           </div>
					          <div class="col-sm-2">
				              	<div class="form-group">
				              	   <label>Crianças de 17 e 18</label>
				                   <select name="parametrosReserva.detalhes[0].numeroCriancas17a18" class="form-control">
										<option value="">Crianças de 17 e 18</option>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
					                    <option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
									</select>
				                </div>
				            	</div>
				            </div>
			              </fieldset>
			             
			            </c:when> 
			            <c:otherwise>
		                    <c:forEach var="detalhe" items="${ reservasView.parametrosReserva.detalhes }" varStatus="idx" >
			              		<fieldset class="molduraLegenda">
		           			    	<legend>Reserva n. ${idx.index+1}</legend>
		           			    	<div class="row">
							          <div class="col-sm-6">
							              <div class="form-group">
							                <label>Adultos</label>
				           			     	<select name="parametrosReserva.detalhes[${idx.index}].numeroAdultos" class="form-control">
												<option value="">Nº de adultos</option>
												<option value="0" <c:if test="${0 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>0</option>
												<option value="1" <c:if test="${1 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>1</option>
												<option value="2" <c:if test="${2 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>2</option>
												<option value="3" <c:if test="${3 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>3</option>
												<option value="4" <c:if test="${4 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>4</option>
							                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>5</option>
												<option value="6" <c:if test="${6 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>6</option>
												<option value="7" <c:if test="${7 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>7</option>
												<option value="8" <c:if test="${8 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>8</option>
												<option value="9" <c:if test="${9 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>9</option>
												<option value="10" <c:if test="${10 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>10</option>
											</select>
										</div>
									</div>
									<div class="col-sm-3">
				              	<div class="form-group">
				              	   <label>Crianças</label>
				                   <select name="parametrosReserva.detalhes[${idx.index}].numeroCriancas0a5" class="form-control">
										<option value="">Nº de crianças (0-5)</option>
										<option value="0" <c:if test="${0 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>0</option>
										<option value="1" <c:if test="${1 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>1</option>
										<option value="2" <c:if test="${2 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>2</option>
										<option value="3" <c:if test="${3 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>3</option>
										<option value="4" <c:if test="${4 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>4</option>
					                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>5</option>
										<option value="6" <c:if test="${6 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>6</option>
										<option value="7" <c:if test="${7 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>7</option>
										<option value="8" <c:if test="${8 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>8</option>
										<option value="9" <c:if test="${9 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>9</option>
										<option value="10" <c:if test="${10 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>10</option>
									</select>
				                </div>
				            	</div>
				            	</div>
				            	<div class="row">
				            		<div class="col-sm-6">
							              <div class="form-group">
							               <label>Crianças de 6 a 16</label>
				           			     	<select name="parametrosReserva.detalhes[${idx.index}].numeroCriancas6a16" class="form-control">
												<option value="">Crianças de 6 a 16</option>
												<option value="0" <c:if test="${0 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>0</option>
												<option value="1" <c:if test="${1 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>1</option>
												<option value="2" <c:if test="${2 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>2</option>
												<option value="3" <c:if test="${3 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>3</option>
												<option value="4" <c:if test="${4 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>4</option>
							                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>5</option>
												<option value="6" <c:if test="${6 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>6</option>
												<option value="7" <c:if test="${7 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>7</option>
												<option value="8" <c:if test="${8 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>8</option>
												<option value="9" <c:if test="${9 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>9</option>
												<option value="10" <c:if test="${10 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>10</option>
											</select>
							                
										</div>
									</div>
									<div class="col-sm-3">
				              	<div class="form-group">
				              	   <label>Crianças de 17 e 18</label>
				                   <select name="parametrosReserva.detalhes[${idx.index}].numeroCriancas0a5" class="form-control">
										<option value="">Crianças de 17 e 18</option>
										<option value="0" <c:if test="${0 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>0</option>
										<option value="1" <c:if test="${1 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>1</option>
										<option value="2" <c:if test="${2 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>2</option>
										<option value="3" <c:if test="${3 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>3</option>
										<option value="4" <c:if test="${4 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>4</option>
					                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>5</option>
										<option value="6" <c:if test="${6 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>6</option>
										<option value="7" <c:if test="${7 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>7</option>
										<option value="8" <c:if test="${8 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>8</option>
										<option value="9" <c:if test="${9 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>9</option>
										<option value="10" <c:if test="${10 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>10</option>
									</select>
				                </div>
				            	</div>
				            	</div>
		           				</fieldset>
		           			</c:forEach>
		           		</c:otherwise>
			           </c:choose>
			         </div>
	            <div class="col-sm-2">
              		<button type="submit" class="btn btn-primary btn-block">Procurar Quartos</button>
            	</div>
		   </div>
         </form>
         </div>
       </div>
     </div>
 </section>
</body>
</html>