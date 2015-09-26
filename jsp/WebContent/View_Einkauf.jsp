
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<header>
   
</header>



<center><h2 id="KleinererText">Alles um deine Einkaefe</h2>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> E_ID</th> 
<th> Anzahl</th> 
<th> Datum </th> 

<th> K_ID</th> 
<th> Kontoname</th> 
<th> Bankleitzahl </th> 
<th> Kontonummer</th> 
<th> Betrag</th> 
<th> Minimum</th> 

<th> M_ID</th> 
<th> Marktname</th> 
<th> Adresse </th> 
<th> Postleitzahl</th> 
<th> Entfernung</th> 

<th> P_ID</th> 
<th> Produktname</th> 
<th> Gewicht </th> 
<th> Preis</th> 
</tr> 
</table>
<p></p>


<fieldset><legend><b>Einkaefe Aendern/Eintragen</b></legend>

<p><label>Anzahl: <input type="text" name="i_einkaufanzahl"></label><br>
<p><label>Datum: <input type="text" name="i_einkaufdatum"></label><br>

<p><label>K_ID: <input type="text" name="i_einkaufkontoID"></label><br>
<p><label>Kontoinhaber: <input type="text" name="i_einkaufkontoname"></label><br>
<p><label>Kontonummer: <input type="text" name="i_einkaufkontonummer"></label><br>
<p><label>Bankleitzahl:<input type="text" name="i_einkaufkontobankleitzahl"></label><br>
<p><label>Minimum:<input type="text" name="i_einkaufkontominimum"></label><br>
<p><label>Betrag:<input type="text" name="i_einkaufkontobetrag"></label><br>

<p><label>M_ID: <input type="text" name="i_einkaufmarktID"></label><br>
<p><label>Marktname: <input type="text" name="i_einkaufmarktname"></label><br>
<p><label>Adresse: <input type="text" name="i_einkaufmarktadresse"></label><br>
<p><label>Postleitzahl:<input type="text" name="i_einkaufmarktpostleitzahl"></label><br>
<p><label>Entfernung:<input type="text" name="i_einkaufmarktentfernung"></label><br>

<p><label>P_ID: <input type="text" name="i_einkaufproduktID"></label><br>
<p><label>Produktname: <input type="text" name="i_einkaufproduktname"></label><br>
<p><label>Gewicht: <input type="text" name="i_einkaufproduktgewicht"></label><br>
<p><label>Preis:<input type="text" name="i_einkaufproduktpreis"></label><br>


<br></br>
<input type="submit" name="i_einkaufeintragen" value="Eintragen">
<input type="submit" name="i_einkaufaendern" value="Aendern">

</fieldset>



</html>
            