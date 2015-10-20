<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="CONTROLLER.CONTROLLER_Statments"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="w3.css">
<body w3-white-grey>

<nav class="w3-sidenav w3-white w3-card-2 w3-animate-left" style="display:none">
  <a href="javascript:void(0)"
  onclick="w3_close()"
	    class="w3-closenav w3-large">Close</a>
 <a href="<%=CONTROLLER_Statments.menu.login.toString()%>"  >Login</a>
  <a href="<%=CONTROLLER_Statments.menu.Hauptmenue.toString()%>">Hauptmenue</a>
  <a href="<%=CONTROLLER_Statments.menu.Benutzerverwaltung.toString()%>"> Benutzerverwaltung </a>
  
</nav>

<header class="w3-container w3-blue-grey">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Menue </span>
</header>


<center>
<form class="w3-container w3-card-4" action="<%=CONTROLLER_Statments.caller.usermanagement.toString()%>" method="post">

<div class="w3-group">

		<input class="w3-input" type="text" name="i_username" size = "20" value = "" > Name <br>

		<input class="w3-input" type="password" name = "i_passwort" size = "20" value = "" > Passwort  <br>
<input class="w3-input" type="password" name = "i_passwort2" size = "20" value = "" > Nochmal das Passwort  <br>
    <input class="w3-input" type="text" name = "i_datenbank" size = "20" value = "" > Datenbank  <br>

		<input class="w3-btn w3-light-blue" type="submit" value="Login">

</div>

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