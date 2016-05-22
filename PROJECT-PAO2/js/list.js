$(document).ready(function(){
	 $('.active').removeClass('active');
	$("li[id*='"+document.getElementById("curr").value+"']").addClass('active');
$('.list').click(function(){
    $('.active').removeClass('active');
    $(this).addClass('active');
});

});