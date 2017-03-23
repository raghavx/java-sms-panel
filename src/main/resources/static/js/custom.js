$(document).ready(function() {
    $('#user-data').DataTable();
} );

$(function () {
    $('#datetimepicker1').datepicker();
});

	$(function(){
	$('#datetimepicker2').datepicker();
});

$('.delete-tr').click(function(){
	$(this).parent().parent().remove();
});