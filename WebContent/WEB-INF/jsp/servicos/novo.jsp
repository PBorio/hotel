<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Serviços</title>
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
			  <form class="form-horizontal" action='<c:url value="/servicos/salva"/>' method="post">
			  	<fieldset>
					<legend>Serviços</legend>
				  	  <input type="hidden" name="servico.id" value="${servico.id}" />
				  	  <div class="form-group">
							<label class="control-label col-xs-2">Descrição:</label>
							<div class="col-xs-10">
								<input type="text" class="col-xs-10" name="servico.descricao" id="servico.descricao" value="${servico.descricao}" />
							</div>
					  </div> 
					  <div class="form-group">
							<label class="control-label col-xs-2">Valor Sugerido:</label>
							<div class="col-xs-10">
								<input type="text" class="col-xs-10" name="servico.valorSugerido" id="servico.valorSugerido" value="${servico.valorSugerido}" />
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
							Salvar (desabilitado na versão de demonstração)
						</button>
					</div>
				</div>
				</security:authorize>
				 </fieldset>
			  </form>
			 </div>
</body>
</html>