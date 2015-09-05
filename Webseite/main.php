<?php
//login starten
//$v_verbindung = new login;

//mainfenster
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

//admin
//truncate all -> neuen userhinzufuegen


/*$befehl = "select * from Einkauf;";
 $anfrage = mysqli_query($verbindung,$befehl);

 while( $datensatz = mysqli_fetch_object($anfrage))
 {
 echo $datensatz->anzahl;
 }*/
 

?>