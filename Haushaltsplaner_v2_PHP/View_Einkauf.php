
<html><header>

        <meta charset=UTF-8>
        <title>Einkauf view</title>

        <style type=text/css media=screen>
            body {
                background-image: url(back.jpg);
                padding: 10px;
            }

            #KleinererText {
                font-size: 50px;
                font-family: Arial Black, Arial, sans-serif;
                color: #fff;
            }
        </style>

    </header>


    <center>
        <h2 id=KleinererText>Alles um deine Einkaeufe</h2>

        <table bgcolor=#00FF00 border=1 cellspacing=1 cellpadding=1>
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
            echo "<tr>";

            foreach ($mod_array as $var) {

                foreach ($var->array_return() as $var2) {
                    echo "<td> ";
                    echo $var2;
                    echo "</td>";
                }
                echo "</tr>";
            }
            ?>


            <html>