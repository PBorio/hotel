<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkin</title>
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

		<div class="widget-title">
			<span class="icon">
				<i class="icon-align-justify"></i>									
			</span>
			<h5>Checkin</h5>
		</div>
		<div class="container">
			<div class="header">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="/">Home</a></li>
			</ul>
			</div>
			  <form class="form-horizontal" action='<c:url value="/categorias/salva"/>' method="post">
			  	<fieldset>
					<legend>Checkin</legend>
		    		<input type="hidden" name="estadia.id" value="${estadia.id}" />
		    		<input type="hidden" name="hospede.id" value="${hospede.id}" />
		    		<input type="hidden" name="estadia.quarto.id" value="${estadia.quarto.id}" />
		    		<input type="hidden" name="estadia.reserva.id" value="${estadia.reserva.id}" />
					<div class="control-group">
						<label class="control-label">Quarto:</label>
						<div class="controls">
							<input id="estadia.quarto.numero" type="text" name="estadia.quarto.numero" value="${estadia.quarto.numero}" readonly="readonly" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Valor da Diária:</label>
						<div class="controls">
							<input id="estadia.valorDiaria" type="text" name="estadia.valorDiaria" value="${estadia.valorDiaria}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Hóspede:</label>
						<div class="controls">
							<input type="text" name="hospede.nome" id="hospede.nome" value="${hospede.nome}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Email:</label>
						<div class="controls">
							<input type="text" name="hospede.email" id="hospede.email" value="${hospede.email}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">CPF:</label>
						<div class="controls">
							<input type="text" name="hospede.cpf" id="hospede.cpf" value="${hospede.cpf}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">RG:</label>
						<div class="controls">
							<input type="text" name="hospede.rg" id="hospede.rg" value="${hospede.rg}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Endereço:</label>
						<div class="controls">
							<input type="text" name="hospede.endereco" id="hospede.endereco" value="${hospede.endereco}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Telefone:</label>
						<div class="controls">
							<input type="text" name="hospede.telefone" id="hospede.telefone" value="${hospede.telefone}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Celular:</label>
						<div class="controls">
							<input type="text" name="hospede.celular" id="hospede.celular" value="${hospede.celular}" />
						</div>
					</div>
					 <div class="control-group">
						<label class="control-label" for="singlebutton"></label>
						<div class="controls">
							<button id="singlebutton" name="singlebutton" class="btn btn-primary">
								Salvar
							</button>
						</div>
						<label class="control-label" for="singlebutton"></label>
						<div class="controls">
							<button type="button" id="outroHospede" name="outroHospede" class="btn btn-primary" onClick="this.form.action='<c:url value="/checkin/salvaEPreparaMaisHospedes"/>';this.form.submit()">
								Cadastrar Outro Hóspede
							</button>
							<button type="button" id="concluir" name="concluir" class="btn btn-primary" onClick="this.form.action='<c:url value="/checkin/salva"/>';this.form.submit()">Concluir</button>
						</div>
					 </div>
				</fieldset>
			  </form>
			</div>
		</div>
					
</body>
</html>