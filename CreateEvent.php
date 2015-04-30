<?php
    $con=mysqli_connect("localhost","my_user","my_password","my_db");
    
    $title = $_POST["time"];
    $age = $_POST["age"];
    $date = $_POST["date"];
    $time = $_POST["time"];
	$extentOfReach =$_POST["extentOfReach"];
	$description =$_POST["description"];
	$category =$_POST["category"];
	
    
    $statement = mysqli_prepare($con, "INSERT INTO User (title, age, date, time, extentOfReach, description,Category) VALUES (?, ?, ?, ?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssssss", $title, $age, $date, $time , $extentOfReach , $description , $category);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_close($statement);
    
    mysqli_close($con);
?>
