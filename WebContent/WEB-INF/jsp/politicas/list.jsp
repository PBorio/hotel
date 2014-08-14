<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Quartos</title>

</head>

<body>
	
		
		
<div class="fluid-container">
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

<h1 id="page-header">Pol�ticas de Pre�o</h1>	
<div class="header">
	<ul class="nav nav-pills pull-right">
		<li class="active"><a href="<c:url value='/politicas/novo'/>">Nova Pol�tica</a></li>
	</ul>
</div>
			
			<!-- widget grid -->
			<section id="widget-grid" class="">
				
				<!-- row-fluid -->
				
				<div class="fluid-container">
			
			<!-- widget grid -->
			<section id="widget-grid" class="">
				
				<!-- row-fluid -->
				
				<div class="row-fluid">
					<article class="span12">
						<!-- new widget -->
						<div class="jarviswidget" id="widget-id-0">
						    <!-- wrap div -->
						    <div>
						    
						        <div class="inner-spacer"> 
						        <!-- content goes here -->
									<table class="table table-striped table-bordered responsive" id="dtable">
										<thead>
											<tr>
												<th>Descri��o</th>
												<th>Categoria</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="politicaDePrecos" items="${politicaDePrecosList}">
										    <tr class="gradeA">
												<td class="center"><a href="<c:url value='/politicas/${politicaDePrecos.id}'/>" title="title">${politicaDePrecos.descricao}</a></td>
												<td class="center"><a href="<c:url value='/politicas/${politicaDePrecos.id}'/>" title="title">${politicaDePrecos.categoria.descricao}</a></td>
												<td><a href="" title="Delete"
														onclick="remove(${politicaDePrecos.id}); return false;"><img width="16px" height="16px"
															src="<c:url value="/resources/imagens/icons/cross.png"/>" alt="Delete"/></a> </td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
							        	
							    </div>
							    <!-- end content-->
						    </div>
						    <!-- end wrap div -->
						</div>
						<!-- end widget -->
					</article>
				</div>
				
				<!-- end row-fluid -->
			</article>
		</div>
	</section>
</div>			
</body>
</html>
