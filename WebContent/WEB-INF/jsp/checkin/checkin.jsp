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
		    		<input type="hidden" name="reserva.id" value="${reserva.id}" />
					<div class="control-group">
						<label class="control-label">Quarto:</label>
						<div class="controls">
							<input id="reserva.quarto.numero" type="text" name="reserva.quarto.numero" value="${reserva.quarto.numero}" readonly="true" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Hóspede:</label>
						<div class="controls">
							<input type="text" name="reserva.hospede.nome" id="reserva.hospede.nome" value="${reserva.hospede.nome}" readonly="true" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">CPF:</label>
						<div class="controls">
							<input type="text" name="reserva.hospede.nome" id="reserva.hospede.nome" value="${reserva.hospede.nome}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">RG:</label>
						<div class="controls">
							<input type="text" name="reserva.hospede.nome" id="reserva.hospede.nome" value="${reserva.hospede.nome}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Endereço:</label>
						<div class="controls">
							<input type="text" name="reserva.hospede.nome" id="reserva.hospede.nome" value="${reserva.hospede.nome}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Telefone:</label>
						<div class="controls">
							<input type="text" name="reserva.hospede.nome" id="reserva.hospede.nome" value="${reserva.hospede.nome}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Celular:</label>
						<div class="controls">
							<input type="text" name="reserva.hospede.nome" id="reserva.hospede.nome" value="${reserva.hospede.nome}" />
						</div>
					</div>
					 <div class="form-actions">
						<button type="submit" class="btn btn-primary">Salvar</button>
					</div>
			  </form>
			</div>
		</div>
					
</body>
</html>