<?php
//this is the script to login to our system
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
		//email
		//password
	//action
		//makes an select query to mySQL database with given data
	//output
		//message on login.

//we would want this script to run whenever they login to the system as user
//by Hohyun Jeon @10/27/2013

// include db config class
require_once dirname(__FILE__) . '/db_config.php';

//Connect to DB
$link = mysqli_connect(DB_SERVER, DB_READ, DB_READ_PASS, DB_DATABASE);
if (mysqli_connect_errno($link)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

//input
$email = $_POST['email'];
$password = $_POST['password'];
$email = mysqli_real_escape_string($mysqli, $email);
$password = mysqli_real_escape_string($mysqli, $password);

//query to lookup password account
if($loginPass = mysqli_query($mysqli, "SELECT `password` FROM `Account` WHERE `email` = '$email';")){
	if(mysqli_fetch_object($loginPass)->password == $password){
		//insert code to do things after auth
		echo "Login and query success";
	}
	else
		{echo "Query success, but Password does not match";}
}
else
	{echo "Query failed";}
?>
