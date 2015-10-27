<%@page import="CONTROLLER.CONTROLLER_Statments"%>

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
      <a href="<%=CONTROLLER_Statments.menu.Export.toString()%>"> Datenexport </a>
    <a href="<%=CONTROLLER_Statments.menu.Import.toString()%>"> Datenimport </a>
</nav>

<header class="w3-container w3-blue-grey">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Menue </span>
</header>

<div class="w3-row-padding w3-center w3-margin-top">


<div class="w3-quarter">
  <a href="<%=CONTROLLER_Statments.redirect.VIEW_Statistik.toString() %>" ><div class="w3-card-2" style="min-height:260px">
  <h4>Statistik</h4><br>
  <img src=statistic.png>
  <p> Anzeigen der Statistik </p> </a>
  </div>
</div>
</div>
<hr>

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