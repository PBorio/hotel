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
	 <form action='<c:url value="/categorias/salva"/>' method="post">
	    <input type="hidden" name="categoria.id" value="${categoria.id}" />
	    <c:if test="${not empty mensagem}">
			<p class="mensagem">
				${mensagem}
			</p>
		</c:if>
		<h3>Cadastro de Categorias</h3>
		<fieldset>
		   <p class="half">
		        <span>Descrição: </span>
				<input type="text" name="categoria.descricao" id="categoria.descricao" value="${categoria.descricao}" />
		   </p>
		   <p class="half">
				<span>Observação:</span>
				<textarea name="categoria.observacao" id="categoria.observacao">${categoria.observacao}</textarea>
		   </p>
		    <p>
		   		<input type="submit" value="Salvar" />
		   	</p>
	  </fieldset>
	</form>
</body>
</html>