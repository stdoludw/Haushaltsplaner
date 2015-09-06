

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Markt view</title>

<style type="text/css" media="screen">
body {
	background-image: url(back.jpg);
	padding: 10px;
}

#KleinererText {
	font-size: 50px;
	font-family: "Arial Black", Arial, sans-serif;
	color: #fff;
}
</style>
<h2 id="KleinererText">Alles um deine Märkte</h2>

</head>
<body>

	<center>
		<table bgcolor="#00FF00" border="1" cellspacing="1" cellpadding="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Postleizahl</th>
				<th>Adresse</th>
				<th>Entfernung</th>
				<th>Ändern</th>
			</tr>

<?php
include 'Markt.php';
/*
 * ;
 * $befehl = "select * from Markt;";
 * $anfrage = mysqli_query ( $verbindung, $befehl );
 *
 * while ( $datensatz = mysqli_fetch_object ( $anfrage ) ) {
 * $tmp = new Markt ( $datensatz->name, $datensatz->postleitzahl, $datensatz->adresse, $datensatz->entfernung, $datensatz->M_ID );
 * $tmp->ausgabe();
 * }
 */

$tmp = new Markt ( "12", "12", "12", 0, 0 );
$tmp2 = new Markt ( "122", "122", "122", 0, 1 );

$tmp->ausgabe ();

?>



		</table>
		<table border=0>

			<tr>
				<td bgcolor=#00FF00><input type=text name=i_marktname size=20> Name</input><br>
					<input type=text name=i_marktpostleitzahl size=20> Postleitzahl</input><br>
					<input type=text name=i_marktadresse size=20> Adresse</input><br> <input
					type=text name=i_martentfernung size=20> Entfernung</input><br> <input
					type=submit name=wahl value=Eintragen </input> <input type=submit
					name=wahl value=Update </input><br>
		
		</table>
	</center>

</body>
</html>