<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>User Registation</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<script src="/js/core/jquery.min.js"></script>
<script src="/js/core/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">E-RAIL</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="/login">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="#">About us</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Gallery</a></li>

			</ul>
		</div>
	</nav>


	<div class="container-fluid">
		<div class="row">
			<div class="col-4" style="margin-top: 5%">
				<div class="jumbotron"
					style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
					<h3 style="color: white; text-align: center; margin-bottom: 5%">
						User Registration:</h3>
					<form action="javascript:void(0)">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Email
									</label> <input type="text" class="form-control" placeholder="email"
										id="username">
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Password:
									</label> <input type="password" class="form-control" id="password">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Confirm
										Password: </label> <input type="password" class="form-control"
										id="confirmpassword">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Name:
									</label> <input type="text" class="form-control" id="name">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Gender:
									</label> <select id="ddlViewBy">
										<option value="1">Male</option>
										<option value="2" selected="selected">Female</option>
										<option value="3">Others</option>
									</select>
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Date
										of Birth: </label> <input type="text" class="form-control"
										id="dateofbirth">
								</div>
							</div>



							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Phone
										Number: </label> <input type="number" class="form-control"
										id="phonenumber">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Street
										Address: </label> <input type="text" class="form-control"
										id="streetaddress">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">City:
									</label> <input type="text" class="form-control" id="city">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Province:
									</label> <input type="text" class="form-control" id="province">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Postal
										Code: </label> <input type="text" class="form-control" id="postalcode">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: red" id="error"></label>
								</div>
							</div>

							<div class="col-md-12" style="text-align: center; margin-top: 5%">
								<div class="form-group">
									<input type="hidden" id="userRole" value="0">
									<button type="submit" class="btn btn-lg btn-outline-warning"
										id="register">Register</button>
								</div>
							</div>
						</div>
					</form>
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

<script>
	$(document).ready(function() {

	});
	$('#register').click(
			function() {

				var pass = $('#password').val();
				var conPass = $('#confirmpassword').val();
				var n = pass.localeCompare(conPass);

				if ($('#username').val() == '' || $('#password').val() == ''
						|| $('#confirmpassword').val() == ''
						|| $('#dateofbirth').val() == ''
						|| $('#name').val() == '' || $('#emailid').val() == ''
						|| $('#phonenumber').val() == ''
						|| $('#streetaddress').val() == ''
						|| $('#city').val() == '' || $('#province').val() == ''
						|| $('#postalcode').val() == '') {
					$("#error").empty();
					$("#error").append('Input can not be left blank');
				} else if (n != 0) {
					$("#error").empty();
					$("#error").append('Confirm password should match');
				} else {
					userRegistration();
				}
			});

	function userRegistration() {
		$("#error").empty();
		var e = document.getElementById("ddlViewBy");
		var strUser = e.options[e.selectedIndex].text;

		var obj = {
			"username" : $('#username').val(),
			"password" : $('#password').val(),
			"name" : $('#name').val(),
			"gender" : strUser,
			"dateofbirth" : $('#dateofbirth').val(),
			"phone" : $('#phonenumber').val(),
			"streetaddress" : $('#streetaddress').val(),
			"city" : $('#city').val(),
			"province" : $('#province').val(),
			"postalcode" : $('#postalcode').val(),
			"role" : $('#userRole').val(),
		};

		var jsonString = JSON.stringify(obj);
		console.log(jsonString);
		$.ajax({
			url : "/addUser",
			type : "POST",
			data : jsonString,
			contentType : "application/json",
			dataType : "json",
			success : function(response) {
				console.log(response);
				if (response.status == 200) {
					window.location.href = "/";
				} else if (response.status == 202) {
					$("#error").empty();
					$("#error").append(response.Error);
				} else {
					$("#error").empty();
					$("#error").append(response.Error);
					console.log(response);
				}
			},
			error : function(response, status, xhr) {
				$("#error").empty();
				$("#error").append(response);
				console.log(response);
			}
		});
	}
</script>
</html>