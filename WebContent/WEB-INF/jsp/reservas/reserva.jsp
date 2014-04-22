<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faça sua Reserva</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/datepicker.css'/>" />
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
		 <div class="row">
	        <div class="col-md-4">
	             <form class="form-horizontal" action='<c:url value="/politicas/salva"/>' method="post">
	             	<div class="form-group">
						<label class="col-sm-2">Inicio:</label>
						<div class="col-sm-7">
							<input type="text" style="font-size: 14px;" class="col-sm-10" name="reserva.inicio" id="reserva.inicio" value="<fmt:formatDate value='${politicaDePrecos.inicio}'/>" />
						</div>
					  </div> 
					  <div class="form-group">
							<label class="col-sm-2">Fim:</label>
							<div class="col-sm-7">
								<input type="text" style="font-size: 14px;" class="col-sm-10" name="reserva.fim" id="reserva.fim" value="<fmt:formatDate value='${politicaDePrecos.fim}'/>"/>
							</div>
					  </div> 
	             </form>
	        </div>
	        <div class="col-md-8">
	            Body content
	        </div>
    	</div>
	</div>

<script src="<c:url value='/resources/scripts/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/scripts/bootstrap-datepicker.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$('#reserva\\.inicio').datepicker({
			format: 'dd/mm/yyyy'
		});
		$('#reserva\\.fim').datepicker({
			format: 'dd/mm/yyyy'
		});
	});
</script>
</body>
</html>