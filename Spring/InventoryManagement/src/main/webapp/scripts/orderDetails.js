function initializeDetails() {	
	var random = Math.floor(Math.random()*100000);
	var column = [
	              	{ "sTitle": "DetailID", "mData": "detailId" },
		           	{ "sTitle": "CheckID", "mData": "checkId" },
		            { "sTitle": "MenuItemNo", "mData": "menuItemNo" },
		            { "sTitle": "MainItem Id", "mData": "isCondiment" },
		            { "sTitle": "Quantity", "mData": "quantity" },
					{ "sTitle": "IsUpdated", "mData": "isUpdated" },
					{ "sTitle": "ChkOpenTime", "mData": "chkOpenTime" }
		         ];
	initializeDataTable('orderDetailsPageData.html', column);
	
	$("#EditMenu").unbind();
	$('#EditMenu').click(function(e) {
		var id = $('#selectedrow').val();
		$.get("orderDetailsEdit.html?selectedrow=" + id,
			 function( data ) {
				$( "#editorPanel" ).html(data);
				$( "#editorPanel" ).css("display","block");
	    	 	$( "#editorPanel" ).dialog( "open" );
		    },
		    "html"
		);
		e.stopPropagation();
	});

	$("#DeleteMenu").unbind();
	$("#DeleteMenu").click(function(e) {
		
		if ( confirm( 'Are you sure you wish to delete this record?' ) ) {
			var id = $('#selectedrow').val();
			$.get("orderDetailsDelete.html?selectedrow=" + id,
				 function( data ) {
		    	 	$("#contentList").html(data);
			    }
			);
			e.stopPropagation();
		}
	});
}

/*function validateMenuItemNo() {
	if($.isNumeric($('#menuItemNo').val()) || $('#menuItemNo').val().length==0) {
		$("#errorMsgTset").show().html("");
		return true;
	} else {
		$("#errorMsgTset").show().html("Menu Item No Value Should be Numeric !");
		return false;
	}
}
*/
function validateQuantity() {
	if($.isNumeric($('#quantity').val()) || $('#quantity').val().length==0) {
		$("#errorMsgTset").show().html("");
		return true;
	} else {
		$("#errorMsgTset").show().html("Quantity Value Should be Numeric !");
		return false;
	}
}

function validateMenuItemNo(){
	var menuItemNo = $('#menuItemNo').val();
	$.get("validateMenuItem.html?selectedno=" + menuItemNo, 
		function(responseText) { 
			if(responseText == "false"){
				$("#errorMsgTset").show().html("Corresponding MenuItem is not Present !");
				$("#validationNoImage").attr("src", "images/wrong.png");
			}else{
				$("#errorMsgTset").show().html("");
				$("#validationNoImage").attr("src", "images/correct.png");
			}
	});
}

function validateOrderFields(){
	 if(!($('#checkId').val()) || !($('#detailId').val())){
		$('#errorMsgTset').html('Items marked with * are mandatory!');
		return false;
	} else if(!validateMenuItemNo() && !validateQuantity()){
		$('#errorMsgTset').html('MenuItemNo, Quantity Fields has to be Numeric!');
		return false;
	} else if(!validateQuantity()){
		$('#errorMsgTset').html('Quantity Field has to be Numeric!');
		return false;
	} else if($("#detailIdOrg").val() != $("#detailId").val()) {
		$('#errorMsgTset').html('Cannot change the DetailId !!');
		return false;
	} else if($("#validationNoImage").attr("src").indexOf("wrong") > 0 ){
		$('#errorMsgTset').html('Corresponding Menuitem is not present. Enter the correct no !');
		return false;
	} else {
		return true;
	}
}
