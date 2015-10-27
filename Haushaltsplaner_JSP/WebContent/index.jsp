<%@page import="CONTROLLER.CONTROLLER_Statments"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>


<link rel='stylesheet' href='w3.css'/>
<title>Login</title>
</head>
<body>

<header class="w3-container w3-blue-grey w3-padding-64" id="myHeader">

  <i onclick="w3_open()" class="fa fa-bars w3-xlarge w3-opennav"></i> 

  <div class="w3-center">

  <h1>Willkommen</h1>

  <h3> Login </h3>

</header>
<center>
<form class="w3-container w3-card-4" action="Controller" method="post">
<%
session.setAttribute("site", CONTROLLER_Statments.caller.index.toString());
%>
<div class="w3-group">

		<input class="w3-input" type="text" name="i_username" size = "20" value = "" > Name <br>

		<input class="w3-input" type="password" name = "i_passwort" size = "20" value = "" > Passwort  <br>

    <input class="w3-input" type="text" name = "i_datenbank" size = "20" value = "" > Datenbank  <br>

		<input class="w3-btn w3-light-blue" type="submit" value="Login">

</div>
</form>
</center>

</body>
</html>
