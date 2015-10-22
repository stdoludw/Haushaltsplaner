<%@page import="MODEL.MODEL_Produkt"%>
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
   <a href="VIEW_Export.jsp"> Export </a>
      <a href="VIEW_Import.jsp"> Import </a>
</nav>

<header class="w3-container w3-blue-grey">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Menue </span>
</header>

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

ArrayList<Object> tmp = (ArrayList<Object>)session.getAttribute(CONTROLLER_Statments.session.mvecModel.toString());

for (int i = 0;i<tmp.size();i++)
{

	
	if (tmp.get(i) instanceof MODEL_Produkt) 
	{
	%>
	<tr class="w3-white">	
	<td>
	<%
	out.print(((MODEL_Produkt)tmp.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Produkt)tmp.get(i)).getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Produkt)tmp.get(i)).getMintGewicht());
	%>
	</td>
	<td>
	<%
	out.print(((MODEL_Produkt)tmp.get(i)).getMfltPreis());
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

<center>
<br>
<form action="<%=CONTROLLER_Statments.redirect.VIEW_UPDATE_Produkt.toString() %>" method="POST">
Produkt ID Eingeben: <input type="text" name="p_ID">
<input class="w3-btn w3-light-blue" type="submit" value="Bearbeiten">
</form>
<br>
<form action="<%=CONTROLLER_Statments.caller.Delete_Produkt.toString() %>" method="POST">
Produkt ID Eingeben: <input type="text" name="p_ID">
<input class="w3-btn w3-light-blue" type="submit" value="Entfernen">
</form>

<br>

<form action="<%=CONTROLLER_Statments.redirect.VIEW_INSERT_Produkt.toString() %>" method="POST">
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
            