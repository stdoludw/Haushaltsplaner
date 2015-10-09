<%@page import="jsp.access"%>
<%@page import="java.util.Vector"%>
<%@page import="jsp.Model_Produkt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<fieldset><legend><b>Produkte Eintragen</b></legend>

	        	  
<form action="Add_Markt_SQL" method="POST">
Marktname<input class="w3-input" style="width:95%" type="text" name="i_markt_name" size="20" >		
Postleitzahl<input class="w3-input" style="width:95%" type="text" name="i_markt_plz" size="20" >		
Adresse<input class="w3-input" style="width:95%" type="text" name="i_markt_adresse" size="20" >		
Entfernung<input class="w3-input" style="width:95%" type="text" name="i_markt_entfernung" size="20" >		
	
	
<input type="submit" name="markt" value="i_markt_change" /> 
</form>

</fieldset>

</body>
</html>