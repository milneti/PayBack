<?php

/*
* Following code will list all transactions
*/

// array for JSON response
$response = array();

// include db config class
require_once dirname(__FILE__) . '/db_config.php';
// connect to db
$link = mysqli_connect(DB_SERVER, DB_READ, DB_READ_PASS, DB_DATABASE) or die("Failed to connect to MySQL: " . mysqli_error($link));

// get all transactions from Transaction table
$result = mysqli_query($link, "SELECT * FROM Transaction;") or die(mysqli_error($link));

$link->close();

// check for empty result
if (mysqli_num_rows($result) > 0) {
    // success
    $response["result"] = 1;

    // looping through all results
    $response["quantity"] = mysqli_num_rows($result);
    $response["transactions"] = array();
    
    while ($row = mysqli_fetch_array($result)) {
        // temp transaction array
        $trans = array();
        $trans["TransactionID"] = $row["TransID"];
        $trans["LenderID"] = $row["LenderID"];
        $trans["BorrowerID"] = $row["BorrowerID"];
        $trans["Amount"] = $row["Amount"];
        $trans["Description"] = $row["Description"];
        $trans["TransDate"] = $row["TransDate"];

        // push single product into final response array
        array_push($response["transactions"], $trans);
    }

    // echoing JSON response
    echo json_encode($response);
}
else {
    // no accounts found
    $response["result"] = -1;
    $response["message"] = "No transactions found";

    // echo no users JSON
    echo json_encode($response);
}
?>
