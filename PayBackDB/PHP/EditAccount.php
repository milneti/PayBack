//this is the script to delete any tuple in DB given proper rights to mySQL db
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
		//user $email
		//$password
		//$attribute
		//$value
	//action
		//updates table for user $email with change to $attribute with $value
		
	//output
		//JSON object on success, error message in JSON object on fail.

//by Hohyun Jeon @11/17/2013
//Ready to add

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
$email = $_POST['email'];
$password = $_POST['password'];
$email = mysql_real_escape_string($email);
$password = mysql_real_escape_string($password);

//auth
if($loginPass = mysqli_query("SELECT `password` FROM `Account` WHERE `email` = \""+$email+"\";")){
	if(mysqli_fetch_object($loginPass) == $password){
		//insert code to do things after auth

		if($lookup = mysqli_query("UPDATE Account SET "+$attribute+"="+$value+" WHERE Email="+$email+";"){
		$data = mysqli_fetch_all($lookup);
		echo json_encode($data);
		
			//echo "Login and query success";
		
			
	}
	else
		{echo json_encode("Query sucess, but Password does not match");}
}
else
	{echo json_encode("Query for auth failed: user does not exist");}


?>
