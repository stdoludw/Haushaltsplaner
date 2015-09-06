<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produkt view</title>

<style type="text/css" media="screen">
body { background-image:url(back.jpg); padding:10px; }
#KleinererText { font-size:50px; font-family:"Arial Black",Arial,sans-serif; color:#fff; }

</style>
</head>
<body>

<center><h2 id="KleinererText">Alles um deine Produkte</h2>


<table bgcolor="#00FF00" border="1" cellspacing="1" cellpadding="1">
 <tr>
 <th> ID</th>
 <th> Name</th>
 <th> Gewicht</th>
 <th> Preis</th>
 </tr>
 
 <?php
include 'Produkt.php';/*
$befehl = "select * from Produkt;";
$anfrage = mysqli_query ( $verbindung, $befehl );

while ( $datensatz = mysqli_fetch_object ( $anfrage ) ) {
	$tmp = new Produkt ( $datensatz->name, $datensatz->gewicht, $datensatz->preis, $datensatz->P_ID );
	$tmp->ausgabe ();
}*/


?>
 
 
 </table>
 
 
 <table border=0>
			<tr>
				<td height=10></td>
			</tr>
			<tr>
				<td bgcolor=#00FF00>
					<form action=login.php method=post>
						<input type=text name=i_produktname size=20> Name <br> <input
							type=text name=i_produktgewicht size=20> Gewicht <br> <input
							type=text name=i_produktpreis size=20> Preis <br> <input
							type=submit name=wahl value=Eintragen <br> <input
							type=submit name=wahl value=Update
							
		</form>
		</table>
 
 </center>
</body>
</html>