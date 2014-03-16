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
			<p class="mensagem">
				${mensagem}
			</p>
		</c:if>
		<div class="widget-title">
			<span class="icon">
				<i class="icon-align-justify"></i>									
			</span>
			<h5>Cadastro de Políticas de Preço</h5>
		</div>
		<div class="widget-box">
			<div class="widget-content nopadding">
			  <form class="form-horizontal" action='<c:url value="/politicas/salva"/>' method="post">
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
							  		<input type="checkbox" name="politicaDePrecos.padrao" id="politicaDePrecos.padrao" checked="${politicaDePrecos.padrao}" style="opacity: 0" />
							  	</span>
							</label>
							</div>
						</div>
				  </div> 
				  <div class="form-actions">
						<button type="submit" class="btn btn-primary">Salvar</button>
				  </div>
			  </form>
		   </div>
		 </div>
		  
		   
</body>
</html>