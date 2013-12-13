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
$lenderID = filter_input(INPUT_POST, 'lenderID',FILTER_VALIDATE_INT, $ID_options);
$borrowerID = filter_input(INPUT_POST, 'borrowerID',FILTER_VALIDATE_INT, $ID_options);
$amount = filter_input(INPUT_POST, 'amount', FILTER_SANITIZE_NUMBER_FLOAT);
$amount = $amount/100;
$description = $_POST['description'];
$transDate = $_POST['transDate'];
$lenderEmail = $_POST['lenderEmail'];
$borrowerEmail = $_POST['borrowerEmail'];

$email = $_POST['email'];
$password = $_POST['password'];

// connect to db
$link = mysqli_connect(DB_SERVER, DB_WRITE, DB_WRITE_PASS, DB_DATABASE);
if (mysqli_connect_errno($link)) {
    $response["result"] = -1;
    $response["message"] = "Failed to connect to MySQL: " . mysqli_connect_error();
    echo json_encode($response);
}
else {
    // escape inputs
    $email = mysqli_real_escape_string($link, $email);
    $password = mysqli_real_escape_string($link, $password);
    $amount = mysqli_real_escape_string($link, $amount);
    $description = mysqli_real_escape_string($link, $description);
    $transDate = mysqli_real_escape_string($link, $transDate);
    $lenderID = mysqli_real_escape_string($link, $lenderID);
    $lenderEmail = mysqli_real_escape_string($link, $lenderEmail);
    $borrowerID = mysqli_real_escape_string($link, $borrowerID);
    $borrowerEmail = mysqli_real_escape_string($link, $borrowerEmail);

    // authorize
    if($loginPass = mysqli_query($link, "SELECT `password` FROM `Account` WHERE `email` = '$email';")){
	    if(mysqli_fetch_object($loginPass)->password == $password){

            // login successful, validate lenderID
            if ($lenderID !== false) {
                $IDquery = mysqli_query($link, "SELECT `AccountID` FROM Account WHERE `AccountID` = '$lenderID';");
                if (mysqli_fetch_object($IDquery)->AccountID != $lenderID) {
                    $lenderID = false;
                }
            }
            // if invalid lenderID, get lenderID from lenderEmail
            if ($lenderID == false) {
                $IDquery = mysqli_query($link, "SELECT `AccountID`,`Email` FROM Account WHERE `Email` = '$lenderEmail';");
                if (mysqli_num_rows($IDquery) == 1) {
                    $lenderID = mysqli_fetch_object($IDquery)->AccountID;
                }
                else if (mysqli_num_rows($IDquery) > 1) {
                    $response["result"] = -4;
                    $response["message"] = "Failed: Lender Email '$lenderEmail' matched >1 Account";
                    $response["lenderIDmatches"] = array();
                    while ($row = mysqli_fetch_array($IDquery)) {
                        // temp array
                        $account = array();
                        $account["LenderID"] = $row["AccountID"];
                        $account["LenderEmail"] = $row["Email"];
                        // push match into response array
                        array_push($response["lenderIDmatches"], $account);
                    }
                //    echo json_encode($response);
                }
                else {
                    $response["result"] = -5;
                    $response["message"] = "Failed: Lender (ID: ".$_POST['lenderID'].", Email: $lenderEmail) matched no Account";
                //    echo json_encode($response);
                }
            }
            
            // validate borrowerID
            if ($borrowerID !== false) {
                $IDquery = mysqli_query($link, "SELECT `AccountID` FROM Account WHERE `AccountID` = '$borrowerID';");
                if (mysqli_fetch_object($IDquery)->AccountID != $borrowerID) {
                    $borrowerID = false;
                }
            }
            // if invalid borrowerID, get borrowerID from borrowerEmail
            if ($borrowerID == false) {
                $IDquery = mysqli_query($link, "SELECT `AccountID`,`Email` FROM Account WHERE `Email` = '$borrowerEmail';");
                if (mysqli_num_rows($IDquery) == 1) {
                    $borrowerID = mysqli_fetch_object($IDquery)->AccountID;
                }
                else if (mysqli_num_rows($IDquery) > 1) {
                    $response["result"] = -6;
                    $response["message"] = "Failed: Borrower Email '$borrowerEmail' matched >1 Account";
                    $response["borrowerIDmatches"] = array();
                    while ($row = mysqli_fetch_array($IDquery)) {
                        // temp array
                        $account = array();
                        $account["BorrowerID"] = $row["AccountID"];
                        $account["BorrowerEmail"] = $row["Email"];
                        // push match into response array
                        array_push($response["borrowerIDmatches"], $account);
                    }
                //    echo json_encode($response);
                }
                else {
                    $response["result"] = -7;
                    $response["message"] = "Failed: Borrower (ID: ".$_POST['borrowerID'].", Email: $borrowerEmail) matched no Account";
                //    echo json_encode($response);
                }
            }

            // validate amount range
            if ($amount < 1) {
                $response["result"] = -8;
                $response["message"] = "Failed: Amount cannot be less than $0.01";
                $amount = false;
            }
            else if ($amount > 99999999) {
                $response["result"] = -8;
                $response["message"] = "Failed: Amount cannot be greater than $999,999.99";
                $amount = false;
            }

            // validate transDate, set to current server time if invalid
            if(!preg_match('/\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}/',$transDate)) {
                $transDate = new DateTime();
                $transDate = $transDate->format('Y-m-d H:i:s');
            }
        
            $queryData = array();
            $queryData["lenderID"] = $lenderID;
            $queryData["borrowerID"] = $borrowerID;
            $queryData["amount"] = $amount;
            $queryData["description"] = $description;
            $queryData["transDate"] = $transDate;
            
            //query to add transaction
            if ($response["result"] == null) {
                $newTransaction = mysqli_query($link, "INSERT INTO `Transaction` (`Amount`,`Description`,`TransDate`, `LenderID`, `BorrowerID`) VALUES ('$amount','$description','$transDate','$lenderID','$borrowerID');");

                if ($newTransaction) {
                    $response["result"] = 1;
                    $response["message"] = "Transaction created successfully";
                }
                else {
                    $response["result"] = -9;
                    $response["message"] = "Transaction creation query failed";
                }
                // add query data to response
                $response["queryData"] = array();
                array_push($response["queryData"], $queryData);
            }
        }
        else {
            $response["result"] = -3;
            $response["message"] = "Login query success, but Password does not match";
        }
    }
    else {
    $response["result"] = -2;
    $response["message"] = "Query for auth failed: user does not exist";
    }
}

// close mysqli connection
$link->close();

echo json_encode($response);

?>
