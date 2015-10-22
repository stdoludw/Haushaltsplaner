<%@page import="java.util.ArrayList"%>
<%@page import="CONTROLLER.CONTROLLER_Statments"%>
<%@page import="wox.serial.Easy" %>

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
  
</nav>

<header class="w3-container w3-blue-grey">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Menue </span>
</header>

<body>

<% 
ArrayList<Object> tmp = (ArrayList<Object>)session.getAttribute(CONTROLLER_Statments.session.mvecModel.toString());

Easy.save(tmp, "/var/www/html/xml/data.xml"); 

out.print("Daten wurden gespeichert");
response.sendRedirect("http://www.dfch-ludwig.de/xml/data.xml");
%>

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