$(document).ready(function() {
	$('#clickButton').on('click', function() {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "/rest/hello", false);
		xhr.send();

		console.log(xhr.status);
		console.log(xhr.statusText);
		console.log(xhr.response);
		$('#divId').html(xhr.response);
	});
	
});