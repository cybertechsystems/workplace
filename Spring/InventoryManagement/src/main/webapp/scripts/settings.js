function initialize() {
	var  column = [
		            { "sTitle": "Setting ID",   "mData": "settingID" },
		            { "sTitle": "Setting Name",  "mData": "settingName" },
		            { "sTitle": "Setting Description", "mData": "settingDescription" }
		          ];
	initializeDataTable('settingPageData.html', column, false);
	
	$("#AddMenu").unbind();
	$('#AddMenu').click(function(e) {
		$.get("settingAdd.html",
			 function( data ) {
	    	 	$( "#editorPanel" ).html(data);
	    	 	$( "#editorPanel" ).css("display","block");
	    	 	$( "#editorPanel" ).dialog( "open" );
		    },
		    "html"
		);
		e.stopPropagation();
	});
	
	$("#EditMenu").unbind();
	$('#EditMenu').click(function(e) {
		var id = $('#selectedrow').val();
		$.get("settingEdit.html?selectedrow=" + id,
			 function( data ) {
				$( "#editorPanel" ).html(data);
				$( "#editorPanel" ).css("display","block");
	    	 	$( "#editorPanel" ).dialog( "open" );
		    },
		    "html"
		);
		e.stopPropagation();
	});
	
	$("#CopyMenu").unbind();
	$('#CopyMenu').click(function(e) {
		var id = $('#selectedrow').val();
		$.get("settingCopy.html?selectedrow=" + id,
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
			$.get("settingDelete.html?selectedrow=" + id,
				 function( data ) {
		    	 	$("#contentList").html(data);
			    }
			);
			
			e.stopPropagation();
		}
	});
	
}

function validateName(){
	var settingName = $('#settingName').val();
	$.get("validateName.html?selectedname=" + settingName, 
		function(responseText) { 
			if(responseText == "true"){
				$("#errorMsgTset").show().html("Setting Name Must be unique!");
				$("#validationNameImage").attr("src", "images/wrong.png");
			}else{
				$("#errorMsgTset").show().html("");
				$("#validationNameImage").attr("src", "images/correct.png");
			}
	});
}

function validateSettingFields(){	
	 if(!($('#settingName').val()) || !($('#settingDescription').val())){
		$('#errorMsgTset').html('Items marked with * are mandatory!');
		return false;
	}else if($("#validationNameImage").attr("src").indexOf("wrong") > 0 ){
		$('#errorMsgTset').html('Setting Name Must be unique!');
		return false;
	}else if( $("#settingNameOrg").val() == $("#settingName").val() &&  $("#settingID").val() == 0) {
		$('#errorMsgTset').html('Setting Details already exists. Please change the setting Details!');
		return false;
	} 
	else {
		return true;
	}
}

