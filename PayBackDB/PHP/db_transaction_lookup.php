<?php

// array for JSON response
$response = array();

// include db config class
require_once dirname(__FILE__) . '/db_config.php';

$replace = array('+', '-');
$userID = filter_input(INPUT_POST, 'userID', FILTER_SANITIZE_NUMBER_FLOAT);
$userID = substr(str_pad(str_replace($replace, '', $userID), 8, '0', STR_PAD_LEFT), -8, 8);
$userEmail = $_POST['userEmail'];
$password = $_POST['password'];
$lookupWith = $_POST['lookupWith'];

// connect to db
$link = mysqli_connect(DB_SERVER, DB_READ, DB_READ_PASS, DB_DATABASE);
if (mysqli_connect_errno($link)) {
    $response["result"] = -1;
    $response["message"] = "Failed to connect to MySQL: " . mysqli_connect_error();
}
else if (strlen($password) > 0) {
    // escape inputs
    $userID = mysqli_real_escape_string($link, $userID);
    $userEmail = mysqli_real_escape_string($link, $userEmail);
    $password = mysqli_real_escape_string($link, $password);
    $lookupWith = mysqli_real_escape_string($link, $lookupWith); 

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
	    //check that lookupWith is one of the following: userAsBorrower, userAsLender
	    if ($lookupWith == "userAsBorrower"){
		    $lookup = mysqli_query($link, "SELECT `TransID`, `LenderID` AS `AccountID`, `Email`, `Fname`, `Lname`, `Amount`, `Description`, `ResolvedFlag`, `TransDate` FROM `Transaction` JOIN `Account` ON `AccountID` = `LenderID` WHERE `BorrowerID` = $userID ORDER BY `TransID`;");
            if ($lookup) {
                $response["result"] = 1;
                $response["message"] = "Transaction lookup successful with User as Borrower.";
                $response["quantity"] = mysqli_num_rows($lookup);
                $response["transactions"] = array();
                while ($row = mysqli_fetch_array($lookup)) {
                    // temp array
                    $transaction = array();
                    $transaction["TransID"] = $row["TransID"];
                    $transaction["AccountID"] = $row["AccountID"];
                    $transaction["Email"] = $row["Email"];
                    $transaction["Fname"] = $row["Fname"];
                    $transaction["Lname"] = $row["Lname"];
                    $transaction["Amount"] = $row["Amount"];
                    $transaction["Description"] = $row["Description"];
                    $transaction["ResolvedFlag"] = $row["ResolvedFlag"];
                    $transaction["TransDate"] = $row["TransDate"];
                    array_push($response["transactions"], $transaction);
                }
            }
            else {
                $response["result"] = -8;
                $response["message"] = "Transaction lookup failed with User as Borrower.";
            }
	    }
	    else if($lookupWith == "userAsLender"){
	        $lookup = mysqli_query($link, "SELECT `TransID`, `BorrowerID` AS `AccountID`, `Email`, `Fname`, `Lname`, `Amount`, `Description`, `ResolvedFlag`, `TransDate` FROM `Transaction` JOIN `Account` ON `AccountID` = `BorrowerID` WHERE `LenderID` = $userID ORDER BY `TransID`;");
            if ($lookup) {
                $response["result"] = 1;
                $response["message"] = "Transaction lookup successful with User as Lender.";
                $response["quantity"] = mysqli_num_rows($lookup);
                $response["transactions"] = array();
                while ($row = mysqli_fetch_array($lookup)) {
                    // temp array
                    $transaction = array();
                    $transaction["TransID"] = $row["TransID"];
                    $transaction["AccountID"] = $row["AccountID"];
                    $transaction["Email"] = $row["Email"];
                    $transaction["Fname"] = $row["Fname"];
                    $transaction["Lname"] = $row["Lname"];
                    $transaction["Amount"] = $row["Amount"];
                    $transaction["Description"] = $row["Description"];
                    $transaction["ResolvedFlag"] = $row["ResolvedFlag"];
                    $transaction["TransDate"] = $row["TransDate"];
                    array_push($response["transactions"], $transaction);
                }
            }
            else {
                $response["result"] = -9;
                $response["message"] = "Transaction lookup failed with User as Lender.";
            }
	    }
	    else {
            $response["result"] = -7;
            $response["message"] = "Query failed due to lookupWith value =" + $lookupWith;
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
