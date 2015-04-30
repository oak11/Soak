<?php
    $con=mysqli_connect("localhost","my_user","my_password","my_db");
      
     $title = $_POST["time"];
    $age = $_POST["age"];
    $date = $_POST["date"];
    $time = $_POST["time"];
	$extentOfReach =$_POST["extentOfReach"];
	$description =$_POST["description"];
	$category =$_POST["category"];
	
    
    $statement = mysqli_prepare($con, "SELECT * FROM User WHERE category = ? AND extentOfReach = ?");
    mysqli_stmt_bind_param($statement, "ss", $category, $extentOfReach);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $title, $age, $date, $time , $extentOfReach , $description , $category);
    
    $event = array();
    
    while(mysqli_stmt_fetch($statement)){
        $user["title"] = $title;
        $user["date"] = $date;
        $user["time"] = $time;
        $user["description"] = $description;
    }
    
    echo json_encode($event);
    mysqli_close($con);
?>
