<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="dataTables_wrapper" style="margin: 1% 2%">
	<script type="text/javascript" charset="utf-8">
		$("#contentList").ready(
			function() {
			initialize();
			}
		);
	</script>
	<div class="dataTables_length"></div>
	<div class="dataTables_filter"></div>
	<input type="hidden" id="selectedrow" name="selectedrow" value="" />
	<table id="main-table" width="100%">
		<thead>
			<tr>
				<th class="sorting"></th>
				<th class="sorting"></th>
				<th class="sorting"></th>
				<th class="sorting"></th>
				<th class="sorting"></th>
				<th class="sorting"></th>
				<th class="sorting"></th>
			</tr>
		</thead>
		<tbody id="table-body">
			<tr>
				<td class="dataTables_empty" colspan="6">Loading data from server</td>
			</tr>
		</tbody>
	</table>
	<div class="dataTables_info"></div>
	<div class="dataTables_paginate"></div>
</div>
	
<div id="uploadPopup">
 	<span class="close" onclick="javascript:hidePopup()">
    		[&nbsp;X&nbsp;]&nbsp;
 	</span>
  	<fieldset class="upload_fieldset upload_popup">
    	<legend>
    		Upload
    	</legend>
    	<form id="uploadForm"
    		action="<%= request.getContextPath() %>/file.upload?comeback=category.html"
     		method="post" enctype="multipart/form-data">
      		<div id="submit-field">
       			<label for="upload">Image:</label>
       			<input id="upload" name="upload" type="file" />
      		</div>
     		<div>
       			<input type="submit" value="Submit" />
       			<input id="delfile" name="delfile" type="button" value="Delete" />
     		</div>
    	</form>
  	</fieldset>
</div>

