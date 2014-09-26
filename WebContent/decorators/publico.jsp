<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"  prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Seu Hotel</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="shortcut icon" href="favicon.ico">

<!-- Stylesheets -->


<link rel="stylesheet" href="<c:url value='/resources/public/css/animate.css'/>" >
<link rel="stylesheet" href="<c:url value='/resources/public/css/bootstrap.css'/>" >
<link rel="stylesheet" href="<c:url value='/resources/public/css/font-awesome.min.css'/>" >
<link rel="stylesheet" href="<c:url value='/resources/public/css/owl.carousel.css'/>" >
<link rel="stylesheet" href="<c:url value='/resources/public/css/owl.theme.css'/>" >
<link rel="stylesheet" href="<c:url value='/resources/public/css/prettyPhoto.css'/>" >
<link rel="stylesheet" href="<c:url value='/resources/public/css/smoothness/jquery-ui-1.10.4.custom.min.css'/>" >
<link rel="stylesheet" href="<c:url value='/resources/public/rs-plugin/css/settings.css'/>" >
<link rel="stylesheet" href="<c:url value='/resources/public/css/theme.css'/>" >

<c:choose>
  <c:when test="${empty hotel}">
	<link rel="stylesheet" href="<c:url value='/resources/public/css/colors/demonstracao.css'/>" >
  </c:when>
  <c:otherwise>
  	<link rel="stylesheet" href="<c:url value='/resources/public/css/colors/pordosol.css'/>" >
  </c:otherwise>
</c:choose>
<link rel="stylesheet" href="<c:url value='/resources/public/css/responsive.css'/>" >
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600,700">


<decorator:head />
</head>

<body>



<!-- Header -->
<header>
  <!-- Navigation -->
  <div class="navbar yamm navbar-default" id="sticky">
    <div class="container">
      <div class="navbar-header">
        <button type="button" data-toggle="collapse" data-target="#navbar-collapse-grid" class="navbar-toggle"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        <a href="index.html" class="navbar-brand">         
        <!-- Logo -->
        <div class="logo">Logo</div>
        </a> </div>
      <div id="navbar-collapse-grid" class="navbar-collapse collapse">
       
      </div>
    </div>
  </div>
</header>


<section class="parallax-effect">
  <div >
    <div class="color-overlay"> 
      <!-- Page title -->
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <h1>Reservas On-Line</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<decorator:body />

<!--  -->

<!-- Footer -->
<footer>
  <div class="container">
    <div class="row">
      <div class="col-md-3 col-sm-3">
        <h4>About Starhotel</h4>
        <p>Suspendisse sed sollicitudin nisl, at dignissim libero. Sed porta tincidunt ipsum, vel volutpat. <br>
          <br>
          Nunc ut fringilla urna. Cras vel adipiscing ipsum. Integer dignissim nisl eu lacus interdum facilisis. Aliquam erat volutpat. Nulla semper vitae felis vitae dapibus. </p>
      </div>
      <div class="col-md-3 col-sm-3">
        <h4>Recieve our newsletter</h4>
        <p>Suspendisse sed sollicitudin nisl, at dignissim libero. Sed porta tincidunt ipsum, vel volutpa!</p>
        <form role="form">
          <div class="form-group">
            <input name="newsletter" type="text" id="newsletter" value="" class="form-control" placeholder="Please enter your E-mailaddress">
          </div>
          <button type="submit" class="btn btn-lg btn-black btn-block">Submit</button>
        </form>
      </div>
      <div class="col-md-3 col-sm-3">
        <h4>From our blog</h4>
        <ul>
          <li><a href="#">Amazing post with all the goodies<br>
            January 23, 2014</a></li>
          <li><a href="#">Integer dignissim nisl eu lacus<br>
            January 21, 2014</a></li>
          <li><a href="#">Aliquam erat volutpat. Nulla semper<br>
            January 14, 2014</a></li>
        </ul>
      </div>
      <div class="col-md-3 col-sm-3">
        <h4>Address</h4>
        <address>
        <strong>Twitter, Inc.</strong><br>
        795 Folsom Ave, Suite 600<br>
        San Francisco, CA 94107<br>
        <abbr title="Phone">P:</abbr> <a href="#">(123) 456-7890</a><br>
        <abbr title="Email">E:</abbr> <a href="#">mail@example.com</a><br>
        <abbr title="Website">W:</abbr> <a href="#">www.slashdown.nl</a><br>
        </address>
      </div>
    </div>
  </div>
  <div class="footer-bottom">
    <div class="container">
      <div class="row">
        <div class="col-xs-6"> &copy; 2014 Starhotel All Rights Reserved </div>
        <div class="col-xs-6 text-right">
          <ul>
            <li><a href="contact-01.html">Contact</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</footer>

<!-- Go-top Button -->
<div id="go-top"><i class="fa fa-angle-up fa-2x"></i></div>


<!-- Javascripts --> 
<script type="text/javascript" src="<c:url value='/resources/public/js/jquery-1.11.0.min.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/resources/public/js/bootstrap.min.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/resources/public/js/bootstrap-hover-dropdown.min.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/resources/public/js/owl.carousel.min.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/resources/public/js/jquery.parallax-1.1.3.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/resources/public/js/jquery.nicescroll.js'/>" ></script>  
<script type="text/javascript" src="<c:url value='/resources/public/js/jquery.prettyPhoto.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/resources/public/js/jquery-ui-1.10.4.custom.min.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/resources/public/js/jquery.jigowatt.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/resources/public/js/jquery.sticky.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/resources/public/js/waypoints.min.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/resources/public/js/custom.js'/>" ></script> 

</body>
</html>