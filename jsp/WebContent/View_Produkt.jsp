
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<header>

     
</header>



<center><h2 id="KleinererText">Alles um deine Produkten</h2>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> P_ID</th> 
<th> Produktname</th> 
<th> Gewicht </th> 
<th> Preis</th> 
</tr> 
</table>
<p></p>


<fieldset><legend><b>Produkte Aendern/Eintragen</b></legend>

<p><label>Name: <input type="text" name="i_produktname"></label><br>
<p><label>Gewicht: <input type="text" name="i_produktgewicht"></label><br>
<p><label>Preis:<input type="text" name="i_produktpreis"></label><br>
<br></br>
<input type="submit" name="i_produkteintragen" value="Eintragen">
<input type="submit" name="i_produktaendern" value="Aendern">

</fieldset>



</html>
            