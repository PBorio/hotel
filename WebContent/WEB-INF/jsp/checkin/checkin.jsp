<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkin</title>
<script type="text/javascript">
	jQuery(document).ready(function() {
		  
		$("#divHospede").hide();
	    mudarTipoPagamento();	
	});
	
	function mostrarHospede(){
		$("#divHospede").show();
	}
</script>
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
					<li class="active"><a href="<c:url value='/painel'/>">Home</a></li>
				</ul>
				<h3 class="text-muted">Checkin</h3>
			</div>
		
			<fieldset>
				<legend>Quarto</legend>
				<table class="table table-striped table-bordered" id="example"
							cellpadding="0" cellspacing="0" border="0" width="100%">
						<thead> 
							<tr>
								<th class="ui-state-default" width="100%">Quarto</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
							   	<th>${checkin.quarto.numero}</th>
							</tr>
						</tfoot>
				</table>
			</fieldset>
			<c:if test="${checkin.mostraResponsavel}">
			<fieldset>
				<legend>Informações de Contato do Responsável</legend>
				<table class="table table-striped table-bordered" id="example"
						cellpadding="0" cellspacing="0" border="0" width="100%">
					<tfoot>
						<tr>
							<th>${checkin.reserva.hospede.nomeCompleto} - ${checkin.reserva.hospede.email} - ${checkin.reserva.hospede.telefone}</th>
						   	<th><a href="<c:url value='/checkin/responsavel/estadia/'/>" title="title">Marcar como Hóspede</a></th>
						</tr>
					</tfoot>
				</table>
				</fieldset>
			</c:if>
			<c:if test="${ not empty checkin.hospedes}">
			<fieldset>
				<legend>Hospedes Já Registrados</legend>
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
				</fieldset>
			</c:if>
			 <form class="form-horizontal" method="post">
			<div class="form-group">
				<label class="control-label col-xs-2" for="singlebutton"></label>
				<div class="col-xs-10">
					<button type="button" id="outroHospede" name="outroHospede" class="btn btn-primary" onClick="mostrarHospede();">
						Cadastrar Outro Hóspede
					</button>
					<button type="button" id="concluir" name="concluir" class="btn btn-primary" onClick="this.form.action='<c:url value="/checkin/continua/para/valores"/>';this.form.submit()">
						Ir Para Datas e Valores
					</button>
				</div>
			</div>
			</form>
			<div id="divHospede">
			  <form class="form-horizontal" action='<c:url value="/checkin/novo/hospede"/>' method="post">
			  	<fieldset>
					<legend>Checkin</legend>
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
							<button type="submit" id="concluir" name="concluir" class="btn btn-primary">
								Salvar
							</button>
						</div>
					 </div>
				</fieldset>
			  </form>
			</div>
		</div>
</body>
</html>