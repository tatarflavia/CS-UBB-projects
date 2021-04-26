<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LoggingReports-Start Page</title>
    <link rel="stylesheet" type="text/css" href="myStyle.css">
</head>
<body>

<?php

$no_of_records_per_page = 4;
$urlParams = explode('/', $_SERVER['REQUEST_URI']);
$functionName = $urlParams[3];
$functionName = explode('?', $functionName);
$functionName = $functionName[0];









$functionName();

function start(){
        $username= htmlspecialchars($_GET['q']);
        $con = mysqli_connect('localhost','root','','loggingreport');
        if (!$con) {
            die('Could not connect: ' . mysqli_error($con));
        }

        $sql = "UPDATE currentversion SET currentUser='$username' WHERE id=1";

        if (mysqli_query($con, $sql)) {
            echo "Thank you, ".$username."!";
        } else {
            echo "Error:" . mysqli_error($con);
        }

        mysqli_close($con);
}


function show($con,$sql){
    global $no_of_records_per_page;
    $pageno = intval($_GET['pageno']);
    $offset = ($pageno-1) * $no_of_records_per_page;



    $sqlFinal=$sql." LIMIT $offset, $no_of_records_per_page";

    $result = mysqli_query($con,$sqlFinal);

    echo "<table>
    <tr>
    <th>LogID</th>
    <th>Type</th>
    <th>Severity</th>
    <th>LogDate</th>
    <th>UserName</th>
    <th>LogMessage</th>
    </tr>";

    if(!(is_bool($result))){
        while($row = mysqli_fetch_array($result)) {
            echo "<tr>";
            echo "<td>" . $row['LogID'] . "</td>";
            echo "<td>" . $row['Type'] . "</td>";
            echo "<td>" . $row['Severity'] . "</td>";
            echo "<td>" . $row['LogDate'] . "</td>";
            echo "<td>" . $row['UserName'] . "</td>";
            echo "<td>" . $row['LogMessage'] . "</td>";
            echo "</tr>";
        }
        echo "</table>";
    }


}



function showAll(){
    global $offset,$no_of_records_per_page;

    $con = mysqli_connect('localhost','root','','loggingreport');
    if (!$con) {
        die('Could not connect: ' . mysqli_error($con));
    }

    mysqli_select_db($con,"loggingreport");

    $sql = "SELECT * FROM logs";
    show($con,$sql);
    mysqli_close($con);
}



function deleteYour(){
    $q=intval($_GET['q']);

    $con = mysqli_connect('localhost','root','','loggingreport');
    if (!$con) {
        die('Could not connect: ' . mysqli_error($con));
    }

    $sql = "DELETE FROM logs WHERE LogID=$q AND UserName IN (SELECT currentUser from currentversion where id=1)";


    if (mysqli_query($con, $sql)) {
        if(mysqli_affected_rows($con)==0){
            echo "No affected rows!(id not in the list)<br>";
        }

        mysqli_select_db($con,"loggingreport");
        $sql1="SELECT * FROM logs WHERE UserName IN (SELECT currentUser from currentversion where id=1)";
        show($con,$sql1);
    } else {
        echo "Error deleting record: " . mysqli_error($con);
    }
    mysqli_close($con);
}






function addYour(){
    $type=htmlspecialchars($_GET['t']);
    $severity=htmlspecialchars($_GET['s']);
    $date=htmlspecialchars($_GET['d']);
    $message=htmlspecialchars($_GET['m']);

    $con = mysqli_connect('localhost','root','','loggingreport');
    if (!$con) {
        die('Could not connect: ' . mysqli_error($con));
    }

    $sqlForUserName="SELECT currentUser from currentversion where id=1";
    $result=mysqli_query($con,$sqlForUserName);
    $row = mysqli_fetch_array($result);
    $username = $row['currentUser'];

    $sql = "INSERT INTO logs(Type,Severity,LogDate,UserName,LogMessage) VALUES('$type', '$severity','$date','$username','$message');";

    if (mysqli_query($con, $sql)) {

        mysqli_select_db($con,"loggingreport");
        $sql1="SELECT * FROM logs WHERE UserName IN (SELECT currentUser from currentversion where id=1)";
        show($con,$sql1);
    } else {
        echo "Error adding record: " . mysqli_error($con);
    }
    mysqli_close($con);
}






function updateYour(){
    $type=htmlspecialchars($_GET['t']);
    $severity=htmlspecialchars($_GET['s']);
    $date=htmlspecialchars($_GET['d']);
    $message=htmlspecialchars($_GET['m']);
    $id=intval($_GET['i']);

    $con = mysqli_connect('localhost','root','','loggingreport');
    if (!$con) {
        die('Could not connect: ' . mysqli_error($con));
    }

    $result=True;

    if(strcmp($type,"")!=0){
        $sql = "UPDATE logs SET Type='$type' WHERE LogID=$id AND UserName IN (SELECT currentUser from currentversion where id=1)";
        $result=mysqli_query($con, $sql);
    }

    if(strcmp($message,"")!=0){
        $sql = "UPDATE logs SET LogMessage='$message' WHERE LogID=$id AND UserName IN (SELECT currentUser from currentversion where id=1)";
        $result=mysqli_query($con, $sql);
    }

    if(strcmp($date,"")!=0){
        $sql = "UPDATE logs SET LogDate='$date' WHERE LogID=$id AND UserName IN (SELECT currentUser from currentversion where id=1)";
        $result=mysqli_query($con, $sql);
    }

    if(strcmp($severity,"")!=0){
        $sql = "UPDATE logs SET Severity='$severity' WHERE LogID=$id AND UserName IN (SELECT currentUser from currentversion where id=1)";
        $result=mysqli_query($con, $sql);
    }

    if ($result) {

        if(mysqli_affected_rows($con)==0){
            echo "No affected rows!<br>";
        }
        mysqli_select_db($con,"loggingreport");
        $sql1="SELECT * FROM logs WHERE UserName IN (SELECT currentUser from currentversion where id=1)";
        show($con,$sql1);
    } else {
        echo "Error updating record: " . mysqli_error($con);
    }
    mysqli_close($con);
}






function showYour(){
    $con = mysqli_connect('localhost','root','','loggingreport');
    if (!$con) {
        die('Could not connect: ' . mysqli_error($con));
    }

    mysqli_select_db($con,"loggingreport");
    $sql="SELECT * FROM logs WHERE UserName IN (SELECT currentUser from currentversion where id=1)";
    $result = mysqli_query($con,$sql);
    if(mysqli_affected_rows($con)==0){
        echo "No returned rows!<br>";
    }
    else{
        show($con,$sql);
    }
    mysqli_close($con);
}


function showFilter(){

    $q=htmlspecialchars($_GET['q']);

    $con = mysqli_connect('localhost','root','','loggingreport');
    if (!$con) {
        die('Could not connect: ' . mysqli_error($con));
    }

    mysqli_select_db($con,"loggingreport");
    $sql="SELECT * FROM Logs WHERE Type LIKE '$q%' OR Severity LIKE '$q%'";
    $result = mysqli_query($con,$sql);

    if(mysqli_affected_rows($con)==0){
        echo "No returned rows!<br>";
    }
    else{
        show($con,$sql);
    }

    mysqli_close($con);
}



?>
</body>
</html>