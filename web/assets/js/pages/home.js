/* ------------------------------------------------------------------------------
*
*  # Basic form inputs
*
*  Specific JS code additions for form_input_basic.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

	// Default file input style
	$(".file-styled").uniform({
		fileButtonHtml: '<i class="icon-plus2"></i>'
	});


	// Primary file input
	$(".file-styled-primary").uniform({
		wrapperClass: 'bg-warning',
		fileButtonHtml: '<i class="icon-plus2"></i>'
	});


	if($('.datatable-basic').length != 0){
		// Table setup
		// ------------------------------

		// Setting datatable defaults
		$.extend( $.fn.dataTable.defaults, {
			autoWidth: false,
			columnDefs: [{
				orderable: false,
				width: '100px',
				targets: [ 5 ]
			}],
			dom: '<"datatable-header"fl><"datatable-scroll"t><"datatable-footer"ip>',
			language: {
				search: '<span>Filter:</span> _INPUT_',
				lengthMenu: '<span>Show:</span> _MENU_',
				paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
			},
			drawCallback: function () {
				$(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').addClass('dropup');
			},
			preDrawCallback: function() {
				$(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').removeClass('dropup');
			}
		});
		// Basic datatable
		$('.datatable-basic').DataTable();
		$('.datatable-answer').DataTable();
		// Add placeholder to the datatable filter option
		$('.dataTables_filter input[type=search]').attr('placeholder','Type to filter...');


		// Enable Select2 select for the length option
		$('.dataTables_length select').select2({
			minimumResultsForSearch: "-1"
		});
	}

	if ($('.daterange-single').length != 0){
		// Single picker
		$('.daterange-single').daterangepicker({
			singleDatePicker: true,
			startDate: moment()
		});
	}

	if ($('.select').length != 0){
		$('.select').select2({
			minimumResultsForSearch: "-1"
		});
	}

	if ($('.steps-basic').length != 0){
		// Basic wizard setup
		$(".steps-basic").steps({
			headerTag: "h6",
			bodyTag: "fieldset",
			transitionEffect: "fade",
			titleTemplate: '<span class="number">#index#</span> #title#',
			labels: {
				finish: 'Submit'
			},
			onFinished: function (event, currentIndex) {
				$('input:checkbox').each(function () {
					var name = $(this).attr('name');
					$(this).prop("checked") || $(this).is(":checked")
						?  $(this).attr('value','1')
						: $(this).html("<input type='hidden' name='"+name+"' value='0' />");
				});
				$('.steps-basic').submit();
			}
		});
	}

});
