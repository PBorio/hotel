<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>
</head>
<body>
	 
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


		<div class="container">
			<div class="header">
				<ul class="nav nav-pills pull-right">
					<li class="active"><a href="<c:url value='/'/>">Home</a></li>
				</ul>								
			</div>
			<%@include file="../painel/fragmentos/estadia.jspf" %>
			<c:choose>
			<c:when test="${estadia.pendente}">
			<fieldset>
				<legend>Informar Pagamento</legend>
			 		<form class="form-horizontal" action='<c:url value="/checkout/registrar/pagamento"/>' method="post">
			 			<input type="hidden" name="estadia.id" value="${estadia.id}" />
			 	   		<%@include file="../consultas/fragmentos/formPagamento.jspf" %>
					</form>
			</fieldset>
			</c:when>
			<c:otherwise>
					<legend>Fechar</legend>
			 		<form class="form-horizontal" action='<c:url value="/checkout/fechar"/>' method="post">
			 			<input type="hidden" name="estadia.id" value="${estadia.id}" />
			 			<div class="form-actions">
							<button type="submit"class="btn btn-primary">Fechar</button>
					    </div>
					</form>
			</c:otherwise>
			</c:choose>
		</div>

</body>
</html>