<?php 
session_start();
$_SESSION["Admin"] = 1;
?>
<html>
<head>
<title>Eintrag Konto</title>
<body>
<center>
<table border = 0 >
<tr> <td height = 100> </td> </tr>
<tr>  <td width='60%' bgcolor = 'FFCF3A' >  <h1>Eintrag Konto<h1> </td> </tr>
<tr> <td bgcolor = 'FFCF3A' width=200> 

<?php

if($_SESSION["Admin"] == 1)
{

//Auswertung
$s_ID = $_POST['i_ID'];

echo"Gelöscht wurde das Konto mit der ID:";
echo"ID: $s_ID<br>\n";
echo "<a href= \"/index.php\" > Hauptmen&uuml; </a> ";

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

//Befehl ausführen
$befehl = "DELETE FROM Konto where K_ID = '$s_ID'";

//Anfrage absenden
$anfrage = mysqli_query($verbindung,$befehl);


//Verbindung schließen
mysqli_close($verbindung);

}

?>
</body>
</html>

