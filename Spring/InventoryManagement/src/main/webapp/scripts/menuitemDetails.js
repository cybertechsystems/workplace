var __uploadId;
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
										
					var data = {
						id : nCell.id,
						value : value
					};
					$.ajax( {
						type : "POST",
						url : "menu.html?action=update",
						scriptCharset: "utf-8" , 
						contentType: "application/json; charset=utf-8",
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
			$.post("menu.html?action=add");
			window.location.href=window.location.href + '?new=true';
		}
		
	});
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
	$('.sorting').click(function() {	
		$('.delBtn').click(removeRow);
		$('.cBox').click(checkBox);
		$('.dDown > select').change(dropDown);
		$('.uLoad').click(uploader);
	    $('#uploadForm').submit(saveFilename);
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
						
						var data = {
							id : nCell.id,
							value : value
						};
						$.ajax( {
							type : "POST",
							url : "menu.html?action=update",
							scriptCharset: "utf-8" , 
							contentType: "application/json; charset=utf-8",
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
		$('.uLoad').click(uploader);
	    $('#uploadForm').submit(saveFilename);
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

						var data = {
							id : nCell.id,
							value : value
						};
						$.ajax( {
							type : "POST",
							url : "menu.html?action=update",
							scriptCharset: "utf-8" , 
							contentType: "application/json; charset=utf-8",
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
		$('.uLoad').click(uploader);
	    $('#uploadForm').submit(saveFilename);
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
						
						var data = {
							id : nCell.id,
							value : value
						};
						$.ajax( {
							type : "POST",
							url : "menu.html?action=update",
							scriptCharset: "utf-8" , 
							contentType: "application/json; charset=utf-8",
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
		$('.uLoad').click(uploader);
	    $('#uploadForm').submit(saveFilename);
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
						
						var data = {
							id : nCell.id,
							value : value
						};
						$.ajax( {
							type : "POST",
							url : "menu.html?action=update",
							scriptCharset: "utf-8" , 
							contentType: "application/json; charset=utf-8",
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
				url : "menu.html?action=update",
				scriptCharset: "utf-8" , 
				contentType: "application/json; charset=utf-8",
				data : JSON.stringify(data)
			});
		}				
	);	
	var temp = $('#main-table').width();
	temp = temp;
	$('#main-table').width(temp);
	$('.header').width($('#main-table').width());
	$('.footer').width($('#main-table').width());
    $('.uLoad').click(uploader);
    $('#uploadForm').submit(saveFilename);
    $('#delfile').click(saveFilename);
    // Sort immediately with column 3 (at position 1 in the array. More could be sorted with additional array elements
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
			url : "menu.html?action=update",
			scriptCharset: "utf-8" , 
			contentType: "application/json; charset=utf-8",
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
			url : "menu.html?action=update",
			scriptCharset: "utf-8" , 
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(data)
		});
}

function uploader(e) {
	__uploadId = $(this).attr('id');
	var handle = $('#uploadPopup');
	if (handle.is(':visible')) {
	  handle.hide();
	}
	$('#uploadPopup').css({
		"position": "absolute",  
		"top": e.pageY,  
		"left": e.pageX
		});
	handle.show('slow');
}
function saveFilename(event) {
	var id = __uploadId;
	//var val = $('#upload').val();
	var val = id;
	if (event.target.id == 'delfile') {
		val = '';
		console.log('id=' + id + ' val=' + val);
		if (confirm('Are you sure you wish to delete this image?')) {
			// Update value
			var data = {
				id : id,
				value : val
			};
			$.ajax( {
				type : "POST",
				url : "menu.html?action=update",
				scriptCharset: "utf-8" , 
				contentType: "application/json; charset=utf-8",
				data : JSON.stringify(data)
			});
			window.location.href=$('#uploadForm').attr('action') + '&delete=' + id;
		}
	} else {
		console.log('id=' + id + ' val=' + val);
		if ($.trim(val).length == 0) {
			alert('Please select an image.');
			return false;
		} else {
			// Update value
			var data = {
				id : id,
				value : val
			};
			$.ajax( {
				type : "POST",
				url : "menu.html?action=update",
				scriptCharset: "utf-8" , 
				contentType: "application/json; charset=utf-8",
				data : JSON.stringify(data)
			});
			var input = $("<input>").attr("type", "hidden").attr("name", "upname").val(val);
			$('#uploadForm').append($(input));
			return true;
		}
	}
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
			url : "menu.html?action=delete",
			cache: false,
			data : JSON.stringify(data)
		});
		location.reload();
	}	
}
function hidePopup() {
	$('#uploadPopup').hide('slow');
}