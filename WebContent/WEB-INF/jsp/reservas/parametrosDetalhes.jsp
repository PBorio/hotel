<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Detalhes da Reserva</title>
</head>
<body>
            <div class="reservas-box02">
            		<h3>Escolha os quartos de sua Reserva</h3>
            <fieldset>
            	<fieldset class="molduraLegenda">
	          		<legend>Reserva N. ${reservasView.numeroDeQuartosJaSelecionados + 1}</legend>
	           			<p class="half">
	           			  <input type="text" value="<fmt:formatDate value='${reservasView.chegada}' pattern='dd/MM/yyyy'/>" readonly="readonly"/>
	           			</p>
	           			<p class="half">
	           			  <input type="text" value="<fmt:formatDate value='${reservasView.saida}' pattern='dd/MM/yyyy'/>" readonly="readonly"/>
	           			</p>
	           			<p class="half">
	           			  <label>Adultos:</label>
	           			  <input type="text" value="${detalhesDosParametros.numeroAdultos}" readonly="readonly"/>
	           			</p>
	           			<p class="half">
	           			  <label>0 a 5 anos:</label>
	           			  <input type="text" value="${detalhesDosParametros.numeroCriancas0a5}" readonly="readonly"/>
	           			</p>
	           			<p class="half">
	           			  <label>6 a 16 anos:</label>
	           			  <input type="text" value="${detalhesDosParametros.numeroCriancas6a16}" readonly="readonly"/>
	           			</p>
				        <p class="half">
				          <label>17 a 18 anos:</label>
	           			  <input type="text" value="${detalhesDosParametros.numeroCriancas17a18}" readonly="readonly"/>
	           			</p>
				            		
					<div class="clear"></div>
            	</fieldset>            	
			              
			  <c:forEach var="detalhe" items="${reservasView.parametrosReserva.detalhes}">
			    <c:if test="${not empty detalhe.quarto}">
				    <fieldset class="molduraLegenda">
			  	  	  <legend>Quarto já reservado: ${detalhe.quarto.numero}</legend>
					  <p class="half">
							<label>Adultos:</label>
							<input type="text" value="${detalhe.numeroAdultos}" readonly="readonly" />
					  </p>
					  <p class="half">
							<label>0 a 5 anos:</label>
							<input type="text" value="${detalhe.numeroCriancas0a5}" readonly="readonly" />
					  </p>
					  <p class="half">
							<label>6 a 16 anos:</label>
							<input type="text" value="${detalhe.numeroCriancas6a16}" readonly="readonly" />
					  </p>
					  <p class="half">
							<label>17 a 18 anos:</label>
							<input type="text" class="col-xs-10" value="${detalhe.numeroCriancas17a18}" readonly="readonly" />
					  </p>
					  <p>
							<label>Valor:</label>
							<input type="text" class="col-xs-10" value="${reserva.valorReserva}" readonly="readonly" />
					  </p>
					  </fieldset>
					</c:if>
			  </c:forEach>
              <div class="clear"></div>
              </fieldset>
		</div>	
       <div class="reservas-box02">
            <h3>Escolha um quarto para sua Reserva</h3>
            <fieldset>
            	<div class="places">
          	    		
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
