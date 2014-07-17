<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<c:url value='/resources/css/reservados.css'/>" />
<title>Consulta Reservas</title>
</head>
<body>
  <div id="containerConsulta">
	<table>
	 <thead>
   	 <tr>
   	    <th><a href="<c:url value='/consultas/anterior/${hotelCalendario.mes}/${hotelCalendario.ano}'/>"><img src="<c:url value='/resources/imagens/arrow_left.png'/>"></a></th>
   	    <th colspan="30"><h2>${hotelCalendario.nomeDoMes}/${hotelCalendario.ano}</h2></th>
   	    <th><a href="<c:url value='/consultas/proximo/${hotelCalendario.mes}/${hotelCalendario.ano}'/>"><img src="<c:url value='/resources/imagens/arrow_right.png'/>"></a></th>
   	 </tr>
   	 <tr>
   	 	<th class="registroData"></th>	
   	    <c:forEach var="dia" items="${hotelCalendario.cabecalho.dias}">
   	         <th class="registroData">${dia.texto}</th>
   	    </c:forEach>
     </tr>
     </thead>
    <tbody>
          <c:forEach var="linha" items="${hotelCalendario.linhas}" >
          	<tr>
	            <td>${linha.numeroQuarto}</td>
	            <c:forEach var="dia" items="${linha.dias}">
	              <td class="${dia.texto}"><a href="<c:url value='/consultas/${dia.idReserva}'/>">${dia.texto}</a></td>
	            </c:forEach>
             </tr>
          </c:forEach>
    </tbody>
   </table>
   </div>
</body>
</html>