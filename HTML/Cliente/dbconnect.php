<?php
$db = "pelayowo_info";
$user = "pelayowo_info";
$pass = "queenmaster99ap";

$conn = new mysqli("localhost","$user","$pass","$db"); 

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>