<%@page import="jsp.Model_Markt"%>
<%@page import="jsp.access"%>
<%@page import="java.util.ArrayList"%>

<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
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
  <a href="usermanagment.html"> Benutzerverwaltung </a>
  
</nav>

<header class="w3-container w3-blue-grey">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Menue </span>
</header>

<body>

<div class="w3-container">
  <hr>
  <div class="w3-center">
    <h2>Mäkte</h2>	
    <p w3-class="w3-large">Alles über deine Märkte</p>
  </div>
<div class="w3-responsive w3-card-4">
<table class="w3-table w3-striped w3-bordered">
<thead>

<tr class="w3-theme"> 
<th> M_ID</th> 
<th> Marktname</th> 
<th> Postleitzahl </th> 
<th> Adresse</th> 
<th> Entfernung</th> 
</tr> 
</thead>
<tbody>
<%


ArrayList<Object> tmp = access.getMvecModel();

for (int i = 0;i<tmp.size();i++)
{
	
	
	if (tmp.get(i) instanceof Model_Markt) {
	%>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMstrPLZ());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMstrAdr());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMintEntfernung());
	%>
	</td>
	<%
	}
	
	%>
	</tr>
	<%
	
}

%>
</tbody>
</table>
</div>
</div>


<center>
<br>
<form action="Change_Markt.jsp" method="POST">
Markt ID Eingeben: <input type="text" name="m_ID">
<input class="w3-btn w3-light-blue" type="submit" value="Bearbeiten">
</form>
<br>



<form action="Add_Markt.jsp" method="POST">
<input class="w3-btn w3-light-green" type="submit" value="Hinzufuegen">
</form>
</center>

<script>
function w3_open() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
}
function w3_close() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
}
</script>

</html>