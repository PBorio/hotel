<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Produtos</title>

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
					<li class="active"><a href="<c:url value='/produtos/novo'/>">Novo Produto</a></li>
					<li class="active"><a href="<c:url value='/painel/'/>">Home</a></li>
				</ul>
				<h3 class="text-muted">Produtos</h3>
			</div>

			<table class="table table-striped table-bordered" id="example"
					cellpadding="0" cellspacing="0" border="0" width="100%">
				<thead> 
					<tr>
						<th class="ui-state-default" width="80%">Descrição</th>
						<th class="ui-state-default" width="10%">Preço</th>
						<th class="ui-state-default" width="10%"></th>
					</tr>
				</thead>
				
				<tfoot>
					<c:forEach var="produto" items="${produtoList}">
						<tr id="produto-${produto.id}">
							<th><a href="<c:url value='/produtos/${produto.id}'/>" title="title">${produto.descricao}</a></th>
							<th><a href="<c:url value='/produtos/${produto.id}'/>" title="title">${produto.preco}</a></th>
							<th><a href="" title="Delete" onclick="remove(${produto.id}); return false;"><img width="16px" height="16px"
									src="<c:url value="/resources/imagens/icons/cross.png"/>"
									alt="Delete"/></a> 
							</th>
						</tr>
					</c:forEach>
				</tfoot>
			</table>
	</div>
</body>
</html>