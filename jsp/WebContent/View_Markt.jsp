
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<header>

     
</header>



<center><h2 id="KleinererText">Alles um deine Maerkte</h2>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> M_ID</th> 
<th> Marktname</th> 
<th> Adresse </th> 
<th> Postleitzahl</th> 
<th> Entfernung</th> 
</tr> 
</table>
<p></p>


<fieldset><legend><b>Maerkte Aendern/Eintragen</b></legend>

<p><label>Name: <input type="text" name="i_marktname"></label><br>
<p><label>Adresse: <input type="text" name="i_marktadresse"></label><br>
<p><label>Postleitzahl:<input type="text" name="i_marktpostleitzahl"></label><br>
<p><label>Entfernung:<input type="text" name="i_marktentfernung"></label><br>
<br></br>
<input type="submit" name="i_markteintragen" value="Eintragen">
<input type="submit" name="i_marktaendern" value="Aendern">

</fieldset>



</html>
            