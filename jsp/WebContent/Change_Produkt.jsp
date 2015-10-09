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

<fieldset><legend><b>Produkte Aendern</b></legend>

<%
int ID = Integer.valueOf(request.getParameter("p_ID"));
Vector<Object> tmp = access.getMvecModel();
Model_Produkt produkt = null;

for (int i = 0;i<tmp.size();i++)
{
	if (tmp.get(i) instanceof Model_Produkt) {
	

		if(((Model_Produkt)tmp.get(i)).getMintID() == ID)
		{
			produkt = ((Model_Produkt)tmp.get(i));
			
		}	
		
	}
}

%>
	        	  
<form action="change_Produkt_SQL" method="POST">
<input class="w3-input" style="width:95%" type="text" name="i_produkt_id" size="20" 
value="<%out.print(produkt.getMintID());%>">	
<input class="w3-input" style="width:95%" type="text" name="i_produkt_name" size="20" 
value="<%out.print(produkt.getMstrName());%>">		
<input class="w3-input" style="width:95%" type="text" name="i_produkt_gewicht" size="20" 
value="<%out.print(produkt.getMintGewicht());%>">		
<input class="w3-input" style="width:95%" type="text" name="i_produkt_preis" size="20" 
value="<%out.print(produkt.getMfltPreis());%>">		
	
<input type="submit" name="produkt" value="i_produkt_change" /> 
</form>

</fieldset>

</body>
</html>