<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<script src="/js/core/jquery.min.js"></script>
<script src="/js/core/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
a {
	color: #ffffff;
}

a:hover {
	color: greenyellow;
}
</style>
</head>

<body
	style="background: url('http://www.toca-ch.com/data/walls/46/22924157.jpg'); background-size: cover; height: 800px">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">E-RAIL</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav ml-auto">
				<%--				<li class="nav-item active"><a class="nav-link" href="/login">Login</a></li>--%>

			</ul>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-4" style="margin-top: 5%">
				<div class="jumbotron"
					style="background: #333b4dcf; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
					<h2 style="color: white; text-align: center; margin-bottom: 5%">
						Login</h2>
					<form action="javascript:void(0)">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">User
										ID: </label> <input type="text" class="form-control" id="userid">
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
									<a href="/forgetuserid">Forget UserID</a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="/forgetpassword">Forget Password</a>
								</div>
							</div>
							<div class="col-md-12" style="text-align: center; margin-top: 5%">
								<div class="form-group">
									<button type="submit"
										class="col-md-6 btn-lg btn-outline-warning" id="loginBtn">Login</button>
								</div>
							</div>
							<div class="col-md-12" style="text-align: center; margin-top: 5%">
								<div class="form-group">
									<button class="col-md-6 btn-lg btn-outline-info"
										id="registerBtn">Register</button>
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
<script src="/js/loadingoverlay.js"></script>

<script src="/js/plugins/bootstrap-notify.js"></script>
<script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	// $('#loginBtn').click(
	// 		function() {
	// 			if ($('#userid').val() == '' || $('#password').val() == '') {
	// 				alert('Input can not be left blank');
	// 			} else {
	// 				loginCheck();
	// 			}
	// 		});

	$('#loginBtn').click(function() {
		var obj = {
			"username" : $("#userid").val(),
			"password" : $("#password").val()
		};

		var jsonString = JSON.stringify(obj);
		$.LoadingOverlay("show");
		$.ajax({
			url : "/userLogin",
			type : "POST",
			data : jsonString,
			contentType : "application/json",
			dataType : "json",
			async : false,
			success : function(response) {
				console.log(response);
				$.LoadingOverlay("hide");
				if (response.status == 200) {
					var role = response.role;

					// if(role === 1){
					// 	window.location.href = "/adminPanel";
					// }else{
					window.location.href = "/";
					// }
				} else {
					$.notify({
						message : 'Invalid Credentials'
					}, {
						// settings
						placement : {
							from : "top",
							align : "center"
						},
						allow_dismiss : true,
						type : 'danger'
					});

				}

			},
			error : function(response, status, xhr) {
				$.LoadingOverlay("hide");
				console.log(response);
			}
		});
	});

	function loginCheck() {

	}

	$('#registerBtn').click(function() {
		window.location.href = "/userregistration";
	})
</script>
</html>