<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estadia</title>
</head>
<body>
	 
	    <c:if test="${not empty mensagem}">
			<p class="mensagem">
				${mensagem}
			</p>
		</c:if>

		<div class="widget-title">
			<span class="icon">
				<i class="icon-align-justify"></i>									
			</span>
			<h5>Estadia</h5>
		</div>
		<div class="widget-box">
			<div class="widget-content nopadding">
			  <form class="form-horizontal" method="post">
		    		<input type="hidden" name="estadia.id" value="${estadia.id}" />
					<div class="control-group">
						<label class="control-label">Quarto:</label>
						<div class="controls">
							<input id="estadia.quarto.numero" type="text" name="estadia.quarto.numero" value="${estadia.quarto.numero}" readonly="readonly" />
						</div>
					</div>
					<c:forEach var="hospede" items="${estadia.hospedes}">
						<div class="control-group">
							<label class="control-label">Hospede:</label>
							<div class="controls">
								<input id="estadia.quarto.numero" type="text" name="estadia.quarto.numero" value="${hospede.nome}" readonly="readonly" />
							</div>
						</div>
					</c:forEach>
					<div class="control-group">
						<label class="control-label">Checkin:</label>
						<div class="controls">
							<input id="estadia.dataCheckin" type="text" name="estadia.dataCheckin" value="<joda:format pattern='dd/MM/yyyy' value='${estadia.dataCheckin}'/>" readonly="readonly" />
						</div>
					</div>
			  </form>
			</div>
		</div>
					
</body>
</html>