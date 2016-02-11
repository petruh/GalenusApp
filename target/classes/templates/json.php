<?php 

$x =array (
"id"=> 1455195680616,
"username"=> "jdoe",
"password"=> "$2a$10$cB3l3oTDSRHCPNNLDktoV.11B9KXQ3szAPORomwLbgtB..e5B.uNa",
"firstName"=> "John",
"lastName"=> "Doe",
"address"=> "Middle of nowhere",
"phone"=> "555-12345",
"email"=> "john.doe@yahoo.com",
"fullName"=> "John Doe",
"accountNonExpired"=> true,
"accountNonLocked"=> true,
"credentialsNonExpired"=> true,
"enabled"=> true,
);


echo json_encode($x);

?>