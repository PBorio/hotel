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
			<p class="mensagem">
				${mensagem}
			</p>
		</c:if>

		<div class="widget-title">
			<span class="icon">
				<i class="icon-align-justify"></i>									
			</span>
			<h5>Checkin</h5>
		</div>
		<div class="widget-box">
			<div class="widget-content nopadding">
			  <form class="form-horizontal" action='<c:url value="/checkin/salva"/>' method="post">
		    		<input type="hidden" name="estadia.id" value="${estadia.id}" />
		    		<input type="hidden" name="hospede.id" value="${hospede.id}" />
					<div class="control-group">
						<label class="control-label">Quarto:</label>
						<div class="controls">
							<input id="estadia.quarto.numero" type="text" name="estadia.quarto.numero" value="${estadia.quarto.numero}" readonly="true" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Hóspede:</label>
						<div class="controls">
							<input type="text" name="hospede.nome" id="hospede.nome" value="${hospede.nome}" readonly="true" />
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
					 <div class="form-actions">
					 	<button type="submit" class="btn btn-primary">Cadastrar Outro Hóspede</button>
						<button type="submit" class="btn btn-primary">Concluir</button>
					</div>
			  </form>
			</div>
		</div>
					
</body>
</html>