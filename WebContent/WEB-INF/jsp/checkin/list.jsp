<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reservas</title>

</head>

<body>


		<ul class="shortcut-buttons-set">
			<!-- Replace the icons URL's with your own -->

			<li><a class="shortcut-button"
				href="<c:url value='/quartos/novo'/>"><span> <img class="novo" src="../resources/imagens/icons/novo32.png"/><br />
						Sem Reserva
				</span></a></li>
		</ul>
		<!-- End .shortcut-buttons-set -->
		<div class="clear"></div>
		<!-- End .clear -->
		
		<br>


			<div class="content-box-header">
				<c:if test="${not empty mensagem}">
					<p class="mensagem">
						${mensagem}
					</p>
				</c:if>
				<h3>Reservas</h3>

				<div class="clear"></div>

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
					
			<table class="table table-bordered data-table dataTable">
	
				<thead>
					<tr>
						<th class="ui-state-default" width="20%">Quarto</th>
						<th class="ui-state-default" width="50%">Hóspede</th>
						<th class="ui-state-default" width="30%">Início</th>
					</tr>
	
				</thead>
				
				<tbody>
					<c:forEach var="reserva" items="${reservaList}">
						<tr class="gradeA" id="reserva-${reserva.id}">
							<td class="sorting_1"><a href="<c:url value='/checkin/${reserva.id}'/>" title="title">${reserva.quarto.numero}</a></td>
							<td><a href="<c:url value='/checkin/${reserva.id}'/>" title="title">${reserva.hospede.nome}</a></td>
							<td>${reserva.inicio}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

</body>
</html>