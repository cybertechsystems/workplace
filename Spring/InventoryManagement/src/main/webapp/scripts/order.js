function initialize() {	
	var random = Math.floor(Math.random()*100000);
	var column = [
	              	{ "sTitle": "", "mData": "checkId", "sClass": "hideCheckId", "bVisible": false},
		            { "sTitle": "Check ID","mData": "checkId", "mRender": function (data) {
		            	return '<button type="button" id="checkID'+data+'" class="addBtn" onclick="loadDetails('+data+')">'+data+'</button>';	
						}
			        },
		            { "sTitle": "Order Number", "mData": "orderNum" },
		            { "sTitle": "Check Open Time", "mData": "chkOpenTime" },
					{ "sTitle": "IPad ID", "mData": "ipadId" },
					{ "sTitle": "IsOpen", "mData": "isOpen" },
					{ "sTitle": "IsUpdated", "mData": "isUpdated" }
		         ];
	
	initializeDataTable('orderPageData.html', column, true);
	
	$("#EditMenu").unbind();
	$('#EditMenu').click(function(e) {
		var id = $('#selectedrow').val();
		$.get("orderEdit.html?selectedrow=" + id,
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
		var id = $('#selectedrow').val();
		$.get("validateDetails.html?selectedId=" + id,
			function(data) {
				if(data=="false") {
				if ( confirm( 'Are you sure you wish to delete this record?' ) ) {
					$.get("orderDelete.html?selectedrow=" + id,
						 function( data ) {
				    	 	$("#contentList").html(data);
					    }
					);
					e.stopPropagation();
					}
				} else {
					alert("Please delete the corresponding order details for this order & try again !");
				}
		});
	});
}

function validateOrderNum() {
	if($.isNumeric($('#orderNum').val())) {
		$("#errorMsgTset").show().html("");
		return true;
	} else if($('#orderNum').val().length==0) {
		$("#errorMsgTset").show().html("Order Number can't be null !");
		return false;
	} else {		
		$("#errorMsgTset").show().html("OrderNum Value Should be Numeric !");
		return false;
	}
}

function validateOrderFields(){
	 if(!($('#checkId').val()) || !($('#orderNum').val())){
		$('#errorMsgTset').html('Items marked with * are mandatory!');
		return false;
	} else if($("#validationCheckImage").attr("src").indexOf("wrong") > 0 ){
		$('#errorMsgTset').html('Menu Name must be unique!');
		return false;
	}  else if(!validateOrderNum()){
		$('#errorMsgTset').html('OrderNum cannot be null and has to be Numeric!');
		return false;
	} else if($("#checkIdOrg").val() != $("#checkId").val()) {
		$('#errorMsgTset').html('Cannot change the CheckId !!');
		return false;
	} else {
		return true;
	}
}

function loadDetails(CheckId) {
	
	$('#checkidselected').val(CheckId);
	$('#gotoDetailsPage').submit();
}