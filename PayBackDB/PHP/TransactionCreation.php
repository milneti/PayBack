<?php

//this is the script to add user to mySQL db
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php

//function:
	//input
		//amount
		//description
		//transdate
		//lenderID
		//borrowerID
	//action
		//makes an input query to mySQL database with given data
	//output
		//true or false on query.
//template code by Hohyun Jeon @10/27/2013
//updated code for TransactionCreation by Ryan Murphy 11/16/13

//Connect to DB
//Please contact Chase for proper admin login info

$mysqli = mysqli_connect("localhost", "Admin", "Password", "PayBackDB");
if (mysqli_connect_errno($mysqli)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

//input

$amount = $_POST['amount'];
$description = $_POST['description'];
$transdate = $_POST['transdate'];
$lenderID = $_POST['lenderID'];
$borrowerID = $_POST['borrowerID'];

$amount = mysqli_real_escape_string($mysqli, $amount);
$description = mysqli_real_escape_string($mysqli, $description);
$transdate = mysqli_real_escape_string($mysqli, $transdate);
$lenderID = mysqli_real_escape_string($mysqli, $lenderID);
$borrowerID = mysqli_real_escape_string($mysqli, $borrowerID);

//query to add account

$inserttransaction = mysqli_query($mysqli, "INSERT INTO `Transaction` (`Amount`,`Description`,`TransDate`, 'LenderID', `BorrowerID`) VALUES (\"$amount\",\"$description\",\"$transdate\",\"$lenderID\",\"$borrowerID\");");

//output

echo $inserttransaction;

?>
