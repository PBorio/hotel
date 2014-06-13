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
<link rel="stylesheet" href="<c:url value='/resources/css/kanban.css'/>" />
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
						<label class="col-sm-4" style="font-size: 14px;">Inicio:</label>
						<div class="col-sm-6">
							<input type="text" style="font-size: 14px;" class="col-sm-10" name="parametrosReserva.chegada" id="parametrosReserva.chegada" value="<fmt:formatDate value='${reservasView.chegada}'/>" />
						</div>
					  </div> 
					  <div class="form-group">
							<label class="col-sm-4" style="font-size: 14px;">Fim:</label>
							<div class="col-sm-6">
								<input type="text" style="font-size: 14px;" class="col-sm-10" name="parametrosReserva.saida" id="parametrosReserva.saida" value="<fmt:formatDate value='${reservasView.saida}'/>"/>
							</div>
					  </div> 
					   <div class="form-group">
							<label class="col-sm-4" style="font-size: 14px;">Adultos:</label>
							<div class="col-sm-6">
								<select name="parametrosReserva.numeroAdultos" style="font-size: 14px;">
									<option value="">Nº de adultos</option>
									<option value="0" <c:if test="${0 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>0</option>
									<option value="1" <c:if test="${1 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>01</option>
									<option value="2" <c:if test="${2 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>02</option>
									<option value="3" <c:if test="${3 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>03</option>
									<option value="4" <c:if test="${4 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>04</option>
                                    <option value="5" <c:if test="${5 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>05</option>
									<option value="6" <c:if test="${6 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>06</option>
									<option value="7" <c:if test="${7 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>07</option>
									<option value="8" <c:if test="${8 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>08</option>
									<option value="9" <c:if test="${9 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>09</option>
									<option value="10" <c:if test="${10 == parametrosReserva.numeroAdultos}">selected="true"</c:if>>10</option>
								</select>
							</div>
					  </div> 
					   <div class="form-group">
							<label class="col-sm-4" style="font-size: 14px;">0 a 5 anos:</label>
							<div class="col-sm-6">
								<select name="parametrosReserva.numeroCriancas0a5" style="font-size: 14px;">
									<option value="">Nº de crianças (0-5)</option>
									<option value="0" <c:if test="${0 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>0 ${parametrosReserva.numeroCriancas0a5}</option>
									<option value="1" <c:if test="${1 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>01</option>
									<option value="2" <c:if test="${2 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>02</option>
									<option value="3" <c:if test="${3 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>03</option>
									<option value="4" <c:if test="${4 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>04</option>
                                    <option value="5" <c:if test="${5 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>05</option>
									<option value="6" <c:if test="${6 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>06</option>
									<option value="7" <c:if test="${7 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>07</option>
									<option value="8" <c:if test="${8 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>08</option>
									<option value="9" <c:if test="${9 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>09</option>
									<option value="10" <c:if test="${10 == parametrosReserva.numeroCriancas0a5}">selected="true"</c:if>>10</option>
								</select>
							</div>
					  </div> 
					   <div class="form-group">
							<label class="col-sm-4" style="font-size: 14px;">6 a 16 anos:</label>
							<div class="col-sm-6">
								<select name="parametrosReserva.numeroCriancas6a16" style="font-size: 14px;">
									<option value="">Nº de crianças (6-16)</option>
									<option value="0" <c:if test="${0 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>0</option>
									<option value="1" <c:if test="${1 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>01</option>
									<option value="2" <c:if test="${2 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>02</option>
									<option value="3" <c:if test="${3 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>03</option>
									<option value="4" <c:if test="${4 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>04</option>
                                    <option value="5" <c:if test="${5 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>05</option>
									<option value="6" <c:if test="${6 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>06</option>
									<option value="7" <c:if test="${7 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>07</option>
									<option value="8" <c:if test="${8 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>08</option>
									<option value="9" <c:if test="${9 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>09</option>
									<option value="10" <c:if test="${10 == parametrosReserva.numeroCriancas6a16}">selected="true"</c:if>>10</option>
								</select>
							</div>
					  </div> 
					   <div class="form-group">
							<label class="col-sm-4" style="font-size: 14px;">17 a 18 anos:</label>
							<div class="col-sm-6">
								<select name="parametrosReserva.numeroCriancas17a18" style="font-size: 14px;" >
									<option value="">Nº de crianças (16-18)</option>
									<option value="0" <c:if test="${0 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>0</option>
									<option value="1" <c:if test="${1 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>01</option>
									<option value="2" <c:if test="${2 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>02</option>
									<option value="3" <c:if test="${3 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>03</option>
									<option value="4" <c:if test="${4 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>04</option>
                                    <option value="5" <c:if test="${5 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>05</option>
									<option value="6" <c:if test="${6 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>06</option>
									<option value="7" <c:if test="${7 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>07</option>
									<option value="8" <c:if test="${8 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>08</option>
									<option value="9" <c:if test="${9 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>09</option>
									<option value="10" <c:if test="${10 == parametrosReserva.numeroCriancas17a18}">selected="true"</c:if>>10</option>
								</select>
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
	            	
	            	<div class="col-lg-12 divreserva">
	               	   <div class="col-lg-4"><img alt="${quarto.numero}" src="${linkTo[QuartosController].foto[quarto.id] }"></div>
	               	   <div class="col-lg-8">
	               	      <div class="col-md-4">${quarto.numero}</div>
	               	      <div class="col-md-8">${quarto.descricao}</div> 
	               	      <div class="col-md-4">Valor:</div>
	               	      <div class="col-md-8">${quarto.valorDaDiaria}</div>
	               	      <div class="col-md-12">
		               	      <form class="form-horizontal" action='<c:url value="/reservas/reservar"/>' method="post">
		               	        <input type="hidden" name="reservasView.chegada" value="<fmt:formatDate value='${quarto.inicio}'/>" />
		               	        <input type="hidden" name="reservasView.saida" value="<fmt:formatDate value='${quarto.fim}'/>" />
		               	        <input type="hidden" name="quarto.id" value="${quarto.id}" />
								<button name="singlebutton" class="btn btn-primary">
									Reservar
								</button>
							  </form>
						</div>
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
		$('#parametrosReserva\\.chegada').datepicker({
			format: 'dd/mm/yyyy'
		});
		$('#parametrosReserva\\.saida').datepicker({
			format: 'dd/mm/yyyy'
		});
	});
</script>
</body>
</html>