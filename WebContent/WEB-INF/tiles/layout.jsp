<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<tiles:importAttribute name="title" scope="request" />
<tiles:importAttribute name="css" scope="request" />
<tiles:importAttribute name="js" scope="request" />
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="${title}" /></title>
<meta charset="utf-8">
<meta http-equiv="Content-Security-Policy" content="default-src *;script-src * 'unsafe-inline' 'unsafe-eval';style-src 'self' 'unsafe-inline' 'unsafe-eval'">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="Keywords" content="" />
<meta name="msapplication-TileImage" content="images/bootstrap/metis-tile.png" />
<link rel="stylesheet" href="style/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="style/font-awesome/font-awesome.min.css">
<link rel="stylesheet" href="style/linearicons/style.css">
<link rel="stylesheet" href="style/chartist/chartist-custom.css">
<link rel="stylesheet" href="style/main.css">
<link rel="stylesheet" href="style/demo.css">
<link rel="apple-touch-icon" sizes="76x76" href="images/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96" href="images/favicon.png">
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
<script src="scripts/jquery/jquery.min.js"></script>
<script src="scripts/bootstrap/bootstrap.min.js"></script>
<script src="scripts/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="scripts/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
<script src="scripts/chartist/chartist.min.js"></script>
<script src="scripts/klorofil-common.js"></script>
<script src="scripts/main.js"></script>
<c:if test="${!empty css}">
	<link href="<tiles:getAsString name="css"/>" type="text/css" rel="stylesheet" />
</c:if>
<c:if test="${!empty js}">
	<script src="<tiles:getAsString name="js"/>"></script>
</c:if>
</head>
<body>
	<div id="wrapper">
		<div>
			<tiles:insertAttribute name="header" />
		</div>
		<div>
			<tiles:insertAttribute name="left" />
		</div>
		<div>
			<tiles:insertAttribute name="body" />
		</div>
		<div>
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>