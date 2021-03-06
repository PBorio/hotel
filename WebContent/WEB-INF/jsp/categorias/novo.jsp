<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
		<c:forEach var="error" items="${errors}">
			<div class="alert alert-danger">
				${error.message}
			</div>
		</c:forEach>
		<div class="container">
			<div class="header">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="<c:url value='/painel/'/>">Home</a></li>
			</ul>
			</div>
			  <form class="form-horizontal" action='<c:url value="/categorias/salva"/>' method="post">
			  	<fieldset>
					<legend>Categorias</legend>
				  	  <input type="hidden" name="categoria.id" value="${categoria.id}" />
				  	  <div class="form-group">
							<label class="control-label col-xs-2">Descri��o:</label>
							<div class="col-xs-10">
								<input type="text" class="col-xs-10" name="categoria.descricao" id="categoria.descricao" value="${categoria.descricao}" />
							</div>
					  </div> 
					  <div class="form-group">
							<label class="control-label col-xs-2">Observa��o:</label>
							<div class="col-xs-10">
								<textarea name="categoria.observacao" id="categoria.observacao" class="col-xs-10">${categoria.observacao}</textarea>
							</div>
					  </div> 
					  <div class="form-group">
							<label class="control-label col-xs-2">Valor:</label>
							<div class="col-xs-10">
								<input type="text" class="col-xs-10" name="categoria.valor" id="categoria.valor" value="${categoria.valor}" />
							</div>
					  </div> 
					  <div class="form-group">
						<label class="control-label col-xs-2">2 Crian�as de 0 a 5:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="categoria.valorACada2CriancasDe0a5" id="categoria.valorACada2CriancasDe0a5" value="${categoria.valorACada2CriancasDe0a5}" />
						</div>
				  </div> 
				   <div class="form-group">
						<label class="control-label col-xs-2">Crian�as de 6 a 16:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="categoria.valorCadaCrianca0a16" id="categoria.valorCadaCrianca0a16" value="${categoria.valorCadaCrianca0a16}" />
						</div>
				  </div> 
				   <div class="form-group">
						<label class="control-label col-xs-2">Adulto Extra:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="categoria.valorCadaAdultoExtra" id="categoria.valorCadaAdultoExtra" value="${categoria.valorCadaAdultoExtra}" />
						</div>
				  </div> 
				   <div class="form-group">
						<label class="control-label col-xs-2">Um Adulto:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="categoria.valorParaUmAdulto" id="categoria.valorParaUmAdulto" value="${categoria.valorParaUmAdulto}" />
						</div>
				  </div> 
				<security:authorize ifAnyGranted="ROLE_SYSADMIN">
				   <div class="form-group">
					<label class="control-label col-xs-2" for="singlebutton"></label>
					<div class="col-xs-10">
						<button id="singlebutton" name="singlebutton" class="btn btn-primary btn-lg">
							Salvar
						</button>
					</div>
				</div>
				</security:authorize>
				<security:authorize ifAnyGranted="ROLE_DEMO">
				   <div class="form-group">
					<label class="control-label col-xs-2" for="singlebutton"></label>
					<div class="col-xs-10">
						<button id="singlebutton" name="singlebutton" class="btn btn-primary btn-lg" disabled="disabled">
							Salvar (desabilitado na vers�o de demonstra��o)
						</button>
					</div>
				</div>
				</security:authorize>
				 </fieldset>
			  </form>
			 </div>
</body>
</html>