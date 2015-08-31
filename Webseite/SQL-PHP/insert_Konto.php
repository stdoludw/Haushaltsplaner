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
$s_name = $_POST['i_name'];
$s_betrag = $_POST['i_betrag'];
$s_blz = $_POST['i_blz'];
$s_kontonummer = $_POST['i_Kontonummer'];
$s_min = $_POST['i_min'];
$s_user = $_POST['i_user'];




echo"Eingetragen wurde";
echo"ID: $s_ID<br>\n";
echo"Name: $s_name<br>\n";
echo"Betrag: $s_betrag<br>\n";
echo"Bankleihzahl: $s_blz<br>\n";
echo"Kontnummer: $s_kontonummer<br>\n";
echo"Minimum: $s_min<br>\n";
echo"User: $s_user <br>\n";
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
$befehl = "INSERT INTO Konto(K_ID,betrag,name,bankleitzahl,kontonummer,minimum,u_ID) VALUES ('$s_ID' ,'$s_betrag', '$s_name' , '$s_blz' ,'$s_kontonummer' , '$s_min', '$s_user');";

//Anfrage absenden
$anfrage = mysqli_query($verbindung,$befehl);


//Verbindung schließen
mysqli_close($verbindung);

}

?>
</body>
</html>

