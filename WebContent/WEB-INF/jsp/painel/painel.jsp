<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
   <title>Painel</title>        
   <link rel="stylesheet" href="<c:url value='/resources/css/kanban.css'/>" />
   <script src=" <c:url value='/resources/scripts/jquery-1.7.1.min.js'/>"></script> 
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

    <div id="painel">
		<c:forEach var="estadia" items="${estadiaList}">
			<a class="shortcut-button" href="<c:url value='/painel/${estadia.id}'/>">
				<div class="quarto"> 
					<span class="numeroQuarto">${estadia.quarto.numero}</span>
				</div>
			</a>
		</c:forEach>
	</div>
</div>
 
</body>
</html>