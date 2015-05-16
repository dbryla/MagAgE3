$(document).ready(function() {

	getNodesTable = function() {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "/rest/nodes", true);
		console.log(xhr.response);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.response == "") {
					alert("Please start cluster and refresh page")
				} else {
					var jsonResponse = JSON.parse(xhr.responseText);
					$.each(jsonResponse, function() {
						var documentRow = $('<tr>');
						documentRow.append($('<td>').append(this.id));
						documentRow.append($('<td>').append(this.type));
						documentRow.append($('<td>').append(this.workerState));
						documentRow.append($('<td>').append(this.errors));
						$('#tableNodesContent').append(documentRow);
					});
				}
			}
		}
		xhr.send();
	}
	getClusterInfo = function() {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "/rest/cluster", true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.response == "") {
				} else {
					var jsonResponse = JSON.parse(xhr.responseText);
					$('#clientNameValue').html(jsonResponse.clientName);
					$('#masterNodeValue').html(jsonResponse.masterNode);
					var members = "";
					$.each(jsonResponse.members, function() {
						members += this + " ";
					});
					$('#membersValue').html(members);
				}
			}
		}
		xhr.send();
	}

	getNodesTable();
	getClusterInfo();

	$('#refreshButton').on('click', function() {
		$('#tableNodesContent').find('tr').remove();
		getNodesTable();
		getClusterInfo();
	});

});
