$(document).ready(function(){
		table = $("#projectTable").DataTable({
	        "processing": true,
	        "destroy": true,
	        "paging": true,
	        "searching": true,
	        "serverSide": true,
	        "ajax": {
	            "url":  "/ProjectA-0.5.0/projecttable",
	            "type": "POST",
	            dataType: "json",
	            "contentType": "application/json",
	            "data": function(d) {
	                return JSON.stringify(d);
	            }
	        },
	        "columns": [{"data":"CITY","title":"City"},
	        	{"data":"SHORT_STATE","title":"Short State"},
	        	{"data":"FULL_STATE","title":"Full State"},
	        	{"data":"COUNTY","title":"County"},
	        	{"data":"CITY_ALIAS","title":"City Alias"}],
	        initComplete: function() {
	        	$("#projectTable_filter").hide();
	            var col = this.api();
	            $('#projectTable thead th').each(function(i) {
	                        var title = $(this).text();
	                        $("#headSearch").append(
	                                '<th><input type="text" class="columnSearch" data-index="' + i +'" /></th>')
	                    });
	            $('.columnSearch').unbind().bind('keyup',function() {
	                        var val = $(this).val();
	                        col.column($(this).attr('data-index')).search(val ? $(this).val() :val,true,false).draw();
	                    });
	        },
	        "lengthMenu": [
	                       [10, 25],
	                       [10, 25]
	                   ]
	    });
})