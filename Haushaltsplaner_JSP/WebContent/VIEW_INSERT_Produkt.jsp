<%@page import="CONTROLLER.CONTROLLER_Access"%>
<%@page import="java.util.Vector"%>
<%@page import="MODEL.MODEL_Produkt"%>
<%@page import="CONTROLLER.CONTROLLER_Statments"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <h2>Mäkte</h2>	
    <p w3-class="w3-large">Produkte Hinzufügen</p>
  </div>
<div class="w3-responsive w3-card-4">
<table class="w3-table w3-striped w3-bordered">
<thead>
	        	  
<form action="<%=CONTROLLER_Statments.caller.Insert_Produkt.toString() %>" method="POST">
Produktname<input class="w3-input" style="width:95%" type="text" name="i_produkt_name" size="20" >		
Gewicht<input class="w3-input" style="width:95%" type="text" name="i_produkt_gewicht" size="20" >	
<p></p>	
Preis<input class="w3-input" style="width:95%" type="text" name="i_produkt_preis" size="20" >		
	<br>
	<center>
<input class="w3-btn w3-light-green" type="submit" value="Produkt Hinzufügen">
</center>
</form>
	

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