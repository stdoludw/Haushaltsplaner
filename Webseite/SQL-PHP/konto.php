<?php
session_start();
$_SESSION["Admin"] = 1;
?>

<html>
<head>
<title>Endschalter</title>
<body>
<center>
<h2>Abfrage Endschalter</h2>

<?php 

if($_SESSION["Admin"] == 1)
{
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

$befehl = "Select * from Konto";

$befehl = $befehl." ;";
//Hier können beliebige einschraenkungen gemacht werden!

//$befehl = $befehl." order by id ;";

//Befehl ausführen
$anfrage = mysqli_query($verbindung,$befehl);
echo "<table border=1 bgcolor='FFCF3A'>";
   echo "<tr>" ;
   echo "<th> ID </th>";
   echo "<th> Name </th>";
   echo "<th> Betrag </th>";
   echo "<th> Bankleihzahl </th>";
   echo "<th> Kontonummer </th>";
   echo "<th> Minimumg </th>";
    echo "<th> UserID </th>";
   echo "</tr>";
while( $datensatz = mysqli_fetch_object($anfrage))
   {
   echo "<tr>" ;
   //$erid = $datensatz->M_ID;
   echo "<td>".$datensatz->K_ID."</td>";
   echo "<td>".$datensatz->name."</td>";
   echo "<td>".$datensatz->betrag."</td>";
   echo "<td>".$datensatz->bankleihzahl."</td>";
   echo "<td>".$datensatz->kontonummer."</td>";
   echo "<td>".$datensatz->minimum."</td>";
   echo "<td>".$datensatz->u_id."</td>";
   echo "</tr>";
   }
echo "</table>";
echo "<br> <br>";
echo "<br>";

echo "<a href= \"/index.php\" > Hauptmen&uuml; </a>";

echo "<p>";


mysqli_close($verbindung);

} else {

	echo "Bitte Erneut Einloggen!";
	echo "<br>";
	echo "<a href=index.html > Login </a>";

}

?>

</p>
</body>
</html>

