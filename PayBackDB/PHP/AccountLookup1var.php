//this is the script to lookup an account to mySQL db
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
		//attribute
		//value
	//action
		//makes a select query to mySQL database with given data
	//output
		//JSON object on success, false on fail.

//by Hohyun Jeon @10/27/2013
<?php
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
$attribute = mysql_real_escape_string($attribute);
$value = mysql_real_escape_string($value);

//query to add account
// = mysqli_query($mysqli, "INSERT INTO 'Account' ('email', 'fname', 'lname', 'password') VALUES("+$email+", "+$fname+", "+$lname+", "+$password+");");
//check that attribute is one of the following: email, fname, lname

//sql query
SELECT 'email', 'fname', 'lname'

//output
echo $insertuser;

?>
