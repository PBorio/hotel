<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"  prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title><decorator:title default="Weblogia"/></title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap-responsive.min.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/fullcalendar.css'/>" />	
		<link rel="stylesheet" href="<c:url value='/resources/css/unicorn.main.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/select2.css" />' />
		<link rel="stylesheet" href="<c:url value='/resources/css/uniform.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/unicorn.grey.css'/>"  class="skin-color" />
		<link rel="stylesheet" href="<c:url value='/resources/css/kanban.css'/>" />
		<decorator:head />
		
	</head>
	<body>
		
		
		<div id="header">
			<h1><a href="./dashboard.html">Unicorn Admin</a></h1>		
		</div>
		
		<div id="search">
			<input type="text" placeholder="Search here..."/><button type="submit" class="tip-right" title="Search"><i class="icon-search icon-white"></i></button>
		</div>
		<div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">
                <li class="btn btn-inverse" ><a title="" href="#"><i class="icon icon-user"></i> <span class="text">Profile</span></a></li>
                <li class="btn btn-inverse dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">Messages</span> <span class="label label-important">5</span> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a class="sAdd" title="" href="#">new message</a></li>
                        <li><a class="sInbox" title="" href="#">inbox</a></li>
                        <li><a class="sOutbox" title="" href="#">outbox</a></li>
                        <li><a class="sTrash" title="" href="#">trash</a></li>
                    </ul>
                </li>
                <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Settings</span></a></li>
                <li class="btn btn-inverse"><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
            </ul>
        </div>
            
		<div id="sidebar">
			<a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
			<ul>
				<li class="active"><a href="<c:url value='/quartos/list'/>"><i class="icon icon-home"></i> <span>Quartos</span></a></li>
				<li class="active"><a href="<c:url value='/categorias/list'/>"><i class="icon icon-home"></i> <span>Categorias</span></a></li>
				<li class="active"><a href="<c:url value='/politicas/list'/>"><i class="icon icon-home"></i> <span>Preços</span></a></li>
				<li class="active"><a href="<c:url value='/reservas/reserva'/>"><i class="icon icon-home"></i> <span>Reservas</span></a></li>
				<li class="active"><a href="<c:url value='/reservas/consulta'/>"><i class="icon icon-home"></i> <span>Consulta</span></a></li>
			</ul>
		
		</div>
		
		<div id="style-switcher">
			<i class="icon-arrow-left icon-white"></i>
			<span>Style:</span>
			<a href="#grey" style="background-color: #555555;border-color: #aaaaaa;"></a>
			<a href="#blue" style="background-color: #2D2F57;"></a>
			<a href="#red" style="background-color: #673232;"></a>
		</div>
		
		<div id="content">
			<div id="content-header">
				<h1>Dashboard</h1>
				<div class="btn-group">
					<a class="btn btn-large tip-bottom" title="Manage Files"><i class="icon-file"></i></a>
					<a class="btn btn-large tip-bottom" title="Manage Users"><i class="icon-user"></i></a>
					<a class="btn btn-large tip-bottom" title="Manage Comments"><i class="icon-comment"></i><span class="label label-important">5</span></a>
					<a class="btn btn-large tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a>
				</div>
			</div>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#" class="current">Dashboard</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12 center" style="text-align: center;">					
						 <decorator:body/>
					</div>	
				</div>
			</div>
			
		</div>
		<div class="row-fluid">
				<div id="footer" class="span12">
					2014 &copy; Weblogia <a href="http://www.weblogia.com.br">Weblogia</a>
				</div>
			</div>

            <script src="/resources/scripts/excanvas.min.js"></script>
            <script src="/resources/scripts/jquery.min.js"></script>
            <script src="/resources/scripts/jquery.ui.custom.js"></script>
            <script src="/resources/scripts/bootstrap.min.js"></script>
            <script src="/resources/scripts/jquery.flot.min.js"></script>
            <script src="/resources/scripts/jquery.flot.resize.min.js"></script>
            <script src="/resources/scripts/jquery.peity.min.js"></script>
            <script src="/resources/scripts/fullcalendar.min.js"></script>
            <script src="/resources/scripts/unicorn.js"></script>
            <script src="/resources/scripts/unicorn.dashboard.js"></script>
            
           
	</body>
</html>
