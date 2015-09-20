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
require_once './Model_Produkt.php';
require_once './Model_Konto.php';
require_once './Model_Einkauf.php';

class Controller {

    function __construct() {
        /*  $username = $_POST[i_username];
          $passwort = $_POST[i_passwort];
          $datenbank = $_POST[i_datenbank];

          $conn = new mysqli('dfch-ludwig.de', $username, $passwort, $datenbank); */
        $conn = new mysqli('dfch-ludwig.de', "bro", "bro", "HausHaltsPlaner_Database");


        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if ($_POST[markt] == "Maerkte anzeigen") {
            $this->view_markt($conn);
        } else if ($_POST[produkt] == "Produkte anzeigen") {
            $this->view_produkt($conn);
        } else if ($_POST[konto] == "Konten anzeigen") {
            $this->view_konto($conn, bro);
        } else if ($_POST[einkauf] == "Einkaeufe anzeigen") {
            $this->view_einkauf($conn, bro);
        } else if ($_POST[statistik] == "Statistiken anzeigen") {
            $this->view_statistik($conn);
        }
    }

    function view_markt($conn) {
        $sql = "SELECT * FROM Markt";
        $result = $conn->query($sql);

        $mod_array = array();
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                $markt = new Model_Markt($row["name"], $row["postleitzahl"], $row["adresse"], $row["entfernung"], $row["M_ID"]);
                $mod_array[] = $markt;
            }
        } else {
            echo "0 results";
        }
        $conn->close();

        include 'View_Markt.php';
    }

    function view_produkt($conn) {
        $sql = "SELECT * FROM Produkt";
        $result = $conn->query($sql);

        $mod_array = array();
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                $produkt = new Model_Produkt($row["name"], $row["gewicht"], $row["preis"], $row["P_ID"]);
                $mod_array[] = $produkt;
            }
        } else {
            echo "0 results";
        }
        $conn->close();

        include 'View_Produkt.php';
    }

    function view_konto($conn, $passwd) {
        $sql = "SELECT * FROM Konto";
        $result = $conn->query($sql);

        $mod_array = array();
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {

                $konto = new Model_Konto($row["name"], $row["betrag"], $row["bankleitzahl"], $row["kontonummer"], $row["minimum"], $row["K_ID"], $passwd);
                $mod_array[] = $konto;
            }
        } else {
            echo "0 results";
        }
        $conn->close();

        include 'View_Konto.php';
    }

    function view_einkauf($conn, $passwd) {
        $sql = "SELECT * FROM ViewAll";
        $result = $conn->query($sql);

        $mod_array = array();
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                $einkauf = new Model_Einkauf($row["anzahl"], $row["datum"], $row["E_ID"], $row["Produktname"], $row["gewicht"], $row["preis"], $row["P_ID"], $row["Marktname"], $row["postleitzahl"], $row["adresse"], $row["entfernung"], $row["M_ID"], $row["Kontoinhaber"], $row["betrag"], $row["bankleitzahl"], $row["kontonummer"], $row["minimum"], $row["K_ID"], $passwd);

                $mod_array[] = $einkauf;
            }
        } else {
            echo "0 results";
        }
        $conn->close();

        include 'View_Einkauf.php';
    }

    function view_statistik($conn) {


        $kuchen = array();
        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
          where ve.datum like('____-01-__') or
          ve.datum like('____-02-__')or
          ve.datum like('____-03-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $kuchen[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-04-__') or
        ve.datum like('____-05-__')or
        ve.datum like('____-06-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $kuchen[] = $row["SUM(ve.preis)"];

        $sql = " select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-07-__') or
        ve.datum like('____-08-__')or
        ve.datum like('____-09-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $kuchen[] = $row["SUM(ve.preis)"];

        $sql = " select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-10-__') or
        ve.datum like('____-11-__')or
        ve.datum like('____-12-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $kuchen[] = $row["SUM(ve.preis)"];

        $bar = array();
        $sql = " select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
          where ve.datum like('____-01-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-02-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-03-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-04-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = " select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-05-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-06-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-07-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-08-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-09-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-10-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = "select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-11-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $sql = " select SUM(ve.preis) from HausHaltsPlaner_Database.ViewAll ve
        where ve.datum like('____-12-__')";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $bar[] = $row["SUM(ve.preis)"];

        $kuchenProduktValue = array();
        $kuchenProduktName = array();
        $sql = "select Produktname, SUM(anzahl) from HausHaltsPlaner_Database.ViewAll
        group by Produktname order by 2 desc";
        $result = $conn->query($sql);

        while ($row = $result->fetch_assoc()) {
            $kuchenProduktValue[] = $row["SUM(anzahl)"];
            $kuchenProduktName[] = $row["Produktname"];
        }

        $kuchenMarktValue = array();
        $kuchenMarktName = array();
        $sql = "select Marktname, Count(Marktname) from HausHaltsPlaner_Database.ViewAll
        group by Marktname order by 2 desc";
        $result = $conn->query($sql);

        while ($row = $result->fetch_assoc()) {
            $kuchenMarktValue[] = $row["Count(Marktname)"];
            $kuchenMarktName[] = $row["Marktname"];
        }

        $tableStatistikName = array();
        $tableStatistikValue = array();
        $tableStatistikValue2 = array();
        $tableStatistikInfo = array();

        $sql = "select name, AVG(entfernung), adresse from HausHaltsPlaner_Database . Markt";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["name"];
        $tableStatistikValue[] = $row["AVG(entfernung)"];
        $tableStatistikValue2[] = $row["adresse"];
        $tableStatistikInfo[] = "Durchschnittliche Entfernung";


        $sql = "select name, max(entfernung), adresse from HausHaltsPlaner_Database . Markt";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["name"];
        $tableStatistikValue[] = $row["max(entfernung)"];
        $tableStatistikValue2[] = $row["adresse"];
        $tableStatistikInfo[] = "Mimale Entfernung Markt";

        $sql = "select name, min(entfernung), adresse from HausHaltsPlaner_Database.Markt";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["name"];
        $tableStatistikValue[] = $row["min(entfernung)"];
        $tableStatistikValue2[] = $row["adresse"];
        $tableStatistikInfo[] = "Minimale Entfernung Markt";

        $sql = "select m.name as marktname, p.name as produktname, max(ein.anzahl) from HausHaltsPlaner_Database.Einkauf ein,
        HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m
        where ein.p_ID = p.P_ID AND ein.m_ID = m . M_ID and anzahl";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["marktname"];
        $tableStatistikValue[] = $row["produktname"];
        $tableStatistikValue2[] = $row["max(ein.anzahl)"];
        $tableStatistikInfo[] = "Maximale Anzahl Produkte [Marktname]";

        $sql = "select m.name as marktname, p.name as produktname, min(ein.anzahl) from HausHaltsPlaner_Database.Einkauf ein,
        HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m
        where ein.p_ID = p.P_ID AND ein.m_ID = m . M_ID and anzahl";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["marktname"];
        $tableStatistikValue[] = $row["produktname"];
        $tableStatistikValue2[] = $row["min(ein.anzahl)"];
        $tableStatistikInfo[] = "Minimale Anzahl Produkte [Marktname]";

        $sql = "select m.name as marktname, p.name as produktname, max(ein.datum) from HausHaltsPlaner_Database.Einkauf ein,
        HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m
        where ein.p_ID = p.P_ID AND ein.m_ID = m . M_ID and datum";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["marktname"];
        $tableStatistikValue[] = $row["produktname"];
        $tableStatistikValue2[] = $row["max(ein.datum)"];
        $tableStatistikInfo[] = "Aelteste Produkt [Marktname]";

        $sql = "select m.name as marktname, p.name as produktname, min(ein.datum) from HausHaltsPlaner_Database.Einkauf ein,
        HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m
        where ein.p_ID = p.P_ID AND ein.m_ID = m . M_ID and datum";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["marktname"];
        $tableStatistikValue[] = $row["produktname"];
        $tableStatistikValue2[] = $row["min(ein.datum)"];
        $tableStatistikInfo[] = "Neuste Produkt [Marktname]";

        $sql = "select name, max(preis), gewicht from HausHaltsPlaner_Database . Produkt";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["name"];
        $tableStatistikValue[] = $row["max(preis)"];
        $tableStatistikValue2[] = $row["gewicht"];
        $tableStatistikInfo[] = "Teuerste Produkt";

        $sql = "select name, min(preis), gewicht from HausHaltsPlaner_Database . Produkt";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["name"];
        $tableStatistikValue[] = $row["min(preis)"];
        $tableStatistikValue2[] = $row["gewicht"];
        $tableStatistikInfo[] = "Guenstigste Produkt";

        $sql = "select name, avg(preis), gewicht from HausHaltsPlaner_Database . Produkt";
        $result = $conn->query($sql);
        $row = $result->fetch_assoc();
        $tableStatistikName[] = $row["name"];
        $tableStatistikValue[] = $row["avg(preis)"];
        $tableStatistikValue2[] = $row["gewicht"];
        $tableStatistikInfo[] = "Durchschnittlicher Preis";


        $conn->close();
        include 'View_Statistik.php';
    }

}

?>