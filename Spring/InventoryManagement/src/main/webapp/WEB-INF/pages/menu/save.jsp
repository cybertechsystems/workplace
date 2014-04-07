<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="menuSave"  class="content">
 	<script type="text/javascript" charset="utf-8">
		$("#menuManageForm").ready(initializeSave);
	</script>
	
	<form:form action="${context}/menuSave.html"
		method="POST" id="menuManageForm"
		commandName="menuitem"  enctype="multipart/form-data">
		<div class="clear"></div>
		<form:hidden path="zPk" />
		<form:hidden path="zThumbImgUrl" />
		<input type="hidden" id="selecteddesc" name="selecteddesc" value="" />
		<input type="hidden" id="chineseDesc" value="${menuitem.zNameCN}" />
		<input type="hidden" id="promotionDescCN" value="${menuitem.descriptionCN}" />
		<input type="hidden" id="promotionsT" value="${menuitem.promotionsTime}" />
		<input type="hidden" id="promDay" value="${menuitem.promotionsDay}"/>
		<input type="hidden" id="adsStartDt" value="${menuitem.adsStartDate}" />
		<input type="hidden" id="adsEndDt" value="${menuitem.adsEndDate}" />
		<input type="hidden" id="isAdvertise" name="isAdvertise" value="${menuitem.isAdvertisement}"/> 
		<input type="hidden" id="isPOSComboo" name="isPOSComboo" value="${menuitem.isPOSCombo}"/>
		<input type="hidden" id="iPodOnlybtn" name="iPodOnlybtn" value="${menuitem.iPodOnly}"/>
		<input type="hidden" id="iPadOnlybtn" name="iPadOnlybtn" value="${menuitem.iPadOnly}"/>
		<input type="hidden" id="subCategories" name="subCategories" value="${menuitem.subCategories}"/>
		<input type="hidden" id="availableCategories" name="availableCategories" value=""/>
		<input type="hidden" id="menuNameOrg" value="${menuitem.zName}" />
		<input type="hidden" id="subCategoriesOrg" value="${menuitem.subCategories}"/>
		<input type="hidden" id="primaryCategoryOrg" value="${menuitem.primaryCategory}"/>
		<input type="hidden" id="compulsoryItemOrg" value="${menuitem.compulsoryItem}"/>

		<!-- For Validation of errors -->
		<div class="validationErrors" id="errorMsgTset"></div>
		<div class="row">
			<label>
				Name <span class="required"> * </span>
			</label>
			<form:input path="zName" id="zName" class="txt" />
			<img id="validationNameImage" alt="" src="${context}/images/white_dot.gif" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Chinese Name
			</label>
			<form:input path="zNameCN" id="zNameCN" class="txt" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Category <span class="required"> * </span>
			</label>
			<form:select path="categoryId" id="categoryId" class="input_dropdown">
				<option value="0">Select Category</option>
				<form:options items="${categoryList}" />
			</form:select>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Price
			</label>
			<form:input path="usualPrice" id="usualPrice" class="txt" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Base Price
			</label>
			<form:input path="basePrice" id="basePrice" class="txt" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Take Away Price
			</label>
			<form:input path="takeawayPrice" id="takeawayPrice" class="txt" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="zThumbImgUrl">
				Image
			</label>
			<input id="zThumbImgUrl" class="txtImg" type="file" name="imageFile">
		</div>
		<c:if test="${fn:length(menuitem.zThumbImgUrl)>0}">
			<div class="row">
				<label for="zThumbImgUrl">Image</label>
				<img src="${menuitem.zThumbImgUrl}" width="90px" height="90px" style="padding-top:10px;"/>
			</div>
		</c:if>
		<div class="clear"></div>
		<div class="row">
			<label>
				Sequence
			</label>
			<form:input path="sequence" id="sequence" class="txt" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Stock Left
			</label>
			<form:input path="stock" id="stock" class="txt" />
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Hide This Item
			</label>
			<button type="button" name="hidebutton" class="hideBtn" id="iPodOnly">HIDE</button>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label>
				Is Promotions
			</label>
			<button type="button" name="yes" class="yesBtn" id="isAdvertisement">YES</button>
		</div>
		<div class="clear"></div>
		<div class="promotionspage" id="promotionspage"> 
			<c:if test="${menuitem.isAdvertisement == '0'}">
				style="display:none;"
			</c:if>
			<label>
				PROMOTION'S DETAILS 
				<jsp:include page="/WEB-INF/pages/menu/promotions.jsp"></jsp:include>
			</label>
		</div>
		<div class="clear"></div>
		<div class="combopage" id = "combopage">
				<h3> COMBO DETAILS  </h3>
				<jsp:include page="/WEB-INF/pages/menu/combo.jsp"></jsp:include>
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