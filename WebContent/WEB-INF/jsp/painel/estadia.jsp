<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estadia</title>


<script type="text/javascript">
	
jQuery(document).ready(function() {
	
	var nContItems = 0;
	$("#adiciona-produto").bind('click', function() {
			addConsumos(false,nContItems);
	        nContItems++;
	});
});

function addConsumos(isRebuilding,nContItems) {
	
	cHtml = '<tr id="row-'+ nContItems + '" class="alt-row-son" >'
	        + '<input id="consumo-id-'+ nContItems +'" type="hidden" name="estadia.consumos[].id"> </input>'
			+ '<input id="produto-id-'+ nContItems +'" type="hidden" name="estadia.consumos[].produto.id"> </input>'
			+ '<td> <input id="descricao-'+ nContItems +'" type="text" class="text-input s70-input" name="estadia.consumos[].produto.descricao"> </input> </td>'
			+ '<td> <input id="quantidade-'+ nContItems +'" type="text" dir="rtl" class="text-input s70-input" name="estadia.consumos[].quantidade" > </input> </td>'
			+ '<td> <label id="preco-'+ nContItems +'" type="text" dir="rtl" class="text-input s70-input" name="estadia.consumos[].preco" > </label> </td>'
			+ '<td> <label id="total-'+ nContItems +'"> </label> </td>'
			+ '<td> <a id="delete-' + nContItems + '" href="javascript:" title="Delete" tabindex="-1"> <img src="../resources/images/icons/cross.png" alt="Delete" tabindex="-1"/> </a> </td>'
			+ '<td></td>'
			+ '</tr>';

	$("#consumos").append(cHtml);
}
	
</script>
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
			<h5>Estadia</h5>
		</div>
		<div class="widget-box">
			<div class="widget-content nopadding">
			  <form class="form-horizontal" method="post">
		    		<input type="hidden" name="estadia.id" value="${estadia.id}" />
					<div class="control-group">
						<label class="control-label">Quarto:</label>
						<div class="controls">
							<input id="estadia.quarto.numero" type="text" name="estadia.quarto.numero" value="${estadia.quarto.numero}" readonly="readonly" />
						</div>
					</div>
					<c:forEach var="hospede" items="${estadia.hospedes}">
						<div class="control-group">
							<label class="control-label">Hospede:</label>
							<div class="controls">
								<input id="estadia.quarto.numero" type="text" name="estadia.quarto.numero" value="${hospede.nome}" readonly="readonly" />
							</div>
						</div>
					</c:forEach>
					<div class="control-group">
						<label class="control-label">Checkin:</label>
						<div class="controls">
							<input id="estadia.dataCheckin" type="text" name="estadia.dataCheckin" value="<joda:format pattern='dd/MM/yyyy' value='${estadia.dataCheckin}'/>" readonly="readonly" />
						</div>
					</div>
					<div class="widget-title">
						<span class="icon">
							<i class="icon-align-justify"></i>									
						</span>
						<h5>Consumo</h5>
					</div>
					<table id="consumos">
						<thead>
							<tr>
								<th width="40%">Descrição</th>
								<th width="15%">Quantidade</th>
								<th width="15%">Preço</th>
								<th width="15%">Total</th>
								<th width="5%"> Deletar</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					
					<div class="form-actions">
						<button type="button" id="adiciona-produto" class="btn btn-primary">Adicionar Consumo</button>
				    </div>
			  </form>
			</div>
		</div>
					
</body>
</html>