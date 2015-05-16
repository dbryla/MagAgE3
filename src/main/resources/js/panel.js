$(document).ready(function() {

	getNodesTable = function() {
		var xhr = new XMLHttpRequest();

		xhr.open("GET", "/rest/nodes", true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
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
		xhr.send();
	}
	getClusterInfo = function() {
		var xhr = new XMLHttpRequest();

		xhr.open("GET", "/rest/cluster", true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var jsonResponse = JSON.parse(xhr.responseText);
				$('#clientNameValue').html(jsonResponse.clientName);
				$('#masterNodeValue').html(jsonResponse.masterNode);
				$('#membersValue').html(jsonResponse.members);
			}
		}
		xhr.send();
	}
	
	getNodesTable();
	getClusterInfo();

	$('#refreshButton').on('click', function() {
		$('#tableNodesContent').find('tr').remove();
		getNodesTable();
	});

});
