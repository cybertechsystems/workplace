
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="dataTables_wrapper" style="margin: 1% 19%"	>
	<script type="text/javascript" charset="utf-8">
		$("#contentList").ready(
			function() {
				initializeDetails();
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