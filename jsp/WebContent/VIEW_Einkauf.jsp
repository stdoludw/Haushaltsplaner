<%@page import="CONTROLLER.CONTROLLER_Statments"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.MODEL_Einkauf"%>
<%@page import="MODEL.MODEL_Konto"%>
<%@page import="MODEL.MODEL_Markt"%>
<%@page import="MODEL.MODEL_Produkt"%>

<%@page import="java.util.List"%>
<%@page import="CONTROLLER.CONTROLLER_Access"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="w3.css">
<link rel="stylesheet" href="w3-theme-red.css">

<body w3-white-grey>

<nav class="w3-sidenav w3-white w3-card-2 w3-animate-left" style="display:none">
  <a href="javascript:void(0)"
  onclick="w3_close()"
	    class="w3-closenav w3-large">Close</a>
      <a href="<%=CONTROLLER_Statments.menu.login.toString()%>"  >Login</a>
  <a href="<%=CONTROLLER_Statments.menu.Hauptmenue.toString()%>">Hauptmenue</a>
  <a href="<%=CONTROLLER_Statments.menu.Benutzerverwaltung.toString()%>"> Benutzerverwaltung </a>
      <a href="<%=CONTROLLER_Statments.menu.Export.toString()%>"> Datenexport </a>
    <a href="<%=CONTROLLER_Statments.menu.Import.toString()%>"> Datenimport </a>
</nav>

<header class="w3-container w3-blue-grey">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Menue </span>
</header>

<body>

<div class="w3-container">
  <hr>
  <div class="w3-center">
    <h2>Einkäufe</h2>	
    <p w3-class="w3-large">Alles über deine Einkäufe</p>
  </div>
<div class="w3-responsive w3-card-4">
<table class="w3-table w3-striped w3-bordered">
<thead>
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
</thead>
<tbody>
	
	<tr>
	
<%


ArrayList<Object> tmp = (ArrayList<Object>)session.getAttribute(CONTROLLER_Statments.session.mvecModel.toString());
for (int i = 0;i<tmp.size();i++)
{
	
	if (tmp.get(i) instanceof MODEL_Einkauf) {
	%>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMintAnzahl());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMstrDatum());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMkntKonto().getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMkntKonto().getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMkntKonto().getMstrBLZ());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMkntKonto().getMstrKnr());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMkntKonto().getMstrBetrag());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMkntKonto().getMintMin());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMmkrMarkt().getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMmkrMarkt().getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMmkrMarkt().getMstrAdr());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMmkrMarkt().getMstrPLZ());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMmkrMarkt().getMintEntfernung());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMprdProdukt().getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMprdProdukt().getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMprdProdukt().getMintGewicht());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Einkauf)tmp.get(i)).getMprdProdukt().getMfltPreis());
	%>
	</td>
	<%
	
	}
	
	%>
	</tr>
	<%
}

%>
</tbody>
</table>
</div>
</div>

<center>
<br>
<form action="<%=CONTROLLER_Statments.redirect.VIEW_UPDATE_Einkauf.toString() %>" method="POST">
Einkauf ID Eingeben: <input type="text" name="ein_ID">
<input class="w3-btn w3-light-blue" type="submit" value="Bearbeiten">
</form>
<br>
<form action="<%=CONTROLLER_Statments.caller.Delete_Einkauf.toString() %>" method="POST">
Einkauf ID Eingeben: <input type="text" name="ein_ID">
<input class="w3-btn w3-light-blue" type="submit" value="Entfernen">
</form>


<form action="<%=CONTROLLER_Statments.redirect.VIEW_INSERT_Einkauf.toString()%>" method="POST">
<input class="w3-btn w3-light-green" type="submit" value="Hinzufuegen">
</form>
</center>


<script>
function w3_open() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
}
function w3_close() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
}
</script>



</body>
</html>
            