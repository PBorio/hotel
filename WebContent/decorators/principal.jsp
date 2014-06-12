<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"  prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title><decorator:title default="Weblogia"/></title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap-theme.css'/>" />
		<script src=" <c:url value='/resources/scripts/jquery-1.7.1.min.js'/>"></script>
		<decorator:head />
		
	</head>
	<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle"
						data-toggle="collapse" data-target=".navbar-collapse">
				</button>
				<a class="navbar-brand" href="<c:url value='/painel/painel'/>">Hotel</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<c:url value='/checkin/list'/>">Checkin</a></li>
					<li><a href="<c:url value='/reservas/reserva'/>">Reservas</a></li> 
					<li><a href="<c:url value='/reservas/consulta'/>">Consulta</a></li>
					<li><a href="<c:url value='/quartos/list'/>">Quartos</a></li>
					<li><a href="<c:url value='/categorias/list'/>">Categorias</a></li>
					<li><a href="<c:url value='/politicas/list'/>">Preços</a></li>
				</ul>
				<div class="nav navbar-nav navbar-right">
					<div class="panel panel-default">
						<c:if test="${not empty reservasView}">
							${reservasView}
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="starter-template">
			<h1>Hotel</h1>
			<p class="lead">Os melhores preços</p>
		</div>
	</div>
		<div class="jumbotron">
			 <decorator:body/>
		</div>
        
	</body>
</html>
