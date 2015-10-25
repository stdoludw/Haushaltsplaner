<%@page import="CONTROLLER.CONTROLLER_Access"%>
<%@page import="CONTROLLER.CONTROLLER_Statments"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.MODEL_Konto"%>

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
    <h2>Konten</h2>	
    <p w3-class="w3-large">Konten ändern</p>
  </div>
<div class="w3-responsive w3-card-4">
<table class="w3-table w3-striped w3-bordered">
<thead>

<%
int ID = Integer.valueOf(request.getParameter("k_ID"));
ArrayList<Object> tmp = (ArrayList<Object>)session.getAttribute(CONTROLLER_Statments.session.mvecModel.toString());
MODEL_Konto konto = null;

for (int i = 0;i<tmp.size();i++)
{
	if (tmp.get(i) instanceof MODEL_Konto) {
	

		if(((MODEL_Konto)tmp.get(i)).getMintID() == ID)
		{
			konto = ((MODEL_Konto)tmp.get(i));
			
		}	
		
	}
}

%>


      	  
<form action="<%=CONTROLLER_Statments.caller.Update_Konto.toString() %>" method="POST">
KontoID<input class="w3-input" style="width:95%" type="text" name="i_konto_id" size="20" 
value="<%out.print(konto.getMintID());%>">	
Kontoinhaber<input class="w3-input" style="width:95%" type="text" name="i_konto_kontoinhaber" size="20" 
value="<%out.print(konto.getMstrName());%>">	
Kontonummer<input class="w3-input" style="width:95%" type="text" name="i_konto_kontonummer" size="20" 
value="<%out.print(konto.getMstrKnr());%>">	
Bankleitzahl<input class="w3-input" style="width:95%" type="text" name="i_konto_bankleitzahl" size="20" 
value="<%out.print(konto.getMstrBLZ());%>">	
Betrag<input class="w3-input" style="width:95%" type="text" name="i_konto_betrag" size="20" 
value="<%out.print(konto.getMstrBetrag());%>">	
Minimum<input class="w3-input" style="width:95%" type="text" name="i_konto_minimum" size="20" 
value="<%out.print(konto.getMintMin());%>">	
	
	<br>
	<center>
<input class="w3-btn w3-light-green" type="submit" value="Konten Ändern">
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