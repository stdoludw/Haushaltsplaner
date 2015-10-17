<%@page import="jsp.access"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jsp.Model_Einkauf"%>
<%@page import="jsp.Model_Konto"%>
<%@page import="jsp.Model_Markt"%>
<%@page import="jsp.Model_Produkt"%>

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
      <a href="index.html">Login</a>
  <a href="login-success.jsp">Hauptmenue</a>
  <a href="usermanagement.html"> Benutzerverwaltung </a>
  
</nav>

<header class="w3-container w3-blue-grey">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Menue </span>
</header>

<body>

<div class="w3-container">
  <hr>
  <div class="w3-center">
    <h2>Einkäufe</h2>	
    <p w3-class="w3-large">Einkäufe ändern</p>
  </div>
<div class="w3-responsive w3-card-4">
<table class="w3-table w3-striped w3-bordered">
<thead>


<%
int ID = Integer.valueOf(request.getParameter("ein_ID"));
ArrayList<Object> tmp = access.getMvecModel();
Model_Einkauf einkauf = null;

for (int i = 0;i<tmp.size();i++)
{
	if (tmp.get(i) instanceof Model_Einkauf) {
	

		if(((Model_Einkauf)tmp.get(i)).getMintID() == ID)
		{
			einkauf = ((Model_Einkauf)tmp.get(i));
			
		}	
		
	}
}

%>
	        	  
<form action="change_Einkauf_SQL" method="POST">	
ID<input class="w3-input" style="width:95%" type="text" name="i_einkauf_id" size="20" 
value="<%out.print(einkauf.getMintID());%>">	
Datum<input class="w3-input" style="width:95%" type="text" name="i_einkauf_datum" size="20" 
value="<%out.print(einkauf.getMstrDatum());%>">	
Anzahl<input class="w3-input" style="width:95%" type="text" name="i_einkauf_anzahl" size="20" 
value="<%out.print(einkauf.getMintAnzahl());%>">	
Kontoinhaber<select name="i_einkauf_konto_cmb" >  
      <%
      for (int i = 0;i<tmp.size();i++)
      {     	
      	if (tmp.get(i) instanceof Model_Konto) {
      		%>
      		<option>
      		<%
      		out.print(((Model_Konto)tmp.get(i)).getMintID() + " " +
      				((Model_Konto)tmp.get(i)).getMstrName());
      		%>
      		</option>
      		<%
      	}
      }
      %>
      </select> 
       <br><br>
      Marktname<select name="i_einkauf_markt_cmb" >   
      <%
      for (int i = 0;i<tmp.size();i++)
      {     	
      	if (tmp.get(i) instanceof Model_Markt) {
      		%>
      		<option>
      		<%
      		out.print(((Model_Markt)tmp.get(i)).getMintID() + " " +
      				((Model_Markt)tmp.get(i)).getMstrName());
      		%>
      		</option>
      		<%
      	}
      }
      %>
      </select> 
       <br><br>
  
	Produktname <select name="i_einkauf_produkt_cmb" >   
      <%
      for (int i = 0;i<tmp.size();i++)
      {     	
      	if (tmp.get(i) instanceof Model_Produkt) {
      		%>
      		<option>
      		<%
      		out.print(((Model_Produkt)tmp.get(i)).getMintID() + " " +
      				((Model_Produkt)tmp.get(i)).getMstrName());
      		%>
      		</option>
      		<%
      	}
      }
      %>
      </select> 

	
	<br>
	<center>
<input class="w3-btn w3-light-green" type="submit" value="Einkäuf Ändern">
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