<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
	<title>Web-Hotelaria</title>
	
	<!-- Meta Tags-->
    <meta charset="utf-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<!-- End Meta Tags-->

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Styesheets-->
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/land/css/bootstrap.min.css'/>"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/land/css/form.css'/>" />
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/land/css/style.css'/>"/>
	<!-- End Stylesheets-->
	
	<!-- Google Font-->
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,800,700,300" />
	<!-- End Google Font-->
	
	
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-54084127-1', 'auto');
  ga('send', 'pageview');

</script>
	
  </head>

  <body>
  
	<!-- Header -->
	<header class="header">	
		<div class="wrapper">
		
			<div class="row-fluid">
				<!-- Logo -->
				<div class="logo">
					<h1><a href="#">Web-Hotelaria</a></h1> 
				</div>
				<!-- End Logo -->
				
				
				
			</div>
			
		</div>
	</header>
	<!-- End Header -->
	
	<!-- Banner -->
	<section id="banner-area">
		<div class="wrapper">
		
			<div class="row-fluid">
				<!-- Banner Content-->
				<div class="span8 banner">
					<h1>Um sistema que você também constrói.</h1>
					<ul>
						<li>Sistema de reservas online para seus hóspedes.</li>
						<li>Calendário para acompanhamento das reservas.</li>
						<li>Políticas de Preço configuráveis por categorias de quarto e datas.</li>
						<li>Checkin e checkout com consumo, serviços de quarto e saldos a pagar.</li>
						<li>Cadastro de quartos com valor de diária e fotos para exibição nas reservas online.</li>
						<li>Além das funcionalidades que você solicita e nós implementamos.</li>
						<li>O sistema irá se adaptar à sua forma de gestão.</li>
						
					</ul>
				</div>
				<!-- End Banner Content-->

				<!-- Download / Sign Up Form -->
				<div class="span4">
					<form method="post" action='<c:url value="/solicitar/acesso/"/>' name="form-area" id="form-area"  class="form-area">
						<h1>Comece <strong>Hoje</strong></h1>
						<p>Solicite a senha de acesso à versão de demonstração, veja o que o sistema lhe oferece e já comece a analisar de que forma ele pode melhorar pra lhe atender.</p>
						<c:forEach var="error" items="${errors}">
								<h4>${error.message}</h4>
						</c:forEach>
						<input type="text" id="email" name="acesso.email" placeholder="Informe o seu Email">
						<input type="submit" name="submit" value="Enviar">
						<div class="form-area-bottom">&nbsp;</div>
					</form>
				</div>
				<!-- End Download / Sign Up Form -->
				
			</div>
			
		</div>
	</section>
	<!-- End Banner -->
	
	
	
	
	<!-- Intro -->
	<section id="intro">
		<div class="wrapper">	
		
			<!-- Headline -->
			<div class="headline">
				<h1>Um Produto Que Evolui A Cada Dia.</h1>
				<h2>Participe também dessa evolução.</h2>
			</div>	
			<!-- End Headline -->
			
			<div class="row-fluid">
				<!-- Main Point 3 -->
				<div class="span4 left-icon">
					<img  src="<c:url value='/resources/land/img/icons/settings.png'/>" alt="" title="" />
					<h4>Amplamente Configurável</h4>
					<p>Um sistema pensado para se adaptar aos diversos perfis sem perder sua unidade. </p>
				</div>	
				<!-- Main Point 3 -->
				
				<!-- Main Point 2 -->
				<div class="span4 left-icon">
					<img src="<c:url value='/resources/land/img/icons/diary.png'/>" alt="" title="" />
					<h4>Contato</h4>
					<p>pauloborio@weblogia.com.br</p>
					<p>eduardoborio@weblogia.com.br</p>
					<p>(41)3266-1186</p>
				</div>
				<!-- End Main Point 2 -->
				
				<!-- Main Point 1 -->
				<div class="span4 left-icon">
					<img src="<c:url value='/resources/land/img/icons/photos.png'/>" alt="" title="" />
					<h4>R$ 284,00/mês</h4>
					<p>Produto totalmente web. Nesse preço já estão inclusos instalação e manutenção de servidores, configuração inicial do sistema, treinamento a distância e customização do produto.</p>
				</div>
				<!-- End Main Point 1 -->
			</div>	
			
		</div>
	</section>
	<!-- End Intro -->
  </body>
</html>
