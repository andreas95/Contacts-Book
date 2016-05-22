$(document).ready(function() {
	$('#contact_add').hide();
	$('#contact_edit').hide();
	$('#pane-right-top2').hide();
	$('#pane-right-top3').hide();
	
	$('.add').click(function(){
		$('#pane-right-top1').hide();
		$('#pane-right-top2').show();
		$('#details').hide();
		$('#contact_add').show();
});
	
	$('.edit').click(function(){
		$('#pane-right-top1').hide();
		$('#pane-right-top3').show();
		$('#details').hide();
		$('#contact_edit').show();
		document.getElementById("oldphone").value=document.getElementById("editphone").value;
});

	$('.done').click(function(){
		$('#pane-right-top2').hide();
		$('#pane-right-top1').show();
		if ($('#addfirstn').val()=="" || $('#addfirstn').val()==" ") {
			alert("First Name is blank!");
		} else 
		if ($('#addlastn').val()=="" || $('#addlastn').val()==" ") {
			alert("Last Name is blank!");
		} else
		if ($('#addphone').val()=="" || $('#addphone').val()==" ") {
			alert("Mobile is blank!");
		} else 
		if ($('#addemail').val()=="" || $('#addemail').val()==" ") {
			alert("Email is blank!");
		} else 
		{
			alert("Success!");
			$("#form_add").submit();
		}
});

$('.done2').click(function(){
		$('#pane-right-top3').hide();
		$('#pane-right-top1').show();
		if ($('#editfirstn').val()=="" || $('#editfirstn').val()==" ") {
			alert("First Name is blank!");
		} else 
		if ($('#editlastn').val()=="" || $('#editlastn').val()==" ") {
			alert("Last Name is blank!");
		} else
		if ($('#editphone').val()=="" || $('#editphone').val()==" ") {
			alert("Mobile is blank!");
		} else 
		if ($('#editemail').val()=="" || $('#editemail').val()==" ") {
			alert("Email is blank!");
		} else 
		{
			alert("Success!");
			$("#form_edit").submit();
		}
});
	
	$('.cancel').click(function(){
	location.reload();
});

	
	
	$('.del').click(function(){
	document.getElementById("idel").value =document.getElementById("curr").value;
	$("#formdel").submit();
});
    $("li").click(function(event) {
    	document.getElementById("curr").value =event.target.id;
		$("#form").submit();
    }); 
});