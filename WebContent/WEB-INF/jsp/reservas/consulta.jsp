<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta Reservas</title>
</head>
<body>
  <div id="containerConsulta">
	<table>
   	 <tr>
   	    <td>
   	    </td>
	   	<c:forEach var="quarto" items="${statusDeReservaNoDiaList[0].quartos}">
			<td class="colunaQuarto">
			  ${quarto.numero}
			</td>	      
	    </c:forEach>
     </tr>
     <c:forEach var="status" items="${statusDeReservaNoDiaList}">
     	<tr>
     	  <td <c:if test="${status.fimDeSemana}">class="registroData"</c:if>
     	      <c:if test="${not status.fimDeSemana}">class="registroFimSemana"</c:if> >
     	    <joda:format value="${status.dia}" pattern="E, dd/MM/yyyy"/>
     	  </td>
     	  <c:forEach var="statusQuarto" items="${status.statusQuartos}">
     	    <td class="${statusQuarto.tipoStatusNaData.description}">
     	      <c:if test="${statusQuarto.reserva.id == null}">${statusQuarto.tipoStatusNaData.description}</c:if>
     	      <c:if test="${statusQuarto.reserva.id != null}"><a href="<c:url value='/reservas/${statusQuarto.reserva.id}'/>">${statusQuarto.tipoStatusNaData.description}</a></li></c:if>
     	    </td>
     	  </c:forEach>
     	</tr>
     </c:forEach>

   </table>
   </div>
</body>
</html>