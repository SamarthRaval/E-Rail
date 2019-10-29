<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">

<title>Insert title here</title>
<style>
th, td {
	text-align: center;
	padding: 5px
}

thead {
	color: white;
	font-size: 18px
}
</style>
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
		<div class="row">
			<div class="col" style="padding: 20px" id="message"></div>
		</div>
		<div class="container-fluid">
			<div class="col-md-12" id="station">
				<div class="jumbotron"
					style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
					<div class="clearfix">
						<h2 style="color: white; text-align: center; margin-bottom: 5%"
							class="float-left">Stations</h2>
						<button type="button" class="btn btn-success float-right"
							id="addEditStation">Add Station</button>
					</div>

					<table id="tblStation" class="table table-striped"
						style="width: 100%; text-align: center">
						<thead>
							<tr>
								<th>Id</th>
								<th>Station Number</th>
								<th>Station Name</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
			<div class="col-6" style="margin-top: 5%;" id="frmStation">
				<div class="jumbotron"
					style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
					<h2 style="color: white; text-align: center; margin-bottom: 5%"
						id="titleStationform">Add Station</h2>
					<form action="javascript:void(0)">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Station
										Number</label> <input type="number" class="form-control" id="txtStationNumber" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Station
										Name : </label> <input type="text" class="form-control"
										id="txtStationName" />
								</div>
							</div>
							<div class="col-md-12" style="text-align: center; margin-top: 5%">
								<div class="form-group">
									<button class="btn btn-lg btn-outline-warning"
										id="btnAddStation" onclick="addStation()">Add Station</button>
									<button class="btn btn-lg btn-outline-warning"
										id="btnEditStation" onclick="editStation()">Edit
										Station</button>
								</div>
							</div>
						</div>
					</form>
					<input type="hidden" id="stationId">
				</div>

			</div>


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
	$(document).ready(function() {
		$('#station').show();
		getStationList();
		$('#frmStation').hide();
	});

	$('#addEditStation').click(function() {
		$('#titleStationform').html('Add Station');
		$('#frmStation').show();
		$('#station').show();
		$('#btnAddStation').show();
		$('#btnEditStation').hide();
		$('#txtStationName').val("");
		$('#txtStationNumber').val("");
	});
	function getStationList() {
		$
				.ajax({
					url : "/getStationList",
					type : "GET",
					dataType : "json",
					success : function(response) {
						console.log(response.data);
						var tabledata = "";
						$('#tblStation tbody').html("");
						for (var i = 0; i < response.data.length; i++) {
							tabledata += '<tr style="color:white"><td id='+response.data[i].id+'>'
									+ response.data[i].id + '</td>';
							tabledata += '<td >'
									+ response.data[i].stationNumber + '</td>';
							tabledata += '<td >' + response.data[i].stationName
									+ '</td>';
							tabledata += '<td ><button id="btnStationDelete_'
									+ response.data[i].id
									+ '"class="btn btn-md btn-danger" onclick="deleteStation('
									+ response.data[i].id
									+ ')">Delete</button> <button id="btnStationEdit_'
									+ response.data[i].id
									+ '"class="btn btn-md btn-warning" onclick="getStationDetail('
									+ response.data[i].id + ',\''
									+ response.data[i].stationName + '\',\''
									+ response.data[i].stationNumber
									+ '\')">Edit</button> </td></tr>';
						}
						$('#tblStation tbody').append(tabledata);
					},
					error : function(response, status, xhr) {
						console.log(status);
					}
				});
	}

	function deleteStation(id) {
		$('#frmStation').hide();
		if (confirm('Are you sure you want to delete this station ?')) {
			$.ajax({
				url : "/deleteStationbyId",
				type : "POST",
				data : {
					stationId : id
				},
				dataType : "json",
				success : function(response) {
					console.log(response.data);
					$.notify({
						// options
						message : 'Station Deleted Successfully'
					}, {
						// settings
						placement : {
							from : "top",
							align : "center"
						},
						type : 'success'
					});

					getStationList();
				},
				error : function(response, status, xhr) {
					console.log(status);
				}
			});
		}
	}

	function getStationDetail(id, Name, Number) {
		$('#titleStationform').html('Edit Station');
		$('#frmStation').show();
		$('#btnEditStation').show();
		$('#btnAddStation').hide();
		$('#txtStationName').val(Name);
		$('#txtStationNumber').val(Number);
		$('#stationId').val(id);
	}

	function addStation() {
		var stationDetail = {
			stationName : $('#txtStationName').val(),
			stationNumber : $('#txtStationNumber').val()
		};
		if ($('#txtStationName').val() != ""
				&& $('#txtStationNumber').val() != "") {
			$.ajax({
				url : "/addStation",
				type : "POST",
				data : JSON.stringify(stationDetail),
				contentType : "application/json; charset=utf-8",
				success : function(response) {
					console.log(response.Message);
					if (response.Message != "Data already Exist")
					{
						console.log(response.data);
						$.notify({
							// options
							message : 'Station Added Successfully'
						}, {
							// settings
							placement : {
								from : "top",
								align : "center"
							},
							type : 'success'
						});
						getStationList();
						$('#frmStation').hide();
					}
					else
					{
						alert("Data already exist, Try an Unique Data");
					}
				},
				error : function(response, status, xhr) {
					console.log(status);
				}
			});
		} else {
			alert("Please enter details to add Station, Fields cannot be empty. ");
		}

	}

	function editStation() {
		var id = $('#stationId').val();
		var stationDetail = {
			stationName : $('#txtStationName').val(),
			stationNumber : $('#txtStationNumber').val()
		};
		if ($('#txtStationName').val() != ""
				&& $('#txtStationNumber').val() != "") {
			$.ajax({
				url : "/updateStationDetails?stationId=" + id,
				type : "POST",
				data : JSON.stringify(stationDetail),
				contentType : "application/json; charset=utf-8",
				success : function(response) {
					if (response.Message != "Data already Exist") {
						console.log(response.data);
						$.notify({
							// options
							message : 'Station Updated Successfully'
						}, {
							// settings
							placement : {
								from : "top",
								align : "center"
							},
							type : 'success'
						});
						getStationList();
						$('#frmStation').hide();
					} else {
						alert("Data already exist, Try an Unique Data");
					}

				},
				error : function(response, status, xhr) {
					console.log(status);
				}
			});
		} else {
			alert("Please enter details to edit Station, Fields cannot be empty. ");
		}

	}
</script>
</html>