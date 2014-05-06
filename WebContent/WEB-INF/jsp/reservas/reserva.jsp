<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faça sua Reserva</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/datepicker.css'/>" />
<style type="text/css">
	.clear{
	   clear: both;
	}
</style>
</head>
<body>
		<c:if test="${not empty mensagem}">
			<div class="alert alert-success">
				${mensagem}
			</div>
		</c:if>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">
				${erro}
			</div>
		</c:if>
	<div class="container">
		 <div class="row">
	        <div class="col-lg-4">
	             <form class="form-horizontal" action='<c:url value="/reservas/quartosDisponiveis"/>' method="post">
	             	<div class="form-group">
						<label class="col-sm-2">Inicio:</label>
						<div class="col-sm-7">
							<input type="text" style="font-size: 14px;" class="col-sm-10" name="reservasView.chegada" id="reservasView.chegada" value="<fmt:formatDate value='${reservaView.chegada}'/>" />
						</div>
					  </div> 
					  <div class="form-group">
							<label class="col-sm-2">Fim:</label>
							<div class="col-sm-7">
								<input type="text" style="font-size: 14px;" class="col-sm-10" name="reservasView.saida" id="reservasView.saida" value="<fmt:formatDate value='${reservaView.saida}'/>"/>
							</div>
					  </div> 
					  <div class="control-group col-xs-2">
						<label class="control-label" for="singlebutton"></label>
						<div class="col-xs-10">
							<button id="singlebutton" name="singlebutton" class="btn btn-primary">
								Enviar
							</button>
						</div>
					</div>
	             </form>
	        </div>
	        <div class="col-lg-8">
	            <c:forEach var="quarto" items="${quartoList}">
	            	${quarto.valorDaDiaria}
	            	<div class="col-lg-12">
	               	   <div class="col-lg-4"><img alt="${quarto.numero}" src="<c:url value='/resources/imagens/imagensold/home-player.png'/>"></div>
	               	   <div class="col-lg-8">
	               	      <div class="col-md-4">${quarto.numero}</div>
	               	      <div class="col-md-8">${quarto.descricao}</div> 
	               	   </div>
	               	   <div class='clear'></div>
	               </div>
	            </c:forEach>
	        </div>
    	</div>
	</div>

<script src="<c:url value='/resources/scripts/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/scripts/bootstrap-datepicker.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$('#reservasView\\.chegada').datepicker({
			format: 'dd/mm/yyyy'
		});
		$('#reservasView\\.saida').datepicker({
			format: 'dd/mm/yyyy'
		});
	});
</script>
</body>
</html>