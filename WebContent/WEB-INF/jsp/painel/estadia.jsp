<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estadia</title>

<style type="text/css">
.ui-corner-all
{
	border-radius: 4px 4px 4px 4px;
	-moz-border-radius: 4px 4px 4px 4px;
}
.ui-widget-content
 {
     border: 1px solid white;
     color: #ffffff;
     background-color: Red;
 }
.ui-widget
{
    font-family: Verdana,Arial,sans-serif;
    font-size: 15px;
}
.ui-menu
{
    display: block;
    float: left;
    list-style: none outside none;
    margin: 0;
    padding: 2px;
}
      
.ui-autocomplete {
    background: #2fa4e7;
    border-radius: 4px;
    cursor: default;
    position: absolute;
}
 .ui-menu .ui-menu-item
 {
     clear: left;
     float: left;
     margin: 0;
     padding: 0;
     width: 100%;
 }
 .ui-menu .ui-menu-item a
 {
     display: block;
     padding: 3px 3px 3px 3px;
     text-decoration: none;
     cursor: pointer;
     background-color: Green;
 }
.ui-autocomplete.source:hover {
    background: #454545;
}
.ui-menu .ui-menu-item:hover
  {
      display: block;
      padding: 3px 3px 3px 3px;
      text-decoration: none;
      color: White;
      cursor: pointer;
      background-color: #0b0b3d;
  }
.ui-state-focus
  {
      display: block;
      padding: 3px 3px 3px 3px;
      text-decoration: none;
      color: White;
      cursor: pointer;
      background-color: #0b0b3d;
  }

</style>
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
});

function addConsumos(nContItems) {
	
	cHtml = '<tr id="row-'+ nContItems + '" class="alt-row-son" >'
			+ '<input id="estadia-id" type="hidden" name="consumo.estadia.id" value="${estadia.id}"></input>'
	        + '<input id="consumo-id-'+ nContItems +'" type="hidden" name="consumo.id"> </input>'
			+ '<input id="produto-id-'+ nContItems +'" type="hidden" name="consumo.produto.id"> </input>'
			+ '<td> <input id="descricao-'+ nContItems +'" type="text" class="col-xs-10" class="text-input s70-input" name="consumo.produto.descricao"> </input> </td>'
			+ '<td> <input id="quantidade-'+ nContItems +'" type="text" class="col-xs-10" dir="rtl" class="text-input s70-input" name="consumo.quantidade" > </input> </td>'
			+ '<td> <input id="preco-'+ nContItems +'" type="text" dir="rtl" class="text-input s70-input" name="consumo.preco" readonly="readonly" > </input> </td>'
			+ '<td> <label id="total-'+ nContItems +'"> </label> </td>'
			+ '<td> <a id="delete-' + nContItems + '" href="javascript:" title="Delete" tabindex="-1"> <img src="../resources/imagens/icons/cross.png" alt="Delete" tabindex="-1"/> </a> </td>'
			+ '<td></td>'
			+ '</tr>';
			
	$("#consumos").append(cHtml);
	$("#descricao-" + nContItems).on("blur",
			function(event) {
				buscarConsumo(nContItems);
			});
	
	$("#descricao-" + nContItems).on("keypress",
			function(event) {
				autoCompleteConsumo(nContItems);
			});

	$('#btnNovoConsumo').hide();
	$('#btnSalvaConsumo').show();
	
}

function autoCompleteConsumo(nLinhaDeItem){
	var descProds = [];
	descricao = $("#descricao-"+nLinhaDeItem ).val();
	$.getJSON('/hotel/painel/buscarProdutosPorDescricao.json', {
		descricao : descricao
		}, function(produtos) {
			for (i = 0; i < produtos.length; i++){
				descProds[i] = {label: produtos[i].descricao};
				
			}
		}
	);
	$( "#descricao-"+nLinhaDeItem).autocomplete({
	      source: descProds,
	      autoFocus: true
	});
}

function buscarConsumo(nLinhaDeItem) {

	$("#produto-id-" + nLinhaDeItem).val("");
	$("#quantidade-" + nLinhaDeItem).html("");

	descricao = $("#descricao-"+nLinhaDeItem ).val();
	
	if (descricao!=null && descricao != undefined && descricao != '')
		{
			$.getJSON('/hotel/painel/buscarProdutoComEstaDescricao.json', {
					descricao : descricao
				}, function(produto) {
					$("#produto-id-" + nLinhaDeItem).val(produto.id);
					$("#descricao-" + nLinhaDeItem).val(produto.descricao);
					$("#preco-" + nLinhaDeItem).val(produto.preco);
	 				$("#quantidade-" + nLinhaDeItem).focus();
				}
	        );
		}	
}

function addServicos(nContItems) {
	
	cHtml = '<tr id="row-'+ nContItems + '" class="alt-row-son" >'
			+ '<input id="estadia-id" type="hidden" name="servicoPrestado.estadia.id"  value="${estadia.id}"> </input>'
	        + '<input id="servico-prestado-id-'+ nContItems +'" type="hidden" name="servicoPrestado.id"> </input>'
			+ '<input id="servico-id-'+ nContItems +'" type="hidden" name="servicoPrestado.servico.id"> </input>'
			+ '<td> <input id="desc-servico-'+ nContItems +'" type="text" class="col-xs-10" class="text-input s70-input" name="servicoPrestado.servico.descricao"> </input> </td>'
			+ '<td> <input id="valor-servico-'+ nContItems +'" type="text" class="col-xs-10" dir="rtl" class="text-input s70-input" name="servicoPrestado.valor" > </input> </td>'
			+ '<td> <textarea id="obs-servico-'+ nContItems +'" class="col-xs-10" name="servicoPrestado.observacao" />  </td>'
			+ '<td> <a id="delete-' + nContItems + '" href="javascript:" title="Delete" tabindex="-1"> <img src="../resources/imagens/icons/cross.png" alt="Delete" tabindex="-1"/> </a> </td>'
			+ '<td></td>'
			+ '</tr>';

	$("#servicos").append(cHtml);
	
	$("#desc-servico-" + nContItems).on("blur",
			function(event) {
				buscarServico(nContItems);
			});
	
	$("#desc-servico-" + nContItems).on("keypress",
		function(event) {
		autoCompleteServico(nContItems);
		});
	
	$('#btnNovoServico').hide();
	$('#btnSalvaServico').show();
}

function autoCompleteServico(nLinhaDeItem){
	var descServ = [];
	descricao = $("#desc-servico-"+nLinhaDeItem ).val();
	$.getJSON('/hotel/painel/buscarServicosPorDescricao.json', {
		descricao : descricao
		}, function(servicos) {
			for (i = 0; i < servicos.length; i++){
				descServ[i] = {label: servicos[i].descricao};
			}
		}
	);
	$( "#desc-servico-"+nLinhaDeItem).autocomplete({
	      source: descServ,
	      autoFocus: true
	});
}

function buscarServico(nLinhaDeItem) {

	$("#servico-id-" + nLinhaDeItem).val("");
	$("#valor-servico-" + nLinhaDeItem).html("");

	descricao = $("#desc-servico-"+nLinhaDeItem ).val();
	
	if (descricao!=null && descricao != undefined && descricao != '')
		{
			$.getJSON('/hotel/painel/buscarServicoComEstaDescricao.json', {
					descricao : descricao
				}, function(servico) {
					$("#servico-id-" + nLinhaDeItem).val(servico.id);
					$("#desc-servico-" + nLinhaDeItem).val(servico.descricao);
					$("#valor-servico-" + nLinhaDeItem).val(servico.valorSugerido);
					$("#valor-servico-" + nLinhaDeItem).focus();
				}
	        );
		}	
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
			  <form class="form-horizontal" method="post" action='<c:url value="/painel/add/consumo/"/>'>
		    		<input type="hidden" name="consumo.estadia.id" value="${estadia.id}" />
					<div class="widget-title">
						<span class="icon">
							<i class="icon-align-justify"></i>									
						</span>
					</div>
					<table id="consumos"class="table table-striped table-bordered" 
							cellpadding="0" cellspacing="0" border="0" width="100%">
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
							<c:forEach var="consumo" items="${estadia.consumos}" varStatus="nContItems">
								<tr id="row-${nContItems.index}" class="alt-row-son" >
									<td> ${consumo.produto.descricao}</td>
									<td> ${consumo.quantidade}</td>
									<td> ${consumo.preco}</td>
									<td> <label id="total-'+ nContItems +'"> </label> </td>
									<td> <a href="<c:url value='/painel/deleta/consumo/${consumo.id}'/>" title="Delete" tabindex="-1"> <img src="../resources/imagens/icons/cross.png" alt="Delete" tabindex="-1"/> </a> </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<div class="form-actions" id="btnNovoConsumo">
						<button type="button" id="adiciona-produto" class="btn btn-primary">Adicionar Consumo</button>
				    </div>
				    <div class="form-actions" id="btnSalvaConsumo">
						<button type="submit" id="salva-produto" class="btn btn-primary">Salvar</button>
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
					<table id="servicos" class="table table-striped table-bordered" 
						   cellpadding="0" cellspacing="0" border="0" width="100%">
						<thead>
							<tr>
								<th width="35%">Descri��o</th>
								<th width="15%">Valor</th>
								<th width="55%">Observa��o</th>
								<th width="5%"> Deletar</th>
							</tr>
						</thead>
						<tbody>
						  <c:forEach var="servico" items="${estadia.servicosPrestados}" varStatus="nContItems">
							<tr id="row-${nContItems.index}" class="alt-row-son" >
							<td> ${servico.servico.descricao} </td>
							<td> ${servico.valor} </td>
							<td> ${servico.observacao}</td>
							<td> <a href="<c:url value='/painel/deleta/servico/${servico.id}'/>" title="Delete" tabindex="-1"> <img src="../resources/imagens/icons/cross.png" alt="Delete" tabindex="-1"/> </a> </td>
							</tr>
						  </c:forEach>
						</tbody>
					</table>
					
					<div class="form-actions" id="btnNovoServico">
						<button type="button" id="adiciona-servico" class="btn btn-primary">Adicionar Servi�o</button>
				    </div>
				    <div class="form-actions" id="btnSalvaServico">
						<button type="submit" id="salva-servico" class="btn btn-primary">Salvar</button>
				    </div>
			  </form>
			 </fieldset>
			</div>
			
</body>
</html>