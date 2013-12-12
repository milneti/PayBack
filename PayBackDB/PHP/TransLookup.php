//this is the script to lookup a transaction to mySQL db
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
			//TransDate:	query by date where $value is constraint date
			//LenderID:		query by LenderID where $value is the constraint lender's email
			//BorrowerID:	query by BorrowerID where $value is the constraint borrower's email.
	//output
		//JSON object on success, error message in JSON object on fail.

//Original template by Hohyun Jeon @10/27/2013
//Modified by Ryan Murphy @12/11/2013
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
		//check that attribute is one of the following: date, email
		if($attribute == "TransDate"){
			if(preg_match('/\d{4}-\d{2}-\d{2}/',$value)){
				//Date needs to be in YYYY-MM-DD format
				if($lookup = mysqli_query("SELECT `Email`, `Fname`, `Lname`, `Amount`, `Description`, `TransDate` FROM (Select AccountID AS BorrowerID, Email, Fname, Lname FROM `Account` WHERE `Email`=`"+$email+"` UNION SELECT * FROM Transaction) WHERE `TransDate`=`"+$value+"`);"){
				$data = mysqli_fetch_all($lookup);
				echo json_encode($data);
				//echo $lookup;
				}
			}else{
				echo json_encode("Inputs data but format invalid. Must be YYYY-MM-DD");
			}
		}else if($attribute == "LenderID"){
			if($lookup = mysqli_query("SELECT `Email`, `Fname`, `Lname`, `Amount`, `Description`, `TransDate` FROM (Select AccountID AS LenderID, Email, Fname, Lname FROM `Account` WHERE `Email`=`"+$email+"` UNION SELECT * FROM Transaction) WHERE `LenderID`=(SELECT AccountID FROM Account WHERE AccountID= "+$value+");"){
			$data = mysqli_fetch_all($lookup);
			echo json_encode($data);
				//echo $lookup;
			}
			
		}else if($attribute == "BorrowerID"){
			if($lookup = mysqli_query("SELECT `Email`, `Fname`, `Lname`, `Amount`, `Description`, `TransDate` FROM (Select AccountID AS BorrowerID, Email, Fname, Lname FROM `Account` WHERE `Email`=`"+$email+"` UNION SELECT * FROM Transaction) WHERE `BorrowerID`=(SELECT AccountID FROM Account WHERE AccountID= "+$value+");"){
			$data = mysqli_fetch_all($lookup);
			echo json_encode($data);
				//echo $lookup;
			}
		}else
			echo "Query failed due to attribute name =" + $attribute;
	
	}
	else
		{echo json_encode("Query success, but Password does not match");}
}
else
	{echo json_encode("Query for auth failed: user does not exist");}


?>
