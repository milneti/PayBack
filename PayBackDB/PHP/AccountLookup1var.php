<?php
//this is the script to lookup an account to mySQL db
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
		//email
		//password
		//attribute
		//value
	//action
		//makes a select query to mySQL database with given data
	//output
		//JSON object on success, false on fail.

//by Hohyun Jeon @10/27/2013

//DO NOT ADD THIS TO THE SERVER!!!!!!!!! THIS IS A WORK IN PROGRESS AND IS MISSING AUTH!!!!!
//ADDING WOULD CREATE A VERY BIG SECURITY HOLE!!!!!!!


//Connect to DB
//Please contact Chase for proper user login info
$mysqli = mysqli_connect("localhost", "Admin", "password", "database");
if (mysqli_connect_errno($mysqli)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
//basic query list would go here
$sacct = mysqli_query($mysqli, "SELECT * as _msg FROM ACCOUNT ");

//input
$attribute = $_POST['attribute'];
$value = $_POST['value'];
$attribute = mysqli_real_escape_string($mysqli, $attribute);
$value = mysqli_real_escape_string($mysqli, $value);
$email = $_POST['email'];
$password = $_POST['password'];
$email = mysqli_real_escape_string($mysqli, $email);
$password = mysqli_real_escape_string($mysqli, $password);

//auth
if($queryResult = mysqli_query($mysqli, "SELECT `password` FROM `Account` WHERE `email` = '$email';")){
	if(mysqli_fetch_object($queryResult)->password == $password){
		//insert code to do things after auth
		//check that attribute is one of the following: email, fname, lname
		if($attribute=="email" || $attribute == "fname" || $attribute == "lname"){
			//sql query
			if($lookup = mysqli_query($mysqli, "SELECT `email`, `fname`, `lname` FROM `Account` WHERE `$attribute` LIKE '%$value%';")){
				$data = mysqli_fetch_all($lookup);
				echo json_encode($data);
				//echo $lookup;
			}
			//echo "Login and query success";
		}
		else
			{echo "Query failed due to attribute name = $attribute";}
	}
	else
		{echo "Query success, but Password does not match";}
}
else
	{echo "Query for auth failed: user does not exist";}

?>
