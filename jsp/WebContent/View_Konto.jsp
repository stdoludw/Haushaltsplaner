<%@page import="jsp.Model_Konto"%>
<%@page import="java.util.List"%>
<%@page import="jsp.access"%>
<%@page import="java.util.ArrayList"%>

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

ArrayList<Object> tmp = access.getMvecModel();

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
<form action="Change_Konto.jsp" method="POST">
Markt ID Eingeben: <input type="text" name="k_ID">
<input class="w3-btn w3-color-teal" type="submit" value="Bearbeiten">
</form>

<form action="Add_Konto.jsp" method="POST">
<input class="w3-btn w3-color-teal" type="submit" value="Hinzufuegen">
</form>
</html>

            