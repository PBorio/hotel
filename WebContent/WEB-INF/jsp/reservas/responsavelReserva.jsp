<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Políticas de Preços</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/datepicker.css'/>" />
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
			<div class="header">
				<ul class="nav nav-pills pull-right">
					<li class="active"><a href="/">Limpar</a></li>
				</ul>
			</div>
		  <form class="form-horizontal" action='<c:url value="/reservas/confirmar"/>' method="post">
		  	<fieldset>
				<legend>Responsável pela Reserva</legend>
			     <input type="hidden" name="hospede.id" value="${hospede.id}" />
			     <div class="form-group">
						<label class="control-label col-xs-2">Nome:</label>
						<div class="col-xs-10">
							<input id="nome" type="text" class="col-xs-10" name="hospede.nome" value="${hospede.nome}" />
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Sobrenome:</label>
						<div class="col-xs-10">
							<input id="nome" type="text" class="col-xs-10" name="hospede.sobrenome" value="${hospede.sobrenome}" />
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Email:</label>
						<div class="col-xs-10">
							<input id="email" type="text" class="col-xs-10" name="hospede.email" value="${hospede.email}" />
						</div>
				 </div>
				<div class="form-group">
					<label class="control-label col-xs-2">Cidade:</label>
					<div class="col-xs-10">
						<input id="cidade" type="text" class="col-xs-10" name="hospede.cidade" value="${hospede.cidade}" />
					</div>
				</div>
				<div class="form-group">
						<label class="control-label col-xs-2">Telefone:</label>
						<div class="col-xs-10">
							<input id="telefone" type="text" class="col-xs-10" name="hospede.telefone" value="${hospede.telefone}" />
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Celular:</label>
						<div class="col-xs-10">
							<input id="celular" type="text" class="col-xs-10" name="hospede.celular" value="${hospede.celular}" />
						</div>
				  </div> 
				 	<div class="control-group col-xs-2">
						<label class="control-label" for="singlebutton"></label>
						<div class="col-xs-10">
							<button id="singlebutton" name="singlebutton" class="btn btn-primary">
								Salvar
							</button>
						</div>
					</div>
			</fieldset>
		  </form>
	</div>
</body>
</html>