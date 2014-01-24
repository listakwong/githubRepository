function doAction(path) {
	document.getElementById('fr').action = path;
	$("#fr").submit();
}

function toAction(path,key,value) {
	document.getElementById('fr').action = path;
	$("#" + key).val(value);
	$("#fr").submit();
}

function doPage(path,page) {
	document.getElementById('fr').action = path;
	$("#page").val(page);
	$("#fr").submit();
}

function showConfirmDialog(message,path,actionType) {
	$("#display-dialog").attr("display","block");
	$("#display-dialog p").text(message);
	
	if("delete" == actionType || "back" == actionType) {
		$( "#display-dialog" ).dialog({
			resizable: true,
			modal: true,
			buttons: {
				"OK": function() {
					$( this ).dialog( "close" );
					doAction(path);
				},
				"Cancel": function() {
		        	$( this ).dialog( "close" );
		        }
			}
		});
	} else if("save" == actionType) {
		$( "#display-dialog" ).dialog({
			resizable: true,
			width: 390,
			modal: true,
			buttons: {
				"Save": function() {
					$( this ).dialog( "close" );
					//doAction(path);
				},
				"Not Save": function() {
					$( this ).dialog( "close" );
					//doAction(path);
				},
		        "Cancel": function() {
		        	$( this ).dialog( "close" );
		        }
			}
		 });
	}
}

function doDelete(message,path,actionType,deleteType) {
	var num = 0 ,_checkboxVal ,_radioVal ,_flag = false;;
	
	if(deleteType == "checkbox"){
		var selectall_ind = $("#selectall_ind").val();
		
		$("input[id='tableTagCheckbox']:checkbox:checked").each(function(){ 
			_checkboxVal = $(this).val() ;
			num ++;
		}) ;
		if(num == 0) {
			showAlertDialog("Please select a row(s) first.");
			return ;
		}
		
		if(selectall_ind == false) {
			if(num != 1) {
				showAlertDialog("Please select one row only.");
				return ;
			}
		} else {
			$('#tableTagCheckbox').val(_checkboxVal);
			showConfirmDialog(message,path,actionType);
		}
	} else if(deleteType == "radio"){
		$("input[id='tableTagRadio']:checkbox:checked").each(function(){ 
			_radioVal = $(this).val() ;
			_flag = true;
		}) ;
		
		if(!_flag){
			showAlertDialog("Please select a row first.");
			return ;
		}
		$('#tableTagRadio').val(_radioVal);
		showConfirmDialog(message,path,actionType);
	} else {
		showConfirmDialog(message,path,actionType);
	}
	
}

function showAlertDialog(message) {
	$("#display-dialog").attr("display","block");
	$("#display-dialog p").text(message);
	
	$( "#display-dialog" ).dialog({
		resizable: true,
		modal: true,
		buttons: {
			"OK": function() {
				$( this ).dialog( "close" );
			}
		}
	 });
}

function checkAll() {
    var checkAll = document.getElementById("selectall");
	var checkboxes = document.getElementsByName("tableTagCheckbox");

	for (var i = 0; i < checkboxes.length; i++) {
		if (checkAll.checked) {
			checkboxes[i].checked = true;
		}
		else {
			checkboxes[i].checked = false;
		}
	}
}

function changeSort(path,sortBy) {
	var sort =  $("#sort").val();
	if (sort == "asc")
		$("#sort").val("desc");
	else
		$("#sort").val("asc");
	$("#sortBy").val(sortBy);
	$("#page").val("1");
	$("#fr").attr('action',path);
	$("#fr").submit();
}

/*
var ignoreElementForConfirmExit = new Array();
$(document).ready(function() {
	// Prevent accidental navigation away
	$(':input',document.forms[0]).filter(function()	{
		// ignore change event if the element in ignoreElementForConfirmExit array
		if (typeof(ignoreElementForConfirmExit) != "undefined")	{
			for (var i = 0; i < ignoreElementForConfirmExit.length; i++)	{
				if ((typeof this.name != "undefined" && 
						ignoreElementForConfirmExit[i] == this.name) ||
					(typeof this.id != "undefined" && 	
						ignoreElementForConfirmExit[i] == this.id))	{
					return false;
				}				
			}
		}

		return true;
	}).bind("change", 
	    	function() {
    	setConfirmUnload(true); 
    	}
	);	

	// Prevent show twice unloadMessage when click href
    $('a').filter(function() {
        return (/^javascript\:/i).test($(this).attr('href'));
    }).each(function() {
        var hrefscript = $(this).attr('href');
        hrefscript = hrefscript.substr(11);
        $(this).data('hrefscript', hrefscript);
    }).click(function() {
        var hrefscript = $(this).data('hrefscript');
        eval(hrefscript);
        return false;
    }).attr('href', '#');

});

*/