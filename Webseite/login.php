<?php

		// Benutzername
		$user = $_POST ['i_username'];
		
		// Passwort
		$password = $_POST ['i_passwort'];
		
		// Hostname
		$hostname = $_POST ['i_hostname'];
		
		// Port
		$port = $_POST ['i_port'];
		
		// Name der Datenbank
		$database = $_POST ['i_datenbank'];
		
		// Verbindung Herstellen
		$verbindung = mysqli_connect ( $hostname, $user, $password, $database ) or die ( "Keine Vebindung möglich" );
		
		mysqli_select_db ( $verbindung, $database ) or die ( "Datenbank existiert nicht" );
		
		header("Location: View_Main.php");
	

?>