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
					<h1>Em breve um email com as informações de acesso para a versão de demonstração será enviado para ${acesso.email}.</h1>
				</div>
				<!-- End Banner Content-->

				
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
					<h4>R$ 185,00/mês</h4>
					<p>Produto totalmente web. Nesse preço já estão inclusos instalação e manutenção de servidores, configuração inicial do sistema, treinamento a distância e customização do produto.</p>
				</div>
				<!-- End Main Point 1 -->
			</div>	
			
		</div>
	</section>
	<!-- End Intro -->


	
	
  </body>
</html>
