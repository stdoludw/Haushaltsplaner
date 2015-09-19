

<?php

echo "<form  method=post >" .
 '<input type = submit name=markt value="Maerkte anzeigen"> <br>' .
 '<input type = submit name=produkt value="Produkte anzeigen"> <br>' .
 '<input type = submit name=konto value="Konten anzeigen"> <br>' .
 '<input type = submit name=einkauf value="Einkaeufe anzeigen"> <br>' .
 '<input type = submit name=statistik value="Statistiken anzeigen"> <br>';




include_once './Controller.php';
$mm = new Controller();
?>