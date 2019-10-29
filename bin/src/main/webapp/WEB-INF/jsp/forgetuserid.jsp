<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Forget UserID</title>
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
					<h2 style="color: white; text-align: center; margin-bottom: 5%">
						Forget User Name</h2>
					<form
						action="javascript:void(0)">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Name:
									</label> <input type="text" class="form-control" id="name">
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Phone
										Number: </label> <input type="number" class="form-control"
										id="phonenumber">
								</div>
							</div>

							<div class="col-md-12" style="text-align: center; margin-top: 5%">
								<div class="form-group">
									<button type="submit" class="btn btn-lg btn-outline-warning"
										id="forgetusername">Forget User Name</button>
								</div>
							</div>

							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white" id="username"></label>
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
	$('#forgetusername').click(function() {
		if ($('#name').val() == '' || $('#phonenumber').val() == '') {
			alert('Input can not be left blank');
		} else {
			forgetUserName();
		}
	});

	function forgetUserName() {

		var obj = {
			"phone" : $("#phonenumber").val(),
			"name" : $("#name").val()
		};

		var jsonString = JSON.stringify(obj);

		$.ajax({
			url : "/findUserByPhone",
			type : "POST",
			data : jsonString,
			contentType : "application/json",
			dataType : "json",
			success : function(response) {
				if (response.status == 200) {
					
					document.getElementById('username').innerHTML = "Username: "+ response.data;

				}
				else{
					document.getElementById('username').innerHTML = "No such data exist";
				}

			},
			error : function(response, status, xhr) {
				
				document.getElementById('username').innerHTML = "No such data exist error";

			}
		});
	}
</script>
</html>