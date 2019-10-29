
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <h2>Booking History</h2>
    </div>
    <div class="container-fluid">
        <div class="row">
        </div>
    </div>

    <div class="container-fluid table-responsive" style="text-align: center">
        <table class="table table-dark table-striped" id="bookingTable" style="display: none;">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Boarding Date</th>
                <th scope="col">Booking Number</th>
                <th scope="col">Class</th>
                <th scope="col">Train Name</th>
                <th scope="col">Arrival Time</th>
                <th scope="col">Source Station</th>
                <th scope="col">Destination Station</th>

            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <div class="modal" tabindex="-1" role="dialog" id="confirmModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Cancel Booking</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure, you want to cancel booking?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick=" cancelBooking()" id="confirm">Confirm</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <div id="bookingListMessage">

        </div>
        <div class="col-4" style="text-align: left">
            <button class="btn btn-lg btn-info" id="bookBtn" style="display: none;" >Book Now</button>
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
    var selectedBookingId;
    $(document).ready(
        function() {
            $('.active').removeClass('active');
            $("#booking").parent().addClass("active");
            getBookingDetails();

        });

    function getBookingDetails () {
        $
            .ajax({
                url : "/getBookingByUserId",
                type : "GET",
                dataType : "json",
                success : function(response) {
                    if (response.data.length !== 0) {
                        $('#bookingTable').show();
                        $('#bookBtn').hide();
                        $('#bookingTable tbody').html("");
                        var tbody = "";
                        for (var i = 0; i < response.data.length; i++) {
                            console.log(response.data[i].status)
                            if (response.data[i].status != "Cancelled"){
                            tbody += '<tr>';
                            tbody += '<td>' + response.data[i].id
                                + '</td>';

                            tbody += '<td>' + new Date(response.data[i].bookingDate).toLocaleDateString()
                                + '</td>';
                            tbody += '<td>'
                                + response.data[i].bookingNumber
                                + '</td>';
                            tbody += '<td>'
                                + response.data[i].classType
                                + '</td>';
                            tbody += '<td>'
                                + response.data[i].trainName
                                + '</td>';

                            tbody += '<td>'
                                + response.data[i].arrivalTime
                                + '</td>';
                            tbody += '<td>'
                                + response.data[i].sourceStationName
                                + '</td>';
                            tbody += '<td>'
                                + response.data[i].destinationStationName
                                + '</td>';
                            tbody += '<td><button class="btnBooking btn btn-danger" onclick=" confirm(this)" >Cancel Booking</button></td></tr>';

                        }
                        }
                        $('#bookingTable tbody').append(tbody);
                    }else{
                        $('#bookingTable').hide();
                        $('#bookingListMessage').html(" No booking available");
                        $('#bookBtn').show();
                    }
                },
                error : function(response, status, xhr) {
                    console.log(status);
                }
            });

    }

    function confirm(row){
        $('#confirmModal').modal('show');
        selectedBookingId = $(row).closest('tr').find('td')[0].innerHTML;
    }


    function cancelBooking(){
        $.LoadingOverlay("show");
        $
            .ajax({
                url : "/deleteBooking?bookingId="+selectedBookingId,
                type : "GET",
                dataType : "json",
                success : function(response) {
                    $.LoadingOverlay("hide");
                    if(response.data == true){
                        $('#confirmModal').modal('hide');
                        getBookingDetails ();
                    }
                },
                error : function(response, status, xhr) {
                    $.LoadingOverlay("hide");
                    console.log(status);
                }
            });
    }

    $("#bookBtn").click(function () {
       window.location.href = "/trains?source=1&destination=2"
    })
</script>

