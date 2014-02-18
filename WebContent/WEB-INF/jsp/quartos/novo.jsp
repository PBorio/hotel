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
	 <form action='<c:url value="/quartos/salva"/>' method="post">
	    <input type="hidden" name="quarto.id" value="${quarto.id}" />
	    <c:if test="${not empty mensagem}">
			<p class="mensagem">
				${mensagem}
			</p>
		</c:if>
		<h3>Cadastro de Quartos</h3>
		<fieldset>
		   <p class="half">
				<span>Número: </span>
				<input id="quarto.numero" type="text" name="quarto.numero" value="${quarto.numero}" />
		   </p>
		   <p class="half">
		   		<span>Categoria: </span>
				<select id="quarto.categoria.id" name="quarto.categoria.id" >  
                    <option> Categorias...</option>  
                    <c:forEach var="categoria" items="${categoriaList}">  
                        <option value="${categoria.id}" <c:if test="${categoria.id == quarto.categoria.id}">selected="true"</c:if>> 
                        	${categoria.descricao} 
                        </option>  
                    </c:forEach>  
                </select>  		   
		   </p>
		   <p class="half">
		        <span>Descrição: </span>
				<input type="text" name="quarto.descricao" id="quarto.descricao" value="${quarto.descricao}" />
		   </p>
		   <p class="half">
				<span>Observação:</span>
				<textarea name="quarto.observacao" id="quarto.observacao">${quarto.observacao}</textarea>
		   </p>
		    <p>
		   		<input type="submit" value="Salvar" />
		   	</p>
	  </fieldset>
	</form>
</body>
</html>