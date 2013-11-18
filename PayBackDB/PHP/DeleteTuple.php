//this is the script to delete any tuple in DB given proper rights to mySQL db
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
		//user $email
		//$password
		//$attribute
		//$value
	//action
		//makes a deletes a tuple in mySQL database with given data
		//$attribute decides the function taken. Currently there are three functions:
			//trans:		Deletes tuples that match LenderID and user $email where Borrower = $value
			//note:			Deletes tuples that match ReceiveID and user $email where Send == $value
			//account:		Deletes tuple for account. Account tuple will be gone permanently after action.
										//if Email and $email match, account will be deleted.
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
		//check that attribute is one of the following: date, email
		if($attribute == "trans"){
			//if($value1=="BorrowerID"||$value1==""){
				if($lookup = mysqli_query("DELETE FROM Transaction WHERE TransID = (SELECT TransID FROM (SELECT AccountID AS LenderID FROM Account WHERE Email ="+$email+" UNION SELECT * FROM Transaction) WHERE BorrowerID =(SELECT AccountID FROM Account WHERE AccountID= "+$value+");"){
				$data = mysqli_fetch_all($lookup);
				echo json_encode($data);
				//echo $lookup;
			//	}
			//}else{
			//	echo json_encode("Inputs data but format invalid. Must be YYYY-MM-DD");
			//}
		}else if($attribute == "note"){
			//if($value1 == "NoteDate"||$value1 == "SendID"){
				if($lookup = mysqli_query("DELETE FROM Notification WHERE NoteID = (SELECT NoteID FROM (SELECT AccountID AS ReceiveID FROM Account WHERE Email = "+$email+" UNION SELECT * FROM Notification) WHERE SendID=(SELECT AccountID FROM Account WHERE AccountID= "+$value+");"){
				$data = mysqli_fetch_all($lookup);
				echo json_encode($data);
			}	//echo $lookup;
			//}
			
		}else if($attribute == "account"){
			if($lookup = mysqli_query("DELETE FROM Account WHERE `Email`=`"+$email+"` LIMIT 1;"){
			$data = mysqli_fetch_all($lookup);
			echo json_encode($data);
				//echo $lookup;
			}
		}else
			echo "Query failed due to attribute name =" + $attribute;
		
			//echo "Login and query success";
		
			
	}
	else
		{echo json_encode("Query sucess, but Password does not match");}
}
else
	{echo json_encode("Query for auth failed: user does not exist");}


?>
