
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<header>

     
</header>



<center><h2 id="KleinererText">Alles um deine Konten</h2>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> K_ID</th> 
<th> Kontoname</th> 
<th> Bankleitzahl </th> 
<th> Kontonummer</th> 
<th> Betrag</th> 
<th> Minimum</th> 
</tr> 
</table>
<p></p>


<fieldset><legend><b>Konto Aendern/Eintragen</b></legend>

<p><label>Name: <input type="text" name="i_kontoname"></label><br>
<p><label>Kontonummer: <input type="text" name="i_kontonummer"></label><br>
<p><label>Bankleitzahl:<input type="text" name="i_kontobankleitzahl"></label><br>
<p><label>Minimum:<input type="text" name="i_kontominimum"></label><br>
<p><label>Betrag:<input type="text" name="i_kontobetrag"></label><br>
<br></br>
<input type="submit" name="i_kontoeintragen" value="Eintragen">
<input type="submit" name="i_kontoaendern" value="Aendern">

</fieldset>



</html>
            