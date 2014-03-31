
function validate(){
	var name = document.getElementById("name").value;
	 var duplicate_name = document.getElementById("duplicate_name").value;
	 if(name == duplicate_name){
	 	return true;
	 }else{
		 alert("template name is different");
		 return false;
	 }
}

jQuery(document).ready(function() {
	try{
		initialize();
		jQuery("#jhtmlPosition").attr("class", "jhtmlarea_msg_on");
		var value = jQuery("#jhtmlmessagevalue").html();
		jQuery("#txtDefaultHtmlArea1").htmlarea('pasteHTML',value);
	}catch(error){
		
	}
});

function hidePagination() {
	if(document.getElementById("page_size")){
		var size = document.getElementById("page_size").value;
		if(size<5){
			var paginator_buttom = document.getElementById("yui-dt0-paginator1");
			paginator_buttom.className = "hidden";
			var paginator_top = document.getElementById("yui-dt0-paginator0");
			paginator_top.className = "hidden";
		}
	}
}

function initialize() {
	jQuery("#txtDefaultHtmlArea1").htmlarea(); // Initialize jHtmlArea's with all
}

function insertDataField() {
	var selectedvalue =  jQuery("#datafields").val();
	jQuery("#txtDefaultHtmlArea1").htmlarea('pasteHTML',selectedvalue);
	jQuery("#datafields").val(0);
}

function updateTextArea() {
	var content = jQuery("#duplicate_content").val();
	jQuery("#txtDefaultHtmlArea1").htmlarea('pasteHTML', content);
}

function getHTMLText() {
 	var text = $("#txtDefaultHtmlArea1").htmlarea('toHtmlString');
 	jQuery('#duplicate_content').val(text);
 	jQuery("#txtDefaultHtmlArea1").htmlarea('pasteHTML', '');
 	var content = jQuery("#duplicate_content").val();
 	jQuery("#txtDefaultHtmlArea1").htmlarea('pasteHTML', content);
 }

function toggle(idFrom) {
	 var valueFrom = idFrom.value;
	 var panel = document.getElementById("detailsTable");
	 if(valueFrom == 'Other'){
		 panel.className = "visible";
	 }else{
		 panel.className = "hidden";
	 }
 }

function showDialog() {
	document.getElementById("confirmForm:deleteReasonPanel").className="visible";
}

function hideDialog() {
	document.getElementById("confirmForm:deleteReasonPanel").className="hidden";
}