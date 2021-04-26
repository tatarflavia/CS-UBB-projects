

<?php

$urlParams = explode('/', $_SERVER['REQUEST_URI']);
$functionName = $urlParams[3];
$functionName = explode('?', $functionName);
$functionName = $functionName[0];
$a=array();
$functionName();

function start(){
        $username= htmlspecialchars($_GET['q']);
        $con = mysqli_connect('localhost','root','','exam');
        if (!$con) {
            die('Could not connect: ' . mysqli_error($con));
        }
        $sql="SELECT * FROM orders WHERE user='$username'";
        $result = mysqli_query($con,$sql);
        if($result){
            if(mysqli_num_rows($result)==0){
                        echo "Error: no such user in the database!";
            }
            else{

            session_start();
            $_SESSION['username'] = $username;
            global $a;
            empty($a);
            echo "Thank you, ".$username."!";
            }
        }
        else{
            echo "Error: no such user in the database!";
        }

        mysqli_close($con);
}


function show($con,$sql){



    $result = mysqli_query($con,$sql);

    echo "<table>
    <tr>
    <th>name</th>
    <th>description</th>
    </tr>";

    if(!(is_bool($result))){
        while($row = mysqli_fetch_array($result)) {
            echo "<tr>";


                echo "<td>" . $row['name'] . "</td>";
                echo "<td>" . $row['description'] . "</td>";

                echo "</tr>";


        }
        echo "</table>";
    }


}



function showAll(){

    session_start();
    if (!isset($_SESSION['username'])) {
    	echo "back to homePage";
    	exit();
    } else {
    	$con = mysqli_connect('localhost','root','','exam');
        if (!$con) {
            die('Could not connect: ' . mysqli_error($con));
        }

        mysqli_select_db($con,"exam");

        $id=$_SESSION['id'];
        $sql = "SELECT * FROM assets where userid='$id'";
        show($con,$sql);
        mysqli_close($con);
    }


}








function addYour(){

    session_start();
    if (!isset($_SESSION['username'])) {
        echo "back to homePage";
        exit();
    } else {
        $name=htmlspecialchars($_GET['n']);
        $description=htmlspecialchars($_GET['d']);



        $con = mysqli_connect('localhost','root','','exam');
        if (!$con) {
            die('Could not connect: ' . mysqli_error($con));
        }



        $sql = "INSERT INTO products(name, description) VALUES('$name','$description');";

        if (mysqli_query($con, $sql)) {
            echo "Product was added!";
        } else {
            echo "Error adding product: " . mysqli_error($con);
        }
        mysqli_close($con);
    }
}


function buyYour(){

    session_start();
    if (!isset($_SESSION['username'])) {
        echo "back to homePage";
        exit();
    } else {
        $name=htmlspecialchars($_GET['n']);
        $quantity=htmlspecialchars($_GET['q']);

        $con = mysqli_connect('localhost','root','','exam');
                if (!$con) {
                    die('Could not connect: ' . mysqli_error($con));
                }
        $result = mysqli_query($con,"SELECT id FROM products WHERE name = '$name'") or die (mysql_error());
        $row = mysqli_fetch_assoc($result);
        $id = $row['id'];


        global $a;
        array_push($a,$id);
        echo "Added to basket:";
    }
}






function showFilter(){

    $q=htmlspecialchars($_GET['q']);

    $con = mysqli_connect('localhost','root','','exam');
    if (!$con) {
        die('Could not connect: ' . mysqli_error($con));
    }

    mysqli_select_db($con,"exam");
    $sql="SELECT * FROM products WHERE name LIKE '$q%'";
    $result = mysqli_query($con,$sql);

    if(mysqli_affected_rows($con)==0){
        echo "No returned rows!<br>";
    }
    else{
        show($con,$sql);
    }

    mysqli_close($con);
}






function logOut(){
    session_start();
	if(isset($_SESSION['username']))
	    unset($_SESSION['username']);
	session_destroy();
	echo "back to homePage";
    exit();
}

?>
