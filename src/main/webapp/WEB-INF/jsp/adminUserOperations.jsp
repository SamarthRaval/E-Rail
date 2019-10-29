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
thead
{
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
		<jsp:include page="navbar.jsp"/>
		<div class="row">
			<div class="col" style="padding: 20px" id="message"></div>
		</div>
		<div class="container-fluid">
			<div class="col" id="user">
				<div class="jumbotron"
					style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
					<div class="clearfix">
						<h2 style="color: white; text-align: center; margin-bottom: 5%"
							class="float-left">Users</h2>
						<div class="float-right">
							<button type="button" class="btn btn-success float-right"
								id="addAdmin">Add New Admin</button>
						</div>
					</div>
					<table id="tblUser" class="table table-striped" style="width: 100%; text-align: center">
						<thead>
							<tr >
								<th>id</th>
								<th>User Name</th>
								<th>Gender</th>
								<th>Date of Birth</th>
								<th>Phone Number</th>
								<th>StreetAddress</th>
								<th>City</th>
								<th>Province</th>
								<th>Postal Code</th>
								<th>Action</th>

							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
			<div class="col-6" style="margin-top: 5%;" id="frmUser">
				<div class="jumbotron"
					style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
					<h2 style="color: white; text-align: center; margin-bottom: 5%"
						id="titleUserform">Add Admin</h2>
					<form action="javascript:void(0)">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" style="color: white">Email
									</label> <input type="text" class="form-control" placeholder="email"
										id="username"><label class="control-label"
										style="color: white" id="suggestion">Suggested Username: </label>
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
									</label>  <select id="ddlViewBy">
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
									<button type="submit" class="btn btn-lg btn-outline-warning"
										id="addUserAdmin">Add</button>
								</div>
							</div>
						</div>
					</form>

					<input type="hidden" id="userRole">
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

<script >
$('#addAdmin').click(function() {

	$('#titleUserform').html('Add New Admin User');
	$('#userRole').val("1");
	$('#frmUser').show();
	$('#user').hide();
	$('#suggestion').hide();
	
	
});

$(document).ready(function() {

	$('#user').show();
	$('#frmUser').hide();
	getUserList();	
});

function getUserList() {
	$.ajax({
				url : "/getUserList",
				type : "GET",
				dataType : "json",
				success : function(response) {
					console.log(response.data);
					var tabledata = "";
					$('#tblUser tbody').html("");
					for (var i = 0; i < response.data.length; i++) {
						tabledata += '<tr style="color:white"><td id='+response.data[i].id+'>'
								+ response.data[i].id + '</td>';
						tabledata += '<td >'
								+ response.data[i].username + '</td>';
						tabledata += '<td >' + response.data[i].gender
								+ '</td>';
						tabledata += '<td >'
								+ response.data[i].dateofbirth
								+ '</td>';
						tabledata += '<td >' + response.data[i].phone
								+ '</td>';
						tabledata += '<td >'
								+ response.data[i].streetaddress
								+ '</td>';
						tabledata += '<td >' + response.data[i].city
								+ '</td>';
						tabledata += '<td >'
								+ response.data[i].province + '</td>';
						tabledata += '<td >'
								+ response.data[i].postalcode + '</td>';
					if(response.data[i].role == 0)
						{
						tabledata += '<td ><button id="btnUserDelete_'
							+ response.data[i].id
							+ '" class="btn btn-md btn-danger" onclick="deleteUser('
							+ response.data[i].id
							+ ')">Delete</button></td></tr>';
						}
					else
						{
						tabledata += '<td ><button id="btnUserDelete_'
						+ response.data[i].id
						+ '" class="btn btn-md btn-danger" disabled onclick="deleteUser('
						+ response.data[i].id
						+ ')">Delete</button></td></tr>';
						}
						
					}
					$('#tblUser tbody').append(tabledata);
				},
				error : function(response, status, xhr) {
					console.log(status);
				}
			});
}

function deleteUser(id) {
	if(confirm("Are you sure you want to delete this user?"))
	{
		$.ajax({
			url : "/deleteUserbyId",
			type : "POST",
			data : {
				userId : id
			},
			dataType : "json",
			success : function(response) {
				console.log(response.data);
				if(response.Message != "User not deleted")
				{
					$.notify({
						// options
						message : 'User Deleted Successfully'
					}, {
						// settings
						placement : {
							from : "top",
							align : "center"
						},
						type : 'success'
					});
					getUserList();	
				}
				else
					{
					$.notify({
						// options
						message : 'User Not Deleted'
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
				console.log(status);
			}
		});
	}
}

$('#addUserAdmin').click(
		function() {
			var pass = $('#password').val();
			var conPass = $('#confirmpassword').val();
			var n = pass.localeCompare(conPass);

			if ($('#username').val() == ''
					|| $('#password').val() == ''
					|| $('#confirmpassword').val() == ''
					|| $('#dateofbirth').val() == ''
					|| $('#name').val() == ''
					|| $('#emailid').val() == ''
					|| $('#phonenumber').val() == ''
					|| $('#streetaddress').val() == ''
					|| $('#city').val() == ''
					|| $('#province').val() == ''
					|| $('#postalcode').val() == '') {
				alert('Input can not be left blank');
			} else if (n != 0) {
				alert('Confirm password should match');
			} else {
				userRegistration();
			}
		});

function userRegistration() {

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
		"role" : $('#userRole').val()
	};

	var jsonString = JSON.stringify(obj);

	$.ajax({
		url : "/addUser",
		type : "POST",
		data : jsonString,
		contentType : "application/json",
		dataType : "json",
		success : function(response) {
			if(response.status == 200)
				{
				console.log(response.data);
				$.notify({
					// options
					message : 'Admin Added Successfully'
				}, {
					// settings
					placement : {
						from : "top",
						align : "center"
					},
					type : 'success'
				});
				window.location.href="/adminUserMgmt"
				}
			else if(response.status == 202)
				{
				$.notify({
					// options
					message : 'Data Already Exist'
				}, {
					// settings
					placement : {
						from : "top",
						align : "center"
					},
					type : 'danger'
				});
				$("#suggestion").show();
				$("#suggestion").empty();
				$("#suggestion").append(response.suggestion);
				$("#error").empty();
				$("#error").append(response.Error);
				}
			else {
				$("#error").empty();
				$("#error").append(response.Error);
				console.log(response);
			}
		},
		error : function(response, status, xhr) {
			console.log(response);
		}
	});
}

</script>
</html>