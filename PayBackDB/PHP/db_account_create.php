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
// by Hohyun Jeon @10/27/2013
// modifications by Chase Mamatey @10/27/2013, 11/13/2013

// contact Chase for proper admin login info

// include db config class
require_once dirname(__FILE__) . '/db_config.php';

// connect to db
$link = mysqli_connect(DB_SERVER, DB_WRITE, DB_WRITE_PASS, DB_DATABASE);
if (mysqli_connect_errno($link)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

// input

$fname = $_POST['fname'];
$lname = $_POST['lname'];
$email = $_POST['email'];
$password = $_POST['password'];

$fname = mysqli_real_escape_string($link, $fname);
$lname = mysqli_real_escape_string($link, $lname);
$email = mysqli_real_escape_string($link, $email);
$password = mysqli_real_escape_string($link, $password);

// query to add account

$insertuser = mysqli_query($link, "INSERT INTO `Account` (`email`,`fname`,`lname`,`password`) VALUES ('$email','$fname','$lname','$password');");

// output

echo $insertuser;

$link->close();

?>
