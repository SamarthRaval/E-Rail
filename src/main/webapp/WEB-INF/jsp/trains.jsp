
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="../assets/img/apple-icon.png">
<link rel="icon" type="image/png" href="../assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Trains</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
	name='viewport' />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link href="/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
	integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay"
	crossorigin="anonymous">
<link href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>

	<div class="wrapper ">
		<jsp:include page="navbar.jsp" />
		<div class="container-fluid" style="margin-top: 2.5%">
			<h2>Trains</h2>
		</div>
		<div class="container-fluid">
			<div style="display: none" id="userId"><%= session.getAttribute("userId") %></div>
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<select class="form-control" id="sourceStation"></select>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<select class="form-control" id="destinationStation"></select>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<input class="datepicker form-control" placeholder="select boarding date" data-date-format="mm/dd/yyyy">
					</div>
				</div>

				<div class="col-md-2">
					<div class="form-group">
						<button type="button" class="btn btn-info btn-md"
							id="searchTrains">Search Trains</button>
					</div>
				</div>
			</div>
		</div>

		<div class="container-fluid table-responsive">
			<table class="table table-dark table-striped" id="trainListTable">
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Train Number</th>
						<th scope="col">Train Name</th>
						<th scope="col">Arrival Time</th>
						<th scope="col">Source Station</th>
						<th scope="col">Destination Station</th>
						<th scope="col">Reach Time</th>
						<th scope="col">Class</th>
						<th scope="col">Booking</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

</body>

<script src="/js/core/jquery.min.js"></script>
<script src="/js/core/popper.min.js"></script>
<script src="/js/core/bootstrap.min.js"></script>
<script src="/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<script src="/js/plugins/bootstrap-notify.js"></script>
<script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/loadingoverlay.js"></script>

<script>
	$(document).ready(

			function() {
				var date = new Date();
				date.setDate(date.getDate()-1);
				$('.datepicker').datepicker({
					format: 'mm/dd/yyyy',
					startDate: date
				});

				$("#datepicker").datepicker();
				var sourceStationId = window.location.href.split('trains?')[1]
						.split('source=')[1].split('&')[0];
				var destinationId = window.location.href.split('trains?')[1]
						.split('source=')[1].split('&')[1]
						.split('destination=')[1];

				getStationList();
				getTrainList(sourceStationId, destinationId);
				getClass();
			});

	function getTrainList(sourceStationId, destinationId) {

		$
				.ajax({
					url : "/getTrainsByStation?sourceStation="
							+ sourceStationId + "&destinationStation="
							+ destinationId,
					type : "GET",
					dataType : "json",
					success : function(response) {
						$('#trainListTable tbody').html("");
						var tbody = "";
						for (var i = 0; i < response.data.trainList.length; i++) {
							tbody += '<tr>';
							tbody += '<td>' + response.data.trainList[i].id
									+ '</td>';
							tbody += '<td>'
									+ response.data.trainList[i].trainNumber
									+ '</td>';
							tbody += '<td>'
									+ response.data.trainList[i].trainName
									+ '</td>';
							for (var j = 0 ; j < response.data.trainList[i].trainStation.length; j++){
								if((response.data.trainList[i].trainStation[j].trainId ==  response.data.trainList[i].id) && (response.data.trainList[i].trainStation[j].stationId.id == sourceStationId)){
									tbody += '<td>'
											+response.data.trainList[i].trainStation[j].time
											+ '</td>';
								}
							}
							tbody += '<td>'
									+ response.data.trainList[i].trainStation[0].stationId.stationName
									+ '</td>';
							tbody += '<td>'
									+ response.data.trainList[i].trainStation[1].stationId.stationName
									+ '</td>';
							for (var j = 0 ; j < response.data.trainList[i].trainStation.length; j++){
								if((response.data.trainList[i].trainStation[j].trainId ==  response.data.trainList[i].id) && (response.data.trainList[i].trainStation[j].stationId.id == destinationId)){
									tbody += '<td>'
											+ response.data.trainList[i].trainStation[j].time
											+ '</td>';
								}
							}

							tbody += '<td>'
									+ '<select id="classType"><option value="economy">Economy</option><option value="business">Business</option></select>'
									+ '</td>';

							tbody += '<td><button class="btnBooking btn-sm btn-success" onclick="createBooking(this)" >Book Now</button></td></tr>';

						}
						$('#trainListTable tbody').append(tbody);

						getClass();
					},
					error : function(response, status, xhr) {
						console.log(status);
					}
				});

	}

	function getStationList() {
		$.ajax({
			url : "/getStationList",
			type : "GET",
			dataType : "json",
			success : function(response) {

				$('#sourceStation').html("");
				$('#destinationStation').html("");
				var options = "";
				for (var i = 0; i < response.data.length; i++) {
					options += '<option value='+response.data[i].id+'>'
							+ response.data[i].stationName;
					options += '</option>';
				}
				$('#sourceStation').append(options);
				$('#destinationStation').append(options);
			},
			error : function(response, status, xhr) {
				console.log(status);
			}
		});
	}

	function createBooking(button) {
		$.LoadingOverlay("show");
		var tr = button.parentElement.parentElement;
		console.log($(button).closest('tr').find('#classType').val());

		var requestBody = '{"bookingNumber":null,"trainName":"'
				+ $(button).closest('tr').find('td')[2].innerHTML
				+ '","sourceStationName":"'
				+ $(button).closest('tr').find('td')[4].innerHTML;
		requestBody += '","destinationStationName":"'
				+ $(button).closest('tr').find('td')[5].innerHTML
				+ '","userId":'+$("#userId").html()+',"classType":"'+$(button).closest('tr').find('#classType :selected').text()+'","arrivalTime":"'
				+ $(button).closest('tr').find('td')[3].innerHTML
				+ '","reachTime":"'
				+ $(button).closest('tr').find('td')[6].innerHTML + '"}';

		$.ajax({
			url : "/createTrainBooking?bookingDate="+$('.datepicker').val() ,
			type : "POST",
			data : requestBody,
			contentType : "application/json",
			dataType : "json",
			success : function(response) {
				$.LoadingOverlay("hide");

				if(response.status == 200){
					console.log(response);
					window.location.href = "/payment";
				}else if(response.status == 3001){
					$.notify({
						message : response.message
					}, {
						// settings
						placement : {
							from : "top",
							align : "center"
						},
						allow_dismiss: true,
						type : 'danger'
					});
				}

			},
			error : function(response, status, xhr) {
				console.log(status);
			}
		});
	}

	$("#searchTrains").click(function() {
		var sourceStationId = $("#sourceStation").val();
		var destinationStationId = $("#destinationStation").val();
		if (sourceStationId != destinationStationId) {
			getTrainList(sourceStationId, destinationStationId);
		} else {
			$.notify({
				message : 'Source and Destination Station cannot be same'
			}, {
				// settings
				placement : {
					from : "top",
					align : "center"
				},
				allow_dismiss: true,
				type : 'danger'
			});
		}
	});


	function getClass(){
		$.ajax({
			url : "/getAllClassType",
			type : "GET",
			dataType : "json",
			success : function(response) {
				$('#classType').html("");
				var options = "";
				for (var i = 0; i < response.data.length; i++) {
					options += '<option value='+response.data[i].id+'>'
							+ response.data[i].type;
					options += '</option>';
				}
				$('#classType').append(options);
			},
			error : function(response, status, xhr) {
				console.log(status);
			}
		});
	}
</script>
</html>