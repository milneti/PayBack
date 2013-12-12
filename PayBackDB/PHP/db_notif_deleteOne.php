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
$noteID = filter_input(INPUT_POST, 'noteID', FILTER_SANITIZE_NUMBER_FLOAT);
$noteID = substr(str_pad(str_replace($replace, '', $noteID), 16, '0', STR_PAD_LEFT), -16, 16);

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
    $noteID = mysqli_real_escape_string($link, $noteID);

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

    // query to delete a notification with receiveID == userID && noteID
    if (($userID != false)) {
        if ($noteID > 0) {
            $notif = mysqli_query($link, "SELECT ReceiveID FROM Notification WHERE NoteID = '$noteID';");
            if (mysqli_fetch_object($notif)->ReceiveID == $userID) {
                $delete = mysqli_query($link, "DELETE FROM `Notification` WHERE (`ReceiveID` = '$userID') AND (`NoteID` = '$noteID');");
                if ($delete) {
                    $response["result"] = 1;
                    $response["message"] = "Notification '$noteID' for user '$userID' deleted successfully";
                }
                else {
                    $response["result"] = -9;
                    $response["message"] = "Notification deletion query failed for notification '$noteID' and user '$userID'";
                }
            }
            else {
                $response["result"] = -8;
                $response["message"] = "Notification '$noteID' does not exist or does not belong to user '$userID'";
            }
        }
        else {
            $response["result"] = -7;
            $response["message"] = "Notification ID cannot be blank";
        }
    }
}
else {
    $response["result"] = -6;
    $response["message"] = "Password cannot be blank";
}

// close mysqli connection
$link->close();

echo json_encode($response);

?>
