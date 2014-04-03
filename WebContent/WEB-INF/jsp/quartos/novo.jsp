<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Quartos</title>
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
	 	
	 	<form class="form-horizontal" action='<c:url value="/quartos/salva"/>' method="post">
	 		<input type="hidden" name="quarto.id" value="${quarto.id}" />
			<fieldset>
				<legend>Quarto</legend>
	 			<div class="form-group">
	 				<label class="control-label col-xs-2">Número:</label>
					<div class="col-xs-10">
						<input id="quarto.numero" class="col-xs-10" type="text" name="quarto.numero" value="${quarto.numero}" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-2">Categoria:</label>
					<div class="col-xs-10">
						<select id="quarto.categoria.id" name="quarto.categoria.id" class="col-xs-10" >   
		                    <option> Categorias...</option>  
		                    <c:forEach var="categoria" items="${categoriaList}">  
		                        <option value="${categoria.id}" <c:if test="${categoria.id == quarto.categoria.id}">selected="true"</c:if>> 
		                        	${categoria.descricao} 
		                        </option>  
		                    </c:forEach>  
		                </select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-2">Descrição:</label>
					<div class="col-xs-10">
						<input type="text" class="col-xs-10" name="quarto.descricao" id="quarto.descricao" value="${quarto.descricao}" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-2">Observação:</label>
					<div class="col-xs-10">
						<textarea name="quarto.observacao" class="col-xs-10" id="quarto.observacao">${quarto.observacao}</textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-xs-2" for="singlebutton"></label>
					<div class="col-xs-10">
						<button id="singlebutton" name="singlebutton" class="btn btn-primary btn-lg">
							Salvar
						</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
					
</body>
</html>