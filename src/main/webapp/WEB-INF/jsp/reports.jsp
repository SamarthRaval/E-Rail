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
		<div class="row" style="margin: 1%">
			<div class="col-6" style="margin-top: 5%;" id="report">
				<div class="jumbotron"
					style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
					<div class="clearfix">
						<h2 style="color: white; text-align: center; margin-bottom: 5%"
							class="float-left">Generate report</h2>
					</div>
					<form id="frmReport" action="javascript:void(0)">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<fieldset class="form-group">
										<div class="row">

											<div class="col-sm-10">
												<div class="form-check">
													<input class="form-check-input" type="radio"
														name="ReportName" id="gridRadios1" value="Booking By Year"
														checked> <label class="form-check-label"
														for="gridRadios1" style="color: white"> Booking By
														Year </label>
												</div>


											</div>
										</div>
									</fieldset>

								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Year
									</label> <select class="form-control" id="drpYear">
										<option value=''>--Select Year--</option>
										<option value='2019'>2019</option>
										<option value='2018'>2018</option>
										<option value='2017'>2017</option>
										<option value='2016'>2016</option>
									</select>
								</div>
							</div>
							<div class="col-md-12" style="text-align: center; margin-top: 5%">
								<div class="form-group">
									<button id="btnGeneratReport">Generate Report</button>

								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-6" id="reportDiv"></div>
		</div>
	</div>
</body>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="/js/core/jquery.min.js"></script>
<script src="/js/core/popper.min.js"></script>
<script src="/js/core/bootstrap.min.js"></script>
<script src="/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<script src="/js/plugins/bootstrap-notify.js"></script>
<script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/loadingoverlay.js"></script>
<script>
	$('#btnGeneratReport').click(
			function() {
				var Month = $("#drpMonth").val();
				var Year = $("#drpYear").val();
				var ReportName = $('input[name=ReportName]:checked',
						'#frmReport').val();
				if (ReportName == "Booking By Month") {
					GetReportForBookingByMonth(Year, Month);
				} else {
					GetReportForBookingByYear(Year);
				}
			});
	function GetReportForBookingByMonth(Year, Month) {
		$.ajax({
			url : "/bookingByMonth",
			type : "GET",
			data : {
				Year : Year,
				Month : Month
			},
			success : function(response) {
				if (response.data_id != null) {
					var Category = new Array();
					var Series = new Array();
					var iter = 0
					for (x in response.data_id) {
						Category[iter] = x;
						Series[iter] = response.data_id[x];
						iter = iter + 1;
					}
					Highcharts.chart('reportDiv', {
						chart : {
							type : 'column'
						},
						title : {
							text : 'Booking By Month'
						},
						subtitle : {
							text : 'Year:' + Year + 'Month' + Month
						},
						xAxis : {
							categories : Category,
							crosshair : true
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Count'
							}
						},
						plotOptions : {
							column : {
								pointPadding : 0.2,
								borderWidth : 0
							}
						},
						series : [ {
							name : "Train",
							data : Series
						} ]
					});
				} else {
					$('#reportDiv').text("");
					$.notify({
						// options
						message : 'No Data to Display'
					}, {
						// settings
						placement : {
							from : "top",
							align : "center"
						},
						type : 'danger'
					});
				}
			},
			error : function(response, status, xhr) {
				console.log(response);
			}
		});
	}
	function GetReportForBookingByYear(Year) {
		$.ajax({
			url : "/bookingByYear?Year=" + Year,
			type : "GET",
			contentType : "application/json",
			success : function(response) {
				console.log(response)
				if (response.data_id != null) {

					var Category = new Array();
					var Series = new Array();
					var iter = 0
					for (x in response.data_id) {
						Category[iter] = x;
						Series[iter] = response.data_id[x];
						iter = iter + 1;
					}
					Highcharts.chart('reportDiv', {
						chart : {
							type : 'column'
						},
						title : {
							text : 'Booking By Year'
						},
						subtitle : {
							text : 'Year:' + Year
						},
						xAxis : {
							categories : Category,
							crosshair : true
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Count'
							}
						},
						plotOptions : {
							column : {
								pointPadding : 0.2,
								borderWidth : 0
							}
						},
						series : [ {
							name : "Train",
							data : Series
						} ]
					});
				} else {
					$('#reportDiv').text("");
					$.notify({
						// options
						message : 'No Data to Display'
					}, {
						// settings
						placement : {
							from : "top",
							align : "center"
						},
						type : 'danger'
					});
				}
			},
			error : function(response, status, xhr) {
				console.log(response);
			}
		});
	}
</script>

</html>
