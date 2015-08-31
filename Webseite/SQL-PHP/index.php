<?php
session_start();
$_SESSION["Admin"] = 1;
?>
<html>
<body>
<center>
<table border = 0 > 
<tr> <td height = 100> </td> </tr>
<tr>  <td width="400" bgcolor = "FFCF3A" >  <h1>Hauptmen&uuml; <h1> </td> </tr>
<tr> <td bgcolor = "FFCF3A" > 

<?php

if($_SESSION["Admin"] == 1)
{
echo "<p> - <a href=maerkte.php> Anzeigen der eingetragenen Maerkte </a> </p>";
echo "<p> - <a href=konto.php> Anzeige der eingetragenen Konten </a> </p>";
echo "<p> - <a href=produkte.php> Anzeigen der eingetragenen Produkte </a> </p>";
echo "<p> - <a href=user.php> Anzeigen der eingetragenen User </a> </p>";
echo "<p> - <a href=einkauf.php> Anzeigen der Einkaufe </a> </p>";
echo "<p> - <a href=insert_Konto.html> Konto einfuegen </a> </p>";
echo "<p> - <a href=delete_Konto.html> Konto L&ouml;schen </a> </p>";
//echo "<p> - <a href=logout.php>Logout </a> </p>";
} elseif ($_SESSION["Nutzer"] == 1) {
echo "<p> - <a href=abfrage_endschalter.html> Endschaltertabelle Abfrage </a> </p>";
echo "<p> - <a href=logout.php>Logout </a> </p>";
} else {
	echo "Bitte Erneut Einloggen!";
	echo "<br>";
	echo "<a href=index.html > Login </a>";
}
?>
</td> </tr> </table>
</center>
</body>
</html>
