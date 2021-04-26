<%@ page import="Domain.User" %><%--
  Created by IntelliJ IDEA.
  User: Armin
  Date: 07.05.2020
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>YEY</title>
    <style>
        body{
            background-image: url("wp.jpg");
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
        img{
            height: 300px;
        }
        table {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        tr:nth-child(even){background-color: #f2f2f2;}

        tr:hover {background-color: #ddd;}

        th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: blueviolet;
            color: white;
        }
        button {
            background-color: #FFD5FA;
            border: #FF42E8;
            border-style: groove;
            padding: 15px 32px;
            text-align: center;
            margin: 4px 2px;
            font-size: 13px; font-weight: bold;font-family: Arial, Helvetica, sans-serif;
        }
        input{
            font-size: 13px; font-weight: bold;font-family: Arial, Helvetica, sans-serif;
            background-color: #FFD5FA;
            border: #FF42E8;
            border-style: groove;
            padding: 10px 20px;
            text-align: center;
            margin: 4px 2px;
        }


        h3,p{
            font-size: 17px; font-weight: bold;font-family: Arial, Helvetica, sans-serif;
        }
    </style>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
                $("#tablediv").hide();
                $("#showFilter").hide();
                $("#filter-result").hide();


                $("#showTable").click(function (event) {
                    $.get('PopulateTable',function (responseJson) {
                        if(responseJson!=null){
                            $("#usersTable").find("tr:gt(0)").remove();
                            var table1=$("#usersTable");
                            $.each(responseJson,function (key,value) {
                                var rowNew=$("<tr><td></td><td></td><td></td><td></td><td></td>");
                                rowNew.children().eq(0).text(value['userID']);
                                rowNew.children().eq(1).text(value['name']);
                                rowNew.children().eq(2).text(value['email']);
                                rowNew.children().eq(3).text(value['age']);
                                rowNew.children().eq(4).text(value['homeTown']);
                                $("<td>"+value['picture']+"</td></tr>").appendTo(rowNew);
                                rowNew.appendTo(table1);
                            });
                        }
                    });
                    $("#tablediv").show();
                });

                $("#update-button").click(function (event) {
                    $.get('UpdateProfile',{name:$("#update-name").val(),
                                            email:$("#update-email").val(),
                                            picture:$("#update-picture").val(),
                                            age:$("#update-age").val(),
                                            homeTown:$("#update-homeTown").val(),
                                            password:$("#update-password").val()},function (response) {$("#update-result").html(response);});
                });

                $("#filterInput").keyup(function (event) {
                    $.get('FilterTable',{input:$("#filterInput").val()},function (responseJson) {
                        if(responseJson!=null){
                            $("#filter-result").hide();
                            $("#filterTable").find("tr:gt(0)").remove();
                            var table2=$("#filterTable");
                            $.each(responseJson,function (key,value) {
                                var rowNew=$("<tr><td></td><td></td><td></td><td></td><td></td>");
                                rowNew.children().eq(0).text(value['userID']);
                                rowNew.children().eq(1).text(value['name']);
                                rowNew.children().eq(2).text(value['email']);
                                rowNew.children().eq(3).text(value['age']);
                                rowNew.children().eq(4).text(value['homeTown']);
                                $("<td>"+value['picture']+"</td></tr>").appendTo(rowNew);
                                rowNew.appendTo(table2);
                            });
                            $("#showFilter").show();
                        }
                        else{
                            $("#showFilter").hide();
                            $("#filter-result").show();
                        }
                    });
                })
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
         out.println("Welcome "+user.getName()+"!");
     }
%>
</h3>
<br><br><br><br><br><br>
<h3>These are all the users:</h3>
<input type="button" value="Show Users" id="showTable"/>
<div id="tablediv">
    <table cellspacing="0" id="usersTable">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Age</th>
            <th scope="col">HomeTown</th>
            <th scope="col">Picture</th>
        </tr>
    </table>
</div>
<br><br><br><br><br><br>
<br><br><br><br><br><br>










<p><b>Start typing a filter for the IDs/names/emails/ages/towns in the field below:</b></p>
<form>
    <p>Filter:</p> <input id="filterInput" type="text">
</form>
<h3>These are the results so far:</h3>
<div id="showFilter">
    <table cellspacing="0" id="filterTable">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Picture</th>
            <th scope="col">Age</th>
            <th scope="col">HomeTown</th>
        </tr>
    </table>
</div>
<div id="filter-result"><p>No such rows!</p></div>
<br><br><br><br><br><br>







<br><br><br><br><br><br>
<h3>Would you like to change your profile?</h3>
<div id="changeProfileDiv">
    <table>
        <tr><td>Enter name:</td><td><input type="text" id="update-name"></td></tr>
        <tr><td>Enter email: </td><td><input type="text" id="update-email"></td></tr>
        <tr><td>Enter picture:  </td><td><input type="text" id="update-picture"></td></tr>
        <tr><td>Enter age:  </td><td><input type="text" id="update-age"></td></tr>
        <tr><td>Enter hometown:  </td><td><input type="text" id="update-homeTown"></td></tr>
        <tr><td>Enter password:  </td><td><input type="text" id="update-password"></td></tr>
        <tr><td><button type="button" id="update-button">Update information</button></td><td></td></tr>
    </table>
</div>

<div id="update-result"><p></p></div>
<br><br><br><br><br><br>






<br><br><br><br><br><br>
<form action="LogoutController" method="get">
    <input type="submit" value="Logout">
</form>





</body>
</html>
