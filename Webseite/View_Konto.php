<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Konto view</title>

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

</head>
<body>

	<center>
		<h2 id="KleinererText">Alles um deine Konten</h2>


		<table bgcolor="#00FF00" border="1" cellspacing="1" cellpadding="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Bankleitzahl</th>
				<th>Kontonummer</th>
				<th>Betrag</th>
				<th>Minimum</th>
			</tr>

<?php
include 'Konto.php';
/*
 * $befehl = "select * from Konto;";
 * $anfrage = mysqli_query ( $verbindung, $befehl );
 *
 * while ( $datensatz = mysqli_fetch_object ( $anfrage ) ) {
 * $tmp = new Konto ( $datensatz->name, $datensatz->betrag, $datensatz->bankleitzahl, $datensatz->kontonummer, $datensatz->minimun, $datensatz->K_ID );
 * $tmp->ausgabe ();
 * }
 */

?>

</table>

		<table border=0>
			<tr>
				<td height=10></td>
			</tr>
			<tr>
				<td bgcolor=#00FF00>
					<form action=login.php method=post>
						<input type=text name=i_kontoname size=20> Name <br> <input
							type=text name=i_kontobankleitzahl size=20> Bankleitzahl <br> <input
							type=text name=i_kontokontonummer size=20> Kontonummer <br> <input
							type=text name=i_kontobetrag size=20> Betrag <br> <input
							type=text name=i_kontominimum size=20> Minimum <br> <input
							type=submit name=wahl value=Eintragen <br> <input type=submit
							name=wahl value=Update
					
					</form>
		
		</table>


	</center>

</body>
</html>