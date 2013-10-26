//this is a template for the basic mySQL access.
//for source please go to: http://www.php.net/manual/en/mysqli.quickstart.dual-interface.php
<?php
//admin portal
$mysqli = mysqli_connect("localhost", "Admin", "2013PayMeBack@3031", "PayBackDB");
if (mysqli_connect_errno($mysqli)) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
//basic query list would go here
$sacct = mysqli_query($mysqli, "SELECT * as _msg FROM ACCOUNT");
//_POST['fname' = Bob];
$fname = $_POST['fname'];
$fname = mysql_real_escape_string($fname);
$findbyfname = mysqli_query($mysqli, "SELECT * FROM ACCOUNT WHERE FNAME = "+$fname);
$row = mysqli_fetch_assoc($sacct);
echo $row['_msg'];

//If access by different user exist we would use this portal
/*$mysqli = new mysqli("example.com", "user", "password", "database");
if ($mysqli->connect_errno) {
    echo "Failed to connect to MySQL: " . $mysqli->connect_error;
}*/

//$res = $mysqli->query("SELECT 'choices to please everybody.' AS _msg FROM DUAL");
//$row = $res->fetch_assoc();
//echo $row['_msg'];
?>
