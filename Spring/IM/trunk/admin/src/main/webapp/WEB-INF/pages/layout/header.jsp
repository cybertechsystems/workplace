<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="header" class="header">
	<div id="logo"></div>
	<ul class="nav-list" id="navigationMenu">
		<li>
			<a href="menu.html">
				Menu
			</a>
		</li>
		<li>
			<a href="ads.html">
				Promotions
			</a>
		</li>
		<li>
			<a href="category.html">
				Category
			</a>
		</li>
		<li>
			<a href="order.html">
				Order
			</a>
		</li>
		<li>
			<a href="setting.html">
				Settings
			</a>
		</li>
	</ul>
	<input type="hidden" id="context" name="context" value="${context}" />
</div>
