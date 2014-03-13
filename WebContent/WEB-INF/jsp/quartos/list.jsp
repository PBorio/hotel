<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Quartos</title>

<script type="text/javascript">

// $(function(){
	
//    CriarPaginacao("<c:url value='/produtos/getPaginas.json'/>","/produtos/list");	
	
// });

//    function remove(id){
// 	   $.get('remove?id=' + id, function(){
// 		   //alert('Pedido removido com sucesso!!');
// 		   $('#quadro-'+id).fadeOut('slow');
// 	   });
//    }
   
//    function edit(){
// 	   var firstBox = $("input:checked").get(0);
//   		if( firstBox == undefined){
//   			alert("Nenhum produto foi selecionado!!!");
//   		}
//   		else{
//   			$("#icon-edit").prop("href","<c:url value='/produtos/"+ firstBox.id.substring(8,firstBox.id.length) +"'/>");
//   		}
//     }

</script>

</head>

<body>


		<ul class="shortcut-buttons-set">
			<!-- Replace the icons URL's with your own -->

			<li><a class="shortcut-button"
				href="<c:url value='/quartos/novo'/>"><span> <img class="novo" src="../resources/imagens/icons/novo32.png"/><br />
						Novo
				</span></a></li>
		</ul>
		<!-- End .shortcut-buttons-set -->
		<div class="clear"></div>
		<!-- End .clear -->
		
		<br>


			<div class="content-box-header">
				<c:if test="${not empty mensagem}">
					<p class="mensagem">
						${mensagem}
					</p>
				</c:if>
				<h3>Quartos</h3>

				<div class="clear"></div>

			</div>
			<!-- End .content-box-header -->

					
			<table class="table table-bordered data-table dataTable">
	
				<thead>
					<tr>
						<th class="ui-state-default" width="10%">Número</th>
						<th class="ui-state-default" width="70%">Descrição</th>
						<th class="ui-state-default" width="10%"></th>
					</tr>
	
				</thead>
				
				<tbody>
					<c:forEach var="quarto" items="${quartoList}">
						<tr class="gradeA" id="quarto-${quarto.id}">
							<td class="sorting_1"><a href="<c:url value='/quartos/${quarto.id}'/>" title="title">${quarto.numero}</a></td>
							<td><a href="<c:url value='/quartos/${quarto.id}'/>" title="title">${quarto.descricao}</a></td>
							<td>
								<a href="" title="Delete"
								onclick="remove(${quarto.id}); return false;"><img width="16px" height="16px"
									src="<c:url value="/resources/imagens/icons/cross.png"/>"
									alt="Delete" /></a> 
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

</body>
</html>