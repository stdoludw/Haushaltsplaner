<%@page import="jsp.Controll_Statments"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="w3.css">
<body w3-white-grey>

<nav class="w3-sidenav w3-white w3-card-2 w3-animate-left" style="display:none">
  <a href="javascript:void(0)"
  onclick="w3_close()"
	    class="w3-closenav w3-large">Close</a>
      <a href="index.html">Login</a>
  <a href="login-success.jsp">Hauptmenue</a>
  <a href="usermanagement.jsp"> Benutzerverwaltung </a>
  
</nav>

<header class="w3-container w3-blue-grey">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Menue </span>
</header>


<center>
<form class="w3-container w3-card-4" action="Controller" method="post">

<div class="w3-group">

		<input class="w3-input" type="text" name="i_username" size = "20" value = "" > Name <br>

		<input class="w3-input" type="password" name = "i_passwort" size = "20" value = "" > Passwort  <br>

    <input class="w3-input" type="text" name = "i_datenbank" size = "20" value = "" > Datenbank  <br>

		<input class="w3-btn w3-light-blue" type="submit" value="Login">

</div>


</body>
</html>