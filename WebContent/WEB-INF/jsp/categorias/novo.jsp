<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Categorias</title>
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
			  <form class="form-horizontal" action='<c:url value="/categorias/salva"/>' method="post">
			  	<fieldset>
					<legend>Categorias</legend>
				  	  <input type="hidden" name="categoria.id" value="${categoria.id}" />
				  	  <div class="control-group">
							<label class="control-label">Descrição:</label>
							<div class="controls">
								<input type="text" name="categoria.descricao" id="categoria.descricao" value="${categoria.descricao}" />
							</div>
					  </div> 
					  <div class="control-group">
							<label class="control-label">Observação:</label>
							<div class="controls">
								<textarea name="categoria.observacao" id="categoria.observacao">${categoria.observacao}</textarea>
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
		</div>
</body>
</html>