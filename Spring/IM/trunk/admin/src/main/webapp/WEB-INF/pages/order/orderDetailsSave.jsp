<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="orderDetailsSave"  class="content">
	<script type="text/javascript" charset="utf-8">
		$("#orderDetailsManageForm").ready(
			function() {
				$('#timestamp').datetimepicker({dateFormat: 'yy-mm-dd',timeFormat: 'HH:mm:ss'});
				$("#menuItemNo").change(validateMenuItemNo);
				$("#quantity").change(validateQuantity);
				$('#savebutton').click(
					function() {
						if(validateOrderFields()) {
							$('#orderDetailsManageForm').submit();
						}
					}
				);
			}
		);
	</script>
	
	<form:form action="${context}/orderDetailsSave.html"
		method="POST" id="orderDetailsManageForm"
		commandName="details">
		
		<!-- For Validation of errors -->
		<div class="validationErrors" id="errorMsgTset"></div>
		<input type="hidden" id="detailIdOrg" value="${details.detailId}" />
		<div class="clear"></div>
		<div class="row">
			<label for="detailId">
				Detail ID
			</label>
			<form:input path="detailId" id="detailId" class="txt onlyRead" readOnly="readonly"/>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="checkId">Check ID</label>
			<form:input path="checkId" id="checkId" class="txt onlyRead" readOnly="readonly"/>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="menuItemNo">
				Menu Item Number
			</label>
			<form:input path="menuItemNo" id="menuItemNo" class="txt"/>
			<img id="validationNoImage" alt="" src="${context}/images/white_dot.gif" />
		</div>
		<div class="clear"></div>
		<div class="clear"></div>
		<div class="row">
			<label for="isCondiment">
				Main Item ID
			</label>
			<form:input path="isCondiment" id="isCondiment" class="txt"/>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="quantity">
				Quantity
			</label>
			<form:input path="quantity" id="quantity" class="txt"/>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="isUpdated">
				IsUpdated
			</label>
			<form:input path="isUpdated" id="isUpdated" class="txt onlyRead" readOnly="readonly"/>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Check Open Time
			</label>
			<form:input path="timestamp" id="timestamp" class="txt"  />
		</div>
		
		<!-- Save,Cancel Buttons -->
		<div class="button_row">
			<button type="button" name="Save" class="addBtn" id="savebutton"
				value="Save" >
				Save
			</button>
		</div>
		<!--/ Save,Cancel Buttons -->
	</form:form>
</div>