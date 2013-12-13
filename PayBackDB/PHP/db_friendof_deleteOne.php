<?php

//this is the script to add user to mySQL db
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
        //userID
        //userPW
		//amount
		//description
		//transdate
		//lenderID AND/OR lenderEmail
		//borrowerID AND/OR borrowerEmail
	//action
		//makes an input query to mySQL database with given data
	//output
		//json object including:
		    // 'result' (1 for success, -int for error)
		    // 'message' (string)
		    // other fields when applicable
//template code by Hohyun Jeon @10/27/2013
//updated code for TransactionCreation by Ryan Murphy 11/16/13
//updated code for live use by Chase Mamatey 11/18/13

// array for JSON response
$response = array();

// include db config class
require_once dirname(__FILE__) . '/db_config.php';

$ID_options = array("options" => array("min_range"=>00000000, "max_range"=>99999999));
$userID = filter_input(INPUT_POST, 'userID',FILTER_VALIDATE_INT, $ID_options);
$userEmail = $_POST['userEmail'];
$password = $_POST['password'];
$friendID = filter_input(INPUT_POST, 'friendID',FILTER_VALIDATE_INT, $ID_options);
$friendEmail = $_POST['friendEmail'];

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
    $FriendID = mysqli_real_escape_string($link, $friendID);
    $FriendEmail = mysqli_real_escape_string($link, $friendEmail);

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
                    $response["result"] = -3;
                    $response["message"] = "Login query success, but Password does not match";
                }
            }
            else if (mysqli_num_rows($loginPass) > 1) {
                $response["result"] = -4;
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
                $response["result"] = -5;
                $response["message"] = "Failed: User (ID: ".$_POST['userID'].", Email: $userEmail) matched no Account";
            }
        }
        else {
            $response["result"] = -2;
            $response["message"] = "Query for auth failed: user does not exist";
        }
    }
    // userID and password should now be validated, otherwise $userID is false


    // validate friendID
    if ($friendID != false) {
        $IDquery = mysqli_query($link, "SELECT `AccountID` FROM Account WHERE `AccountID` = '$friendID';");
        if (mysqli_fetch_object($IDquery)->AccountID != $friendID) {
            $friendID = false;
        }
    }
    // if invalid friendID, get friendID from friendEmail
    if ($friendID == false) {
        $IDquery = mysqli_query($link, "SELECT `AccountID`,`Email` FROM Account WHERE `Email` = '$friendEmail';");
        if (mysqli_num_rows($IDquery) == 1) {
            $friendID = mysqli_fetch_object($IDquery)->AccountID;
        }
        else if (mysqli_num_rows($IDquery) > 1) {
            $response["result"] = -6;
            $response["message"] = "Failed: New Friend Email '$friendEmail' matched >1 Account";
            $response["friendIDmatches"] = array();
            while ($row = mysqli_fetch_array($IDquery)) {
                // temp array
                $account = array();
                $account["FriendID"] = $row["AccountID"];
                $account["FriendEmail"] = $row["Email"];
                // push match into response array
                array_push($response["friendIDmatches"], $account);
            }
        }
        else {
            $response["result"] = -7;
            $response["message"] = "Failed: New Friend (ID: ".$_POST['friendID'].", Email: $friendEmail) matched no Account";
        }
    }

    //query to add transaction
    if (($response["result"] == null) && ($userID != false)&& ($friendID != false)) {
        $friend = mysqli_query($link, "SELECT Account.AccountID, Account.Email, Account.Fname, Account.Lname FROM Account WHERE Account.AccountID = ANY(SELECT Friend_Of.FriendID FROM Friend_Of WHERE Friend_Of.UserID = '$userID' AND Friend_Of.FriendID = '$friendID');");
        if (mysqli_num_rows($friend) == 1) {
            $row = mysqli_fetch_array($friend);
            // temp array
            $account = array();
            $account["AccountID"] = $row["AccountID"];
            $account["Email"] = $row["Email"];
            $account["Fname"] = $row["Fname"];
            $account["Lname"] = $row["Lname"];
            
            $friend = mysqli_query($link, "DELETE FROM Friend_Of WHERE Friend_Of.UserID = '$userID' AND Friend_Of.FriendID = '$friendID';");
            if ($friend) {
                $response["result"] = 1;
                $response["message"] = "Successfully deleted friend (ID: $friendID)";
            }
            else {
                $response["result"] = -10;
                $response["message"] = "Failed to delete friend (ID: $friendID)";
            }
            
            // push match into response array
            $response["friendOfMatches"] = array();
            array_push($response["friendOfMatches"], $account);
        }
        else if (mysqli_num_rows($friend) > 1) {
            $response["result"] = -9;
            $response["message"] = "Failed: friend (ID: $friendID) matched >1 Contact";
            $response["friendIDmatches"] = array();
            while ($row = mysqli_fetch_array($friend)) {
                // temp array
                $account = array();
                $account["UserID"] = $row["UserID"];
                $account["FriendID"] = $row["FriendID"];
                // push match into response array
                array_push($response["friendIDmatches"], $account);
            }
        }
        else {
            $response["result"] = -8;
            $response["message"] = "Search query failed; friend (ID: $friendID) not found in Contacts";
        }
    }
}
else {
    $response["result"] = -11;
    $response["message"] = "Password cannot be blank";
}

// close mysqli connection
$link->close();

if ($response["result"] == 1) echo $response["result"];
else echo json_encode($response);

?>
