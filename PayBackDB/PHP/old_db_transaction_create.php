<?php

//this is the script to add user to mySQL db
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
		//amount
		//description
		//transdate
		//lenderID AND/OR lenderEmail
		//borrowerID AND/OR borrowerEmail
	//action
		//makes an input query to mySQL database with given data
	//output
		//json object including 'result' (0 or 1), 'message' (string), and other fields
//template code by Hohyun Jeon @10/27/2013
//updated code for TransactionCreation by Ryan Murphy 11/16/13
//updated code for live use by Chase Mamatey 11/18/13

// array for JSON response
$response = array();

// include db config class
require_once dirname(__FILE__) . '/db_config.php';

if (isset($_POST['amount']) && (isset($_POST['lenderID']) || isset($_POST['lenderEmail'])) && (isset($_POST['borrowerID']) || isset($_POST['borrowerEmail']))) {

    // connect to db
    $link = mysqli_connect(DB_SERVER, DB_WRITE, DB_WRITE_PASS, DB_DATABASE);
    if (mysqli_connect_errno($link)) {
        $response["result"] = -1;
        $response["message"] = "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    // input
    else {
        // amount input
        $amount = $_POST['amount'];
        
        // description input
        if (isset($_POST['description'])) {
            $description = $_POST['description'];
        }
        else {
            $description = "";
        }
        
        // transaction date input
        if (isset($_POST['transDate'])) {
            $transDate = $_POST['transDate'];
        }
        else {
            $transDate = new DateTime();
            $transDate = $transDate->format('Y-m-d H:i:s');
        }
        
        // lender ID input
        if (isset($_POST['lenderID'])) {
            $lenderID = $_POST['lenderID'];
        }
        else {
            $lenderEmail = $_POST['lenderEmail'];
            $lenderEmail = mysqli_real_escape_string($link, $lenderEmail);
            $IDquery = mysqli_query($link, "SELECT `AccountID`,`Email` FROM Account WHERE `Email` = '$lenderEmail';");
            if (mysqli_num_rows($IDquery) > 1) {
                $response["result"] = -3;
                $response["message"] = "Failed: Lender Email matched >1 Account";
                $response["lenderIDmatches"] = array();
                while ($row = mysqli_fetch_array($IDquery)) {
                    // temp array
                    $account = array();
                    $account["LenderID"] = $row["AccountID"];
                    $account["LenderEmail"] = $row["Email"];
                    // push match into response array
                    array_push($response["lenderIDmatches"], $account);
                }
            }
            else if (mysqli_num_rows($IDquery) == 1) {
                $row = mysqli_fetch_array($IDquery);
                $lenderID = $row["AccountID"];
            }
            else {
                $response["result"] = -4;
                $response["message"] = "Failed: Lender Email matched no Account";
            }
        }
        
        // borrower ID input
        if (isset($_POST['borrowerID'])) {
            $borrowerID = $_POST['borrowerID'];
        }
        else {
            $borrowerEmail = $_POST['borrowerEmail'];
            $borrowerEmail = mysqli_real_escape_string($link, $borrowerEmail);
            $IDquery = mysqli_query($link, "SELECT `AccountID`,`Email` FROM Account WHERE `Email` = '$borrowerEmail';");
            if (mysqli_num_rows($IDquery) > 1) {
                $response["result"] = -5;
                $response["message"] = "Failed: Borrower Email matched >1 Account";
                $response["borrowerIDmatches"] = array();
                while ($row = mysqli_fetch_array($IDquery)) {
                    // temp array
                    $account = array();
                    $account["BorrowerID"] = $row["AccountID"];
                    $account["BorrowerEmail"] = $row["Email"];
                    // push match into response array
                    array_push($response["borrowerIDmatches"], $account);
                }
            }
            else if (mysqli_num_rows($IDquery) == 1) {
                $row = mysqli_fetch_array($IDquery);
                $borrowerID = $row["AccountID"];
            }
            else {
                $response["result"] = -6;
                $response["message"] = "Failed: Borrower Email matched no Account";
            }
        }

        // escape inputs
        $amount = mysqli_real_escape_string($link, $amount);
        $description = mysqli_real_escape_string($link, $description);
        $transDate = mysqli_real_escape_string($link, $transDate);
        $lenderID = mysqli_real_escape_string($link, $lenderID);
        $borrowerID = mysqli_real_escape_string($link, $borrowerID);
        
        $response["queryData"] = array();
        $queryData = array();
        $queryData["lenderID"] = $lenderID;
        $queryData["borrowerID"] = $borrowerID;
        $queryData["amount"] = $amount;
        $queryData["description"] = $description;
        $queryData["transDate"] = $transDate;
        array_push($response["queryData"], $queryData);
        
        //query to add transaction
        if ($response["result"] == null) {
            $newTransaction = mysqli_query($link, "INSERT INTO `Transaction` (`Amount`,`Description`,`TransDate`, `LenderID`, `BorrowerID`) VALUES ('$amount','$description','$transDate','$lenderID','$borrowerID');");

            if ($newTransaction) {
                $response["result"] = 1;
                $response["message"] = "Transaction created successfully";
            }
            else {
                $response["result"] = -7;
                $response["message"] = "Transaction creation query failed";
            }
        }
    }
    // close mysqli connection
    $link->close();
}
else {
    // required field missing
    $response["result"] = -2;
    $response["message"] = "Failed: required field(s) missing";
}

echo json_encode($response);

?>
