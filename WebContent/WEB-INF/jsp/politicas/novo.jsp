<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Políticas de Preços</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/datepicker.css'/>" />
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
					<li class="active"><a href="/">Home</a></li>
				</ul>
			</div>
		  <form class="form-horizontal" action='<c:url value="/politicas/salva"/>' method="post">
		  	<fieldset>
				<legend>Políticas de Preço</legend>
			     <input type="hidden" name="politicaDePrecos.id" value="${politicaDePrecos.id}" />
			     <div class="form-group">
						<label class="control-label col-xs-2">Descrição:</label>
						<div class="col-xs-10">
							<input id="descricao" type="text" class="col-xs-10" name="politicaDePrecos.descricao" value="${politicaDePrecos.descricao}" />
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Categoria:</label>
						<div class="col-xs-10">
							<select id="politicaDePrecos.categoria.id" name="politicaDePrecos.categoria.id" class="col-xs-10">  
			                    <option> Categorias...</option>  
			                    <c:forEach var="categoria" items="${categoriaList}">  
			                        <option value="${categoria.id}" <c:if test="${categoria.id == politicaDePrecos.categoria.id}">selected="true"</c:if>> 
			                        	${categoria.descricao} 
			                        </option>  
			                    </c:forEach>  
			                </select> 
						</div>
				 </div>
				<div class="form-group">
					<label class="control-label col-xs-2">Observação:</label>
					<div class="col-xs-10">
						<textarea name="categoria.observacao" id="categoria.observacao" class="col-xs-10">${categoria.observacao}</textarea>
					</div>
				</div>
				<div class="form-group">
						<label class="control-label col-xs-2">Inicio:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="politicaDePrecos.inicio" id="politicaDePrecos.inicio" value="<fmt:formatDate value='${politicaDePrecos.inicio}'/>" />
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Fim:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="politicaDePrecos.fim" id="politicaDePrecos.fim" value="<fmt:formatDate value='${politicaDePrecos.fim}'/>"/>
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Valor:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="politicaDePrecos.valorDiaria" id="politicaDePrecos.valorDiaria" value="${politicaDePrecos.valorDiaria}" />
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Padrão:</label>
						<div class="col-xs-10">
						    <div class="checker">
							<label>
							    <span>
							  		<input type="checkbox" name="politicaDePrecos.padrao" id="politicaDePrecos.padrao" value="${politicaDePrecos.padrao}" checked="${politicaDePrecos.padrao}"/>
							  	</span>
							</label>
							</div>
						</div>
				  </div> 
				 	<div class="control-group col-xs-2">
						<label class="control-label" for="singlebutton"></label>
						<div class="col-xs-10">
							<button id="singlebutton" name="singlebutton" class="btn btn-primary">
								Salvar
							</button>
						</div>
					</div>
			</fieldset>
		  </form>
	</div>
<script src="<c:url value='/resources/scripts/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/scripts/bootstrap-datepicker.js'/>"></script>
<script type="text/javascript">
$(function(){
	$('#politicaDePrecos\\.inicio').datepicker({
		format: 'dd/mm/yyyy'
	});
	$('#politicaDePrecos\\.fim').datepicker({
		format: 'dd/mm/yyyy'
	});
});
</script>
</body>
</html>