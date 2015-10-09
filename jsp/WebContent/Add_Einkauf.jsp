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

	        	  
<form action="Add_Produkt_SQL" method="POST">
Produktname<input class="w3-input" style="width:95%" type="text" name="i_produkt_name" size="20" >		
Gewicht<input class="w3-input" style="width:95%" type="text" name="i_produkt_gewicht" size="20" >		
Preis<input class="w3-input" style="width:95%" type="text" name="i_produkt_preis" size="20" >		
	
<input type="submit" name="produkt" value="i_produkt_change" /> 
</form>

</fieldset>

</body>
</html>