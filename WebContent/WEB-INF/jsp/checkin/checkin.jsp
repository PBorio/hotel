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
					<li class="active"><a href="<c:url value='/'/>">Home</a></li>
				</ul>
			</div>
			<c:if test="${checkin.mostraResponsavel}">
				<table class="table table-striped table-bordered" id="example"
						cellpadding="0" cellspacing="0" border="0" width="100%">
					<thead> 
						<tr>
							<th class="ui-state-default" width="70%">Informações de Contato do Responsável Pela Reserva</th>
							<th class="ui-state-default" width="30%"></th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>${checkin.reserva.hospede.nomeCompleto} - ${checkin.reserva.hospede.email} - ${checkin.reserva.hospede.telefone}</th>
						   	<th><a href="<c:url value='/checkin/responsavel/estadia/'/>" title="title">Marcar como Hóspede</a></th>
						</tr>
					</tfoot>
				</table>
			</c:if>
			<c:if test="${ not empty checkin.hospedes}">
				<table class="table table-striped table-bordered" id="example"
						cellpadding="0" cellspacing="0" border="0" width="100%">
					<thead> 
						<tr>
							<th class="ui-state-default" width="90%">Hospede</th>
							<th class="ui-state-default" width="10%"></th>
						</tr>
					</thead>
					
					<tfoot>
						<c:forEach var="hospede" items="${checkin.hospedes}">
							<tr id="hospede-${hospede.id}">
								<th>${hospede.nomeCompleto}</th>
								<th><a href="<c:url value='/checkin/remover/${hospede.id}'/>" title="title">Remover</a> 
								</th>
							</tr>
						</c:forEach>
					</tfoot>
				</table>
			</c:if>
			  <form class="form-horizontal" action='<c:url value="/categorias/salva"/>' method="post">
			  	<fieldset>
					<legend>Checkin</legend>
					<div class="form-group">
						<label class="control-label col-xs-2">Quarto:</label>
						<div class="col-xs-10">
							<input id="estadia.quarto.numero" type="text" class="col-xs-10" name="estadia.quarto.numero" value="${checkin.quarto.numero}" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Valor Diária:</label>
						<div class="col-xs-10">
							<input id="estadia.valorDiaria" type="text" class="col-xs-10" name="estadia.valorDiaria" value="${checkin.valorDiaria}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2" >Nome:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.nome" id="hospede.nome" value="${hospede.nome}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2" >Sobrenome:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.sobrenome" id="hospede.sobrenome" value="${hospede.sobrenome}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Email:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.email" id="hospede.email" value="${hospede.email}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">CPF:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.cpf" id="hospede.cpf" value="${hospede.cpf}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">RG:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.rg" id="hospede.rg" value="${hospede.rg}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Endereço:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.endereco" id="hospede.endereco" value="${hospede.endereco}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Telefone:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.telefone" id="hospede.telefone" value="${hospede.telefone}" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-2">Celular:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="hospede.celular" id="hospede.celular" value="${hospede.celular}" />
						</div>
					</div>
					 <div class="form-group">
						<label class="control-label col-xs-2" for="singlebutton"></label>
						<div class="col-xs-10">
							<button type="button" id="outroHospede" name="outroHospede" class="btn btn-primary" onClick="this.form.action='<c:url value="/checkin/salvaEPreparaMaisHospedes"/>';this.form.submit()">
								Cadastrar Outro Hóspede
							</button>
							<button type="button" id="concluir" name="concluir" class="btn btn-primary" onClick="this.form.action='<c:url value="/checkin/salva"/>';this.form.submit()">
								Concluir
							</button>
						</div>
					 </div>
				</fieldset>
			  </form>
			</div>
</body>
</html>