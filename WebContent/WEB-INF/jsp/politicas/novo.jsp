<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
		  <form class="form-horizontal" action='<c:url value="/politicas/salva"/>' method="post">
		  	<fieldset>
				<legend>Políticas de Preço</legend>
			     <input type="hidden" name="politicaDePrecos.id" value="${politicaDePrecos.id}" />
			     <div class="form-group">
						<label class="control-label col-xs-2">Descrição:</label>
						<div class="col-xs-10">
							<input id="descricao" type="text" class="col-xs-10" name="politicaDePrecos.descricao" value="${politicaDePrecos.descricao}" />
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Categoria:</label>
						<div class="col-xs-10">
							<select id="politicaDePrecos.categoria.id" name="politicaDePrecos.categoria.id" class="col-xs-10">  
			                    <option value="-1"> Categorias...</option>  
			                    <c:forEach var="categoria" items="${categoriaList}">  
			                        <option value="${categoria.id}" <c:if test="${categoria.id == politicaDePrecos.categoria.id}">selected="true"</c:if>> 
			                        	${categoria.descricao} 
			                        </option>  
			                    </c:forEach>  
			                </select> 
						</div>
				 </div>
				<div class="form-group">
					<label class="control-label col-xs-2">Observação:</label>
					<div class="col-xs-10">
						<textarea name="categoria.observacao" id="categoria.observacao" class="col-xs-10">${categoria.observacao}</textarea>
					</div>
				</div>
				<div class="form-group">
						<label class="control-label col-xs-2">Inicio:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="politicaDePrecos.inicio" id="inicio" value="<fmt:formatDate value='${politicaDePrecos.inicio}'/>" />
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Fim:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="politicaDePrecos.fim" id="fim" value="<fmt:formatDate value='${politicaDePrecos.fim}'/>"/>
						</div>
				  </div> 
				  <div class="form-group">
						<label class="control-label col-xs-2">Valor:</label>
						<div class="col-xs-10">
							<input type="text" class="col-xs-10" name="politicaDePrecos.valorDiaria" id="politicaDePrecos.valorDiaria" value="${politicaDePrecos.valorDiaria}" />
						</div>
				  </div> 
				 
				 	<security:authorize ifAnyGranted="ROLE_ADMIN">
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
<script src="<c:url value='/resources/scripts/jquery-ui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/scripts/jquery.maskedinput-1.3.min.js'/>"></script>
<script type="text/javascript">

$(function() {
	$('#inicio').datepicker({  
         inline: true,  
         showOtherMonths: true,  
         dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],  
         dateFormat: 'dd/mm/yy',
         dayNames: [ "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" ],
         dayNamesMin: [ "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" ],
     });
	$("#inicio").mask("99/99/9999");
	$('#fim').datepicker({  
        inline: true,  
        showOtherMonths: true,  
        dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        dateFormat: 'dd/mm/yy',
        dayNames: [ "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" ],
        dayNamesMin: [ "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" ],
    });
	$("#fim").mask("99/99/9999");
});

</script>
</body>
</html>