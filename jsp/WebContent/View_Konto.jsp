<%@page import="jsp.Model_Konto"%>
<%@page import="java.util.Vector"%>
<%@page import="jsp.access"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<html>
<header>
<h2>Alles um deine Konten</h2>
     
</header>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> K_ID</th> 
<th> Kontoinhaber</th> 
<th> Kontonummer </th> 
<th> Bankleitzahl</th> 
<th> Betrag</th> 
<th> Minimum</th> 

</tr> 

	<tr>
	
<%

access bean=(access)request.getAttribute("bean");  
Vector<Object> tmp = bean.getMvecModel();

for (int i = 0;i<tmp.size();i++)
{
	
	
	if (tmp.get(i) instanceof Model_Konto) {
	%>
	<td>
	<%
	out.print(((Model_Konto)tmp.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Konto)tmp.get(i)).getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Konto)tmp.get(i)).getMstrKnr());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Konto)tmp.get(i)).getMstrBLZ());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Konto)tmp.get(i)).getMstrBetrag());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Konto)tmp.get(i)).getMintMin());
	%>
	</td>
	<%
	}
	
	%>
	</tr>
	<%
	
}

%>
</table>
<p></p>
<fieldset><legend><b>Konten Aendern/Eintragen</b></legend>


<form action="Controller" method="POST">

		ID<input type= "text" name="i_konto_id" required>
		Kontoinhaber<input type= "text" name="i_konto_name" required>
		Kontonummer<input type="text"  name="i_konto_kontonummer" required>
		Bankleitzahl<input type="text" name="i_konto_bankleitzahl" required>
		Betrag<input type="text"  name="i_konto_betrag" required>
		Minimum<input type="text" name="i_konto_minimum" required>
		
		<input type="submit" name="konto" value ="i_konto_add" /> 
       <input type="submit" name="konto" value="i_konto_change" />
		
	</form>

</fieldset>

</html>
            