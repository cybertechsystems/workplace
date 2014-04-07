function initialize() {	
	var random = Math.floor(Math.random()*100000);
	var column = [
		            { "sTitle": "Category ID",   "mData": "categoryID" },
		            { "sTitle": "Description",  "mData": "description" },
		            { "sTitle": "Chinese Description", "mData": "descriptionCN" },
		            { "sTitle": "Image", "mData": "imgUrl",  "mRender": function ( data, type, row ) {
		            	 													return '<img src="'+data+'?renew='+random+' width="90px" height="90px" style="padding-top:10px;"/>';
																        }},
		            { "sTitle": "Shown", "mData": "isShown" },
		            { "sTitle": "Sequence", "mData": "sequence" },
		            { "sTitle": "","mData": "isShown", "mRender": function (data, type, row) {
						if(data==1){
							return '<button type="button" id="hideRecord'+row.categoryID+'" class="addBtn" onclick="hideCategoryRecordR('+row.categoryID+')">Hide</button>';
						}else{
							return '<button type="button" id="hideRecord'+row.categoryID+'" class="addBtn" onclick="hideCategoryRecordR('+row.categoryID+')">Show</button>';
						}
						}
			         }
		         ];
	initializeDataTable('categoryPageData.html', column, false);
	
	
	$("#AddMenu").unbind();
	$('#AddMenu').click(function(e) {
		$.get("categoryAdd.html",
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
		$.get("categoryEdit.html?selectedrow=" + id,
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
			$.get("categoryDelete.html?selectedrow=" + id,
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
		$.get("categoryCopy.html?selectedrow=" + id,
			 function( data ) {
				$( "#editorPanel" ).html(data);
				$( "#editorPanel" ).css("display","block");
	    	 	$( "#editorPanel" ).dialog( "open" );
		    },
		    "html"
		);
		e.stopPropagation();
	});
	
}

function hideCategoryRecordR(categoryID) {
	$.get("changeHideShow.html?selectedid=" + categoryID,
		function(data){
			var value= $('#hideRecord'+categoryID).text();
			if(value == "Hide") {
				$('#hideRecord'+categoryID).text("Show");
			} else {
				$('#hideRecord'+categoryID).text("Hide");
			}
	});
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

function validateDescription(){
	var description = $('#description').val();
	$.get("validateDescription.html?selecteddesc=" + description, 
		function(responseText) { 
			if(responseText == "true"){
				$("#errorMsgTset").show().html("Category description must be unique");
				$("#validationDescImage").attr("src", "images/wrong.png");
			}else{
				$("#errorMsgTset").show().html("");
				$("#validationDescImage").attr("src", "images/correct.png");
			}
	});
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

function validateCategoryFields(){
	//var val=${isShown > 0};
	 if(!($('#description').val())){
		$('#errorMsgTset').html('Items marked with * are mandatory!');
		return false;
	}else if($("#validationDescImage").attr("src").indexOf("wrong") > 0 ){
		$('#errorMsgTset').html('Category description must be unique!');
		return false;
	}else if($("#categoryDescOrg").val() == $("#description").val() &&  $("#categoryID").val() == 0) {
		$('#errorMsgTset').html('Category Details already exists. Please change the Category Details!');
		return false;
	} else if(!validateSequenceValue()){
		$('#errorMsgTset').html('Sequence Field has to be Numeric!');
		return false;
	} else {
		return true;
	}
}
