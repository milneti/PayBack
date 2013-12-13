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
$friendID = filter_input(INPUT_POST, 'receiveID',FILTER_SANITIZE_NUMBER_FLOAT);
$friendID = substr(str_pad(str_replace($replace, '', $friendID), 8, '0', STR_PAD_LEFT), -8, 8);
$friendEmail = $_POST['receiveEmail'];
$transID = $_POST['transID'];
$sendInfo = $_POST['sendInfo'];

// generate noteDate
$noteDate = new DateTime();
$noteDate = $noteDate->format('Y-m-d H:i:s');

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
    $friendID = mysqli_real_escape_string($link, $friendID);
    $friendEmail = mysqli_real_escape_string($link, $friendEmail);
    $transID = mysqli_real_escape_string($link, $transID);
    $sendInfo = mysqli_real_escape_string($link, $sendInfo);

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

    // validate friendID ('receiveID')
    if ($friendID != false) {
        $IDquery = mysqli_query($link, "SELECT `AccountID` FROM Account WHERE `AccountID` = '$friendID';");
        if (mysqli_fetch_object($IDquery)->AccountID != $friendID) {
            $friendID = false;
        }
    }
    // if invalid friendID, get friendID ('receiveID') from friendEmail ('receiveEmail')
    if ($friendID == false) {
        $IDquery = mysqli_query($link, "SELECT `AccountID`,`Email` FROM Account WHERE `Email` = '$friendEmail';");
        if (mysqli_num_rows($IDquery) == 1) {
            $friendID = mysqli_fetch_object($IDquery)->AccountID;
        }
        else if (mysqli_num_rows($IDquery) > 1) {
            $response["result"] = -6;
            $response["message"] = "Failed: New Friend Email '$friendEmail' matched >1 Account";
            $response["receiveIDmatches"] = array();
            while ($row = mysqli_fetch_array($IDquery)) {
                // temp array
                $account = array();
                $account["ReceiveID"] = $row["AccountID"];
                $account["ReceiveEmail"] = $row["Email"];
                // push match into response array
                array_push($response["receiveIDmatches"], $account);
            }
        }
        else {
            $response["result"] = -7;
            $response["message"] = "Failed: Receiver (ID: ".$_POST['receiveID'].", Email: $friendEmail) matched no Account";
        }
    }
    // friendID ('receiveID') should now be validated, otherwise $friendID is false
    
    //query to add notification
    if (($userID != false) && ($friendID != false)) {
        if (strlen($sendInfo) > 0) {
            if ($transID > 0) {
                $notify = mysqli_query($link, "INSERT INTO `Notification` (`SendID`,`ReceiveID`,`TransID`,`SendInfo`,`NoteDate`) VALUES ('$userID','$friendID','$transID','$sendInfo','$noteDate');");

                if ($notify) {
                    $response["result"] = 1;
                    $response["message"] = "Notification added successfully";
                }
                else {
                    $response["result"] = -10;
                    $response["message"] = "Add Notification(with TransID) query failed";
                }
            }
            else {
                $notify = mysqli_query($link, "INSERT INTO `Notification` (`SendID`,`ReceiveID`,`SendInfo`,`NoteDate`) VALUES ('$userID','$friendID','$sendInfo','$noteDate');");

                if ($notify) {
                    $response["result"] = 1;
                    $response["message"] = "Notification added successfully";
                }
                else {
                    $response["result"] = -11;
                    $response["message"] = "Add Notification(without TransID) query failed";
                }
            }
        }
        else {
            $response["result"] = -9;
            $response["message"] = "Notification text cannot be blank";
}
    }
}
else {
    $response["result"] = -8;
    $response["message"] = "Password cannot be blank";
}

// close mysqli connection
$link->close();

echo json_encode($response);

?>
