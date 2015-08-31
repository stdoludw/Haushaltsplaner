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

//$befehl = "Select * from Einkauf";

$befehl = "select Markt.name AS Markt, Konto.name AS Konto , Produkt.name AS Produkt, User.name AS User, datum,anzahl from Einkauf,Markt,Konto,Produkt,User where Markt.M_ID = Einkauf.m_ID AND Konto.K_ID = Einkauf.k_ID AND Produkt.P_ID = Einkauf.p_ID and User.U_ID = Einkauf.u_ID";

$befehl = $befehl." order by Konto";
$befehl = $befehl." ;";
//Hier können beliebige einschraenkungen gemacht werden!

//$befehl = $befehl." order by id ;";
//echo $befehl;
//Befehl ausführen
$anfrage = mysqli_query($verbindung,$befehl);
echo "<table border=1 bgcolor='FFCF3A'>";
   echo "<tr>" ;
   echo "<th> Anzahl </th>";
   echo "<th> Datum </th>";
   echo "<th> Markt </th>";
   echo "<th> Konto </th>";
   echo "<th> Produkt </th>";
   echo "<th> User </th>";
   echo "</tr>";
while( $datensatz = mysqli_fetch_object($anfrage))
   {
   echo "<tr>" ;
   //$erid = $datensatz->M_ID;
   echo "<td>".$datensatz->anzahl."</td>";
   echo "<td>".$datensatz->datum."</td>";
   echo "<td>".$datensatz->Markt."</td>";
   echo "<td>".$datensatz->Konto."</td>";
   echo "<td>".$datensatz->Produkt."</td>";
   echo "<td>".$datensatz->User."</td>";
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

