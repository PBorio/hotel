<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
   <title>Painel</title>        
   <link rel="stylesheet" href="<c:url value='/resources/css/kanban.css'/>" />
	<script type="text/javascript">                                         
		   jQuery(document).ready(function() {
				jQuery('.quarto').hover(mouseOver, mouseOut);
		   });                   
		
		   function mouseOver(){
			 jQuery(this).addClass('over');
		   }   
		   
		   function mouseOut(){
		     jQuery(this).removeClass('over');
		   }
 	</script> 
</head>
<body>
<div id="container1">
	     <c:if test="${not empty mensagem}">
			<div class="alert alert-success">
				${mensagem}
			</div>
		</c:if>
		<c:forEach var="error" items="${errors}">
			<div class="alert alert-danger">
				${error.message}
			</div>
		</c:forEach>
    <div id="painel">
		<c:forEach var="estadia" items="${estadiaList}">
			<div class="quarto"> 
			<a class="shortcut-button" href="<c:url value='/painel/${estadia.id}'/>">
					<span class="numeroQuarto">${estadia.quarto.numero}</span>
			</a>
			</div>
		</c:forEach>
	</div>
</div>
 
</body>
</html>