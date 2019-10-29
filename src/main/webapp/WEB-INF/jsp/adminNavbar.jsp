<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/adminPage.css">
<script src="/js/core/jquery.min.js"></script>
<script src="/js/core/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#" onclick="openNav()">E-RAIL</a>
		</nav>

		<div id="mySidenav" class="sidenav">
			<div class="clearfix navbar navbar-expand-lg navbar-dark">
				<a class=" navbar-brand float-left" href="#" onclick="closeNav()">E-RAIL</a>
				<a href="javascript:void(0)" class="closebtn float-right"
					onclick="closeNav()">&times;</a>
			</div>
			<div>
			<img src="/image/profile.png" />
			</div>
			<ul class="ml-auto">
		
			<li class=""><a class="txtSideNav" id="Home" href="/">Home</a></li>
				<li class=""><a class="txtSideNav" id="pnlTrain" href="/adminTrainMgmt">Train</a></li>
				<li class="nav-item"><a class="" id="pnlStation" href="/adminStationMgmt">Station</a></li>
				<li class=""><a class="" id="pnlUser" href="/adminUserMgmt">User</a></li>
				<li class=""><a class="" id="pnlReport" href="/report">Reports</a></li>
			</ul>
		</div>
		
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="/js/plugins/bootstrap-notify.js"></script>
<script>

function openNav() {
	document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
	document.getElementById("mySidenav").style.width = "0";
}

</script>

