<?php
$db = "pelayowo_info";
$user = "pelayowo_info";
$pass = "080899_Ap";

$conn = new mysqli("localhost","$user","$pass","$db"); 

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
echo "Connected successfully";
?>