<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="orderSave"  class="content">
	<script type="text/javascript" charset="utf-8">
		$("#orderManageForm").ready(
			function() {
				$('#chkOpenTime').datetimepicker({dateFormat: 'yy-mm-dd',timeFormat: 'HH:mm:ss'}); 
				$('#orderNum').change(validateOrderNum);
				$('#savebutton').click(
					function() {
						if(validateOrderFields()) {
							$('#orderManageForm').submit();
						}
					}
				);
			}
		);
	</script>
	
	<form:form action="${context}/orderSave.html"
		method="POST" id="orderManageForm"
		commandName="order">
		
		<!-- For Validation of errors -->
		<div class="validationErrors" id="errorMsgTset"></div>
		<input type="hidden" id="checkIdOrg" value="${order.checkId}" />
		<div class="clear"></div>
		<div class="row">
			<label for="checkId">Check Id</label>
			<form:input path="checkId" id="checkId" class="txt onlyRead" readOnly="readonly"/>
			<img id="validationCheckImage" alt="" src="${context}/images/white_dot.gif" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="orderNum">
				Order Number <span class="required"> * </span>
			</label>
			<form:input path="orderNum" id="orderNum" class="txt"/>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Check Open Time
			</label>
			<form:input path="chkOpenTime" 	id="chkOpenTime" class="txt" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				IPadID
			</label>
			<form:input path="ipadId" id="ipadId" class="txt" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="isOpen">
				IsOpen
			</label>
			<form:input path="isOpen" id="isOpen" class="txt onlyRead" readOnly="readonly"/>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="isUpdated">
				IsUpdated
			</label>
			<form:input path="isUpdated" id="isUpdated" class="txt onlyRead" readOnly="readonly"/>
		</div>
		<div class="clear"></div>
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