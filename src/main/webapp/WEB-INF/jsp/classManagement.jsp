<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="ISO-8859-1">

    <title>Class Management</title>
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
        <div class="col-md-12"  id="class">
            <div class="jumbotron"
                 style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
                <div class="clearfix">
                    <h2 style="color: white; text-align: center; margin-bottom: 5%"
                        class="float-left">Classes </h2>
                    <button type="button" class="btn btn-success float-right"
                            id="addEditClass">Add Classes</button>
                </div>

                <table id="tblClass" class="table table-striped" style="width: 100%; text-align: center">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Type</th>
                        <th>Fare</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
        <div class="col-6" style="margin-top: 5%;" id="frmClass">
            <div class="jumbotron"
                 style="background: #343a40; padding-bottom: 1%; padding-top: 5%; border-radius: 2%">
                <h2 style="color: white; text-align: center; margin-bottom: 5%"
                    id="titleClassform">Add Class</h2>
                <form action="javascript:void(0)">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label" style="color: white">Class Type : </label> <input type="text" class="form-control"
                                                          id="txtClassType" />
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label" style="color: white"> Fare : </label> <input type="number" class="form-control"
                                                           id="txtClassFare" />
                            </div>
                        </div>
                        <div class="col-md-12" style="text-align: center; margin-top: 5%">
                            <div class="form-group">
                                <button class="btn btn-lg btn-outline-warning"
                                        id="btnAddStation" onclick="addClass()">Add Class</button>
                                <button class="btn btn-lg btn-outline-warning"
                                        id="btnEditClass" onclick="editClass()">Edit
                                    Class</button>
                            </div>
                        </div>
                    </div>
                </form>
                <input type="hidden" id="classId">
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

    $(document).ready(function() {
        $('#station').show();
        getStationList();
        $('#frmClass').hide();
    });

    $('#addEditClass').click(function() {
        $('#titleClassform').html('Add Class');
        $('#frmClass').show();
        $('#station').show();
        $('#btnAddStation').show();
        $('#btnEditClass').hide();
        $('#txtClassFare').val("");
        $('#txtClassType').val("");
    });
    function getStationList() {
        $
            .ajax({
                url : "/getAllClassType",
                type : "GET",
                dataType : "json",
                success : function(response) {
                    console.log(response.data);
                    var tabledata = "";
                    $('#tblClass tbody').html("");
                    for (var i = 0; i < response.data.length; i++) {
                        tabledata += '<tr style="color:white"><td id='+response.data[i].id+'>'
                            + response.data[i].id + '</td>';
                        tabledata += '<td >'
                            + response.data[i].type
                            + '</td>';
                        tabledata += '<td >'
                            + response.data[i].fare
                            + '</td>';
                        tabledata += '<td ><button id="btnStationDelete_'
                            + response.data[i].id
                            + '"class="btn btn-md btn-danger" onclick="deleteClass('
                            + response.data[i].id
                            + ')">Delete</button> <button id="btnStationEdit_'
                            + response.data[i].id
                            + '"class="btn btn-md btn-warning" onclick="getClassDetail('
                            + response.data[i].id
                            + ',\''
                            + response.data[i].type
                            + '\',\''
                            + response.data[i].fare
                            + '\')">Edit</button> </td></tr>';
                    }
                    $('#tblClass tbody').append(tabledata);
                },
                error : function(response, status, xhr) {
                    console.log(status);
                }
            });
    }

    function deleteClass(id) {
        $('#frmClass').hide();
        $.ajax({
            url : "/deleteClassType?id="+id,
            type : "GET",

            dataType : "json",
            success : function(response) {
                console.log(response.data);
                $.notify({
                    // options
                    message : 'Class Deleted Successfully'
                }, {
                    // settings
                    placement : {
                        from : "top",
                        align : "center"
                    },
                    type : 'success'
                });

                getStationList();
            },
            error : function(response, status, xhr) {
                console.log(status);
            }
        });
    }

    function getClassDetail(id, Name, Number) {
        $('#titleClassform').html('Edit Class');
        $('#frmClass').show();
        $('#btnEditClass').show();
        $('#btnAddStation').hide();
        $('#txtClassFare').val(Number);
        $('#txtClassType').val(Name);
        $('#classId').val(id);
    }


    function addClass() {

        if($('#txtClassType').val() != "" && $('#txtClassFare').val() != ""){

            var classDetail = {
                type : $('#txtClassType').val(),
                fare :parseInt( $('#txtClassFare').val())
            };
            $.ajax({
                url : "/saveClassType",
                type : "POST",
                data : JSON.stringify(classDetail),
                dataType : "json",
                contentType : "application/json; charset=utf-8",
                success : function(response) {
                    if(response.status == 200){
                        console.log(response.data);
                        $.notify({
                            // options
                            message : 'Class Added Successfully'
                        }, {
                            // settings
                            placement : {
                                from : "top",
                                align : "center"
                            },
                            type : 'success'
                        });
                        getStationList();
                        $('#frmClass').hide();
                    }else if(response.status == 2001){
                        $.notify({
                            // options
                            message : 'Class already exists'
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
        }else{
            $.notify({
                // options
                message : 'Please enter all the details'
            }, {
                // settings
                placement : {
                    from : "top",
                    align : "center"
                },
                type : 'warning'
            });
        }


    }

    function editClass() {

        if($('#txtClassType').val() != "" && $('#txtClassFare').val() != "") {
            var classId = $('#classId').val();
            var classDetail = {
                id: parseInt(classId),
                fare: parseInt($('#txtClassFare').val()),
                type: $('#txtClassType').val()
            };
            $.ajax({
                url: "/updateClassType",
                type: "POST",
                data: JSON.stringify(classDetail),
                contentType: "application/json; charset=utf-8",
                success: function (response) {
                    if (response.status == 200) {
                        console.log(response.data);
                        $.notify({
                            // options
                            message: 'Class Updated Successfully'
                        }, {
                            // settings
                            placement: {
                                from: "top",
                                align: "center"
                            },
                            type: 'success'
                        });
                        getStationList();
                        $('#frmClass').hide();
                    } else if (response.status == 2001) {
                        $.notify({
                            // options
                            message: 'Class already exists'
                        }, {
                            // settings
                            placement: {
                                from: "top",
                                align: "center"
                            },
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
                // options
                message : 'Please enter all the details'
            }, {
                // settings
                placement : {
                    from : "top",
                    align : "center"
                },
                type : 'warning'
            });
        }
    }
</script>
</html>