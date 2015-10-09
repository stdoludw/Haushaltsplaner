<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="w3.css">
<body w3-white-grey>

<nav class="w3-sidenav w3-white w3-card-2 w3-animate-left" style="display:none">
  <a href="javascript:void(0)"
  onclick="w3_close()"
  class="w3-closenav w3-large">Close</a>
  <a href="index.php">Hauptmenue</a>
  <a href="usermanagement.php"> Benutzerverwaltung </a>
</nav>

<header class="w3-container w3-orange">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Datenbank </span>

</header>

<div class="w3-row-padding w3-center w3-margin-top">

<div class="w3-quarter">
  <a href="View_Markt.jsp">  <div class="w3-card-2" style="min-height:260px">
  <h4>Maerkte</h4><br>
  <img src=market.png>
  <p>Anzeigen aller Eingetragener Maerkte</p> </a>
  </div>
</div>

<div class="w3-quarter">
  <a href="View_Konto.jsp" > <div class="w3-card-2" style="min-height:260px"> 
  <h4>Konten</h4><br>
  <img src=konto.png>
  <p>Anzeigen der Eingetragenen Konten</p> </a>
  </div>
</div>

<div class="w3-quarter">
  <a href="View_Produkt.jsp" ><div class="w3-card-2" style="min-height:260px">
  <h4>Produkte</h4><br>
  <img src=products.png>
  <p> Anzeigen aller Produkte </p> </a>
  </div>
</div>

<div class="w3-quarter">
  <a href="View_Einkauf.jsp" ><div class="w3-card-2" style="min-height:260px">
  <h4>Einkaufe</h4><br>
  <img src=buying.png>
  <p> Anzeigen der Einkaufe </p> </a>
  </div>
</div>

</div>

<hr>


<div class="w3-row-padding w3-orange">
</div>
<center>

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