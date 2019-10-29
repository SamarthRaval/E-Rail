<!DOCTYPE html>
<html lang="en">
<head>
    <title>Homepage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/core/jquery.min.js"></script>
    <script src="/js/core/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<style>
</style>
<body>



<jsp:include page="navbar.jsp"/>
<div class="container-fluid">
    <div class="row" style="margin-top: 5%">
        <div class="col-md-12" style="text-align: center;font-style: italic;font-size: 32px;text-transform: uppercase;">
            Welcome <%= session.getAttribute("name") %> !
        </div>

    </div>
    <div class="row" style="margin: 0% 25%">
        <div class="col-12" style="margin-top: 5%;text-align: center">
            <div class="jumbotron"
                 style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
                <h2 style="color: white; text-align: center; margin-bottom: 5%">Find
                    Trains</h2>
                <form action="javascript:void(0)">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label" style="color: white">Source
                                    : </label> <select class="form-control" id="sourceStation"></select>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label" style="color: white">Destination
                                    : </label> <select class="form-control" id="destinationStation"></select>
                            </div>
                        </div>
                        <div class="col-md-12" style="text-align: center; margin-top: 2.5%">
                            <div class="form-group">
                                <button class="btn btn-md btn-warning"
                                        onclick="getTrains()">Find Trains
                                </button>
                            </div>
                        </div>
                        <hr>

                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label" style="color: white">Find By Train Number
                                    : </label> <input type="number" class="form-control" id="trainNumber"/>
                            </div>
                        </div>
                        <div class="col-md-12" style="text-align: center; margin-top: 2.5%">
                            <div class="form-group">
                                <button class="btn btn-md btn-primary"
                                        onclick="getTrainByNumber()">Find By Number
                                </button>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="modal" id="trainDetails" tabindex="-1" role="dialog">
        <div class="modal-dialog  modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Train Details</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table class="table table-dark table-striped" id="trainDetailsTable" >
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Station Name</th>
                            <th scope="col">Station Number</th>
                            <th scope="col">Arrival Time</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/plugins/bootstrap-notify.js"></script>
<Script>
    $(document).ready(function () {
        $('.active').removeClass('active');
        $("#home").parent().addClass("active");
        getStationList();
    });

    function getStationList() {
        $
            .ajax({
                url: "/getStationList",
                type: "GET",
                dataType: "json",
                success: function (response) {
                    console.log(response.data);
                    $('#sourceStation').html("");
                    $('#destinationStation').html("");
                    var options = "";
                    for (var i = 0; i < response.data.length; i++) {
                        options += '<option value=' + response.data[i].id + '>'
                            + response.data[i].stationName;
                        options += '</option>';
                    }
                    $('#sourceStation').append(options);
                    $('#destinationStation').append(options);
                },
                error: function (response, status, xhr) {
                    console.log(status);
                }
            });
    }

    function getTrains() {
        var sourceStation = $('#sourceStation').val();
        var destinationStation = $('#destinationStation').val();
        if (sourceStation == destinationStation) {
            $.notify({
                message: 'Source and Destination Station cannot be same'
            }, {
                // settings
                placement: {
                    from: "top",
                    align: "center"
                },
                allow_dismiss: true,
                type: 'danger'
            });
        } else {
            window.location.href = "/trains?source=" + sourceStation + "&destination=" + destinationStation;
        }
    }


    function getTrainByNumber(){
        $
            .ajax({
                url: "/findTrainByTrainNumber?trainNumber="+$('#trainNumber').val(),
                type: "GET",
                dataType: "json",
                async: false,
                success: function (response) {
                    if(response.status == 200 ) {

                        $.ajax({
                            url: "/getTrainStationByTrainId?trainId=" +response.data.id,
                            type: "GET",
                            dataType: "json",
                            async : false,
                            success: function (response) {
                                if(response.status == 200){
                                    $('#trainDetailsTable tbody').html("");
                                    var tbody = "";
                                    for (var i = 0; i < response.data.length; i++) {
                                        tbody += '<tr>';
                                        tbody += '<td>' + i
                                            + '</td>';
                                        tbody += '<td>' + response.data[i].stationId.stationName
                                            + '</td>';
                                        tbody += '<td>'
                                            + response.data[i].stationId.stationNumber
                                            + '</td>';
                                        tbody += '<td>'
                                            + response.data[i].time
                                            + '</td>';
                                        tbody += '</tr>';
                                    }
                                    $('#trainDetailsTable tbody').append(tbody);
                                    $('#trainDetails').modal('show');
                                }else{
                                    $.notify({
                                        message: 'Trains not found'
                                    }, {
                                        // settings
                                        placement: {
                                            from: "top",
                                            align: "center"
                                        },
                                        allow_dismiss: true,
                                        type: 'danger'
                                    });
                                }

                            },
                            error: function (response, status, xhr) {
                                console.log(status);
                            }
                        });
                    }else{
                        $.notify({
                            message: 'Train not found'
                        }, {
                            // settings
                            placement: {
                                from: "top",
                                align: "center"
                            },
                            allow_dismiss: true,
                            type: 'danger'
                        });
                    }
                },
                error: function (response, status, xhr) {
                    console.log(status);
                }
            });
    }
</Script>
</body>
</html>
