<%@page import="jsp.access"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jsp.Model_Einkauf"%>
<%@page import="jsp.Model_Konto"%>
<%@page import="jsp.Model_Markt"%>
<%@page import="jsp.Model_Produkt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<fieldset><legend><b>Einkauf Eintragen</b></legend>

<%ArrayList<Object> tmp = access.getMvecModel(); %>
<form action="Add_Einkauf_SQL" method="POST">
Einkaufsdatum<input class="w3-input" style="width:95%" type="text" name="i_einkauf_datum" size="20" >	
Einkaufs anzahl<input class="w3-input" style="width:95%" type="text" name="i_einkauf_anzahl" size="20" >	
Kontoinhaber<select name="i_einkauf_konto_cmb" >  
      <%
      for (int i = 0;i<tmp.size();i++)
      {     	
      	if (tmp.get(i) instanceof Model_Konto) {
      		%>
      		<option>
      		<%
      		out.print(((Model_Konto)tmp.get(i)).getMstrName());
      		%>
      		</option>
      		<%
      	}
      }
      %>
      </select> 
      Marktname<select name="i_einkauf_markt_cmb" >   
      <%
      for (int i = 0;i<tmp.size();i++)
      {     	
      	if (tmp.get(i) instanceof Model_Markt) {
      		%>
      		<option>
      		<%
      		out.print(((Model_Markt)tmp.get(i)).getMstrName());
      		%>
      		</option>
      		<%
      	}
      }
      %>
      </select> 
  
	Produktname <select name="i_einkauf_produkt_cmb" >   
      <%
      for (int i = 0;i<tmp.size();i++)
      {     	
      	if (tmp.get(i) instanceof Model_Produkt) {
      		%>
      		<option>
      		<%
      		out.print(((Model_Produkt)tmp.get(i)).getMstrName());
      		%>
      		</option>
      		<%
      	}
      }
      %>
      </select> 

	
<input type="submit" name="einkauf" value="i_einkauf_change" /> 
</form>

</fieldset>

</body>
</html>