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
<?php
//Connect to DB
//Please contact Chase for proper admin login info
$mysqli = mysqli_connect("localhost", "Admin", "password", "database");
if (mysqli_connect_errno($mysqli)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}


//input
$email = $_POST['email'];
$password = $_POST['password'];
$email = mysql_real_escape_string($email);
$password = mysql_real_escape_string($password);

//query to lookup password account
if($loginPass = mysqli_query("SELECT 'password' FROM 'Account' WHERE 'email' = '"+$email+"';")){
	if(mysqli_fetch_object($loginPass) == $password){
		//insert code to do things after auth
		echo "Login and query sucess";
	}
	else
		{echo "Query sucess, but Password does not match";}
}
else
	{echo "Query failed";}
?>
