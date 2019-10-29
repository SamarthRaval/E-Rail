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
			<div class="col-md-12" id="train">
				<div class="jumbotron"
					style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
					<div class="clearfix">
						<h2 style="color: white; text-align: center; margin-bottom: 5%"
							class="float-left">Trains</h2>
						<button type="button" class="btn btn-success float-right"
							id="addEditTrain">Add Train</button>
					</div>
					<table id="tblTrain" class="table table-striped"
						style="width: 100%; text-align: center">
						<thead>
							<tr style="color: #ffffff; font-size: 18px">
								<th>Id</th>
								<th>Train Number</th>
								<th>Train Name</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-6" style="margin-top: 5%;" id="frmTrain">
				<div class="jumbotron"
					style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
					<h2 style="color: white; text-align: center; margin-bottom: 5%"
						id="titleTrainform">Add Trains</h2>
					<form action="javascript:void(0)">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Train
										Number</label> <input type="number" class="form-control" 
										id="txtTrainNumber" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Train
										Name : </label> <input type="text" class="form-control"
										id="txtTrainName" />
								</div>
							</div>
							<div class="col-md-12" style="text-align: center; margin-top: 5%">
								<div class="form-group">

									<button class="btn btn-lg btn-outline-warning" id="btnAddTrain"
										onclick="addTrain()">Add Train</button>
									<button class="btn btn-lg btn-outline-warning"
										id="btnEditTrain" onclick="editTrain()">Edit Train</button>
								</div>
							</div>
						</div>
					</form>
					<input type="hidden" id="trainId">
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
		$('#train').show();
		getTrainList();
		$('#frmTrain').hide();
	});

	$('#addEditTrain').click(function() {
		$('#titleTrainform').html('Add Train');
		$('#frmTrain').show();
		$('#btnAddTrain').show();
		$('#btnEditTrain').hide();
		$('#train').show();
		$('#txtTrainName').val("");
		$('#txtTrainNumber').val("");
	});

	function addTrain() {
		var traindetail = {
			trainName : $('#txtTrainName').val(),
			trainNumber : $('#txtTrainNumber').val()
		};

		if ($('#txtTrainName').val() != "" && $('#txtTrainNumber').val() != "") {
			$.ajax({
				url : "/addTrain",
				type : "POST",
				data : JSON.stringify(traindetail),
				contentType : "application/json; charset=utf-8",
				success : function(response) {
					console.log(response.Message);
					if (response.Message != "Data already Exist") {
						console.log(response.data);
						$.notify({
							// options
							message : 'Train Added Successfully'
						}, {
							// settings
							placement : {
								from : "top",
								align : "center"
							},
							type : 'success'
						});
						getTrainList();
						$('#frmTrain').hide();
					} else {
						alert("Data already exist, Try an Unique Data")
					}

				},
				error : function(response, status, xhr) {
					console.log(status);
				}
			});
		} else {
			alert("Please enter details to add Train, Fields cannot be empty. ");
		}

	}
	function editTrain() {
		var id = $('#trainId').val();
		var trainDetail = {
			trainName : $('#txtTrainName').val(),
			trainNumber : $('#txtTrainNumber').val()
		};

		if ($('#txtTrainName').val() != "" && $('#txtTrainNumber').val() != "") {
			$.ajax({
				url : "/updateTrainDetails?trainId=" + id,
				type : "POST",
				data : JSON.stringify(trainDetail),
				contentType : "application/json; charset=utf-8",
				success : function(response) {
					console.log(response.data);
					if (response.Message != "Data already Exist") {
						$.notify({
							// options
							message : 'Train Updated Successfully'
						}, {
							// settings
							placement : {
								from : "top",
								align : "center"
							},
							type : 'success'
						});
						$('#frmTrain').hide();
						getTrainList();
					} else {
						alert("Data already exist, Try an Unique Data")
					}

				},
				error : function(response, status, xhr) {
					console.log(status);
				}
			});
		} else {
			alert("Please enter details to edit Train, Fields cannot be empty. ");
		}

	}

	function getTrainList() {
		$
				.ajax({
					url : "/getTrainList",
					type : "GET",
					dataType : "json",
					success : function(response) {
						console.log(response.data);
						var tabledata = "";
						$('#tblTrain tbody').html("");
						for (var i = 0; i < response.data.length; i++) {
							tabledata += '<tr style="color:white"><td id='+response.data[i].id+'>'
									+ response.data[i].id + '</td>';
							tabledata += '<td >' + response.data[i].trainNumber
									+ '</td>';
							tabledata += '<td >' + response.data[i].trainName
									+ '</td>';
							tabledata += '<td ><button id="btnTrainDelete_'
									+ response.data[i].id
									+ '"class="btn btn-md btn-danger" onclick="deleteTrain('
									+ response.data[i].id
									+ ')">Delete</button> <button id="btnTrainEdit_'
									+ response.data[i].id
									+ '"class="btn btn-md btn-warning" onclick="getTrainDetail('
									+ response.data[i].id + ',\''
									+ response.data[i].trainName + '\','
									+ response.data[i].trainNumber
									+ ')">Edit</button> </td></tr>';
						}
						$('#tblTrain tbody').append(tabledata);
					},
					error : function(response, status, xhr) {
						console.log(status);
					}
				});
	}

	function getTrainDetail(id, Name, Number) {
		$('#titleTrainform').html('Edit Train');
		$('#frmTrain').show();
		$('#btnEditTrain').show();
		$('#btnAddTrain').hide();
		$('#txtTrainName').val(Name);
		$('#txtTrainNumber').val(Number);
		$('#trainId').val(id);
	}

	function deleteTrain(id) {
		$('#frmTrain').hide();
		if (confirm('Are you sure you want to delete this train ?')) {
			$.ajax({
				url : "/deleteTrainbyId",
				type : "POST",
				data : {
					trainId : id
				},
				dataType : "json",
				success : function(response) {
					console.log(response.Message);
					if(response.Message == "delete Successfully")
						{
						$.notify({
							// options
							message : 'Train Deleted Successfully'
						}, {
							// settings
							placement : {
								from : "top",
								align : "center"
							},
							type : 'success'
						});
						getTrainList();	
						}
				},
				error : function(response, status, xhr) {
					console.log(status);
				}
			});
		}

	}
</script>
</html>