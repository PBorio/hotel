<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"  prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title><decorator:title default="Weblogia"/></title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/datepicker.css'/>" type="text/css" media="screen" />
		<script src=" <c:url value='/resources/scripts/jquery-1.7.1.min.js'/>"></script>
		<decorator:head />
	</head>
	<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle"
						data-toggle="collapse" data-target=".navbar-collapse">
				</button>
				<a class="navbar-brand" href="<c:url value='/painel/'/>">Home</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<c:url value='/checkin/list'/>">Checkin</a></li>
					<li><a href="<c:url value='/reservas/'/>">Reservas</a></li> 
					<li><a href="<c:url value='/consultas'/>">Consulta</a></li>
					<li><a href="<c:url value='/quartos/list'/>">Quartos</a></li>
					<li><a href="<c:url value='/categorias/list'/>">Categorias</a></li>
					<li><a href="<c:url value='/politicas/list'/>">Preços</a></li>
				</ul>
				<div class="nav navbar-nav navbar-right">
					<div class="panel panel-default">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="page-header">
			
		</div>
	</div>
		<div class="jumbotron">
			 <decorator:body/>
		</div>
        
	</body>
</html>
