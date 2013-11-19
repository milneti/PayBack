<?php
//this is the script to lookup an notification to mySQL db
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
		//user $email
		//$password
		//$attribute
		//$value
	//action
		//makes a select query to mySQL database with given data
		//$attribute decides the function taken. Currently there are three functions:
			//date:			query by date where $value is constraint date
			//emailsent:	query by email where $value is the constraint receive email
			//emailin:		query by email where $value is the constraint send email.
	//output
		//JSON object on success, error message in JSON object on fail.

//by Hohyun Jeon @10/27/2013
//Updated by Hohyun Jeon @11/17/2013
//Ready to add
//updated and taken live by Chase Mamatey 11/18/2013

// include db config class
require_once dirname(__FILE__) . '/db_config.php';

//Connect to DB
$link = mysqli_connect(DB_SERVER, DB_READ, DB_READ_PASS, DB_DATABASE);
if (mysqli_connect_errno($link)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

//input
$attribute = $_POST['attribute'];
$value = $_POST['value'];
$attribute = mysqli_real_escape_string($link, $attribute);
$value = mysqli_real_escape_string($link, $value);
$email = $_POST['email'];
$password = $_POST['password'];
$email = mysqli_real_escape_string($link, $email);
$password = mysqli_real_escape_string($link, $password);

//auth
if($loginPass = mysqli_query($link, "SELECT `password` FROM `Account` WHERE `email` = '$email';")){
	if(mysqli_fetch_object($loginPass)->password == $password){
		//insert code to do things after auth
		//check that attribute is one of the following: date, emailsent, emailin
		if($attribute == "date"){
			if(preg_match('/\d{4}-\d{2}-\d{2}/',$value)){
				//Date needs to be in YYYY-MM-DD format
				if($lookup = mysqli_query($link, "SELECT `Email`, `Fname`, `Lname`, `SendInfo`, `NoteDate`  FROM (SELECT AccountID AS SendID, Email, Fname, Lname  FROM `Account` WHERE `Email`='$email' UNION SELECT * FROM Notification) WHERE `NoteDate`='$value');")){
				    $data = mysqli_fetch_all($lookup);
				    echo json_encode($data);
				    //echo $lookup;
				}
			}
			else{
				echo json_encode("Inputs data but format invalid. Must be YYYY-MM-DD");
			}
		}
		else if($attribute == "emailsent"){
			if($lookup = mysqli_query($link, "SELECT `Email`, `Fname`, `Lname`, `SendInfo`, `NoteDate` FROM (Select AccountID AS SendID, Email, Fname, Lname  FROM `Account` WHERE `Email`='$email' UNION SELECT * FROM Notification) WHERE `ReceiveID`=(SELECT AccountID FROM Account WHERE AccountID= '$value');")){
			    $data = mysqli_fetch_all($lookup);
			    echo json_encode($data);
				//echo $lookup;
			}
			
		}
		else if($attribute == "emailin"){
			if($lookup = mysqli_query($link, "SELECT `Email`, `Fname`, `Lname`, `SendInfo`, `NoteDate` FROM (Select AccountID AS ReceiveID, Email, Fname, Lname  FROM `Account` WHERE `Email`=`'$email' UNION SELECT * FROM Notification) WHERE `SendID`=(SELECT AccountID FROM Account WHERE AccountID= '$value');")){
			$data = mysqli_fetch_all($lookup);
			echo json_encode($data);
				//echo $lookup;
			}
		}else
			echo "Query failed due to attribute name =" + $attribute;
	
	}
	else
		{echo json_encode("Login query success, but Password does not match");}
}
else
	{echo json_encode("Query for auth failed: user does not exist");}

$link->close();
?>
