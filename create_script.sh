#!/bin/bash
#create user mit dazugehoeriger Datenbank
read -p "Geben den ihren Usernamen ein:" UsernameInput
read -p "Geben den ihren Passwort ein:" PasswortInput

echo "(1) Erstelle User"
echo "(2) Erstelle Datenbank"
echo "(3) Erstelle Master"

read -p "Geben sie ihren auswahlnummer ein:" choice

if [ "$choice" -eq 1 ];
    then
  read -p "Geben den ihren Usernamen ein:" Username
  read -p "Geben den ihren Passwort ein:" Passwort

        mysql -u $UsernameInput -p $PasswortInput-e "create user "$Username"@"%" IDENTIFIED BY "$Passwort";"
        mysql -u $UsernameInput -p $PasswortInput-e "grant select, insert, create, update, CREATE VIEW on DBNAME.* to "$Username"@"%";"
        mysql -u $UsernameInput -p $PasswortInput-e "grant select on master.* to "$Username"@"%";"
        mysql -u $UsernameInput -p $PasswortInput-e "grant CREATE on *.* to "$Username"@"%";"

        echo "User wurde hinzugefügt"

    elseif [ "$choice" -eq 2 ];
      then

        read -p "Geben den zuerstellenden Datenbanknamen ein:" nameDB
          mysql -u $UsernameInput -p $PasswortInput-e "create database HausHaltsPlaner_$nameDB;"
      		mysql -u $UsernameInput -p $PasswortInput-e "use HausHaltsPlaner_$nameDB;"
      		mysql -u $UsernameInput -p $PasswortInput-e "create table Einkauf like master.Einkauf;"
      		mysql -u $UsernameInput -p $PasswortInput-e "create table Konto like master.Konto;"
      		mysql -u $UsernameInput -p $PasswortInput-e "create table Markt like master.Markt;"
      		mysql -u $UsernameInput -p $PasswortInput-e "create table Produkt like master.Produkt;"
      		mysql -u $UsernameInput -p $PasswortInput-e "create view ViewAll as select * from master.ViewAll;"
          mysql -u $UsernameInput -p $PasswortInput-e "create view SortAusgaben as select * from master.SortAusgaben;"
      	  mysql -u $UsernameInput -p $PasswortInput-e "create view SortDatum as select * from master.SortDatum;"
      	  mysql -u $UsernameInput -p $PasswortInput-e "create view SortEntfernung as select * from master.SortEntfernung;"
      	  mysql -u $UsernameInput -p $PasswortInput-e "create view SortPreis as select * from master.SortPreis;"

          echo "Datenbank wurde hinzugefuegt"

      elseif [ "$choice" -eq 3 ]
        then
        echo "Master wird erstellt";

        mysql -u $UsernameInput -p $PasswortInput-e "drop database master;"
        mysql -u $UsernameInput -p $PasswortInput-e "create database master;"
        mysql -u $UsernameInput -p $PasswortInput-e "use master;"
        mysql -u $UsernameInput -p $PasswortInput-e "create table Konto(betrag float, name VARCHAR(255), bankleitzahl VARCHAR(8), kontonummer VARCHAR(9), minimum INT, K_ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(K_ID));"
        mysql -u $UsernameInput -p $PasswortInput-e "create table Produkt(preis float, name VARCHAR(255), gewicht INT, P_ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(P_ID));"
        mysql -u $UsernameInput -p $PasswortInput-e "create table Markt(name VARCHAR(255), postleitzahl VARCHAR(5), adresse VARCHAR(255), entfernung INT, M_ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(M_ID));"
        mysql -u $UsernameInput -p $PasswortInput-e "create table Einkauf(anzahl INT, datum DATE, m_ID INT, FOREIGN KEY(m_ID) REFERENCES Markt(M_ID),k_ID INT, FOREIGN KEY(k_ID) REFERENCES Konto(K_ID),p_ID INT,FOREIGN KEY(p_ID) REFERENCES Produkt(P_ID),	E_ID INT NOT NULL AUTO_INCREMENT,PRIMARY KEY(E_ID));"
        mysql -u $UsernameInput -p $PasswortInput-e "create view ViewAll as selectk.betrag, k.name as 'Kontoinhaber', k.bankleitzahl, k.kontonummer,k.minimum, k.K_ID,p.name as 'Produktname',p.gewicht, p.preis, p.P_ID,m.name as 'Marktname', m.postleitzahl,m.adresse, m.entfernung, m.M_ID,ein.anzahl,ein.datum from Einkauf ein, Produkt p, Konto k, Markt m where ein.m_ID = m.M_ID AND ein.p_ID = p.P_ID AND ein.k_ID = k.K_ID;"
        mysql -u $UsernameInput -p $PasswortInput-e "create view SortPreis as select p.name as 'Produktname', p.Preis, p.gewicht from Produkt p order by p.Preis;"
        mysql -u $UsernameInput -p $PasswortInput-e "create view SortAusgaben asselect p.name as 'Produktname', p.preis , ein.anzahl ,ROUND((p.preis * ein.anzahl),2) as 'gesamtpreis' from Produkt p, Einkauf ein where p.P_ID = ein.p_ID;"
        mysql -u $UsernameInput -p $PasswortInput-e "create view SortDatum as select p.name as 'Produktname', p.preis , p.gewicht , ein.datum  from Produkt p, Einkauf ein where p.P_ID = ein.p_ID Order by ein.Datum;"
        mysql -u $UsernameInput -p $PasswortInput-e "create view SortEntfernung as select m.name as 'Martname', m.entfernung , m.postleitzahl, m.adresse  from Markt m;"

        echo "Master wurde hinzugefuegt"

fi