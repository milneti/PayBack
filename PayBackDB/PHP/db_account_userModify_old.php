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

// array for JSON response
$response = array();

// include db config class
require_once dirname(__FILE__) . '/db_config.php';

$ID_options = array("options" => array("min_range"=>00000000, "max_range"=>99999999));
$userID = filter_input(INPUT_POST, 'userID',FILTER_VALIDATE_INT, $ID_options);
$userEmail = $_POST['userEmail'];
$password = $_POST['password'];
$attribute = $_POST['attribute'];
$value = $_POST['value'];

// connect to db
$link = mysqli_connect(DB_SERVER, DB_WRITE, DB_WRITE_PASS, DB_DATABASE);
if (mysqli_connect_errno($link)) {
    $response["result"] = -1;
    $response["message"] = "Failed to connect to MySQL: " . mysqli_connect_error();
}
else if (strlen($password) > 0) {
    // escape inputs
    $userID = mysqli_real_escape_string($link, $userID);
    $userEmail = mysqli_real_escape_string($link, $userEmail);
    $password = mysqli_real_escape_string($link, $password);
    $attribute = mysqli_real_escape_string($link, $attribute);
    $value = mysqli_real_escape_string($link, $value);

    // authorize
    $loginPass = mysqli_query($link, "SELECT `password` FROM `Account` WHERE `accountID` = '$userID';");
    // validate userID 
    if (!$loginPass || (mysqli_fetch_object($loginPass)->password != $password)) {
        // userID not found, or $password and password from database do not match; userID invalid
        $userID = false;
        if ($loginPass = mysqli_query($link, "SELECT `AccountID`,`Password` FROM `Account` WHERE `email` = '$userEmail';")) {
            // validate userEmail and get userID from userEmail
            if (mysqli_num_rows($loginPass) == 1) {
                $row = mysqli_fetch_array($loginPass);
                if ($row["Password"] == $password) {
                    // User email and password authenticated, userID set
                    $userID = $row["AccountID"];
                }
                else {
                    $response["result"] = -4;
                    $response["message"] = "Login query success, but Password does not match";
                }
            }
            else if (mysqli_num_rows($loginPass) > 1) {
                $response["result"] = -5;
                $response["message"] = "Failed: User Email '$userEmail' matched >1 Account";
                $response["userIDmatches"] = array();
                while ($row = mysqli_fetch_array($loginPass)) {
                    // temp array
                    $account = array();
                    $account["UserID"] = $row["AccountID"];
                    // push match into response array
                    array_push($response["UserIDmatches"], $account);
                }
            }
            else {
                $response["result"] = -6;
                $response["message"] = "Failed: User (ID: ".$_POST['userID'].", Email: $userEmail) matched no Account";
            }
        }
        else {
            $response["result"] = -3;
            $response["message"] = "Query for auth failed: user does not exist";
        }
    }
    // userID and password should now be validated, otherwise $userID is false

    if ($userID != false) {
	    //check that attribute is one of the following: email, password, fname, lname
	    if ($attribute == "email"){
		    if(strlen($value) > 0) {
		        $lookup = mysqli_query($link, "UPDATE `Account` SET `Email` = '$value' WHERE `AccountID` = '$userID';");
		        if ($lookup) {
		            $response["result"] = 1;
                    $response["message"] = "$attribute updated to '$value'";
		        }
		        else {
                    $response["result"] = -9;
                    $response["message"] = "Query to update $attribute to '$value' failed";
		        }
		    }
		    else {
                $response["result"] = -8;
                $response["message"] = "$attribute cannot be blank";
		    }
	    }
	    else if($attribute == "password"){
		    if(strlen($value) > 0) {
		        $lookup = mysqli_query($link, "UPDATE `Account` SET `Password` = '$value' WHERE `AccountID` = '$userID';");
		        if ($lookup) {
		            $response["result"] = 1;
                    $response["message"] = "$attribute updated to '$value'";
		        }
		        else {
                    $response["result"] = -9;
                    $response["message"] = "Query to update $attribute to '$value' failed";
		        }
		    }
		    else {
                $response["result"] = -8;
                $response["message"] = "$attribute cannot be blank";
		    }
	    }
	    else if($attribute == "fname"){
		    if(strlen($value) > 0) {
		        $lookup = mysqli_query($link, "UPDATE `Account` SET `Fname` = '$value' WHERE `AccountID` = '$userID';");
		        if ($lookup) {
		            $response["result"] = 1;
                    $response["message"] = "$attribute updated to '$value'";
		        }
		        else {
                    $response["result"] = -9;
                    $response["message"] = "Query to update $attribute to '$value' failed";
		        }
		    }
		    else {
                $response["result"] = -8;
                $response["message"] = "$attribute cannot be blank";
		    }
	    }
	    else if($attribute == "lname"){
		    if(strlen($value) > 0) {
		        $lookup = mysqli_query($link, "UPDATE `Account` SET `Lname` = '$value' WHERE `AccountID` = '$userID';");
		        if ($lookup) {
		            $response["result"] = 1;
                    $response["message"] = "$attribute updated to '$value'";
		        }
		        else {
                    $response["result"] = -9;
                    $response["message"] = "Query to update $attribute to '$value' failed";
		        }
		    }
		    else {
                $response["result"] = -8;
                $response["message"] = "$attribute cannot be blank";
		    }
	    }
	    else {
            $response["result"] = -7;
            $response["message"] = "Query failed due to attribute name =" + $attribute;
	    }
    }
}
else {
    $response["result"] = -2;
    $response["message"] = "Password cannot be blank";
}

// close mysqli connection
$link->close();

echo json_encode($response);

?>
