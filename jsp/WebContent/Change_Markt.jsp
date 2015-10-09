<%@page import="jsp.access"%>
<%@page import="java.util.Vector"%>
<%@page import="jsp.Model_Markt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<fieldset><legend><b>Markt Aendern</b></legend>

<%
int ID = Integer.valueOf(request.getParameter("m_ID"));
Vector<Object> tmp = access.getMvecModel();
Model_Markt markt = null;

for (int i = 0;i<tmp.size();i++)
{
	if (tmp.get(i) instanceof Model_Markt) {
	

		if(((Model_Markt)tmp.get(i)).getMintID() == ID)
		{
			markt = ((Model_Markt)tmp.get(i));
			
		}	
		
	}
}

%>



<form action="Add_Markt_SQL" method="POST">
Marktname<input class="w3-input" style="width:95%" type="text" name="i_markt_name" size="20" 
value="<%out.print(markt.getMstrName());%>">		
Postleitzahl<input class="w3-input" style="width:95%" type="text" name="i_markt_plz" size="20" 
value="<%out.print(markt.getMstrPLZ());%>">			
Adresse<input class="w3-input" style="width:95%" type="text" name="i_markt_adresse" size="20" 
value="<%out.print(markt.getMstrAdr());%>">			
Entfernung<input class="w3-input" style="width:95%" type="text" name="i_markt_entfernung" size="20" 
value="<%out.print(markt.getMintEntfernung());%>">			
ID<input class="w3-input" style="width:95%" type="text" name="i_markt_id" size="20" 
value="<%out.print(markt.getMintID());%>">		
	
<input type="submit" name="markt" value="i_markt_change" /> 
</form>
</fieldset>

</body>
</html>