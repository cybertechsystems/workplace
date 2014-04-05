<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="styles/keytable/KeyTable.css" />
		<link rel="shortcut icon" href="images/favicon.png" />
		<link rel="icon" type="image/ico" href="images/favicon.png" /> 
		<link rel="stylesheet" type="text/css" href="styles/base.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="styles/datePicker.css">
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery.jeditable.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery.dataTables.min.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/keytable/KeyTable.min.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery.ui.datepicker.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/date.js"></script>
		<script type="text/javascript" charset="utf-8"
			src="scripts/jquery/jquery.validate.js"></script>
		<script type="text/javascript" charset="utf-8" src="scripts/settings.js"></script>
		<title>AMAS HUB eOrder - Data Management System</title>
	</head>
	<body>
		<div class="header">
			<div id="logo"></div>
			<ul class="nav-list">
    			<li><a href="menu.html">Menu</a></li>
   				<li><a href="order.html">Order</a></li>    
   				<li><a href="ads.html">Promotions</a></li>
 				<li><a href="category.html">Category</a></li>
   				<li><a href="itemDetail.html">Combo Details</a></li>    
    			<li><a href="setting.html" class="active">Settings</a></li>    
    			<li><a href="logout.html">Logout</a></li>
			</ul>
		</div>

		<div class="content">
			<div class="dataTables_wrapper">
				<div class="dataTables_length"></div>
				<div class="dataTables_filter"></div>
				<table id="main-table" class="display KeyTable">
					<thead>
						<tr>
							<th class="sorting">settingID</th>
							<th class="sorting">settingName</th>
							<th class="sorting">settingDescription</th>						
						</tr>
					</thead>
					<tbody>
						<c:forEach var="setting" items="${settings}" varStatus="ctr">
							<tr>			    
								<td class="locked">${setting.settingID}</td>
								<td class="locked">${setting.settingName}</td>
								<td id="${setting.settingID}_settingDescription">${setting.settingDescription}</td>								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="dataTables_info"></div>
				<div class="dataTables_paginate"></div>
			</div>
		</div>
		<div class="footer">
 			<div id="foot_cen">
				 <h6><a href="http://www.amashub.com" target="_blank">eOrder</a></h6>
   				 <p>Powered by AMAS HUB Pte. Ltd &copy; 2013</p>
			 </div>
		</div>
	</body>
</html>
