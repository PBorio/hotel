<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"  prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="<c:url value='/resources/css/reservados.css'/>" type="text/css" media="screen" />
  <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" type="text/css" media="screen" />
  <script type="text/javascript" src="<c:url value='/resources/scripts/jquery-1.7.1.min.js'/>"></script>    
 <script type="text/javascript">                                         
   jQuery(document).ready(function() {
		jQuery('.livre').hover(mouseOver, mouseOut);
   });                   

   function mouseOver(){
     //alert('mouseOver');
	 jQuery(this).addClass('over');
   }   
   
   function mouseOut(){
     jQuery(this).removeClass('over');
   }
 </script>     
  
</head>
<body>
<div class="container">
    <header>
		<a href="home.php" class="logo">Pousada Pôr do Sol</a>		
		<nav>
			<ul>
				<li>
					<a href="<c:url value='/quartos/list'/>" id="nav-acomodacoes">Quartos</a>
				</li>
				<li><a href="<c:url value='/categorias/list'/>" id="nav-pousada">Categorias</a></li>
				<li><a href="<c:url value='/politicas/list'/>" id="nav-reservas">Preços</a></li>
				<li><a href="<c:url value='/reservas/reserva'/>" id="nav-contato">Reservas</a></li>
				<li><a href="<c:url value='/reservas/consulta'/>">Consulta</a></li>
			</ul>
		</nav>
		 <nav>
	  <ul>
		<li><a href="<c:url value='/quartos/list'/>" id="menu-quarto">Quartos</a></li>
		<li><a href="<c:url value='/categorias/list'/>" id="menu-cat">Categorias</a></li>
		<li><a href="<c:url value='/reservas/reserva'/>" id="menu-reservas">Reservas</a></li>
		<li><a href="<c:url value='/politicas/list'/>" id="menu-preco">Preços</a></li>
		<li><a href="<c:url value='/reservas/consulta'/>" id="menu-consulta">Consultas</a></li>
	</ul>
	</nav>
		<div class="clear"></div>
	</header>
	<section class="inner">
		<div class="content-inner content-form">
		  <decorator:body/>
		</div>
	</section>
</div>
<div class="footer-container">
	<footer>
		
		<dl>
			<dt>Pousada Pôr do Sol</dt>
			<dd>
            	<a href="https://docs.google.com/document/pub?id=1_jGgyyCdPe6UJNyi7ijKYyh2xzqDp2RXj9EqJKJWHbM#h.iqwtgz1knmc9" class="como-chegar">Veja como chegar.</a>
				Nova Brasília - Ilha do Mel - PR<br />
				<a href="mailto:reservas@pousadapordosol.com.br">ilhapordosol@gmail.com</a><br />
				<strong>Tel:</strong> 55 41 3426-8009<br />
				<strong>Cel:</strong> 55 41 7816-2223<br />
				<strong>Fax:</strong> 55 41 3426-8120<br />
				<strong>ID NEXTEL:</strong> 105*1880<br />
				<strong>Skype:</strong> pousada_por_do_sol
			</dd>
		</dl>
		
		<dl class="last">
			<dt>Siga, curta e compartilhe:</dt>
			<dd>
				<ul class="social-media">
					<li><a href="http://www.pousadapordosol.com.br/blog"><img src="<c:url value='/resources/imagens/icon-blogger.png'/>" alt="Blog" /></a></li>

					<li><a href="http://www.facebook.com/pousadapds"><img src="<c:url value='/resources/imagens/icon-fb.png'/>" alt="Facebook" /></a></li>
					
					<li><a href="http://www.youtube.com/user/PorDoSolPousada"><img src="<c:url value='/resources/imagens/icon-yt.png'/>" alt="Youtube" /></a></li>
					
					<li><a href="mailto:ilhapordosol@gmail.com"><img src="<c:url value='/resources/imagens/icon-mail.png'/>" alt="E-mail" /></a></li>
				</ul>
				<div class="clear"></div>
			</dd>
			<dt>Formas de pagamento:</dt>
			<dd>
				<img src="<c:url value='/resources/imagens/footer-cards.png'/>" alt="Cartões" />
			</dd>
		</dl>
		
		<div class="clear"></div>
		
		<div class="nav-foot">
			<a href="home.php">Home</a> &bull; <a href="acomodacoes.php">Acomodações</a> &bull; <a href="pousada.php">Pousada</a> &bull; <a href="reservas.php">Reservas</a> &bull; <a href="contato.php">Contato</a> - Copyright &copy; 2014. Todos os direitos reservados. Desenvolvido por <a href="#">Primapress</a>.
		</div>
			 
	</footer>
</div>
</body>
</html>