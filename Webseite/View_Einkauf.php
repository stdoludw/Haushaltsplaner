<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Einkauf view</title>

<style type="text/css" media="screen">
body { background-image:url(back.jpg); padding:10px; }
#KleinererText { font-size:50px; font-family:"Arial Black",Arial,sans-serif; color:#fff; }

</style>
</head>
<body>

<center><h2 id="KleinererText">Alles um deine Konten</h2>


<table bgcolor="#00FF00" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> Anzahl</th> 
<th> Datum</th> 
<th> E_ID </th> 

<th> M_ID</th> 
<th> Marktname</th> 
<th> Postleizahl </th> 
<th> Adresse</th> 
<th> Entfernung</th> 

<th> P_ID</th> 
<th> Produktname</th> 
<th> Gewicht</th> 
<th> Preis</th> 

<th> K_ID</th> 
<th> Kontoname</th> 
<th> Bankleitzahl </th> 
<th> Kontonummer</th> 
<th> Betrag</th> 
<th> Minimum</th> 

</tr> 

<?php 
include "Einkauf.php"
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
}*/


?>


</table></center>


</body>
</html>