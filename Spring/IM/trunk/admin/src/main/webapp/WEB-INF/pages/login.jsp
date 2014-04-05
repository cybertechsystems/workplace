<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.png" />
<link rel="icon" type="image/ico" href="images/favicon.png" /> 
<link rel="stylesheet" type="text/css" href="styles/base.css" />
<script type="text/javascript" charset="utf-8"
	src="scripts/jquery/jquery.js"></script>

<title>AMAS HUB eOrder - Data Management System</title>
</head>
<body>
<div class="login_header">
<div id="logo"></div>
</div>
<div>
<c:choose>
<c:when test="${sessionScope.fishcoAuthId != null}">
You are logged in. <a href="menu.html" target="#">Click here</a> to proceed.
</c:when>
<c:otherwise>
<fieldset>
<form id="loginForm" method="post">
<div id="quotPan">
<h3>
<span>Please login </span>
</h3>
<input class="txt" type="text" value="User Name" name="username">
<input class="txt" type="password" value="Password" name="password">
<input class="btn" type="submit" value="Login" name="login">
</div>
</form>
</fieldset>
</c:otherwise>
</c:choose>
</div>
<!-- <div class="footer">
 <div id="foot_cen">
 <h6><a href="http://www.amashub.com" target="_blank">eOrder</a></h6>
    <p>Powered by AMAS HUB Pte. Ltd &copy; 2012</p>
 </div> -->
</div>
</body>
</html>
