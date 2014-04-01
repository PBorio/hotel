<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Políticas de Preços</title>
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
			     <div class="control-group">
						<label class="control-label">Descrição:</label>
						<div class="controls">
							<input id="politicaDePrecos.descricao" type="text" name="politicaDePrecos.descricao" value="${politicaDePrecos.descricao}" />
						</div>
				  </div> 
				  <div class="control-group">
						<label class="control-label">Categoria:</label>
						<div class="controls">
							<select id="politicaDePrecos.categoria.id" name="politicaDePrecos.categoria.id" >  
			                    <option> Categorias...</option>  
			                    <c:forEach var="categoria" items="${categoriaList}">  
			                        <option value="${categoria.id}" <c:if test="${categoria.id == politicaDePrecos.categoria.id}">selected="true"</c:if>> 
			                        	${categoria.descricao} 
			                        </option>  
			                    </c:forEach>  
			                </select> 
						</div>
				 </div>
				<div class="control-group">
					<label class="control-label">Observação:</label>
					<div class="controls">
						<textarea name="categoria.observacao" id="categoria.observacao">${categoria.observacao}</textarea>
					</div>
				</div>
				<div class="control-group">
						<label class="control-label">Inicio:</label>
						<div class="controls">
							<input type="text" name="politicaDePrecos.inicio" id="politicaDePrecos.inicio" value="<fmt:formatDate value='${politicaDePrecos.inicio}'/>" />
						</div>
				  </div> 
				  <div class="control-group">
						<label class="control-label">Fim:</label>
						<div class="controls">
							<input type="text" name="politicaDePrecos.fim" id="politicaDePrecos.fim" value="<fmt:formatDate value='${politicaDePrecos.fim}'/>"/>
						</div>
				  </div> 
				  <div class="control-group">
						<label class="control-label">Valor:</label>
						<div class="controls">
							<input type="text" name="politicaDePrecos.valorDiaria" id="politicaDePrecos.valorDiaria" value="${politicaDePrecos.valorDiaria}" />
						</div>
				  </div> 
				  <div class="control-group">
						<label class="control-label">Padrão:</label>
						<div class="controls">
						    <div class="checker">
							<label>
							    <span>
							  		<input type="checkbox" name="politicaDePrecos.padrao" id="politicaDePrecos.padrao"/>
							  	</span>
							</label>
							</div>
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
		  
		   
</body>
</html>