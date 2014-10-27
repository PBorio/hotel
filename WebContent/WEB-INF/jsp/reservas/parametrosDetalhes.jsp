<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Detalhes da Reserva</title>
</head>
<body>

<section id="reservation-form">
    <div class="container">
    
	    <div class="row">
	        <div class="col-md-12">
	          <div class="form-inline reservation-horizontal clearfix">
		        <fieldset>
		        <legend>Reserva N. ${reservasView.numeroDeQuartosJaSelecionados + 1}</legend>
	            <div class="row">
	                <div class="col-sm-6">
		              <div class="form-group">
		                <label for="checkin">Check-in</label>
		                <input type="text" value="<fmt:formatDate value='${reservasView.chegada}' pattern='dd/MM/yyyy'/>" class="form-control" readonly="readonly"/>
		              </div>
		            </div>
		            <div class="col-sm-6">
		              <div class="form-group">
		                <label for="checkin">Check-out</label>
		                 <input type="text" class="form-control" value="<fmt:formatDate value='${reservasView.saida}' pattern='dd/MM/yyyy'/>" readonly="readonly"/>
		              </div>
		            </div>
		           </div>
		           <div class="row">
	              <div class="col-sm-3">
		              <div class="form-group">
		                <label>Adultos:</label>
	           			<input type="text" value="${detalhesDosParametros.numeroAdultos}" class="form-control" readonly="readonly"/>		      
		              </div>
	              </div>
	              <div class="col-sm-3">
		              <div class="form-group">
		                <label>Crianças (0 a 5):</label>
	           			<input type="text" value="${detalhesDosParametros.numeroCriancas0a5}" class="form-control" readonly="readonly"/>		      
		              </div>
	              </div>
	              <div class="col-sm-3">
		              <div class="form-group">
		                <label>Crianças (6 a 16):</label>
	           			<input type="text" value="${detalhesDosParametros.numeroCriancas6a16}" class="form-control" readonly="readonly"/>		      
		              </div>
	              </div>
	              <div class="col-sm-3">
		              <div class="form-group">
		                <label>Crianças (17 e 18):</label>
	           			<input type="text" value="${detalhesDosParametros.numeroCriancas17a18}" class="form-control" readonly="readonly"/>		      
		              </div>
	              </div>
	              </div>
	            
	            </fieldset>
	          </div>
	        </div>
	     </div>
    	  <div class="row">
    	     <c:forEach var="detalhe" items="${reservasView.parametrosReserva.detalhes}">
    	       <c:if test="${not empty detalhe.quarto}">
			        <div class="col-md-12">
			          <div class="form-inline reservation-horizontal clearfix">
			           <fieldset class="molduraLegenda">
		          		 <legend>Quarto já reservado: ${detalhe.quarto.descricao}</legend>
		          		 <div class="row">
			                <div class="col-sm-3">
				              <div class="form-group">
				              	<label>Adultos:</label>
	           					<input type="text" value="${detalhe.numeroAdultos}" class="form-control" readonly="readonly"/>	
				              </div>
				             </div>
				             <div class="col-sm-3">
					              <div class="form-group">
					                <label>Crianças:</label>
				           			<input type="text" value="${detalhe.numeroCriancas0a5}" class="form-control" readonly="readonly"/>		      
					              </div>
				              </div>
				         </div>
		          	   </fieldset>
			          </div>
			        </div>
			    </c:if>  
		     </c:forEach>
	      </div>
    </div>
</section>


<section class="rooms mt100">
  <div class="container">
    <div class="row room-list fadeIn appear"> 
      <c:forEach var="quarto" items="${quartoList}">
      		 <!-- Room -->
		      <div class="col-sm-4 single">
		        <div class="room-thumb"> <img alt="${quarto.numero}" src="${linkTo[QuartosController].foto[quarto.id]}" class="img-responsive" />
		          <div class="mask">
		            <div class="main">
		              <h5>${quarto.categoria}</h5>
		              <div class="price">&real;$ ${quarto.valorDaDiaria}<span>a diária</span></div>
		            </div>
		            <div class="content">
		              <p><span>${quarto.descricao}</span> ${quarto.observacao}</p>
		              
		              <form class="form-horizontal" action='<c:url value="/reservas/reservar"/>' method="post">
               	        <input type="hidden" name="reservasView.chegada" value="<fmt:formatDate value='${quarto.inicio}'/>" />
               	        <input type="hidden" name="reservasView.saida" value="<fmt:formatDate value='${quarto.fim}'/>" />
               	        <input type="hidden" name="quarto.id" value="${quarto.id}" />
               	        <p>
							<button type="submit" class="btn btn-primary btn-block">Reservar</button>
						</p>
					  </form>
		          </div>
		        </div>
		      </div>
		     </div>
      </c:forEach>
    </div>
  </div>
</section>
</body>
</html>