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

        /* $username = $_POST[i_username];
          $passwort = $_POST[i_passwort];
          $datenbank = $_POST[i_datenbank];
          $conn = mysqli("localhost",$username, $passwort, $datenbank);
         */
        $conn = new mysqli("localhost", "bro", "bro", "HausHaltsPlaner_Database");

        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        if ($_POST[markt] == "Maerkte anzeigen") {
            $this->view_markt($conn);
        } else if ($_POST[produkt] == "Produkte anzeigen") {
            $this->view_produkt($conn);
        } else if ($_POST[konto] == "Konten anzeigen") {
            $this->view_konto($conn, $username);
        } else if ($_POST[einkauf] == "Einkaeufe anzeigen") {
            $this->view_einkauf($conn, $username);
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
        

"select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-01-__') or 
ve.datum like('____-02-__')or 
ve.datum like('____-03-__');

  select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-04-__') or 
ve.datum like('____-05-__')or 
ve.datum like('____-06-__');

  select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-07-__') or 
ve.datum like('____-08-__')or 
ve.datum like('____-09-__');

  select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-10-__') or 
ve.datum like('____-1-__')or 
ve.datum like('____-12-__');

#nur aktuelle jahr
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-01-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-02-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-03-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-04-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-05-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-06-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-07-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-08-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-09-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-10-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-11-__');
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
where ve.datum like('____-12-__');

#kuchendiagrame
select Produktname ,SUM(anzahl)  from HausHaltsPlaner_Database.ViewAll
group by Produktname;

select Marktname ,Count(Marktname)  from HausHaltsPlaner_Database.ViewAll
group by Marktname;

#tabelle
select name, AVG(entfernung), adresse from HausHaltsPlaner_Database.Markt;
select name, max(entfernung), adresse from HausHaltsPlaner_Database.Markt;
select name, min(entfernung), adresse from HausHaltsPlaner_Database.Markt;
select  m.name,p.name, max(ein.anzahl) from HausHaltsPlaner_Database.Einkauf ein, 
HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m
where ein.p_ID = p.P_ID AND ein.m_ID = m.M_ID and anzahl;

select  m.name,p.name, min(ein.anzahl) from HausHaltsPlaner_Database.Einkauf ein, 
HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m
where ein.p_ID = p.P_ID AND ein.m_ID = m.M_ID and anzahl;

select  m.name,p.name,max(ein.datum) from HausHaltsPlaner_Database.Einkauf ein, 
HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m
where ein.p_ID = p.P_ID AND ein.m_ID = m.M_ID and datum;

select  m.name,p.name,min(ein.datum) from HausHaltsPlaner_Database.Einkauf ein, 
HausHaltsPlaner_Database.Produkt p, HausHaltsPlaner_Database.Markt m
where ein.p_ID = p.P_ID AND ein.m_ID = m.M_ID and datum;

select name,max(preis),gewicht from HausHaltsPlaner_Database.Produkt;
select name,min(preis),gewicht from HausHaltsPlaner_Database.Produkt;
select name,avg(preis),gewicht from HausHaltsPlaner_Database.Produkt;

#verlaufsdiagramm
select betrag, minimum from HausHaltsPlaner_Database.ViewAll where 
datum like('____-01-__');

  select * from SortPreis;
  select * from SortAusgaben;
  select * from SortDatum;
  select * from SortEntfernung;
  select Kontoinhaber, Produktname, Marktname from HausHaltsPlaner_Database.ViewAll ve where ve.datum like('____-01-__') or ve.datum like('____-02-__')or ve.datum like('____-03-__')or ve.datum like('____-04-__'); 
  select Kontoinhaber, Produktname, Marktname from HausHaltsPlaner_Database.ViewAll ve where ve.datum like('____-05-__') or ve.datum like('____-06-__')or ve.datum like('____-07-__')or ve.datum like('____-08-__'); 
  select Kontoinhaber, Produktname, Marktname from HausHaltsPlaner_Database.ViewAll ve where ve.datum like('____-09-__') or ve.datum like('____-10-__')or ve.datum like('____-11-__')or ve.datum like('____-12-__'); 
  ";
  
  
        $sql = "SELECT * FROM Konto";
        $result = $conn->query($sql);

        $mod_array = array();
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
            }
        } else {
            echo "0 results";
        }
        $conn->close();

        include 'View_Statistik.php';
    }

}

?>