function initialize() {	
	var random = Math.floor(Math.random()*100000);
	var column = [
	              	{ "sTitle": "", "mData": "zPk", "sClass": "hideZPk","bVisible": false},
		            { "sTitle": "Name", "mData": "zName" },
		            { "sTitle": "Price", "mData": "usualPrice" },
		            { "sTitle": "Image", "mData": "zThumbImgUrl",  "mRender": function ( data, type, row ) {
		            	 													return '<img src="'+data+'?renew='+random+' width="90px" height="90px" style="padding-top:10px;"/>';
																        }},
					{ "sTitle": "Category", "mData": "description" },
					{ "sTitle": "Sequence", "mData": "sequence" },
					{ "sTitle": "Stock Left", "mData": "stock" },
					{ "sTitle": "","mData": "iPodOnly", "mRender": function (data, type, row) {
						if(data==1){
							return '<button type="button" id="hideRecord'+row.zPk+'" class="addBtn" onclick="hideMenuRecordR('+row.zPk+')">Hide</button>';
						}else{
							return '<button type="button" id="hideRecord'+row.zPk+'" class="addBtn" onclick="hideMenuRecordR('+row.zPk+')">Show</button>';
						}
						}
			         }
		         ];
	initializeDataTable('menuPageData.html', column, true);
	
	$("#AddMenu").unbind();
	$('#AddMenu').click(function(e) {
		$.get("menuAdd.html",
			 function( data ) {
	    	 	$( "#editorPanel" ).html(data);
	    	 	$( "#editorPanel" ).css("display","block");
	    	 	$( "#editorPanel" ).dialog( "open" );
				//$("#contentList").html(data);
				//$("#menu").hide();
				//$( "#contentList" ).html(data);
		    },
		    "html"
		);
		e.stopPropagation();
	});
	
	$("#EditMenu").unbind();
	$('#EditMenu').click(function(e) {
		var id = $('#selectedrow').val();
			$.get("menuEdit.html?selectedrow=" + id,
				 function( data ) {
					$( "#editorPanel" ).html(data);
					$( "#editorPanel" ).css("display","block");
		    	 	$( "#editorPanel" ).dialog( "open" );
					//$("#contentList").html(data);
					//$("#menu").hide();
			    },
			    "html"
			);
		e.stopPropagation();
	});
	
	
	$("#DeleteMenu").unbind();
	$("#DeleteMenu").click(function(e) {
		if ( confirm( 'Are you sure you wish to delete this record?' ) ) {
			var id = $('#selectedrow').val();
				$.get("menuDelete.html?selectedrow=" + id,
					 function( data ) {
			    	 	$("#contentList").html(data);
				    }
				);
			e.stopPropagation();
		}
	});
	
	$("#CopyMenu").unbind();
	$('#CopyMenu').click(function(e) {
		var id = $('#selectedrow').val();
			$.get("menuCopy.html?selectedrow=" + id,
				 function( data ) {
					$( "#editorPanel" ).html(data);
					$( "#editorPanel" ).css("display","block");
		    	 	$( "#editorPanel" ).dialog( "open" );
					//$("#contentList").html(data);
					//$("#menu").hide();
			    },
			    "html"
			);
		e.stopPropagation();
	});
}

function hideMenuRecordR(zPk) {
	$.get("changeIpodOnlyFlag.html?selectedid=" + zPk,
		function(data){
			var value= $('#hideRecord'+zPk).text();
			if(value == "Hide") {
				$('#hideRecord'+zPk).text("Show");
			} else {
				$('#hideRecord'+zPk).text("Hide");
			}
	});
}
function initializeAds() {	
	var random = Math.floor(Math.random()*100000);
	var column = [
	              	{ "sTitle": "", "mData": "zPk", "sClass": "hideZPk", "bVisible":    false},
		            { "sTitle": "Name", "mData": "zName" },
		            { "sTitle": "Price", "mData": "usualPrice" },
		            { "sTitle": "Image", "mData": "zThumbImgUrl",  "mRender": function ( data, type, row ) {
		            	 													return '<img src="'+data+'?renew='+random+' width="90px" height="90px" style="padding-top:10px;"/>';
																        }},

					{ "sTitle": "Category", "mData": "description" },
					{ "sTitle": "Sequence", "mData": "sequence" },
					{ "sTitle": "Stock Left", "mData": "stock" },
					{ "sTitle": "","mData": "iPodOnly", "mRender": function (data, type, row) {
						if(data==1){
							return '<button type="button" id="hideRecord'+row.zPk+'" class="addBtn" onclick="hideMenuRecordR('+row.zPk+')">Hide</button>';
						}else{
							return '<button type="button" id="hideRecord'+row.zPk+'" class="addBtn" onclick="hideMenuRecordR('+row.zPk+')">Show</button>';
						}
						}
			         }
		         ];
	initializeDataTable('adsPageData.html', column, true);
	
	$("#AddMenu").unbind();
	$('#AddMenu').click(function(e) {
		$.get("menuAdd.html",
			 function( data ) {
	    	 	$( "#editorPanel" ).html(data);
	    	 	$( "#editorPanel" ).css("display","block");
	    	 	$( "#editorPanel" ).dialog( "open" );
				//$("#contentList").html(data);
				//$("#menu").hide();
				//$( "#contentList" ).html(data);
		    },
		    "html"
		);
		e.stopPropagation();
	});
	
	$("#EditMenu").unbind();
	$('#EditMenu').click(function(e) {
		var id = $('#selectedrow').val();
		$.get("menuEdit.html?selectedrow=" + id,
			 function( data ) {
			$( "#editorPanel" ).html(data);
				$( "#editorPanel" ).css("display","block");
	    	 	$( "#editorPanel" ).dialog( "open" );
				//$("#contentList").html(data);
				//$("#menu").hide();
		    },
		    "html"
		);
		e.stopPropagation();
	});
	
	
	$("#DeleteMenu").unbind();
	$("#DeleteMenu").click(function(e) {
		if ( confirm( 'Are you sure you wish to delete this record?' ) ) {
			var id = $('#selectedrow').val();
			$.get("menuDelete.html?selectedrow=" + id,
				 function( data ) {
		    	 	$("#contentList").html(data);
			    }
			);
			
			e.stopPropagation();
		}
	});
	
	$("#CopyMenu").unbind();
	$('#CopyMenu').click(function(e) {
		var id = $('#selectedrow').val();
		$.get("menuCopy.html?selectedrow=" + id,
			 function( data ) {
				$( "#editorPanel" ).html(data);
				$( "#editorPanel" ).css("display","block");
	    	 	$( "#editorPanel" ).dialog( "open" );
				//$("#contentList").html(data);
				//$("#menu").hide();
		    },
		    "html"
		);
		e.stopPropagation();
	});
}

function validateMenuName(){
	var name = $('#zName').val();
	$.get("validateMenuName.html?selectedname=" + name, 
		function(responseText) { 
			if(responseText == "true"){
				$("#errorMsgTset").show().html('Menu name already used.Try with a different name!');
				$("#validationNameImage").attr("src", "images/wrong.png");
			}else{
				$("#errorMsgTset").show().html("");
				$("#validationNameImage").attr("src", "images/correct.png");
			}
	});
}

function primaryCategoryDropdown(){
	var id = $('#categoryId').val();
	$.get("populatePrimaryCategory.html?selectedid=" + id, 
		function(data) { 
			$(".input_dropdown_AC").html(data);
	});
}

function loadPrimaryCategory() {
	
}

function compulsoryItemDropdown(){
	var id = $('.input_dropdown_PC').val();
	$.get("populateCompulsoryItem.html?selectedid=" + id, 
		function(data) { 
			$(".input_dropdown_CI").html(data);
	});
}

function validateStockValue() {
	if($.isNumeric($('#stock').val()) || $('#stock').val().length==0) {
		$("#errorMsgTset").show().html("");
		return true;
	} else {
		$("#errorMsgTset").show().html("Stock Value Should be Numeric !");
		return false;
	}
}

function validateSequenceValue() {
	if($.isNumeric($('#sequence').val()) || $('#sequence').val().length==0) {
		$("#errorMsgTset").show().html("");
		return true;
	} else {
		$("#errorMsgTset").show().html("Sequence Value Should be Numeric !");
		return false;
	}
}

function validateBasePrice() {
	if($.isNumeric($('#basePrice').val()) || $('#basePrice').val().length==0) {
		$("#errorMsgTset").show().html("");
		return true;
	} else {
		$("#errorMsgTset").show().html("Base Price Value Should be Numeric !");
		return false;
	}
}

function validateUsualPrice() {
	if($.isNumeric($('#usualPrice').val()) || $('#usualPrice').val().length==0) {
		$("#errorMsgTset").show().html("");
		return true;
	} else {
		$("#errorMsgTset").show().html("Usual Price Value Should be Numeric !");
		return false;
	}
}

function validateTakeAwayPrice() {
	if($.isNumeric($('#takeawayPrice').val()) || $('#takeawayPrice').val().length==0) {
		$("#errorMsgTset").show().html("");
		return true;
	} else {
		$("#errorMsgTset").show().html("Take Away Price Value Should be Numeric !");
		return false;
	}
}

function validateImgUrl(){
	var val = $('.txtImg').val();
	switch(val.substring(val.lastIndexOf('.') + 1).toLowerCase()){
    case 'gif': case 'jpg': case 'png':
        break;
    default:
        $(this).val('');
    	$('#errorMsgTset').html("Only '.jpg', '.png', '.gif' image formats are allowed.");
        break;
	}
}

function validateMenuFields(){
	 if(!($('#zName').val()) || ($('#categoryId').val()=="0")){
		$('#errorMsgTset').html('Items marked with * are mandatory!');
		return false;
	} else if($("#validationNameImage").attr("src").indexOf("wrong") > 0 ){
		$('#errorMsgTset').html('Menu name already used.Try with a different name!');
		return false;
	} else if($("#menuNameOrg").val() == $("#zName").val() &&  $("#zPk").val() == 0) {
		$('#errorMsgTset').html('Menu Details already exists. Please change the Menu Details!');
		return false;
	} else if(!validateBasePrice() || !validateUsualPrice() || !validateTakeAwayPrice()){
		$('#errorMsgTset').html('Price fields have to be Numeric!');
		return false;
	} else if(!validateStockValue() && !validateSequenceValue()){
		$('#errorMsgTset').html('Stock, Sequence Fields has to be Numeric!');
		return false;
	} else if(!validateStockValue() && validateSequenceValue()){
		$('#errorMsgTset').html('Stock Field has to be Numeric!');
		return false;
	} else if(validateStockValue() && !validateSequenceValue()){
		$('#errorMsgTset').html('Sequence Field has to be Numeric!');
		return false;
	} else {
		return true;
	}
}

function initializeSave() {
	var selectedCategories = $("#subCategories").val();	
	var primaryCategories = "";
	
	$('#promotionsDay').dropdownchecklist();
	
	var days=$("#promDay").val().split(",");
	$.each(days, function(intIndex,item) {
		$('#promotionsDay option[value=' + item + ']').attr('selected', true);
	});
	
	if($("#iPodOnlybtn").val()=="0") {
		$(".yesnoIphoneBtn").text("YES");
		$(".hideBtn").text("SHOW");
	} else {
		$(".yesnoIphoneBtn").text("NO");
		$(".hideBtn").text("HIDE");
	}
	
	if($("#iPadOnlybtn").val()=="0") {
		$(".yesnoIpadBtn").text("YES");
	} else {
		$(".yesnoIpadBtn").text("NO");
	}
	
	if($("#isPOSComboo").val()=="0") {
		$(".yesnoComboBtn").text("YES");
	} else {
		$(".yesnoComboBtn").text("NO");
	}
		
	$("#zNameCN").val($("#chineseDesc").val());
	
	$("#zName").change(validateMenuName);
	$(".txtImg").change(validateImgUrl);

	$("#stock").change(validateStockValue);
	$("#sequence").change(validateSequenceValue);
	$("#usualPrice").change(validateUsualPrice);
	$("#basePrice").change(validateBasePrice);
	$("#takeawayPrice").change(validateTakeAwayPrice);
	
	$("#categoryId").change(primaryCategoryDropdown);
	$(".input_dropdown_PC").change(compulsoryItemDropdown);
	
	
	
	$("#adsStartDate").datepicker({
		dateFormat: 'yy-mm-dd',
        numberOfMonths: 2,
        onSelect: function(selected) {
          $("#adsEndDate").datepicker("option","minDate", selected)
        }
    });
	
	$("#adsEndDate").datepicker({
		dateFormat: 'yy-mm-dd',
        numberOfMonths: 2,
        onSelect: function(selected) {
           $("#adsStartDate").datepicker("option","maxDate", selected)
        }
    });  
	
	$('#startTime').timepicker();
	$('#endTime').timepicker();
	
	$("#descriptionCN").val($("#promotionDescCN").val());
	
	$("#subCategories").val($("#subCategoriesOrg").val());
	$("#primaryCategory").val($("#primaryCategoryOrg").val());
	$("#compulsoryItem").val($("#compulsoryItemOrg").val());
	
	$("#adsStartDate").val($("#adsStartDt").val().split(" ")[0]);
	$("#adsEndDate").val($("#adsEndDt").val().split(" ")[0]);

	$("#startTime").val($("#promotionsT").val().split("-")[0]);
	$("#endTime").val(($("#promotionsT").val().split("-"))[1]);
	
	 $("#MoveRight,#MoveLeft").click(function(event) {
		 	
			var id = $(event.target).attr("id");
	        var selectFrom = id == "MoveRight" ? "#SelectLeft" : "#SelectRight";
	        var moveTo = id == "MoveRight" ? "#SelectRight" : "#SelectLeft";

	        var selectedItems = $(selectFrom + " option:selected");
	        if(id == "MoveRight"){
	        	selectedCategories +=  "|"+$(selectedItems).val();
	        	 //$("#primaryCategory").append(selectedItems.clone());
	        }else{
	        	
	        	/*$("#primaryCategory option[value='"+removedItem+"']").remove();
//	        	$("#primaryCategory").find("option[value='"+removedItem+"']").remove();
	        	$("#SelectRight").each(function () {
	        	    $("<option />", {
	        	        val: this.value,
	        	        text: this.text
	        	    }).appendTo("#primaryCategory");
	        	});*/
	        	var selectedRValue = $(selectedItems).val();
	        	var arr = selectedCategories.split('|');
	        	var arr1 = "";
	        	removeItem=$(selectedItems).val();
	        	arr = jQuery.grep(arr, function(value) {
	        	  return value != removeItem;
	        	});
	        	arr.forEach(function(item){
	        		arr1+="|"+item;
	        	});
	        	selectedCategories=arr1.substring(1);
	        }
	        $(moveTo).append(selectedItems);
	        selectedItems.remove;
	        
	        //Load the primary category based on selected sub-categories
	        //<Start>
	        var selectedValue = $("#primaryCategory option:selected").val();
	        $("select[id$=primaryCategory] > option:gt(0)").remove();
	        
	        if(selectedRValue == selectedValue){
	        	$("select[id$=compulsoryItem] > option:gt(0)").remove();
	        }
	        
	        var options = "";
	        $.each($("#SelectRight option"), function(i, item) {
	            options += '<option value="' + $(item).val() + '">' + $(item).text() + '</option>';
	         });
	        $('#primaryCategory').append(options);
	        $("#primaryCategory").val(selectedValue);
	        //<End>
	        
	        $("#subCategories").val(selectedCategories);
	    });
	
	 
	$("#hidebutton").click(function( event ) {
		event.preventDefault();
		$('#hidebtn').hide();
		$('#hidebutton').hide();
	});
	
	/*$('.yesnoComboBtn').click(function(e) {
		$( "#comboDetails" ).slideToggle("slow");
	});*/
	
	/*$('.yesnoComboBtn').toggle(function(){
	    $(this).text("YES");
			$('.yesnoComboBtn').show();
	    $("#isPOSCombo").attr( "value", 0 );
	}, function(){
	    $(this).text("NO");
			$('.yesnoComboBtn').show();
	    $("#isPOSCombo").attr( "value", 1 );
	});
	*/
	$('.yesnoComboBtn').click(function(e) {
		//$( "#comboDetails" ).slideToggle("slow");
		if($(this).text() == "YES") {
		    $(this).text("NO");
		    $("#isPOSComboo").attr( "value", 1);
		} else {
	    	$(this).text("YES");
	    	$("#isPOSComboo").attr( "value", 0);
	    }
	});
	
	$('.yesnoIphoneBtn').click(function(e) {
		if($(this).text() == "YES") {
		    $(this).text("NO");
		    $("#iPodOnlybtn").attr( "value", 1);
		} else {
	    	$(this).text("YES");
	    	$("#iPodOnlybtn").attr( "value", 0);
	    }
	});
	
	$('.hideBtn').click(function(e) {
		if($(this).text() == "SHOW") {
		    $(this).text("HIDE");
		    $("#iPodOnlybtn").attr( "value", 1);
		} else {
	    	$(this).text("SHOW");
	    	$("#iPodOnlybtn").attr( "value", 0);
	    }
	});

	$('.yesnoIpadBtn').click(function(e) {
		if($(this).text() == "YES") {
		    $(this).text("NO");
		    $("#iPadOnlybtn").attr( "value", 1);
		} else {
	    	$(this).text("YES");
	    	$("#iPadOnlybtn").attr( "value", 0);
	    }
	});
	
	$('.yesBtn').click(function(e) {
		$( "#promotionspage" ).slideToggle("slow");
		if($(this).text() == "YES") {
		    $(this).text("NO");
			$("#promotionspage").css("display", "inline");
		    $("#isAdvertise").attr( "value", 1);
		} else {
	    	$(this).text("YES");
	    	$("#promotionspage").css("display", "none");
	    	$("#isAdvertise").attr( "value", 0);
	    }
	});
	
	$("#savebutton").unbind();
	$('#savebutton').click(
		function() {
			if(validateMenuFields()) {
				$('#menuManageForm').submit();
			}
		}
	);
}