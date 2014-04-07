<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="categorySave"  class="content">
 	<script type="text/javascript" charset="utf-8">
		$("#categoryManageForm").ready(
			function() {
				$("#description").change(validateDescription);
				$(".txtImg").change(validateImgUrl);
				$("#sequence").change(validateSequenceValue);
				$("#descriptionCN").val($("#categoryDescCN").val());
				//Sequence List
				$("#savebutton").unbind();
				$('#savebutton').click(
					function() {
						if(validateCategoryFields()) {
							$('#categoryManageForm').submit();
						}
					}
				);
			}
		);
	</script>
	<form:form action="${context}/categorySave.html"
		method="POST" id="categoryManageForm"
		commandName="category" enctype="multipart/form-data" >

		<!-- For Validation of errors -->
		<div class="validationErrors" id="errorMsgTset"></div>
		<div class="clear"></div>
		<form:hidden path="categoryID" />
		<form:hidden path="imgUrl" />
		<input type="hidden" id="categoryDescOrg" value="${category.description}" />
		<input type="hidden" id="categoryDescCN" value="${category.descriptionCN}" />
		<input type="hidden" id="categoryImg" value="${category.imgUrl}" />
		<input type="hidden" id="categoryIsShown" value="${isShown > 0}" />
		<input type="hidden" id="categorySeq" value="${category.sequence}" />
		<div class="row">
			<label for="description">
				Description <span class="required"> * </span>
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
			<label for="imgUrl">
				Upload Image
			</label>
			<input id="imgUrl" class="txtImg" type="file" name="imageFile">
		</div>
		<c:if test="${fn:length(category.imgUrl)>0}">
			<div class="row">
				<label for="imgUrl">
					Image
				</label>
				<img src="${category.imgUrl}" width="90px" height="90px" style="padding-top:10px;"/>
			</div>
		</c:if>
		<div class="clear"></div>
		<div class="row">
			<label for="isShown">
				Is Shown
			</label>
			<c:choose>
		 	   <c:when test="${isShown > 0}">
		      	 <form:checkbox path="isShown" value="1" id="isShownBool" class="cbox" checked="true"/>
		 	   </c:when>
		 	   <c:otherwise>
		 	      <form:checkbox path="isShown" value="1" id="isShownBool" class="cbox"/>
			    </c:otherwise>
			</c:choose>
		</div>
		<div class="clear"></div>
		<div class="row">
			<label for="sequence">
				Sequence
			</label>
			<form:input path="sequence" id="sequence" class="txt"/>
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