var oTable;
var contextName;
function initializeDataTable(tableUrl, colMapping, hideFlag) {
	contextName = $('#context').val();
	$('#navigationMenu').center();
	$('#menu').center();
	//High light menu selection
	var url = window.location.href;
	$(".nav-list li a").each(function(){
		 $('.nav-list li a[href="'+ url +'"]').addClass('active');  // Will only work if string in href matches with location
		 $('.nav-list li a').filter(function() {  // Will also work for relative and absolute hrefs
		     return this.href == url;
		 }).addClass('active');
	});

	oTable = $('#main-table').dataTable({
		"aaSorting": [[1, "desc"]],
		 "sPaginationType": "full_numbers",
		 "bProcessing": true,
		 "sAjaxSource": tableUrl,
		 "bDestroy": true,
		 "bServerSide": true,
		 "aoColumns": colMapping,
		 "fnServerData": function ( sSource, aoData, fnCallback ) {
				$.ajax( {
					"dataType": 'json', 
					"type": "POST", 
					"url": sSource, 
					"data": aoData, 
					"success": fnCallback
				} );
			}

	});

	$('#main-table tr').live('click', tablerowclick);
	if(hideFlag==true){
		oTable.fnSetColumnVis( 0, false );
		//oTable.fnDraw();
	}
	
	$( "#editorPanel" ).dialog({ autoOpen: false, modal: true, resizable: true, width: '50%'});
}

function formProcess(url, id, contentId) {
    $.post(url, 
    	$("#" + id).serialize(),
	    function( data ) {
    	 	$( "#" + contentId ).html(data);
    	 	$( "#editorPanel" ).dialog( "close" );
	    },
	    "html"
	);
}


function tablerowclick(event){
	
	$("td.row_selected", oTable.fnGetNodes()).removeClass('row_selected');
	
	var selected = $(event.target).parent().find("td");
	selected.addClass('row_selected');
	
	if(selected[0]) {
		var id = oTable.fnGetData( this, 0 );
		$("#selectedrow").attr("value",id);
		event.stopPropagation();
	}
	return false;
}

jQuery.fn.center = function ()
{
    this.css("position","fixed");
    this.css("left", ($(window).width() / 2) - (this.outerWidth() / 2));
    return this;
}

$(window).resize(function(){
   $('#navigationMenu').center();
   $('#menu').center();
});