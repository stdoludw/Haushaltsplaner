<?php
//anmeldung
//session starten

//Benutzername
$user = "bro";
//Passwort
$password = "bro";
//Hostname
$hostname = "dfch-ludwig.de";
//Port
$port = "3306";
//Name der Datenbank
$database = "HausHaltsPlaner_Database";
//Verbindung Herstellen
$verbindung = mysqli_connect($hostname, $user, $password, $database)
or die ("Keine Vebindung möglich");

mysqli_select_db($verbindung,$database)or die ("Datenbank existiert nicht");
echo "test";
?>

