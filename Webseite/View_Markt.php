
<html><header>

        <meta charset=UTF-8>
        <title>Markt view</title>

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
        <h2 id=KleinererText>Alles um deine Märkte</h2>

        <table bgcolor=#00FF00 border=1 cellspacing=1 cellpadding=1>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Postleizahl</th>
                <th>Adresse</th>
                <th>Entfernung</th>
                <th>Aendern</th>
            </tr> 

            <?php
            echo "<tr>";

            foreach ($mod_array as $var) {
                
                foreach ($var->array_return() as $var2)
                {
                echo "<td> ";
                echo $var2;
                echo "</td>";
                }
                 echo "<td><input type=radio name=radQ3 value=7 id=". $mod->get_PK() . "title=aendern /></td>";
            echo "</tr>";
            }

           
            ?>

            <html>
