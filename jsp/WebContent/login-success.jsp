<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="jsp.access"%>
<%@page import="java.util.Vector"%>


<html>
<body>

<%
RequestDispatcher rd=request.getRequestDispatcher("View_Produkt.jsp");  
rd.forward(request, response);
%>


<div class="w3-quarter">
  <a href='javascript:click("Markt")'> 
   <div class="w3-card-2" style="min-height:260px">
  <h4>Maerkte</h4><br>
  <img src=http://hellhero.de/market.png>
  <p>Anzeigen aller Eingetragener Maerkte</p> </a>
  </div>
</div>

<div class="w3-quarter">
  <a href=/jsp/View_Konto.jsp > <div class="w3-card-2" style="min-height:260px"> 
  <h4>Konten</h4><br>
  <img src=http://hellhero.de/konto.png>
  <p>Anzeigen der Eingetragenen Konten</p> </a>
  </div>
</div>

<div class="w3-quarter">
  <a href="/jsp/View_Produkt.jsp" ><div class="w3-card-2" style="min-height:260px">
  <h4>Produkte</h4><br>
  <img src=http://hellhero.de/products.png>
  <p> Anzeigen aller Produkte </p> </a>
  </div>
</div>

<div class="w3-quarter">
  <a href=/jsp/View_Einkauf.jsp ><div class="w3-card-2" style="min-height:260px">
  <h4>Einkaufe</h4><br>
  <img src=http://hellhero.de/buying.png>
  <p> Anzeigen der Einkaufe </p> </a>
  </div>
</div>

</body>
</html>