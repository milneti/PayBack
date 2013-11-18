<?php

/*
* Following code will list all the accounts
*/

// array for JSON response
$response = array();

// include db config class
require_once dirname(__FILE__) . '/db_config.php';
// connect to db
$link = mysqli_connect(DB_SERVER, DB_READ, DB_READ_PASS, DB_DATABASE) or die("Failed to connect to MySQL: " . mysqli_error($link));

// get all accounts from Account table
$result = mysqli_query($link, "SELECT * FROM Friend_Of;") or die(mysqli_error($link));

$link->close();

// check for empty result
if (mysqli_num_rows($result) > 0) {
    // looping through all results
    $response["rows"] = mysqli_num_rows($result);
    $response["contacts"] = array();

    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $tuple = array();
        $tuple["UserID"] = $row["UserID"];
        $tuple["FriendID"] = $row["FriendID"];

        // push single account into final response array
        array_push($response["contacts"], $tuple);
    }
    // success
    $response["result"] = 1;

    // echoing JSON response
    echo json_encode($response);
}
else {
    // no accounts found
    $response["result"] = 0;
    $response["message"] = "No contacts found";

    // echo no users JSON
    echo json_encode($response);
}
?>
