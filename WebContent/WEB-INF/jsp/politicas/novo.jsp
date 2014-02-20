<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Pol�ticas de Pre�os</title>
</head>
<body>
	 <form action='<c:url value="/politicas/salva"/>' method="post">
	    <input type="hidden" name="politicaDePrecos.id" value="${politicaDePrecos.id}" />
	    <c:if test="${not empty mensagem}">
			<p class="mensagem">
				${mensagem}
			</p>
		</c:if>
		<h3>Cadastro de Pol�ticas de Pre�os</h3>
		<fieldset>
		   <p class="half">
				<span>Descri��o: </span>
				<input id="politicaDePrecos.descricao" type="text" name="politicaDePrecos.descricao" value="${politicaDePrecos.descricao}" />
		   </p>
		   <p class="half">
		   		<span>Categoria: </span>
				<select id="politicaDePrecos.categoria.id" name="politicaDePrecos.categoria.id" >  
                    <option> Categorias...</option>  
                    <c:forEach var="categoria" items="${categoriaList}">  
                        <option value="${categoria.id}" <c:if test="${categoria.id == politicaDePrecos.categoria.id}">selected="true"</c:if>> 
                        	${categoria.descricao} 
                        </option>  
                    </c:forEach>  
                </select>  		   
		   </p>
		    <p class="half">
				<span>Inicio:</span>
				<input type="text" name="politicaDePrecos.inicio" id="politicaDePrecos.inicio" value="<fmt:formatDate value='${politicaDePrecos.inicio}'/>" />
		   </p>
		    <p class="half">
				<span>Fim:</span>
				<input type="text" name="politicaDePrecos.fim" id="politicaDePrecos.fim" value="<fmt:formatDate value='${politicaDePrecos.fim}'/>"/>
		   </p>
		   <p class="half">
		        <span>Valor: </span>
				<input type="text" name="politicaDePrecos.valorDiaria" id="politicaDePrecos.valorDiaria" value="${politicaDePrecos.valorDiaria}" />
		   </p>
		    <p class="half">
				<span>Padr�o:</span>
				<input type="checkbox" name="politicaDePrecos.padrao" id="politicaDePrecos.padrao" checked="${politicaDePrecos.padrao}" />
		   </p>
		    <p>
		   		<input type="submit" value="Salvar" />
		   	</p>
	  </fieldset>
	</form>
</body>
</html>