<?php

/*
* Following code will list all notifications
*/

// array for JSON response
$response = array();

// include db config class
require_once dirname(__FILE__) . '/db_config.php';
// connect to db
$link = mysqli_connect(DB_SERVER, DB_READ, DB_READ_PASS, DB_DATABASE) or die("Failed to connect to MySQL: " . mysqli_error($link));

// get all transactions from Transaction table
$result = mysqli_query($link, "SELECT * FROM Notification;") or die(mysqli_error($link));

$link->close();

// check for empty result
if (mysqli_num_rows($result) > 0) {
    // success
    $response["result"] = 1;

    // looping through all results
    $response["quantity"] = mysqli_num_rows($result);
    $response["notifications"] = array();
    
    while ($row = mysqli_fetch_array($result)) {
        // temp transaction array
        $notif = array();
        $notif["NoteID"] = $row["NoteID"];
        $notif["SendID"] = $row["SendID"];
        $notif["ReceiveID"] = $row["ReceiveID"];
        $notif["TransID"] = $row["TransID"];
        $notif["SendInfo"] = $row["SendInfo"];
        $notif["ReadFlag"] = $row["ReadFlag"];
        $notif["NoteDate"] = $row["NoteDate"];

        // push single product into final response array
        array_push($response["notifications"], $notif);
    }

    // echoing JSON response
    echo json_encode($response);
}
else {
    // no accounts found
    $response["result"] = -1;
    $response["message"] = "No notifications found";

    // echo no users JSON
    echo json_encode($response);
}
?>
