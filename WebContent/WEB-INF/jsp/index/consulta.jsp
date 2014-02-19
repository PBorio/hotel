 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
 <head>
 </head>
 <body>
   <table>
   	 <tr>
   	    <th>
   	    </th>
	   	<c:forEach var="quarto" items="${statusDeReservaNoDiaList[0].quartos}">
			<th>
			  ${quarto.numero}
			</th>	      
	    </c:forEach>
     </tr>
     <c:forEach var="status" items="${statusDeReservaNoDiaList}">
     	<tr>
     	  <td>
     	    ${status.dia}
     	  </td>
     	  <c:forEach var="tipo" items="${status.statusDosQuartos}">
     	    <td>
     	      ${tipo.description}
     	    </td>
     	  </c:forEach>
     	</tr>
     </c:forEach>

   </table>
  </body>
</html>
<%--     <c:forEach var="quarto" items="${statusDeReservaPorDiaList[0].quartos}"> --%>
      
<%--     </c:forEach> --%>
<!--     <div id="colEntrada"> -->
<!-- 		<h4>Entrada</h4> -->
<%-- 		<c:forEach var="postIt" items="${listChamadosNaEntrada}"> --%>
<%-- 		  <a class="link" href="javascript:void(window.location = 'scaandamento.list.logic?numeroChamado=${postIt.numeroChamado}')"> --%>
<%-- 			<div class="${postIt.tipo}">  --%>
<%-- 				<span class="nrChamado">${postIt.numeroChamado}</span></br> --%>
<%-- 			  <span>${postIt.siglaSistema}</span></br> --%>
<%-- 			<span class="negrito">${postIt.solicitante}</span></br> --%>
<%-- 		  <span>${postIt.descricao}</span> --%>
<!-- 			</div> -->
<!-- 			</a> -->
<%-- 		</c:forEach> --%>
<!-- 	</div> -->
<!--     <div id="colSuporte"> -->
<!-- 	  <h4>Suporte</h4> -->
<%-- 		<c:forEach var="postIt" items="${listChamadosNoSuporte}"> --%>
<%-- 		<a class="link" href="javascript:void(window.location = 'scaandamento.list.logic?numeroChamado=${postIt.numeroChamado}')"> --%>
<%-- 			<div class="${postIt.tipo}">  --%>
<%-- 			<span class="nrChamado">${postIt.numeroChamado}</span></br> --%>
<%-- 			<span>${postIt.siglaSistema}</span></br> --%>
<%-- 			<span class="negrito">${postIt.solicitante}</span></br> --%>
<%-- 		  <span>${postIt.descricao}</span> --%>
<!-- 		</div> -->
<!-- 		</a> -->
<%-- 		</c:forEach> --%>
<!-- 	</div> -->
<!--   <div id="colPrioridade"> -->
<!-- 	   <h4>Priorização</h4> -->
<%-- 	   <c:forEach var="postIt" items="${listChamadosNaPriorizacao}"> --%>
<%-- 	   <a class="link" href="javascript:void(window.location = 'scaandamento.list.logic?numeroChamado=${postIt.numeroChamado}')"> --%>
<%-- 			<div class="${postIt.tipo}">  --%>
<%-- 			  <span class="nrChamado">${postIt.numeroChamado}</span></br> --%>
<%-- 		    <span>${postIt.siglaSistema}</span></br> --%>
<%-- 			<span class="negrito">${postIt.solicitante}</span></br> --%>
<%-- 		  <span>${postIt.descricao}</span> --%>
<!-- 		  </div> -->
<!-- 		 </a> -->
<%-- 		</c:forEach> --%>
<!-- 	</div> -->
<!-- 	<div id="colDesenv"> -->
<!-- 	  <h4>Desenvolvimento</h4> -->
<%-- 	  <c:forEach var="postIt" items="${listChamadosNoDesenvolvimento}"> --%>
<%-- 	  <a class="link" href="javascript:void(window.location = 'scaandamento.list.logic?numeroChamado=${postIt.numeroChamado}')"> --%>
<%-- 		  <div class="${postIt.tipo}">  --%>
<%-- 			  <span class="nrChamado">${postIt.numeroChamado}</span></br> --%>
<%-- 		   <span>${postIt.siglaSistema}</span></br> --%>
<%-- 			<span class="negrito">${postIt.solicitante}</span></br> --%>
<%-- 		  <span>${postIt.descricao}</span> --%>
<!-- 		  </div> -->
<!-- 		 </a> -->
<%-- 		</c:forEach> --%>
<!-- 	</div> -->
<!-- 	<div id="colTeste"> -->
<!-- 	  <h4>Testes</h4> -->
<%-- 	  <c:forEach var="postIt" items="${listChamadosEmTeste}"> --%>
<%-- 	  <a class="link" href="javascript:void(window.location = 'scaandamento.list.logic?numeroChamado=${postIt.numeroChamado}')"> --%>
<%-- 		  <div class="${postIt.tipo}">  --%>
<%-- 			  <span class="nrChamado">${postIt.numeroChamado}</span></br> --%>
<%-- 		    <span>${postIt.siglaSistema}</span></br> --%>
<%-- 			<span class="negrito">${postIt.solicitante}</span></br> --%>
<%-- 		  <span>${postIt.descricao}</span> --%>
<!-- 		  </div> -->
<!-- 		  </a> -->
<%-- 		</c:forEach> --%>
<!-- 	</div> -->
<!-- 	<div id="colVersao"> -->
<!-- 	  <h4>Aguardando Versão</h4> -->
<%-- 	   <c:forEach var="postIt" items="${listChamadosResolvidos}"> --%>
<%-- 	   <a class="link" href="javascript:void(window.location = 'scaandamento.list.logic?numeroChamado=${postIt.numeroChamado}')"> --%>
<%-- 		  <div class="${postIt.tipo}">  --%>
<%-- 			  <span class="nrChamado">${postIt.numeroChamado}</span></br> --%>
<%-- 		    <span>${postIt.siglaSistema}</span></br> --%>
<%-- 			<span class="negrito">${postIt.solicitante}</span></br> --%>
<%-- 		  <span>${postIt.descricao}</span> --%>
<!-- 		  </div> -->
<!-- 		 </a> -->
<%-- 		</c:forEach> --%>
<!-- 	</div> -->
<!-- </div> -->
<%--  </jsp:attribute> --%>
<%--   <jsp:attribute name="scripts"> --%>
<%--  		 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/estrutura/css/kanban.css"/> --%>
 		 
<!--  		 <script type="text/javascript">                                          -->
// 		 	jQuery(document).ready(function() {
// 			   		jQuery('.correcao').hover(mouseOver, mouseOut);
// 						jQuery('.melhoria').hover(mouseOver, mouseOut);
// 						jQuery('.duvida').hover(mouseOver, mouseOut);
// 						jQuery('.conversao').hover(mouseOver, mouseOut);
// 			   });                   
	
// 	   function mouseOver(){
// 	     //alert('mouseOver');
// 		 	 jQuery(this).addClass('over');
// 	   }   
	   
// 	   function mouseOut(){
// 		   jQuery(this).removeClass('over');
// 	   }
<!--  </script>      -->
<%--  </jsp:attribute> --%>
<!-- </template:listsemmenu> -->
