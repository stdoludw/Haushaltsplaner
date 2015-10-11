<%@page import="java.util.ArrayList"%>
<%@page import="jsp.Model_Einkauf"%>
<%@page import="jsp.Model_Konto"%>
<%@page import="jsp.Model_Markt"%>
<%@page import="jsp.Model_Produkt"%>

<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="jsp.access"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<html>
<header>
<h2>Alles um deine Einkaefe</h2>
     
</header>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> E_ID</th> 
<th> Anzahl</th> 
<th> Datum </th> 

<th> K_ID</th> 
<th> Kontoname</th> 
<th> Bankleitzahl </th> 
<th> Kontonummer</th> 
<th> Betrag</th> 
<th> Minimum</th> 

<th> M_ID</th> 
<th> Marktname</th> 
<th> Adresse </th> 
<th> Postleitzahl</th> 
<th> Entfernung</th> 

<th> P_ID</th> 
<th> Produktname</th> 
<th> Gewicht </th> 
<th> Preis</th> 
</tr> 

	<tr>
	
<%


ArrayList<Object> tmp = access.getMvecModel();
for (int i = 0;i<tmp.size();i++)
{
	
	if (tmp.get(i) instanceof Model_Einkauf) {
	%>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMintAnzahl());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMstrDatum());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMkntKonto().getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMkntKonto().getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMkntKonto().getMstrBLZ());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMkntKonto().getMstrKnr());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMkntKonto().getMstrBetrag());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMkntKonto().getMintMin());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMmkrMarkt().getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMmkrMarkt().getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMmkrMarkt().getMstrAdr());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMmkrMarkt().getMstrPLZ());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMmkrMarkt().getMintEntfernung());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMprdProdukt().getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMprdProdukt().getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMprdProdukt().getMintGewicht());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)tmp.get(i)).getMprdProdukt().getMfltPreis());
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
<form action="Change_Einkauf.jsp" method="POST">
Einkauf ID Eingeben: <input type="text" name="ein_ID">
<input class="w3-btn w3-color-teal" type="submit" value="Bearbeiten">
</form>

<form action="Add_Einkauf.jsp" method="POST">
<input class="w3-btn w3-color-teal" type="submit" value="Hinzufuegen">
</form>
</html>
</html>
            