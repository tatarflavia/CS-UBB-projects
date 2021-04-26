<%@ page import="Domain.User" %><%--
  Created by IntelliJ IDEA.
  User: Armin
  Date: 22.06.2020
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Yey</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#tablediv").hide();
            $("#showFilter").hide();
            $("#filter-result").hide();


            $("#showTable").click(function (event) {
                $.get('PopulateTable',function (responseJson) {
                    if(responseJson!=null){
                        $("#assetsTable").find("tr:gt(0)").remove();
                        var table1=$("#assetsTable");
                        $.each(responseJson,function (key,value) {
                            var rowNew=$("<tr>");
                            if(value['value']>10){
                                $("<td style='background-color: red'>"+value['name']+"</td>").appendTo(rowNew);
                                $("<td style='background-color: red'>"+value['description']+"</td>").appendTo(rowNew);
                                $("<td style='background-color: red'>"+value['value']+"</td></tr>").appendTo(rowNew);
                            }
                            else{
                                rowNew=$("<tr><td></td><td></td><td></td></tr>");
                                rowNew.children().eq(0).text(value['name']);
                                rowNew.children().eq(1).text(value['description']);
                                rowNew.children().eq(2).text(value['value']);
                            }
                            rowNew.appendTo(table1);
                        });
                    }
                });
                $("#tablediv").show();
            });

            $("#addAsset-button").click(function (event) {
                $.get('AddAsset',{name:$("#addAsset-name").val(),
                    description:$("#addAsset-description").val(),
                    value:$("#addAsset-value").val(),
                    },function (response) {$("#addAsset-result").html(response);});
            });

            $("#update-button").click(function (event) {
                $.get('Update',{id:$("#update-id").val(),
                    name:$("#update-name").val(),
                    description:$("#update-description").val(),
                    value:$("#update-value").val(),
                    },function (response) {$("#update-result").html(response);});
            });

            $("#filterInput").keyup(function (event) {
                $.get('FilterTable',{input:$("#filterInput").val()},function (responseJson) {
                    if(responseJson!=null){
                        $("#filter-result").hide();
                        $("#filterTable").find("tr:gt(0)").remove();
                        var table2=$("#filterTable");
                        $.each(responseJson,function (key,value) {
                            var rowNew=$("<tr><td></td><td></td><td></td><td></td><td></td>");
                            rowNew.children().eq(0).text(value['userId']);
                            rowNew.children().eq(1).text(value['name']);
                            rowNew.children().eq(2).text(value['description']);
                            rowNew.children().eq(3).text(value['value']);
                            rowNew.appendTo(table2);
                        });
                        $("#showFilter").show();
                    }
                    else{
                        $("#showFilter").hide();
                        $("#filter-result").show();
                    }
                });
            });


        });
    </script>
</head>
<body>
<h3>
    <%
        User user = (User) session.getAttribute("user");
        if (user==null) {
            return;
        }
        else{
            out.println("Welcome "+user.getUsername()+"!");
        }
    %>
</h3>
<br><br><br><br><br><br>
<h3>These are all your assets:</h3>
<input type="button" value="Show Assets" id="showTable"/>
<div id="tablediv">
    <table cellspacing="0" id="assetsTable">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Value</th>
        </tr>
    </table>
</div>
<br><br><br><br><br><br>

<br><br><br><br><br><br>
<h3>Would you like to add another asset?</h3>
<div id="addAssetDiv">
    <table>
        <tr><td>Enter name:</td><td><input type="text" id="addAsset-name"></td></tr>
        <tr><td>Enter description: </td><td><input type="text" id="addAsset-description"></td></tr>
        <tr><td>Enter value:  </td><td><input type="text" id="addAsset-value"></td></tr>
        <tr><td><button type="button" id="addAsset-button">Add</button></td><td></td></tr>
    </table>
</div>

<div id="addAsset-result"><p></p></div>
<br><br><br><br><br><br>




<br><br><br><br><br><br>
<h3>Would you like to change an asset?</h3>
<div id="updateDiv">
    <table>
        <tr><td>Enter id:</td><td><input type="text" id="update-id"></td></tr>
        <tr><td>Enter name:</td><td><input type="text" id="update-name"></td></tr>
        <tr><td>Enter description: </td><td><input type="text" id="update-description"></td></tr>
        <tr><td>Enter value:  </td><td><input type="text" id="update-value"></td></tr>
        <tr><td><button type="button" id="update-button">Update information</button></td><td></td></tr>
    </table>
</div>

<div id="update-result"><p></p></div>
<br><br><br><br><br><br>


<br><br><br><br><br><br>
<p><b>Start typing a filter for the asset description in the field below:</b></p>
<form>
    <p>Filter:</p> <input id="filterInput" type="text">
</form>
<h3>These are the results so far:</h3>
<div id="showFilter">
    <table cellspacing="0" id="filterTable">
        <tr>
            <th scope="col">userID</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Value</th>
        </tr>
    </table>
</div>
<div id="filter-result"><p>No such rows!</p></div>
<br><br><br><br><br><br>



<br><br><br><br><br><br>
<form action="LogoutController" method="get">
    <input type="submit" value="Logout">
</form>
</body>
</html>
