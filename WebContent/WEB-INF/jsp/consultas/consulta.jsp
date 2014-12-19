<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<c:url value='/resources/css/reservados.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/fullcalendar.css'/>" />
<title>Consulta Reservas</title>
</head>
<body>
  <div class="container" id="containerConsulta">
  		<c:forEach var="error" items="${errors}">
		<div class="alert alert-danger">
			${error.message}
		</div>
	</c:forEach>
    <c:if test="${not empty reservasInternasView.reservas}">
  		<div class="row">
			 <div class="col-xs-12 col-md-12 col-lg-12">
	                  <div class="panel panel-default height">
	                      <div class="panel-heading">Reservas Pendentes</div>
	                      <div class="panel-body">
	                       	 <c:forEach var="reserva" items="${reservasInternasView.reservas}">
	                          <strong>Reserva: </strong> ${reserva.quarto.numero} - A Partir de <joda:format value="${reserva.inicio}" pattern="dd/MM/yyyy"/><br>
	                         </c:forEach>
	                       <div class="header">
							<ul class="nav nav-pills pull-right">
								<li class="active"><a href="<c:url value='/reserva/interna'/>">Confirmar</a></li>
								<li class="active"><a href="<c:url value='/limpar/nova/reserva/${hotelCalendario.primeiraSegundaFeira}/${hotelCalendario.mesAtual}/${hotelCalendario.anoAtual}'/>">Limpar</a></li>
							</ul>
						</div>
                      </div>
                  </div>
              </div>
		</div>
	</c:if>
	<table>
	 <thead>
   	 <tr>
   	    <th><a href="<c:url value='/consultas/anterior/${hotelCalendario.primeiraSegundaFeira}/${hotelCalendario.mesAtual}/${hotelCalendario.anoAtual}'/>"><img src="<c:url value='/resources/imagens/arrow_left.png'/>"></a></th>
   	    <th class="nomeMes" colspan="${hotelCalendario.diasRestantesMesAtual}"><h3>${hotelCalendario.nomeDoMesAtual}/${hotelCalendario.anoAtual}</h3></th>
   	    <c:if test="${hotelCalendario.diasRestantesProximoMes > 0}">
   	    	<th class="nomeMes" colspan="${hotelCalendario.diasRestantesProximoMes}"><h3>${hotelCalendario.nomeDoProximoMes}/${hotelCalendario.proximoAno}</h3></th>
   	    </c:if>
   	    <th><a href="<c:url value='/consultas/proximo/${hotelCalendario.primeiraSegundaFeira}/${hotelCalendario.mesAtual}/${hotelCalendario.anoAtual}'/>"><img src="<c:url value='/resources/imagens/arrow_right.png'/>"></a></th>
   	 </tr>
   	 <tr>
   	 	<th class="registroData fc-widget-header"></th>	
   	    <c:forEach var="dia" items="${hotelCalendario.cabecalho.dias}">
   	         <th <c:choose>
   	               <c:when test="${dia.hoje}">class="fc-widget-header destaque"</c:when>
   	               <c:when test="${dia.fimDeSemana}">class="fimDeSemana fc-widget-header"</c:when>
   	               <c:otherwise>class="registroData fc-widget-header"</c:otherwise>
   	             </c:choose> > 
   	             ${dia.texto}</th>
   	    </c:forEach>
   	    <th class="registroData fc-widget-header"></th>
     </tr>
     </thead>
    <tbody>
          <c:forEach var="linha" items="${hotelCalendario.linhas}" >
          	<tr>
	            <td class="fc-widget-header">${linha.numeroQuarto}</td>
	            <c:forEach var="dia" items="${linha.dias}">
	              <c:choose>
	              	<c:when test="${empty dia.idReserva}">
	              	  <td class="fc-widget-content">
	              		<a href="<c:url value='/nova/reserva/${dia.quarto.id}/${dia.diaDoMes}/${dia.mes}/${dia.ano}/${hotelCalendario.primeiraSegundaFeira}/${hotelCalendario.mesAtual}/${hotelCalendario.anoAtual}'/>">${dia.texto}</a>
	              	  </td>
	              	</c:when>
	              	<c:otherwise>
	              	    <td class="fc-widget-content ${dia.marcacao}">
	              	        <a href="<c:url value='/pagamento/${dia.idReserva}'/>">${dia.texto}</a>
	              	    </td>
	              	</c:otherwise>
	              </c:choose>
	            </c:forEach>
	            <td class="fc-widget-header">${linha.numeroQuarto}</td>
             </tr>
          </c:forEach>
    </tbody>
   </table>
   </div>
</body>
</html>