<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76"
          href="../assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="../assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Dashboard</title>
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
    <div class="container-fluid">
    <div class="row" style="margin-top: 5%; text-align: center">
        <div class="card col-md-3" style="width: 18rem; margin-left: 8%;padding: 0%">
            <div style="background: #000000ba;padding: 5%;">
            <i class="fa fa-subway" style="font-size: 220px;color:white" aria-hidden="true"></i>
            </div>
            <div class="card-body">
                <h3 class="card-title">Total Bookings</h3>
                <p class="card-text" style="font-size: 28px;" id="totalBooking">0</p>
            </div>
        </div>
        <div class="card  col-md-3" style="width: 18rem; margin-left: 4%;padding: 0%">
            <div style="background: #000000ba;padding: 5%;">
            <i class="fa fa-train" style="font-size: 220px;color:white" aria-hidden="true"></i>
            </div>
            <div class="card-body">
                <h3 class="card-title">Total Bookings Cancelled</h3>
                <p class="card-text" style="font-size: 28px;" id="cancelCount">0</p>
            </div>
        </div>
        <div class="card  col-md-3" style="width: 18rem; margin-left: 4%;padding: 0%">
            <div style="background: #000000ba;padding: 5%;">
                <i class="fa fa-user" style="font-size: 220px;color:white" aria-hidden="true"></i>
            </div>
            <div class="card-body">
                <h3 class="card-title">Users Registered</h3>
                <p class="card-text" style="font-size: 28px;" id="registeredUser">0</p>

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
    $(document).ready(function(){
        getCancellationCount();
        getBookingCount();
        getRegisteredUserCount();
    });

    function getCancellationCount() {
        $
            .ajax({
                url : "/getTotalCancellation",
                type : "GET",
                dataType : "json",
                aync : false,
                success : function(response) {
                    console.log(response.count);
                    $('#cancelCount').html(response.count)
                },
                error : function(response, status, xhr) {
                    console.log(status);
                }
            });
    }

    function getBookingCount() {
        $
            .ajax({
                url : "/getTotalBooking",
                type : "GET",
                dataType : "json",
                aync : false,
                success : function(response) {
                    $('#totalBooking').html(response.count)
                },
                error : function(response, status, xhr) {
                    console.log(status);
                }
            });
    }

    function getRegisteredUserCount() {
        $
            .ajax({
                url : "/getTotalNonAdminUser",
                type : "GET",
                dataType : "json",
                aync : false,
                success : function(response) {
                    $('#registeredUser').html(response.count)
                },
                error : function(response, status, xhr) {
                    console.log(status);
                }
            });
    }
</script>
