$(document).ready(function() {
	var oTable = $('#main-table').dataTable();
	var keys = new KeyTable( {
		"table" : document.getElementById('main-table'),
		"datatable" : oTable
	});

	/* Apply a return key event to each cell in the table */
	$('#main-table tbody td').each(function() {
		keys.event.action(this, function(nCell) {
			if (!$(nCell).hasClass("locked")) {
				/*
				 * Block KeyTable from performing any events while jEditable is
				 * in edit mode
				 */
				keys.block = true;

				/* Initialise the Editable instance for this table */

				$(nCell).editable(function(value) {
					/* Submit function (local only) - unblock KeyTable */
					keys.block = false;
					if ($(nCell).hasClass("price")) {
						value = value.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
						var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
					    if(!floatRegex.test(value)) {
					    	alert('Price value must be numeric and greater than zero.');
							  value=null;
					    }
					}					
					if ($(nCell).hasClass("price") && value < 0) {
						  alert('Price value must be greater than zero.');
						  value=null;
					}
					var data = {
						id : nCell.id,
						value : value
					};
					$.ajax( {
						type : "POST",
						url : "itemDetail.html?action=update",
						data : JSON.stringify(data)
					});
					
					return value;
				}, {
					onblur : 'submit',
					onreset : function() {
						/*
						 * Unblock KeyTable, but only after this 'esc' key event
						 * has finished. Otherwise it will 'esc' KeyTable as
						 * well
						 */
						setTimeout(function() {
							keys.block = false;
						}, 0);
					}
				});

				/*
				 * Dispatch click event to go into edit mode - Saf 4 needs a
				 * timeout...
				 */
				setTimeout(function() {
					$(nCell).click();					
				}, 0);
			}
		});
	});
	$('.addBtn').click(function() {
		if ( confirm( 'Add new record?' ) )
		{
			$.post("itemDetail.html?action=add");
			window.location.href=window.location.href + '?new=true';
		}
		
	});
	$('.delBtn').click(removeRow);
	$('.cBox').click(checkBox);
	$('.dDown > select').change(dropDown);
	$('.sorting').click(function() {
		$('.delBtn').click(removeRow);
		$('.cBox').click(checkBox);
	});	
	$('.datepicker').click(function() {	
		var temp = $(this);
		var val = $(this)[0].value;
		if($(this)[0].value==null || $(this)[0].value=="")
		{
			$(this).datePicker().val(new Date().asString()).trigger('change');
		}
	});
	$('.sorting').click(function() {	
		$('.delBtn').click(removeRow);
		$('.cBox').click(checkBox);
		$('.dDown > select').change(dropDown);
		$('.datepicker').click(function() {	
			var temp = $(this);
			var val = $(this)[0].value;
			if($(this)[0].value==null || $(this)[0].value=="")
			{
				$(this).datePicker().val(new Date().asString()).trigger('change');
			}
		});
		/* Apply a return key event to each cell in the table */
		$('#main-table tbody td').each(function() {
			keys.event.action(this, function(nCell) {
				if (!$(nCell).hasClass("locked")) {
					/*
					 * Block KeyTable from performing any events while jEditable is
					 * in edit mode
					 */
					keys.block = true;

					/* Initialise the Editable instance for this table */

					$(nCell).editable(function(value) {
						/* Submit function (local only) - unblock KeyTable */
						keys.block = false;
						if ($(nCell).hasClass("price") && value <= 0) {
							  alert('Price value must be greater than zero.');
							  value=1;
						}
						var data = {
							id : nCell.id,
							value : value
						};
						$.ajax( {
							type : "POST",
							url : "itemDetail.html?action=update",
							data : JSON.stringify(data)
						});
						
						return value;
					}, {
						onblur : 'submit',
						onreset : function() {
							/*
							 * Unblock KeyTable, but only after this 'esc' key event
							 * has finished. Otherwise it will 'esc' KeyTable as
							 * well
							 */
							setTimeout(function() {
								keys.block = false;
							}, 0);
						}
					});

					/*
					 * Dispatch click event to go into edit mode - Saf 4 needs a
					 * timeout...
					 */
					setTimeout(function() {
						$(nCell).click();					
					}, 0);
				}
			});
		});	
	});
	$('.paginate_enabled_next').click(function() {	
		$('.delBtn').click(removeRow);
		$('.cBox').click(checkBox);
		$('.dDown > select').change(dropDown);
		$('.datepicker').click(function() {	
			var temp = $(this);
			var val = $(this)[0].value;
			if($(this)[0].value==null || $(this)[0].value=="")
			{
				$(this).datePicker().val(new Date().asString()).trigger('change');
			}
		});
		/* Apply a return key event to each cell in the table */
		$('#main-table tbody td').each(function() {
			keys.event.action(this, function(nCell) {
				if (!$(nCell).hasClass("locked")) {
					/*
					 * Block KeyTable from performing any events while jEditable is
					 * in edit mode
					 */
					keys.block = true;

					/* Initialise the Editable instance for this table */

					$(nCell).editable(function(value) {
						/* Submit function (local only) - unblock KeyTable */
						keys.block = false;
						if ($(nCell).hasClass("price") && value <= 0) {
							  alert('Price value must be greater than zero.');
							  value=1;
						}
						var data = {
							id : nCell.id,
							value : value
						};
						$.ajax( {
							type : "POST",
							url : "itemDetail.html?action=update",
							data : JSON.stringify(data)
						});
						
						return value;
					}, {
						onblur : 'submit',
						onreset : function() {
							/*
							 * Unblock KeyTable, but only after this 'esc' key event
							 * has finished. Otherwise it will 'esc' KeyTable as
							 * well
							 */
							setTimeout(function() {
								keys.block = false;
							}, 0);
						}
					});

					/*
					 * Dispatch click event to go into edit mode - Saf 4 needs a
					 * timeout...
					 */
					setTimeout(function() {
						$(nCell).click();					
					}, 0);
				}
			});
		});	
	});
	
	$('.main-table_filter').change(function() {	
		$('.delBtn').click(removeRow);
		$('.cBox').click(checkBox);
		$('.dDown > select').change(dropDown);
		$('.datepicker').click(function() {	
			var temp = $(this);
			var val = $(this)[0].value;
			if($(this)[0].value==null || $(this)[0].value=="")
			{
				$(this).datePicker().val(new Date().asString()).trigger('change');
			}
		});
		/* Apply a return key event to each cell in the table */
		$('#main-table tbody td').each(function() {
			keys.event.action(this, function(nCell) {
				if (!$(nCell).hasClass("locked")) {
					/*
					 * Block KeyTable from performing any events while jEditable is
					 * in edit mode
					 */
					keys.block = true;

					/* Initialise the Editable instance for this table */

					$(nCell).editable(function(value) {
						/* Submit function (local only) - unblock KeyTable */
						keys.block = false;
						if ($(nCell).hasClass("price") && value <= 0) {
							  alert('Price value must be greater than zero.');
							  value=1;
						}
						var data = {
							id : nCell.id,
							value : value
						};
						$.ajax( {
							type : "POST",
							url : "itemDetail.html?action=update",
							data : JSON.stringify(data)
						});
						
						return value;
					}, {
						onblur : 'submit',
						onreset : function() {
							/*
							 * Unblock KeyTable, but only after this 'esc' key event
							 * has finished. Otherwise it will 'esc' KeyTable as
							 * well
							 */
							setTimeout(function() {
								keys.block = false;
							}, 0);
						}
					});

					/*
					 * Dispatch click event to go into edit mode - Saf 4 needs a
					 * timeout...
					 */
					setTimeout(function() {
						$(nCell).click();					
					}, 0);
				}
			});
		});	
	});
	$('.main-table_length').change(function() {	
		$('.delBtn').click(removeRow);
		$('.cBox').click(checkBox);
		$('.dDown > select').change(dropDown);
		$('.datepicker').click(function() {	
			var temp = $(this);
			var val = $(this)[0].value;
			if($(this)[0].value==null || $(this)[0].value=="")
			{
				$(this).datePicker().val(new Date().asString()).trigger('change');
			}
		});
		/* Apply a return key event to each cell in the table */
		$('#main-table tbody td').each(function() {
			keys.event.action(this, function(nCell) {
				if (!$(nCell).hasClass("locked")) {
					/*
					 * Block KeyTable from performing any events while jEditable is
					 * in edit mode
					 */
					keys.block = true;

					/* Initialise the Editable instance for this table */

					$(nCell).editable(function(value) {
						/* Submit function (local only) - unblock KeyTable */
						keys.block = false;
						if ($(nCell).hasClass("price") && value <= 0) {
							  alert('Price value must be greater than zero.');
							  value=1;
						}
						var data = {
							id : nCell.id,
							value : value
						};
						$.ajax( {
							type : "POST",
							url : "itemDetail.html?action=update",
							data : JSON.stringify(data)
						});
						
						return value;
					}, {
						onblur : 'submit',
						onreset : function() {
							/*
							 * Unblock KeyTable, but only after this 'esc' key event
							 * has finished. Otherwise it will 'esc' KeyTable as
							 * well
							 */
							setTimeout(function() {
								keys.block = false;
							}, 0);
						}
					});

					/*
					 * Dispatch click event to go into edit mode - Saf 4 needs a
					 * timeout...
					 */
					setTimeout(function() {
						$(nCell).click();					
					}, 0);
				}
			});
		});	
	});	
	$('.datepicker').datePicker(
	{
		onSelect: function(dateText) {
	        $(this).change();
	        }
			   })
		.change(function() {
			var val = this.value;				
			// Update value
			var data = {
					id : this.parentNode.id,
					value : val
				};
			$.ajax( {
				type : "POST",
				url : "itemDetail.html?action=update",
				data : JSON.stringify(data)
			});
		}				
	);	
	var temp = $('#main-table').width();
	temp = temp + 300;
	$('#main-table').width(temp);
	$('.header').width($('#main-table').width());
	$('.footer').width($('#main-table').width());
	oTable.fnSort( [ [1,'asc'] ] );
});

function checkBox(){	
	console.log($(this).attr('id'));
		// Get index of the selected cell
		//var aPos = oTable.fnGetPosition(this);
		var val = $(this).children('input').eq(0).is(':checked');
		if(val == true)
			val = 1;
		else
			val = 0;
		// Update value
		var data = {
				id : this.id,
				value : val
			};
		$.ajax( {
			type : "POST",
			url : "itemDetail.html?action=update",
			data : JSON.stringify(data)
		});
}
function dropDown(){
	    var id = $(this).parent('.dDown').attr('id');
		var val = $(this).val();
		console.log('id=' + id + ' val=' + val);
		// Update value
		var data = {
				id : id,
				value : val
			};
		$.ajax( {
			type : "POST",
			url : "itemDetail.html?action=update",
			data : JSON.stringify(data)
		});
}
function removeRow() {
	if ( confirm( 'Are you sure you wish to delete this record?' ) )
	{
		console.log($(this).attr('id'));
		var data = {
			id : $(this).attr('id')
		};
		$.ajax( {
			type : "POST",
			url : "itemDetail.html?action=delete",
			cache: false,
			data : JSON.stringify(data)
		});
		location.reload();
	}	
}