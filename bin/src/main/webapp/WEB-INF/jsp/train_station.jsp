<!DOCTYPE html>
<html lang="en">
<head>
    <title>Train Station</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/core/jquery.min.js"></script>
    <script src="/js/core/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/css/select2.min.css" rel="stylesheet" />

</head>
<style>
</style>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid">

    <div class="row" style="margin-top: 2%">
        <div class="col-6" >
            <div class="jumbotron"
                 style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
                <h2 style="color: white; text-align: center; margin-bottom: 5%">Assign Stations</h2>
                <form action="javascript:void(0)">
                    <div class="row" >
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label" style="color: white">Trains
                                    : </label> <select class="form-control" id="trains"></select>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label" style="color: white">Stations
                                    : </label> <select class="form-control" id="stations" multiple></select>
                            </div>
                        </div>
                        <div class="col-md-6" id="trainStationDiv" style="display: none;">
                            <div class="form-group">
                                <label class="control-label stationName" id="stationName" style="color: white">Station Name
                                    : </label> <input type="text" class="form-control" id="stationTime" placeholder="arrival time"/>
                            </div>
                        </div>
                        <div class="col-md-12" id="arrivalTimeDiv">

                        </div>


                        <div class="col-md-12" style="text-align: center; margin-top: 5%">
                            <div class="form-group">
                                <button class="btn btn-lg btn-outline-warning"
                                        onclick="updateStations()">Add/Update Stations</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/plugins/bootstrap-notify.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/js/select2.min.js"></script>
<script src="/js/loadingoverlay.js"></script>
<script>

    var assignedStation = [];
    var assignedStationTime = [];
    $(document).ready(function(){
        $('.active').removeClass('active');
        $('#trainStation').parent().addClass("active");
        getTrainList();
        $('#trains').select2();
        $('#stations').select2();
    });

    function getTrainList(){
        $
            .ajax({
                url : "/getTrainList",
                type : "GET",
                dataType : "json",
                aync:false,
                success : function(response) {
                    $('#trains').html("");
                    var options = "";
                    for (var i = 0; i < response.data.length; i++) {
                        options += '<option value='+response.data[i].id+'>'
                            + response.data[i].trainName;
                        options += '</option>';
                    }
                    $('#trains').append(options);
                    getStations();
                },
                error : function(response, status, xhr) {
                    console.log(status);
                }
            });
    }

    function getStations(){
        $
            .ajax({
                url : "/getStationList",
                type : "GET",
                dataType : "json",
                aync : false,
                success : function(response) {
                    $('#stations').html("");
                    var options = "";
                    for (var i = 0; i < response.data.length; i++) {

                            options += '<option value='+response.data[i].id+' >'
                                + response.data[i].stationName;
                            options += '</option>';
                        }
                    $('#stations').append(options);
                    getTrainStation();

                },
                error : function(response, status, xhr) {
                    console.log(status);
                }
            });
    }

    function getTrainStation() {
        $
            .ajax({
                url : "/getTrainStationByTrainId?trainId="+$('#trains').val(),
                type : "GET",
                dataType : "json",
                aync : false,
                success : function(response) {
                    assignedStation = [];
                    assignedStationTime = [];
                    for (var i = 0; i < response.data.length; i++) {
                        assignedStation.push(response.data[i].stationId.id);
                        assignedStationTime.push(response.data[i].time);
                        var stationId = response.data[i].id;
                        $("#trainStationDiv").clone().appendTo("#arrivalTimeDiv").attr("id", i).css("display", "block").find("#stationName").html(response.data[i].stationId.stationName + " : ");
                        $('#'+i).find("#stationTime").attr("id","stationTime"+stationId);
                        $('#stationTime'+stationId).val( assignedStationTime[i])
                    }
                    console.log(assignedStation);
                    console.log();
                    $('#stations').val(assignedStation).trigger('change');
                },
                error : function(response, status, xhr) {
                    console.log(status);
                }
            });
    }

    $('#trains').change(function(){
        getStations();
    });

    $('#stations').change(function () {
        $( "#arrivalTimeDiv" ).children().remove();
        var selectedStations = $('#stations').select2('data');
        for(var i = 0 ; i < selectedStations.length ; i++) {

           var check  =  $.inArray( parseInt(selectedStations[i].id) , assignedStation );
           console.log(check);
           if(check >= 0){
               var stationId = selectedStations[i].id;
               $("#trainStationDiv").clone().appendTo("#arrivalTimeDiv").attr("id", i).css("display", "block").find("#stationName").html(selectedStations[i].text + " : ");
               $('#'+i).find("#stationTime").attr("id","stationTime"+stationId);
               $('#stationTime'+stationId).val(assignedStationTime[check]);
           }else{
               var stationId = selectedStations[i].id;
               $("#trainStationDiv").clone().appendTo("#arrivalTimeDiv").attr("id", i).css("display", "block").find("#stationName").html(selectedStations[i].text + " : ");
               $('#'+i).find("#stationTime").attr("id","stationTime"+stationId);
           }

        }

    });


    function updateStations() {
        var trainId = $('#trains').val();
        var station = $('#stations').select2('data');
        var data = '[';
        for(var i = 0 ; i < station.length ; i ++){
            data += '{';
            data += '"trainId":'+trainId;
            data += ',"time":"'+$('#stationTime'+station[i].id).val();
            data += '","stationId":{"id":'+station[i].id+'}';
            data += "},"
        }
        data = data.slice(0,-1);
        data += ']';
        $.LoadingOverlay("show");
        $
            .ajax({
                url : "/saveTrainStationMapping",
                type : "POST",
                data : data,
                dataType : "json",
                contentType : "application/json",
                aync : false,
                success : function(response) {
                    $.LoadingOverlay("hide");
                   if(response.status == 200){
                       $.notify({
                           message : "Station for Train " + $('#trains :selected').html() + " has updated"
                       }, {
                           // settings
                           placement : {
                               from : "top",
                               align : "center"
                           },
                           allow_dismiss: true,
                           type : 'success'
                       });
                       getTrainList();
                   }else if(response.status == 1001){
                       $.notify({
                           message : "Stations cannot contain same arrival time."
                       }, {
                           // settings
                           placement : {
                               from : "top",
                               align : "center"
                           },
                           allow_dismiss: true,
                           type : 'danger'
                       });
                   }else if(response.status == 2001){
                       $.notify({
                           message : "Please enter valid Time"
                       }, {
                           // settings
                           placement : {
                               from : "top",
                               align : "center"
                           },
                           allow_dismiss: true,
                           type : 'danger'
                       });
                   }
                },
                error : function(response, status, xhr) {
                    $.LoadingOverlay("hide");
                    console.log(status);
                }
            });
    }
</script>