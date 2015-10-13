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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="w3-theme-red.css">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">

<body>

<div class="w3-container">
  <hr>
  <div class="w3-center">
    <h2>Produkte</h2>	
    <p w3-class="w3-large">Alles über deine Produkte</p>
  </div>
<div class="w3-responsive w3-card-4">
<table class="w3-table w3-striped w3-bordered">
<thead>
<tr class="w3-theme">
<th> Aendern</th> 
<th> Produktname</th> 
<th> Gewicht </th> 
<th> Preis</th> 
</tr> 
</thead>
<tbody>
	

<%

ArrayList<Object> tmp = access.getMvecModel();

for (int i = 0;i<tmp.size();i++)
{

	
	if (tmp.get(i) instanceof Model_Produkt) 
	{
	%>
	<tr class="w3-white">	
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
	</tr>
	<%
	}	
	%>
	

	
	<%	
	}
	%>
</tbody>
</table>
</div>
</div>

<form action="Change_Produkt.jsp" method="POST">
Produkt ID Eingeben: <input type="text" name="p_ID">
<input class="w3-btn w3-color-teal" type="submit" value="Bearbeiten">
</form>

<form action="Add_Produkt.jsp" method="POST">
<input class="w3-btn w3-color-teal" type="submit" value="Hinzufuegen">
</form>
</body>
</html>
            