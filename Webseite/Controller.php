<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Controller
 *
 * @author dominik
 */
require_once './Model_Markt.php';

class Controller {

    function __construct() {

        /* $username = $_POST[i_username];
          $passwort = $_POST[i_passwort];
          $datenbank = $_POST[i_datenbank];
          $port = $_POST[i_port];
          $hostname = $_POST[i_hostname];

          $datenbank = HausHaltsPlaner_Database;
          $username = bro;
          $passwort = bro;
          $hostname = localhost; */
        // Verbindung zu localhost auf port 3307
        
        $link = mysql_connect('dfch-ludwig.de:3306', 'root', 'uuK#aS)639M(U9qkarV@nz}$T?z.F');
        if (!$link) {
                    echo "Erfolgreich verbunden";

            die('Verbindung schlug fehl: ' . mysql_error());
        }
        echo "Erfolgreich verbunden";
        mysql_close($link);

        /*
          // Create connection
          $conn = new mysqli($hostname, $username, $passwort, $datenbank);

          // Check connection
          if ($conn->connect_error) {
          die("Connection failed: " . $conn->connect_error);
          }

          $sql = "SELECT * FROM Markt";
          $result = $conn->query($sql);

          if ($result->num_rows > 0) {
          // output data of each row
          while ($row = $result->fetch_assoc()) {
          echo "Name: " . $row[name] . "<br>";
          }
          } else {
          echo "0 results";
          }
          $conn->close();


          /* $mod = new Model_Markt("Schokolade", "60385", "HanauerLdr", 10, 1);
          $mod2 = new Model_Markt("Schokolade", "60385", "HanauerLdr", 10, 2);

          $mod_array = array($mod, $mod2);
          include 'View_Markt.php'; */
    }

}

?>