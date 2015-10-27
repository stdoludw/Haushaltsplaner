<%@page import="MODEL.MODEL_Konto"%>
<%@page import="java.util.List"%>
<%@page import="CONTROLLER.CONTROLLER_Access"%>
<%@page import="CONTROLLER.CONTROLLER_Statments"%>
<%@page import="java.util.ArrayList"%>

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
    <h2>Konten</h2>	
    <p w3-class="w3-large">Alles über deine Konten</p>
  </div>
<div class="w3-responsive w3-card-4">
<table class="w3-table w3-striped w3-bordered">
<thead>
<tr class="w3-theme">
<th> K_ID</th> 
<th> Kontoinhaber</th> 
<th> Kontonummer </th> 
<th> Bankleitzahl</th> 
<th> Betrag</th> 
<th> Minimum</th> 
</tr> 
</thead>
<tbody>
	
	<tr>
	
<%

ArrayList<Object> tmp = (ArrayList<Object>)session.getAttribute(CONTROLLER_Statments.session.mvecModel.toString());

for (int i = 0;i<tmp.size();i++)
{
	
	
	if (tmp.get(i) instanceof MODEL_Konto) {
	%>
	<td>
	<%
	out.print(((MODEL_Konto)tmp.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Konto)tmp.get(i)).getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Konto)tmp.get(i)).getMstrKnr());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Konto)tmp.get(i)).getMstrBLZ());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Konto)tmp.get(i)).getMstrBetrag());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Konto)tmp.get(i)).getMintMin());
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
<form action="<%=CONTROLLER_Statments.redirect.VIEW_UPDATE_Konto.toString() %>" method="POST">
Konto ID Eingeben: <input type="text" name="k_ID">
<input class="w3-btn w3-light-blue" type="submit" value="Bearbeiten">
</form>
<br>

<form action="<%=CONTROLLER_Statments.caller.Delete_Konto.toString() %>" method="POST">
Konto ID Eingeben: <input type="text" name="k_ID">
<input class="w3-btn w3-light-blue" type="submit" value="Entfernen">
</form>

<form action="<%=CONTROLLER_Statments.redirect.VIEW_INSERT_Konto.toString() %>" method="POST">
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
            