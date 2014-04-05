<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="comboSave">
 	<script type="text/javascript" charset="utf-8"></script>
		
		<div class="clear"></div>
		
		<!-- For Validation of errors -->
		<div class="validationErrors" id="errorMsgTset"></div>

		<div class="clear"></div>
		<div class="row">
			<label for="isPOSCombo">
				Is POS Combo
			</label>
			<button type="button" name="yes" class="yesnoComboBtn" id="isPOSCombo">
				NO
			</button>
		</div>
  <div id="comboDetails">
	<div id="comboDetails1">			
		<h3  style="margin : 0"> SUB-CATEGORIES</h3>
		<div id="subcategory">
			<div class="clear"></div>
			<div class="listBox" >
				<div class="row" id="availableCategories">
					<h2 style="width:152px;float:left"> Available Categories </h2>
				</div>
				<div class="row" >
			    	<h2 style="width:152px;float:right"> Selected Categories </h2>
				</div>
				<div class="clear"></div>
				<select id="SelectLeft" multiple="multiple" class="input_dropdown_AC">
		            <c:forEach items="${subCategoryList}" var="item">
					   <option value="${item.categoryID}">${item.description}</option>
					</c:forEach>
		      	</select>
		        <div style="float:left;margin:20px 8px 0px 8px">
				    <input id="MoveRight" type="button" value=" >> " /> <br/>
				    <input id="MoveLeft" type="button" value=" << " />
			    </div>
		     
		      	<select id="SelectRight" multiple="multiple" > 
		      		<c:forEach items="${subCatList}" var="item">
					   <option value="${item.categoryID}">${item.description}</option>
					</c:forEach>         
		      	</select>
			</div>
			
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label>
				PRIMARY-CATEGORY
			</label>
			<form:select path="primaryCategory" id="primaryCategory" class="input_dropdown_PC">
				<option value=" ">Select Primary Category</option>
				<c:forEach items="${subCatList}" var="item">
					<option value="${item.categoryID}">${item.description}</option>
				</c:forEach>
			</form:select>
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label>
				Compulsory Item
			</label>
			<form:select path="compulsoryItem" id="compulsoryItem" class="input_dropdown_CI">
				<option value=" ">Select Compulsory Item</option>
				<c:forEach items="${compulsoryItemList}" var="item">
					<option value="${item.zProductId}">${item.zName}</option>
				</c:forEach>
			</form:select>
		</div>
		
		<div class="clear"></div>
		<div class="row">
			<label for="maxSubItem">
				Max Sub Item
			</label>
			<form:input path="maxSubItem" id="maxSubItem" class="txt"/>	
		</div>
	</div>
  </div>
</div>