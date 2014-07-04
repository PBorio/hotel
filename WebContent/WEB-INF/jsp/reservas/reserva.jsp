<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script type="text/javascript">
  jQuery(document).ready(function(obj) {
	var nContItems = 0;
	$("#numeroQuartos").bind('change', function(self) {
		addConsumos(nContItems);
	});
});

function addConsumos(obj) {
	alert(obj.value);
	cHtml = '<p class="half">'+
			'	<select name="parametrosReserva.numeroAdultos">'+
			'		<option value="">Nº de adultos</option> '+
			'		<option value="0" <c:if test="${0 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>0</option> '+
			'		<option value="1" <c:if test="${1 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>01</option>'+
			'		<option value="2" <c:if test="${2 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>02</option>'+
			'		<option value="3" <c:if test="${3 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>03</option>'+
			'		<option value="4" <c:if test="${4 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>04</option>'+
			'	    <option value="5" <c:if test="${5 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>05</option>'+
			'		<option value="6" <c:if test="${6 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>06</option>'+
			'		<option value="7" <c:if test="${7 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>07</option>'+
			'		<option value="8" <c:if test="${8 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>08</option>'+
			'		<option value="9" <c:if test="${9 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>09</option>'+
			'		<option value="10" <c:if test="${10 == reservasView.parametrosReserva[1].numeroAdultos}">selected="true"</c:if>>10</option>'+
			'	</select>'+
			'</p>'+
			'<p class="half">'+
			'	<select name="parametrosReserva.numeroCriancas0a5">'+
			'		<option value="">Nº de crianças (0-5)</option>'+
			'		<option value="0" <c:if test="${0 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>0</option>'+
			'		<option value="1" <c:if test="${1 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>01</option>'+
			'		<option value="2" <c:if test="${2 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>02</option>'+
			'		<option value="3" <c:if test="${3 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>03</option>'+
			'		<option value="4" <c:if test="${4 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>04</option>'+
			'	    <option value="5" <c:if test="${5 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>05</option>'+
			'		<option value="6" <c:if test="${6 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>06</option>'+
			'		<option value="7" <c:if test="${7 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>07</option>'+
			'		<option value="8" <c:if test="${8 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>08</option>'+
			'		<option value="9" <c:if test="${9 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>09</option>'+
			'		<option value="10" <c:if test="${10 == reservasView.parametrosReserva[1].numeroCriancas0a5}">selected="true"</c:if>>10</option>'+
			'	</select>'+
			'</p>'+
			'<p class="half">'+
			'	<select name="parametrosReserva.numeroCriancas6a16">'+
			'		<option value="">Nº de crianças (6-16)</option>'+
			'		<option value="0" <c:if test="${0 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>0</option>'+
			'		<option value="1" <c:if test="${1 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>01</option>'+
			'		<option value="2" <c:if test="${2 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>02</option>'+
			'		<option value="3" <c:if test="${3 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>03</option>'+
			'		<option value="4" <c:if test="${4 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>04</option>'+
			'	    <option value="5" <c:if test="${5 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>05</option>'+
			'		<option value="6" <c:if test="${6 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>06</option>'+
			'		<option value="7" <c:if test="${7 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>07</option>'+
			'		<option value="8" <c:if test="${8 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>08</option>'+
			'		<option value="9" <c:if test="${9 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>09</option>'+
			'		<option value="10" <c:if test="${10 == reservasView.parametrosReserva[1].numeroCriancas6a16}">selected="true"</c:if>>10</option>'+
			'	</select>'+
			'</p>'+
			'<p class="half">'+
			'	 <select name="parametrosReserva.numeroCriancas17a18">'+
			'		<option value="">Nº de crianças (16-18)</option>'+
			'		<option value="0" <c:if test="${0 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>0</option>'+
			'		<option value="1" <c:if test="${1 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>01</option>'+
			'		<option value="2" <c:if test="${2 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>02</option>'+
			'		<option value="3" <c:if test="${3 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>03</option>'+
			'		<option value="4" <c:if test="${4 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>04</option>'+
			'	    <option value="5" <c:if test="${5 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>05</option>'+
			'		<option value="6" <c:if test="${6 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>06</option>'+
			'		<option value="7" <c:if test="${7 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>07</option>'+
			'		<option value="8" <c:if test="${8 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>08</option>'+
			'		<option value="9" <c:if test="${9 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>09</option>'+
			'		<option value="10" <c:if test="${10 == reservasView.parametrosReserva[1].numeroCriancas17a18}">selected="true"</c:if>>10</option>'+
			'	 </select>'+
			'</p>';

	$("#reservas").append(cHtml);
}
	
</script>
</head>
<body>
          <form id="form-reservas" action='<c:url value="/reservas/quartosDisponiveis"/>' method="post">
            <div class="reservas-box01">
            		<h3>Escolha as datas de sua Reserva</h3>
            		<fieldset>
            		<p>
            			 <label>Número de Quartos:</label>
		            	 <select name="reservasView.numeroDeQuartos" id="numeroQuartos">
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
           			  <input type="text" placeholder="Data de Chegada"  name="reservasView.chegada" id="parametrosReserva.chegada" value="<fmt:formatDate value='${reservasView.chegada}' pattern='dd/MM/yyyy'/>" />
           			</p>
           			<p class="half">
           			  <input type="text" placeholder="Data de Saída" name="reservasView.saida" id="parametrosReserva.saida" value="<fmt:formatDate value='${reservasView.saida}' pattern='dd/MM/yyyy'/>"/>
           			</p>
           			<div id="reservas">
	           			<p class="half">
	            				<select name="reservasView.parametrosReserva[0].numeroAdultos">
									<option value="">Nº de adultos</option>
									<option value="0" <c:if test="${0 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>0</option>
									<option value="1" <c:if test="${1 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>01</option>
									<option value="2" <c:if test="${2 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>02</option>
									<option value="3" <c:if test="${3 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>03</option>
									<option value="4" <c:if test="${4 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>04</option>
				                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>05</option>
									<option value="6" <c:if test="${6 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>06</option>
									<option value="7" <c:if test="${7 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>07</option>
									<option value="8" <c:if test="${8 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>08</option>
									<option value="9" <c:if test="${9 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>09</option>
									<option value="10" <c:if test="${10 == reservasView.parametrosReserva[0].numeroAdultos}">selected="true"</c:if>>10</option>
								</select>
	            		</p>
	            		<p class="half">
	            				<select name="reservasView.parametrosReserva[0].numeroCriancas0a5">
									<option value="">Nº de crianças (0-5)</option>
									<option value="0" <c:if test="${0 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>0</option>
									<option value="1" <c:if test="${1 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>01</option>
									<option value="2" <c:if test="${2 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>02</option>
									<option value="3" <c:if test="${3 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>03</option>
									<option value="4" <c:if test="${4 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>04</option>
				                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>05</option>
									<option value="6" <c:if test="${6 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>06</option>
									<option value="7" <c:if test="${7 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>07</option>
									<option value="8" <c:if test="${8 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>08</option>
									<option value="9" <c:if test="${9 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>09</option>
									<option value="10" <c:if test="${10 == reservasView.parametrosReserva[0].numeroCriancas0a5}">selected="true"</c:if>>10</option>
								</select>
	            		</p>
	            		<p class="half">
	            				<select name="reservasView.parametrosReserva[0].numeroCriancas6a16">
									<option value="">Nº de crianças (6-16)</option>
									<option value="0" <c:if test="${0 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>0</option>
									<option value="1" <c:if test="${1 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>01</option>
									<option value="2" <c:if test="${2 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>02</option>
									<option value="3" <c:if test="${3 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>03</option>
									<option value="4" <c:if test="${4 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>04</option>
				                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>05</option>
									<option value="6" <c:if test="${6 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>06</option>
									<option value="7" <c:if test="${7 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>07</option>
									<option value="8" <c:if test="${8 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>08</option>
									<option value="9" <c:if test="${9 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>09</option>
									<option value="10" <c:if test="${10 == reservasView.parametrosReserva[0].numeroCriancas6a16}">selected="true"</c:if>>10</option>
								</select>
	            		</p>
		            	<p class="half">
				            	 <select name="reservasView.parametrosReserva[0].numeroCriancas17a18">
									<option value="">Nº de crianças (16-18)</option>
									<option value="0" <c:if test="${0 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>0</option>
									<option value="1" <c:if test="${1 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>01</option>
									<option value="2" <c:if test="${2 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>02</option>
									<option value="3" <c:if test="${3 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>03</option>
									<option value="4" <c:if test="${4 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>04</option>
				                    <option value="5" <c:if test="${5 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>05</option>
									<option value="6" <c:if test="${6 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>06</option>
									<option value="7" <c:if test="${7 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>07</option>
									<option value="8" <c:if test="${8 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>08</option>
									<option value="9" <c:if test="${9 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>09</option>
									<option value="10" <c:if test="${10 == reservasView.parametrosReserva[0].numeroCriancas17a18}">selected="true"</c:if>>10</option>
								 </select>
		            	</p>
		            </div>
            	<p>
            	   <input type="submit" value="Enviar" name="Enviar"/>
            	</p>
            	<c:if test="${not empty reservasView.quartos}">
            	<fieldset>
				  	  <legend>Quartos Selecionados</legend>
					  <c:forEach var="quarto" items="${reservasView.quartos}">
					  	<p>
							<input type="text" value="${quarto.numero}" readonly="readonly" />
						</p>
					  </c:forEach>
				  </fieldset>
				</c:if>
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
												
            		<c:forEach var="quarto" items="${quartoList}">
	           		<article>
						<h2>${quarto.numero}</h2>
						<img alt="${quarto.numero}" src="${linkTo[QuartosController].foto[quarto.id] }">
						<div class='clear'></div>
						<p>
							${quarto.descricao}
						</p>
						<p>
						  Valor:${quarto.valorDaDiaria}
						</p>
               	      <form class="form-horizontal" action='<c:url value="/reservas/reservar"/>' method="post">
               	        <input type="hidden" name="reservasView.chegada" value="<fmt:formatDate value='${quarto.inicio}'/>" />
               	        <input type="hidden" name="reservasView.saida" value="<fmt:formatDate value='${quarto.fim}'/>" />
               	        <input type="hidden" name="quarto.id" value="${quarto.id}" />
               	        <p>
							<input type="submit" name="Reservar" value="Enviar"/>
						</p>
					  </form>
						<div class='clear'></div>
					</article>
            	</c:forEach>
			</div>
            </fieldset>
            <div class='clear'></div>
       </div>
</body>
</html>
