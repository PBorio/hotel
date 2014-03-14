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
			<p class="mensagem">
				${mensagem}
			</p>
		</c:if>
		<div class="widget-title">
			<span class="icon">
				<i class="icon-align-justify"></i>									
			</span>
			<h5>Cadastro de Quartos</h5>
		</div>
		<div class="widget-box">
			<div class="widget-content nopadding">
			  <form class="form-horizontal" action='<c:url value="/categorias/salva"/>' method="post">
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
				  <div class="form-actions">
						<button type="submit" class="btn btn-primary">Salvar</button>
				  </div>
			  </form>
			 </div>
		</div>
</body>
</html>