<%@page import="jsp.Model_Produkt"%>
<%@page import="jsp.access"%>
<%@page import="java.util.ArrayList"%>

<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<html>
<header>
<h2>Alles um deine Produkten</h2>    
</header>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> Aendern</th> 
<th> Produktname</th> 
<th> Gewicht </th> 
<th> Preis</th> 
</tr> 

	<tr>	
<%

ArrayList<Object> tmp = access.getMvecModel();

for (int i = 0;i<tmp.size();i++)
{
	
	
	if (tmp.get(i) instanceof Model_Produkt) {
	%>
	<td>
	<%
	out.print(((Model_Produkt)tmp.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Produkt)tmp.get(i)).getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Produkt)tmp.get(i)).getMintGewicht());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Produkt)tmp.get(i)).getMfltPreis());
	%>
	</td>
	<%
	}
	
	%>
	</tr>
	<%
	
}

%>
</form>
</table>

<form action="Change_Produkt.jsp" method="POST">
Produkt ID Eingeben: <input type="text" name="p_ID">
<input class="w3-btn w3-color-teal" type="submit" value="Bearbeiten">
</form>

<form action="Add_Produkt.jsp" method="POST">
<input class="w3-btn w3-color-teal" type="submit" value="Hinzufuegen">
</form>
</html>
            