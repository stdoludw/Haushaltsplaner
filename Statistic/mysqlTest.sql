#balkendiagramme 
#alle jahre
select SUM(betrag) from HausHaltsPlaner_Database.ViewAll ve 
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

