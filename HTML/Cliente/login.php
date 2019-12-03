<?php 
session_start();

use dbconnect.php;

$username = $_POST['nombre'];
$password = md5($_POST['pwd']);

$query = "SELECT * FROM registered_users WHERE name='$username' AND password='$password'";
$result = mysql_query($query)or die(mysql_error());
$num_row = mysql_num_rows($result);
$row=mysql_fetch_array($result);
if( $num_row >=1 ) { 
echo 'true';
$_SESSION['user_name']=$row['name'];
}
else{
echo 'false';
}
?>