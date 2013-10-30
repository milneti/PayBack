<?php

//this is the script to add user to mySQL db
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
		//fname
		//lname
		//email
		//password
	//action
		//makes an input query to mySQL database with given data
	//output
		//true or false on query.
//by Hohyun Jeon @10/27/2013

//Connect to DB
//Please contact Chase for proper admin login info

$mysqli = mysqli_connect("localhost", "Admin", "Password", "PayBackDB");
if (mysqli_connect_errno($mysqli)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

//input

$fname = $_POST['fname'];
$lname = $_POST['lname'];
$email = $_POST['email'];
$password = $_POST['password'];

$fname = mysqli_real_escape_string($mysqli, $fname);
$lname = mysqli_real_escape_string($mysqli, $lname);
$email = mysqli_real_escape_string($mysqli, $email);
$password = mysqli_real_escape_string($mysqli, $password);

//query to add account

$insertuser = mysqli_query($mysqli, "INSERT INTO `Account` (`email`,`fname`,`lname`,`password`) VALUES (\"$email\",\"$fname\",\"$lname\",\"$password\");");

//output

echo $insertuser;

?>
