<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reservas</title>

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
					<li class="active"><a href="<c:url value='/quartos/novo'/>">Sem Reserva</a></li>
					<li class="active"><a href="<c:url value='/'/>">Home</a></li>
				</ul>
				<h3 class="text-muted">Checkin</h3>
			</div>

			<!-- End .content-box-header -->
			<div class="widget-box">
				<div class="widget-content nopadding">
					<form action="<c:url value='/checkin/pesquisar'/>" class="form-horizontal">
				     <div class="control-group">
						<label class="control-label">Nome do Hóspede:</label>
						<div class="controls">
						    <input type="text" name="pesquisa">
							<input value="Pesquisar" type="submit" class="button" id="btnPesquisa" >
						</div>
					</div>
					
					</form>
				</div>
			</div>
					
			<table class="table table-striped table-bordered" id="example"
					cellpadding="0" cellspacing="0" border="0" width="100%">
				<thead> 
					<tr>
						<th class="ui-state-default" width="20%">Quartos</th>
						<th class="ui-state-default" width="50%">Hóspede</th>
						<th class="ui-state-default" width="30%">Início</th>
					</tr>
	
				</thead>
				
				<tfoot>
					<c:forEach var="reserva" items="${reservaList}">
					    <c:forEach var="quarto" items="${reserva.quartos}">
							<tr class="gradeA" id="reserva-${reserva.id}">
								<td class="sorting_1"><a href="<c:url value='/checkin/${reserva.id}/${quarto.id }'/>" title="title">${quarto.numero}</a></td>
								<td><a href="<c:url value='/checkin/${reserva.id}/${quarto.id }'/>" title="title">${reserva.hospede.nome}</a></td>
								<td>${reserva.inicio}</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</tfoot>
			</table>
		</div>
</body>
</html>