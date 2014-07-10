<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

function addParametrosDeReservas(indiceDosParametros){
	
	var num = indiceDosParametros + 1;
	cHtml = "<fieldset class='molduraLegenda'>"+
			"<legend>Reserva N. "+num+"</legend>"+
		    "<p class='half'>"+
			"	<select name='parametrosReserva.detalhes["+indiceDosParametros+"].numeroAdultos'>"+
			"		<option value=''>Nº de adultos</option> "+
			"		<option value='0'>0</option> "+
			"		<option value='1'>01</option>"+
			"		<option value='2'>02</option>"+
			"		<option value='3'>03</option>"+
			"		<option value='4'>04</option>"+
			"	    <option value='5'>05</option>"+
			"		<option value='6'>06</option>"+
			"		<option value='7'>07</option>"+
			"		<option value='8'>08</option>"+
			"		<option value='9'>09</option>"+
			"		<option value='10'>10</option>"+
			"	</select>"+
			"</p>"+
			"<p class='half'>"+
			"	<select name='parametrosReserva.detalhes["+indiceDosParametros+"].numeroCriancas0a5'>"+
			"		<option value=''>Nº de crianças (0-5)</option>"+
			"		<option value='0'>0</option>"+
			"		<option value='1'>01</option>"+
			"		<option value='2'>02</option>"+
			"		<option value='3'>03</option>"+
			"		<option value='4'>04</option>"+
			"	    <option value='5'>05</option>"+
			"		<option value='6'>06</option>"+
			"		<option value='7'>07</option>"+
			"		<option value='8'>08</option>"+
			"		<option value='9'>09</option>"+
			"		<option value='10'>10</option>"+
			"	</select>"+
			"</p>"+
			"<p class='half'>"+
			"	<select name='parametrosReserva.detalhes["+indiceDosParametros+"].numeroCriancas6a16'>"+
			"		<option value=''>Nº de crianças (6-16)</option>"+
			"		<option value='0'>0</option>"+
			"		<option value='1'>01</option>"+
			"		<option value='2'>02</option>"+
			"		<option value='3'>03</option>"+
			"		<option value='4'>04</option>"+
			"	    <option value='5'>05</option>"+
			"		<option value='6'>06</option>"+
			"		<option value='7'>07</option>"+
			"		<option value='8'>08</option>"+
			"		<option value='9'>09</option>"+
			"		<option value='10'>10</option>"+
			"	</select>"+
			"</p>"+
			"<p class='half'>"+
			"	 <select name='parametrosReserva.detalhes["+indiceDosParametros+"].numeroCriancas17a18'>"+
			"		<option value=''>Nº de crianças (16-18)</option>"+
			"		<option value='0'>0</option>"+
			"		<option value='1'>01</option>"+
			"		<option value='2'>02</option>"+
			"		<option value='3'>03</option>"+
			"		<option value='4'>04</option>"+
			"	    <option value='5'>05</option>"+
			"		<option value='6'>06</option>"+
			"		<option value='7'>07</option>"+
			"		<option value='8'>08</option>"+
			"		<option value='9'>09</option>"+
			"		<option value='10'>10</option>"+
			"	 </select>"+
			"</p>"+
			"</fieldset>";

	$("#reservas").append(cHtml);
}
	
</script>
</head>
<body>
          <form id="form-reservas" action='<c:url value="/reservas/parametrosDetalhes"/>' method="post">
            <div class="reservas-box01">
            		<h3>Escolha as datas de sua Reserva</h3>
            		<fieldset>
            		<p>
            			 <label>Número de Quartos:</label>
		            	 <select name="parametrosReserva.numeroDeQuartos" id="numeroQuartos">
							<option value="1" <c:if test="${1 == reservasView.numeroDeQuartos}">selected="true"</c:if>>01</option>
							<option value="2" <c:if test="${2 == reservasView.numeroDeQuartos}">selected="true"</c:if>>02</option>
							<option value="3" <c:if test="${3 == reservasView.numeroDeQuartos}">selected="true"</c:if>>03</option>
							<option value="4" <c:if test="${4 == reservasView.numeroDeQuartos}">selected="true"</c:if>>04</option>
		                    <option value="5" <c:if test="${5 == reservasView.numeroDeQuartos}">selected="true"</c:if>>05</option>
							<option value="6" <c:if test="${6 == reservasView.numeroDeQuartos}">selected="true"</c:if>>06</option>
							<option value="7" <c:if test="${7 == reservasView.numeroDeQuartos}">selected="true"</c:if>>07</option>
							<option value="8" <c:if test="${8 == reservasView.numeroDeQuartos}">selected="true"</c:if>>08</option>
							<option value="9" <c:if test="${9 == reservasView.numeroDeQuartos}">selected="true"</c:if>>09</option>
							<option value="10" <c:if test="${10 == reservasView.numeroDeQuartos}">selected="true"</c:if>>10</option>
						 </select>
            		</p>
           			<p class="half">
           			  <input type="text" placeholder="Data de Chegada"  name="parametrosReserva.chegada" id="parametrosReserva.chegada" value="<fmt:formatDate value='${reservasView.chegada}' pattern='dd/MM/yyyy'/>" />
           			</p>
           			<p class="half">
           			  <input type="text" placeholder="Data de Saída" name="parametrosReserva.saida" id="parametrosReserva.saida" value="<fmt:formatDate value='${reservasView.saida}' pattern='dd/MM/yyyy'/>"/>
           			</p>
           			<div id="reservas">
           			  <c:choose>
           			  		<c:when test="${empty reservasView.parametrosReserva || empty reservasView.parametrosReserva.detalhes }">
		           			 <fieldset class="molduraLegenda">
		           			    <legend>Reserva n. 1</legend>
		  	           			<p class="half">
			            				<select name="parametrosReserva.detalhes[0].numeroAdultos">
											<option value="">Nº de adultos</option>
											<option value="0">0</option>
											<option value="1">01</option>
											<option value="2">02</option>
											<option value="3">03</option>
											<option value="4">04</option>
						                    <option value="5">05</option>
											<option value="6">06</option>
											<option value="7">07</option>
											<option value="8">08</option>
											<option value="9">09</option>
											<option value="10">10</option>
										</select>
			            		</p>
			            		<p class="half">
			            				<select name="parametrosReserva.detalhes[0].numeroCriancas0a5">
											<option value="">Nº de crianças (0-5)</option>
											<option value="0">0</option>
											<option value="1">01</option>
											<option value="2">02</option>
											<option value="3">03</option>
											<option value="4">04</option>
						                    <option value="5">05</option>
											<option value="6">06</option>
											<option value="7">07</option>
											<option value="8">08</option>
											<option value="9">09</option>
											<option value="10">10</option>
										</select>
			            		</p>
			            		<p class="half">
			            				<select name="parametrosReserva.detalhes[0].numeroCriancas6a16">
											<option value="">Nº de crianças (6-16)</option>
											<option value="0">0</option>
											<option value="1">01</option>
											<option value="2">02</option>
											<option value="3">03</option>
											<option value="4">04</option>
						                    <option value="5">05</option>
											<option value="6">06</option>
											<option value="7">07</option>
											<option value="8">08</option>
											<option value="9">09</option>
											<option value="10">10</option>
										</select>
			            		</p>
				            	<p class="half">
						            	 <select name="parametrosReserva.detalhes[0].numeroCriancas17a18">
											<option value="">Nº de crianças (16-18)</option>
											<option value="0">0</option>
											<option value="1">01</option>
											<option value="2">02</option>
											<option value="3">03</option>
											<option value="4">04</option>
						                    <option value="5">05</option>
											<option value="6">06</option>
											<option value="7">07</option>
											<option value="8">08</option>
											<option value="9">09</option>
											<option value="10">10</option>
										 </select>
				            	</p>
				              </fieldset>
			              </c:when>
			              <c:otherwise>
			                    <c:forEach var="detalhe" items="${ reservasView.parametrosReserva.detalhes }" varStatus="idx" >
				              		<fieldset class="molduraLegenda">
			           			    <legend>Reserva n. ${idx.index+1}</legend>
			  	           			<p class="half">
				            				<select name="parametrosReserva.detalhes[${idx.index}].numeroAdultos">
												<option value="">Nº de adultos</option>
												<option value="0" <c:if test="${0 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>0</option>
												<option value="1" <c:if test="${1 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>01</option>
												<option value="2" <c:if test="${2 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>02</option>
												<option value="3" <c:if test="${3 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>03</option>
												<option value="4" <c:if test="${4 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>04</option>
							                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>05</option>
												<option value="6" <c:if test="${6 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>06</option>
												<option value="7" <c:if test="${7 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>07</option>
												<option value="8" <c:if test="${8 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>08</option>
												<option value="9" <c:if test="${9 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>09</option>
												<option value="10" <c:if test="${10 == reservasView.parametrosReserva.detalhes[idx.index].numeroAdultos}">selected="true"</c:if>>10</option>
											</select>
				            		</p>
				            		<p class="half">
				            				<select name="parametrosReserva.detalhes[${idx.index}].numeroCriancas0a5">
												<option value="">Nº de crianças (0-5)</option>
												<option value="0" <c:if test="${0 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>0</option>
												<option value="1" <c:if test="${1 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>01</option>
												<option value="2" <c:if test="${2 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>02</option>
												<option value="3" <c:if test="${3 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>03</option>
												<option value="4" <c:if test="${4 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>04</option>
							                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>05</option>
												<option value="6" <c:if test="${6 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>06</option>
												<option value="7" <c:if test="${7 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>07</option>
												<option value="8" <c:if test="${8 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>08</option>
												<option value="9" <c:if test="${9 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>09</option>
												<option value="10" <c:if test="${10 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas0a5}">selected="true"</c:if>>10</option>
											</select>
				            		</p>
				            		<p class="half">
				            				<select name="parametrosReserva.detalhes[${idx.index}].numeroCriancas6a16">
												<option value="">Nº de crianças (6-16)</option>
												<option value="0" <c:if test="${0 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>0</option>
												<option value="1" <c:if test="${1 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>01</option>
												<option value="2" <c:if test="${2 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>02</option>
												<option value="3" <c:if test="${3 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>03</option>
												<option value="4" <c:if test="${4 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>04</option>
							                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>05</option>
												<option value="6" <c:if test="${6 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>06</option>
												<option value="7" <c:if test="${7 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>07</option>
												<option value="8" <c:if test="${8 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>08</option>
												<option value="9" <c:if test="${9 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>09</option>
												<option value="10" <c:if test="${10 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas6a16}">selected="true"</c:if>>10</option>
											</select>
				            		</p>
					            	<p class="half">
							            	 <select name="parametrosReserva.detalhes[${idx.index}].numeroCriancas17a18">
												<option value="">Nº de crianças (16-18)</option>
												<option value="0" <c:if test="${0 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>0</option>
												<option value="1" <c:if test="${1 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>01</option>
												<option value="2" <c:if test="${2 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>02</option>
												<option value="3" <c:if test="${3 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>03</option>
												<option value="4" <c:if test="${4 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>04</option>
							                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>05</option>
												<option value="6" <c:if test="${6 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>06</option>
												<option value="7" <c:if test="${7 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>07</option>
												<option value="8" <c:if test="${8 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>08</option>
												<option value="9" <c:if test="${9 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>09</option>
												<option value="10" <c:if test="${10 == reservasView.parametrosReserva.detalhes[idx.index].numeroCriancas17a18}">selected="true"</c:if>>10</option>
											 </select>
					            	</p>
					              </fieldset>
				              </c:forEach>
			              </c:otherwise>
		             </c:choose>
		            </div>
            	<p>
            	   <input type="submit" value="Enviar" name="Enviar"/>
            	</p>
            	
            	<div class="clear"></div>
            	</fieldset>
            	<div class="clear"></div>
	</div>	
  </form>
      <div class="reservas-box02">
            <h3>Escolha um quarto para sua Reserva</h3>
            <fieldset>
            	<div class="places">
          	    		
          	    	<c:forEach var="error" items="${errors}">
          	    		<article>
						<div class="alert">
							${error.message}
						</div>
						</article>
					</c:forEach>
            		
			</div>
            </fieldset>
            <div class='clear'></div>
       </div>
</body>
</html>
