<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="promotionsSave">
		<div class="clear"></div>
		
		<input type="hidden" id="selecteddesc" name="selecteddesc" value="" />
		
		<!-- For Validation of errors -->
		<div class="validationErrors" id="errorMsgTset"></div>

		<div class="clear"></div>
		<div class="row">
			<label for="description">
				Description
			</label>
			<form:input path="description" id="description" class="txt"/>
			<img id="validationDescImage" alt="" src="${context}/images/white_dot.gif" />
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label for="descriptionCN">
				Chinese Description
			</label>
			<form:input path="descriptionCN" id="descriptionCN" class="txt"/>
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label for="iPodOnly">
				Is IPhone Only
			</label>
			<button type="button" name="yes" class="yesnoIphoneBtn" id="iPodOnly">YES</button>
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label for="iPadOnly">
				Is IPad Only
			</label>
			<button type="button" name="yes" class="yesnoIpadBtn" id="iPadOnly">YES</button>
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label for="adsStartDate">
				Start Date
			</label>
			<form:input path="adsStartDate" id="adsStartDate" class="txt"/>
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label for="adsEndDate">
				End Date
			</label>
			<form:input path="adsEndDate" id="adsEndDate" class="txt"/>
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label>
				Start Time
			</label>
			<form:input path="promotionsStartTime" id="startTime" class="timebtn"/>
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label>
				End Time
			</label>
			<form:input path="promotionsEndTime" id="endTime" class="timebtn"/>
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label>
				Day Of Week
			</label>
			<form:select path="promotionsDay" id="promotionsDay" class="input_dropdown_day" multiple="true">
				<option value=" ">Select Promotions Day</option>
				<form:options items="${promotionsDayList}" />
			</form:select>
		</div>
</div>