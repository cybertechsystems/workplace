<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" scope="application"/>
<html>
	<head>
		<meta name="keywords" content="CTECH-Inventory Management" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT" />

		<link rel="shortcut icon" href="images/favicon.png" />
		<link rel="icon" type="image/ico" href="images/favicon.png" />
		<link rel="stylesheet" type="text/css" href="styles/base.css" />
		<link rel="stylesheet" type="text/css" media="screen"
			href="styles/datePicker.css" />

		<link rel="stylesheet" type="text/css" media="screen"
			href="styles/dot-luv/jquery-ui-1.9.2.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen"
			href="styles/datatable.css" />
		<link rel="stylesheet" type="text/css" media="screen"
			href="styles/jquery-ui-timepicker-addon.css" />
			
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery-1.8.3.js"><!-- -->
		</script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery-ui-1.9.2.custom.js"><!-- -->
		</script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery.jeditable.js"><!-- -->
		</script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery.dataTables.js"><!-- -->
		</script>
		
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery.ui.datepicker.js"><!-- -->
		</script>
		<script type="text/javascript" charset="utf-8" 
			src="scripts/keytable/KeyTable.min.js"><!-- -->
		</script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/common.js"><!-- -->
		</script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery-ui-timepicker-addon.js"><!-- -->
		</script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/ui.dropdownchecklist.js"><!-- -->
		</script>
		<script type="text/javascript" charset="utf-8" src="scripts/jquery/date.js"><!-- -->
		</script>
	</head>

	<body>
		<c:if test="${sessionScope.fishcoAuthId != null}">
			<tiles:insertAttribute name="header" />
		</c:if>
		<tiles:insertAttribute name="menu" />
		<c:if test="${sessionScope.fishcoAuthId != null}">
			<div id="contentList" class="content">
				<tiles:insertAttribute name="content" />
			</div>
		</c:if>
			<div class="footer">
				<div id="foot_cen"></div>
			</div>
	</body>
</html>
