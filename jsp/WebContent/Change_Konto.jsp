<%@page import="jsp.access"%>
<%@page import="java.util.Vector"%>
<%@page import="jsp.Model_Konto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<fieldset><legend><b>Konten Aendern</b></legend>

<%
int ID = Integer.valueOf(request.getParameter("k_ID"));
Vector<Object> tmp = access.getMvecModel();
Model_Konto konto = null;

for (int i = 0;i<tmp.size();i++)
{
	if (tmp.get(i) instanceof Model_Konto) {
	

		if(((Model_Konto)tmp.get(i)).getMintID() == ID)
		{
			konto = ((Model_Konto)tmp.get(i));
			
		}	
		
	}
}

%>


      	  
<form action="change_Konto_SQL" method="POST">
<input class="w3-input" style="width:95%" type="text" name="i_konto_id" size="20" 
value="<%out.print(konto.getMintID());%>">	
<input class="w3-input" style="width:95%" type="text" name="i_konto_kontoinhaber" size="20" 
value="<%out.print(konto.getMstrName());%>">	
<input class="w3-input" style="width:95%" type="text" name="i_konto_kontonummer" size="20" 
value="<%out.print(konto.getMstrKnr());%>">	
<input class="w3-input" style="width:95%" type="text" name="i_konto_bankleitzahl" size="20" 
value="<%out.print(konto.getMstrBLZ());%>">	
<input class="w3-input" style="width:95%" type="text" name="i_konto_betrag" size="20" 
value="<%out.print(konto.getMstrBetrag());%>">	
<input class="w3-input" style="width:95%" type="text" name="i_konto_minimum" size="20" 
value="<%out.print(konto.getMintMin());%>">	
	
<input type="submit" name="konto" value="i_konto_change" /> 
</form>

</fieldset>

</body>
</html>