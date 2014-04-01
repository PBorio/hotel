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
	 			<div class="control-group">
	 				<label class="control-label">Número:</label>
					<div class="controls">
						<input id="quarto.numero" type="text" name="quarto.numero" value="${quarto.numero}" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Categoria:</label>
					<div class="controls">
						<select id="quarto.categoria.id" name="quarto.categoria.id" >   
		                    <option> Categorias...</option>  
		                    <c:forEach var="categoria" items="${categoriaList}">  
		                        <option value="${categoria.id}" <c:if test="${categoria.id == quarto.categoria.id}">selected="true"</c:if>> 
		                        	${categoria.descricao} 
		                        </option>  
		                    </c:forEach>  
		                </select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Descrição:</label>
					<div class="controls">
						<input type="text" name="quarto.descricao" id="quarto.descricao" value="${quarto.descricao}" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Observação:</label>
					<div class="controls">
						<textarea name="quarto.observacao" id="quarto.observacao">${quarto.observacao}</textarea>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="singlebutton"></label>
					<div class="controls">
						<button id="singlebutton" name="singlebutton" class="btn btn-primary">
							Salvar
						</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
					
</body>
</html>