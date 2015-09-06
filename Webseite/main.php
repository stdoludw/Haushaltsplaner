<?php
// login starten
 //$v_verbindung = new login;

// mainfenster
echo "<h2>Willkommen im Haushaltsplaner V4 </h2>";
echo "<table border = 0 >";
echo "<tr> <td height = 10> </td> </tr>";
echo "<tr>  <td width= 60 bgcolor = FFCF3A > </td> </tr>";
echo "<tr> <td bgcolor = FFCF3A>";
echo "<p> - <a href=Markt.php> Alles um die eingetragenen Maerkte </a> </p>";
echo "<p> - <a href=Konto.php> Alles um die eingetragenen Konten </a> </p>";
echo "<p> - <a href=Produkt.php>Alles um die eingetragenen Produkte </a> </p>";
echo "<p> - <a href=Einkaur.php>Alles um die eingetragenen Einkaufe </a> </p>";
echo "<p> - <a href=Statistik.php> Statisitk </a> </p>";

echo "</form>";
echo "</form>";
echo "</table>";


/*$befehl = "select * from ViewAll;";
$anfrage = mysqli_query ( $verbindung, $befehl );
while ( $datensatz = mysqli_fetch_object ( $anfrage ) ) {
	$tmp = new Einkauf (
			$datensatz->anzahl, $datensatz->datum, $datensatz->E_ID,
			$datensatz->name, $datensatz->gewicht, $datensatz->preis, $datensatz->P_ID,
			$datensatz->name, $datensatz->postleitzahl, $datensatz->adresse, $datensatz->entfernung, $datensatz->M_ID,
			$datensatz->name, $datensatz->betrag, $datensatz->bankleitzahl, $datensatz->kontonummer, $datensatz->minimun, $datensatz->K_ID,
			$passwort );

	$tmp->ausgabe ();
}

$befehl = "select * from Markt;";
$anfrage = mysqli_query ( $verbindung, $befehl );

while ( $datensatz = mysqli_fetch_object ( $anfrage ) ) {
	$tmp = new Markt ( $datensatz->name, $datensatz->postleitzahl, $datensatz->adresse, $datensatz->entfernung, $datensatz->M_ID );
	$tmp->ausgabe();
}

$befehl = "select * from Produkt;";
$anfrage = mysqli_query ( $verbindung, $befehl );

while ( $datensatz = mysqli_fetch_object ( $anfrage ) ) {
	$tmp = new Produkt ( $datensatz->name, $datensatz->gewicht, $datensatz->preis, $datensatz->P_ID );
	$tmp->ausgabe ();
}

$befehl = "select * from Konto;";
$anfrage = mysqli_query ( $verbindung, $befehl );

while ( $datensatz = mysqli_fetch_object ( $anfrage ) ) {
	$tmp = new Konto ( $datensatz->name, $datensatz->betrag, $datensatz->bankleitzahl, $datensatz->kontonummer, $datensatz->minimun, $datensatz->K_ID );
	$tmp->ausgabe ();
}*/
?>