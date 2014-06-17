<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Categorias</title>

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
					<li class="active"><a href="<c:url value='/politicas/novo'/>">Nova Política</a></li>
					<li class="active"><a href="<c:url value='/'/>">Home</a></li>
				</ul>
				<h3 class="text-muted">Políticas de Preço</h3>
			</div>
		
			<table class="table table-striped table-bordered" id="example"
					cellpadding="0" cellspacing="0" border="0" width="100%">
				<thead> 
					<tr>
						<th class="ui-state-default" width="40%">Descrição</th>
						<th class="ui-state-default" width="40%">Categoria</th>
						<th class="ui-state-default" width="20%">Valor</th>
						<th class="ui-state-default" width="20%"></th>
					</tr>
				</thead>
				
				<tfoot>
					<c:forEach var="politicaDePrecos" items="${politicaDePrecosList}">
						<tr id="categoria-${categoria.id}">
							<td><a href="<c:url value='/politicas/${politicaDePrecos.id}'/>" title="title">${politicaDePrecos.descricao}</a></td>
							<td><a href="<c:url value='/politicas/${politicaDePrecos.id}'/>" title="title">${politicaDePrecos.categoria.descricao}</a></td>
							<td>${politicaDePrecos.valorDiaria}</td>
							<td>
								<a href="" title="Delete"
								onclick="remove(${politicaDePrecos.id}); return false;"><img width="16px" height="16px"
									src="<c:url value="/resources/imagens/icons/cross.png"/>" alt="Delete"/></a> 
							</td>
						</tr>
					</c:forEach>
				</tfoot>
			</table>
	</div>
</body>
</html>