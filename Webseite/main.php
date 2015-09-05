<?php
include 'Markt.php';
include 'Konto.php';
include 'Produkt.php';
include 'Einkauf.php';
include "login.php";

//login starten
//$v_verbindung = new login;

//mainfenster
//eventuell mit session = admin

echo "<h2>Willkommen im Haushaltsplaner V4 </h2>";
echo "<table border = 0 >";
echo "<tr> <td height = 10> </td> </tr>";
echo "<tr>  <td width= 60 bgcolor = FFCF3A > </td> </tr>";
echo "<tr> <td bgcolor = FFCF3A>";
echo "<p> - <a href=maerkte.php> Anzeigen der eingetragenen Maerkte </a> </p>";
echo "<p> - <a href=konto.php> Anzeige der eingetragenen Konten </a> </p>";
echo "<p> - <a href=produkte.php> Anzeigen der eingetragenen Produkte </a> </p>";
echo "<p> - <a href=user.php> Anzeigen der eingetragenen User </a> </p>";
echo "<p> - <a href=einkauf.php> Anzeigen der Einkaufe </a> </p>";
echo "<p> - <a href=insert_Konto.html> Markt einfuegen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Produkt einfuegen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Konto einfuegen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Einkauf einfuegen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Statisitk </a> </p>";

echo "</form>";
echo "</form>";
echo "</table>";

/*wenn session = admin
echo "<table border = 0 >";
echo "<tr> <td height = 10> </td> </tr>";
echo "<tr>  <td width= 60 bgcolor = FFCF3A > </td> </tr>";
echo "<tr> <td bgcolor = FFCF3A>";
echo "<p> - <a href=maerkte.php> Anzeigen der eingetragenen Maerkte </a> </p>";
echo "<p> - <a href=konto.php> Anzeige der eingetragenen Konten </a> </p>";
echo "<p> - <a href=produkte.php> Anzeigen der eingetragenen Produkte </a> </p>";
echo "<p> - <a href=user.php> Anzeigen der eingetragenen User </a> </p>";
echo "<p> - <a href=einkauf.php> Anzeigen der Einkaufe </a> </p>";
echo "<p> - <a href=insert_Konto.html> Markt einfuegen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Produkt einfuegen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Konto einfuegen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Einkauf einfuegen </a> </p>";
echo "<p> - <a href=insert_Konto.html> Markt entfernen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Produkt entfernen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Konto entfernen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Einkauf entfernen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Clean All</a> </p>";
echo "<p> - <a href=delete_Konto.html> User Hinzufuegen</a> </p>";
echo "<p> - <a href=delete_Konto.html> Statisitk </a> </p>";

echo "</form>";
echo "</form>";
echo "</table>";*/


/*$befehl = "select * from Einkauf;";
 $anfrage = mysqli_query($verbindung,$befehl);

 while( $datensatz = mysqli_fetch_object($anfrage))
 {
 echo $datensatz->anzahl;
 }*/
 

?>