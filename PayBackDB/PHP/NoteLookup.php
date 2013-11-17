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
		if($attribute == "date"){
			if(preg_match('/\d{4}-\d{2}-\d{2}/',$value)){
				//Date needs to be in YYYY-MM-DD format
				if($lookup = mysqli_query("SELECT `Email`, `Fname`, `Lname`, `SendInfo`, `NoteDate`  FROM (Select AccountID AS SendID, Email, Fname, Lname  FROM `Account` WHERE `Email`=`"+$email+"` UNION SELECT * FROM Notification) WHERE `NoteDate`=`"+$value+"`);"){
				$data = mysqli_fetch_all($lookup);
				echo json_encode($data);
				//echo $lookup;
				}
			}else{
				echo json_encode("Inputs data but format invalid. Must be YYYY-MM-DD");
			}
		}else if($attribute == "emailsent"){
			if($lookup = mysqli_query("SELECT `Email`, `Fname`, `Lname`, `SendInfo`, `NoteDate` FROM (Select AccountID AS SendID, Email, Fname, Lname  FROM `Account` WHERE `Email`=`"+$email+"` UNION SELECT * FROM Notification) WHERE `ReceiveID`=(SELECT AccountID FROM Account WHERE AccountID= "+$value+");"){
			$data = mysqli_fetch_all($lookup);
			echo json_encode($data);
				//echo $lookup;
			}
			
		}else if($attribute == "emailin"){
			if($lookup = mysqli_query("SELECT `Email`, `Fname`, `Lname`, `SendInfo`, `NoteDate` FROM (Select AccountID AS ReceiveID, Email, Fname, Lname  FROM `Account` WHERE `Email`=`"+$email+"` UNION SELECT * FROM Notification) WHERE `SendID`=(SELECT AccountID FROM Account WHERE AccountID= "+$value+");"){
			$data = mysqli_fetch_all($lookup);
			echo json_encode($data);
				//echo $lookup;
			}
		}else
			echo "Query failed due to attribute name =" + $attribute;
	
	}
	else
		{echo json_encode("Query sucess, but Password does not match");}
}
else
	{echo json_encode("Query for auth failed: user does not exist");}


?>
