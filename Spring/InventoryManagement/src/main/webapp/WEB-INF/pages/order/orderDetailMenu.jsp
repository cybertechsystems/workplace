<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8" src="scripts/orderDetails.js"><!-- --></script>

<div id="menu" class="menu">
	<ul class="nav-list">
		<li>
			<a href="#" id="EditMenu">
				<img height="10px" width="10px"   alt="" src="${context}/images/edit.png"/> Edit
			</a>
		</li>
		<li>
			<a href="#" id="DeleteMenu">
				<img height="10px" width="10px"  alt="" src="${context}/images/remove.png"/> Delete
			</a>
		</li>
	</ul>
	<div id="editorPanel"  title="Order Details" class="dialog" style="display:none;"></div>
</div>