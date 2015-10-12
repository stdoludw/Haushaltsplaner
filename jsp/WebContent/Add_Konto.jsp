<%@page import="jsp.access"%>
<%@page import="jsp.Model_Konto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<fieldset><legend><b>Konto Eintragen</b></legend>

	        	  
<form action="Add_Konto_SQL" method="POST">
Kontoinhaber<input class="w3-input" style="width:95%" type="text" name="i_konto_kontoinhaber" size="20"> 
Kontonummer<input class="w3-input" style="width:95%" type="text" name="i_konto_kontonummer" size="20" >
Bankleitzahl<input class="w3-input" style="width:95%" type="text" name="i_konto_bankleitzahl" size="20" >
Betrag<input class="w3-input" style="width:95%" type="text" name="i_konto_betrag" size="20" >
<p></p>
Minimum<input class="w3-input" style="width:95%" type="text" name="i_konto_minimum" size="20" >
	
<input type="submit" name="konto" value="i_konto_change" /> 
</form>

</fieldset>

</body>
</html>