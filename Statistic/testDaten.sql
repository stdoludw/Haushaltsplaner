insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Schokolade",100,1.5);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Milch",1000,2);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Kakao",1000,3.5);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Coca Cola",1000,1.75);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Wasser",1500,0.5);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("TV",15000,530);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Musik Anlage",null,230);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Laptop",null,450);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Buch",null,20);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Kaugummie",50,0.5);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Mehl",1000,1.2);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Zucker",1000,1.5);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Backpulver",null,2);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Äpfel",1000,1.5);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Birnen",1000,2);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Bananen",100,2);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("DVDs",null,9.99);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Telefon",null,85.99);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Handy",null,250.99);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Sofa",null,500.99);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Stift",null,0.99);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Block",null,0.99);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Internet",null,39.99);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Kneipe",null,25.99);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Bier",1000,2.5);
insert into HausHaltsPlaner_Database.Produkt (name, gewicht, preis) Values("Musik",null,9.99);
--25
--java -jar aes.jar bro test Decode
-- passwort/ string/ Decode oder Encode

insert into HausHaltsPlaner_Database.Konto (name, kontonummer, bankleitzahl, betrag, minimum) Values("lPoNDXmYy1NjnoTDZfK7oA==","DimIk/c2BwOJ3K8J9FbnoQ==", "Odo7siKPOseC4WW8zcBhoQ==","nZmKbVoA9n9UrJu8DwwAaQ==","OGHxNydLLYfnKPaahzfuNQ==");
insert into HausHaltsPlaner_Database.Konto (name, kontonummer, bankleitzahl, betrag, minimum) Values("+uC1ucX/cG+3kKtPtDC0Gg==","Odo7siKPOseC4WW8zcBhoQ==","DimIk/c2BwOJ3K8J9FbnoQ==","OGHxNydLLYfnKPaahzfuNQ==","nZmKbVoA9n9UrJu8DwwAaQ==");
insert into HausHaltsPlaner_Database.Konto (name, kontonummer, bankleitzahl, betrag, minimum) Values("5mVSNk+n2DUzWVO6XB2tEA==", "DimIk/c2BwOJ3K8J9FbnoQ==","Odo7siKPOseC4WW8zcBhoQ==","lPoNDXmYy1NjnoTDZfK7oA==", "OGHxNydLLYfnKPaahzfuNQ==");
--3

insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("REWE","Inheitnerstrasse 12","60385",1);
insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("REWE","Bergerstraße 13","60314",2);
insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("Aldi","Seckbacherlandstrasse","60384",12);
insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("Aldi","Roßdorferstrasse","60380",34);
insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("Aldi","Kettelerallee","60378",11);
insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("Lidle","Brueder-Grimm-Strasse","60360",2);
insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("Lidle","Hanuerlandstrasse","60220",5);
insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("Lidle","Rincklakeweg 102","60385",90);
insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("Penny","Gremendorfer Weg 132","60350",34);
insert into HausHaltsPlaner_Database.Markt (name, adresse, postleitzahl, entfernung) Values("Penny","Donaueschingen","60380",2);
--10

insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 1 Month), 1,1,1,1);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 2 Month), 2,2,2,2);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 3 Month), 3,3,3,3);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 4 Month), 4,4,1,4);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 5 Month), 5,5,2,5);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 6 Month), 6,6,3,6);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 7 Month), 7,7,1,7);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 8 Month), 8,8,2,8);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 9 Month), 9,9,3,9);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 10 Month), 1,10,1,10);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 11 Month), 12,1,2,11);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 12 Month), 13,2,3,12);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 13 Month), 14,3,1,13);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 14 Month), 15,4,2,14);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 15 Month), 16,5,3,15);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 16 Month), 17,6,1,16);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 17 Month), 18,7,2,17);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 18 Month), 19,8,3,18);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 19 Month), 20,9,1,19);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 20 Month), 21,10,2,20);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 21 Month), 22,1,3,21);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 22 Month), 1,2,1,22);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 23 Month), 2,3,2,23);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 24 Month), 3,4,3,24);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 25 Month), 4,5,1,25);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 26 Month), 5,6,2,1);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 27 Month), 6,7,3,2);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 28 Month), 7,8,1,3);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 29 Month), 8,9,2,4);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 30 Month), 9,10,3,5);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 31 Month), 1,1,1,6);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 32 Month), 11,2,2,7);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 33 Month), 12,3,3,8);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 34 Month), 13,4,1,9);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 35 Month), 14,5,2,10);
insert into HausHaltsPlaner_Database.Einkauf (datum, anzahl, m_ID, k_ID, p_ID) Values(DATE_ADD(sysdate() , INTERVAL 36 Month), 15,6,3,11);
--37

