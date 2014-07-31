<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estadia</title>

<script src="<c:url value='/resources/scripts/jquery-ui.min.js'/>"></script>
<script type="text/javascript">
	
jQuery(document).ready(function() {
	
	var nContItems = 0;
	$('#btnSalvaConsumo').hide();
	$('#btnSalvaServico').hide();
	$("#adiciona-produto").bind('click', function() {
			addConsumos(nContItems);
	        nContItems++;
	});
	
	//TODO Provisorio, remover
	$("#salva-produto").bind('click', function() {
		$('#btnSalvaConsumo').hide();
		$('#btnNovoConsumo').show();
	});
	
	var nContItems = 0;
	$("#adiciona-servico").bind('click', function() {
		addServicos(nContItems);
	        nContItems++;
	});
	
	//TODO Provisorio, remover
	$("#salva-servico").bind('click', function() {
		$('#btnSalvaServico').hide();
		$('#btnNovoServico').show();
	});
	autoComplete();
});

function addConsumos(nContItems) {
	
	cHtml = '<tr id="row-'+ nContItems + '" class="alt-row-son" >'
	        + '<input id="consumo-id-'+ nContItems +'" type="hidden" name="consumo.id"> </input>'
			+ '<input id="produto-id-'+ nContItems +'" type="hidden" name="consumo.produto.id"> </input>'
			+ '<td> <input id="descricao-'+ nContItems +'" type="text" class="col-xs-10" class="text-input s70-input" name="consumo.produto.descricao"> </input> </td>'
			+ '<td> <input id="quantidade-'+ nContItems +'" type="text" class="col-xs-10" dir="rtl" class="text-input s70-input" name="estadia.consumo.quantidade" > </input> </td>'
			+ '<td> <label id="preco-'+ nContItems +'" type="text" dir="rtl" class="text-input s70-input" name="consumo.preco" > </label> </td>'
			+ '<td> <label id="total-'+ nContItems +'"> </label> </td>'
			+ '<td> <a id="delete-' + nContItems + '" href="javascript:" title="Delete" tabindex="-1"> <img src="../resources/imagens/icons/cross.png" alt="Delete" tabindex="-1"/> </a> </td>'
			+ '<td></td>'
			+ '</tr>';
			
	$("#consumos").append(cHtml);
	$("#descricao-" + nContItems).on("blur",
			function(event) {
				buscarConsumo(nContItems);
			});

	autoComplete(nContItems);

	$('#btnNovoConsumo').hide();
	$('#btnSalvaConsumo').show();
	
}

function autoComplete(nContItems){
	$(function() {
	    var availableTags = [
	      "Refrigerante - Coca Cola",
	      "Refrigerante - Pepsi 2L",
	      "Refrigerante - Fanta 2L",
	    ];
	    $( "#descricao-"+nContItems).autocomplete({
	      source: availableTags
	    });
	  });
}

function buscarConsumo(nLinhaDeItem) {

	$("#produto-id-" + nLinhaDeItem).val("");
	$("#quantidade-" + nLinhaDeItem).html("");

	descricao = $("#descricao-"+nLinhaDeItem ).val();
	
	if (descricao!=null && descricao != undefined && descricao != '')
		{
			$.getJSON('/hotel/painel/buscarProdutoPorDescricao.json', {
				descricao : descricao
			}, function(produto) {
				var prodid = produto.id;
				var desc = produto.descricao;
				$("#produto-id-" + nLinhaDeItem).val(produto.id);
				$("#descricao-" + nLinhaDeItem).html(produto.descricao);
				$("#preco-" + nLinhaDeItem).val(produto.precoDeCusto);
				var cfop = $("#cfop-id").val();
 				$("#quantidade-" + nLinhaDeItem).focus();
			}
	     	);
		}	
}

function addServicos(nContItems) {
	
	cHtml = '<tr id="row-'+ nContItems + '" class="alt-row-son" >'
	        + '<input id="servico-id-'+ nContItems +'" type="hidden" name="estadia.servicosPrestados[].id"> </input>'
			+ '<input id="servico-id-'+ nContItems +'" type="hidden" name="estadia.servicosPrestados[].servico.id"> </input>'
			+ '<td> <input id="descricao-'+ nContItems +'" type="text" class="col-xs-10" class="text-input s70-input" name="estadia.servicosPrestados[].servico.descricao"> </input> </td>'
			+ '<td> <input id="valor-'+ nContItems +'" type="text" class="col-xs-10" dir="rtl" class="text-input s70-input" name="estadia.servicosPrestados[].valorSugerido" > </input> </td>'
			+ '<td> <a id="delete-' + nContItems + '" href="javascript:" title="Delete" tabindex="-1"> <img src="../resources/imagens/icons/cross.png" alt="Delete" tabindex="-1"/> </a> </td>'
			+ '<td></td>'
			+ '</tr>';

	$("#servicos").append(cHtml);
	$('#btnNovoServico').hide();
	$('#btnSalvaServico').show();
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
					<li class="active"><a href="<c:url value='/'/>">Home</a></li>
				</ul>								
			</div>
			<fieldset>
				<legend>Quarto</legend>
				<table class="table table-striped table-bordered" id="example"
							cellpadding="0" cellspacing="0" border="0" width="100%">
						<tfoot>
							<tr>
							   	<th>${estadia.quarto.numero}</th>
							</tr>
						</tfoot>
				</table>
			</fieldset>
			
			<fieldset>
				<legend>Hospedes J� Registrados</legend>
				<table class="table table-striped table-bordered" id="example"
						cellpadding="0" cellspacing="0" border="0" width="100%">
					<tfoot>
						<c:forEach var="hospede" items="${estadia.hospedes}">
							<tr id="hospede-${hospede.id}">
								<th>${hospede.nomeCompleto}</th>
							</tr>
						</c:forEach>
					</tfoot>
				</table>
			</fieldset>
			<fieldset>
				<legend>Valores</legend>
				<table class="table table-striped table-bordered" id="example"
						cellpadding="0" cellspacing="0" border="0" width="100%">
					<thead> 
					<tr>
						<th class="ui-state-default" width="20%"></th>
						<th class="ui-state-default" width="80%"></th>
					</tr>
					</thead>
					<tbody>
							<tr>
							    <td>Checkin</td>
								<td><joda:format pattern='dd/MM/yyyy' value='${estadia.dataCheckin}'/></td>
							</tr>
							<tr>
							    <td>Checkout</td>
								<td><joda:format pattern='dd/MM/yyyy' value='${estadia.previsaoCheckout}'/></td>
							</tr>
							<tr>
							    <td>Valor Di�ria</td>
								<td>${estadia.valorDiaria}</td>
							</tr>
							<tr>
							    <td>Valor Final</td>
								<td>${estadia.previsaoDoValorFinal}</td>
							</tr>
							<tr>
							    <td>Valor Pago</td>
								<td>${estadia.valorPago}</td>
							</tr>
							<tr>
							    <td>Saldo a Pagar</td>
								<td>${estadia.saldoAPagar}</td>
							</tr>
					</tbody>
				</table>
			</fieldset>
			<fieldset>
			  <legend>Consumo</legend>
			  <form class="form-horizontal" method="post" action='<c:url value="/painel/add/consumo"/>'>
		    		<input type="hidden" name="consumo.estadia.id" value="${estadia.id}" />
					<div class="widget-title">
						<span class="icon">
							<i class="icon-align-justify"></i>									
						</span>
					</div>
					<table id="consumos">
						<thead>
							<tr>
								<th width="40%">Descri��o</th>
								<th width="15%">Quantidade</th>
								<th width="15%">Pre�o</th>
								<th width="15%">Total</th>
								<th width="5%"> Deletar</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					
					<div class="form-actions" id="btnNovoConsumo">
						<button type="button" id="adiciona-produto" class="btn btn-primary">Adicionar Consumo</button>
				    </div>
				    <div class="form-actions" id="btnSalvaConsumo">
						<button type="button" id="salva-produto" class="btn btn-primary">Salvar</button>
				    </div>
			  </form>
			 </fieldset>
			 <fieldset>
			  <legend>Servi�os</legend>
			  <form class="form-horizontal" method="post" action='<c:url value="/painel/add/servico/"/>'> 
		    		<input type="hidden" name="estadia.id" value="${estadia.id}" />
					<div class="widget-title">
						<span class="icon">
							<i class="icon-align-justify"></i>									
						</span>
					</div>
					<table id="servicos">
						<thead>
							<tr>
								<th width="70%">Descri��o</th>
								<th width="25%">Valor</th>
								<th width="5%"> Deletar</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					
					<div class="form-actions" id="btnNovoServico">
						<button type="button" id="adiciona-servico" class="btn btn-primary">Adicionar Servi�o</button>
				    </div>
				    <div class="form-actions" id="btnSalvaServico">
						<button type="button" id="salva-servico" class="btn btn-primary">Salvar</button>
				    </div>
			  </form>
			 </fieldset>
			</div>
			
</body>
</html>