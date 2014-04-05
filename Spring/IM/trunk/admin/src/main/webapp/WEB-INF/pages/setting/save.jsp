<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="settingSave"  class="content">
	<script type="text/javascript" charset="utf-8">
		$("#settingManageForm").ready(
			function() {
				$("#settingName").change(validateName);
				$("#savebutton").unbind();
				$('#savebutton').click(
					function() {
						if(validateSettingFields()) {
							formProcess($("#settingManageForm").attr("action"), 'settingManageForm', 'contentList');
						}
					}
				);
			}
		);
	</script>
	<form:form action="${context}/settingSave.html"
		method="POST" id="settingManageForm"
		commandName="setting">
		<!-- For Validation of errors -->
		<div class="validationErrors" id="errorMsgTset"></div>
		<div class="clear"></div>
		<form:hidden path="settingID" />
		<input type="hidden" id="settingNameOrg" value="${setting.settingName}" />
		<div class="row">
			<label for="settingName">
				 Name <span class="required"> * </span>
			</label>
			<form:input path="settingName" id="settingName" class="txt"/>
			<img id="validationNameImage" alt="" src="${context}/images/white_dot.gif" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="settingDescription">
				 Description <span  class="required"> * </span>
			</label>
			<form:textarea path="settingDescription" id="settingDescription" class="txt" rows="6"/>
			<img id="validationDescriptionImage" alt="" src="${context}/images/white_dot.gif" />
		</div>
		<div class="clear"> <!--  --></div>
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