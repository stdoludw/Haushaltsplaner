<%@page import="jsp.Model_Einkauf"%>
<%@page import="jsp.Model_Konto"%>
<%@page import="jsp.Model_Markt"%>
<%@page import="jsp.Model_Produkt"%>

<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Vector"%>
<%@page import="jsp.Controll_Main"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<html>
<header>
<h2>Alles um deine Einkaefe</h2>
     
</header>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> E_ID</th> 
<th> Anzahl</th> 
<th> Datum </th> 

<th> K_ID</th> 
<th> Kontoname</th> 
<th> Bankleitzahl </th> 
<th> Kontonummer</th> 
<th> Betrag</th> 
<th> Minimum</th> 

<th> M_ID</th> 
<th> Marktname</th> 
<th> Adresse </th> 
<th> Postleitzahl</th> 
<th> Entfernung</th> 

<th> P_ID</th> 
<th> Produktname</th> 
<th> Gewicht </th> 
<th> Preis</th> 
</tr> 

	<tr>
	
<%
for (int i = 0;i<Controll_Main.mvecModel.size();i++)
{
	
	
	if (Controll_Main.mvecModel.get(i) instanceof Model_Einkauf) {
	%>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMintAnzahl());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMstrDatum());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMkntKonto().getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMkntKonto().getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMkntKonto().getMstrBLZ());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMkntKonto().getMstrKnr());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMkntKonto().getMstrBetrag());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMkntKonto().getMintMin());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMmkrMarkt().getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMmkrMarkt().getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMmkrMarkt().getMstrAdr());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMmkrMarkt().getMstrPLZ());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMmkrMarkt().getMintEntfernung());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMprdProdukt().getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMprdProdukt().getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMprdProdukt().getMintGewicht());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Einkauf)Controll_Main.mvecModel.get(i)).getMprdProdukt().getMfltPreis());
	%>
	</td>
	<%
	}
	
	%>
	</tr>
	<%
}

%>
</table>
<p></p>
<fieldset><legend><b>Einkaefe Eintragen</b></legend>


<form action="add.jsp?file=einkauf" method="POST">
ID<input type= "text" name="i_einkauf_id" required>	
Anzahl<input type= "text" name="i_einkauf_anzahl" required>
Datum<input type= "text" name="i_einkauf_datum" required>	


      Kontoinhaber<select name="i_konto_cmb" >  
      <%
      for (int i = 0;i<Controll_Main.mvecModel.size();i++)
      {     	
      	if (Controll_Main.mvecModel.get(i) instanceof Model_Konto) {
      		%>
      		<option>
      		<%
      		out.print(((Model_Konto)Controll_Main.mvecModel.get(i)).getMstrName());
      		%>
      		</option>
      		<%
      	}
      }
      %>
      </select> 
  
      Marktname<select name="i_markt_cmb" > 
      
      <%
      for (int i = 0;i<Controll_Main.mvecModel.size();i++)
      {     	
      	if (Controll_Main.mvecModel.get(i) instanceof Model_Markt) {
      		%>
      		<option>
      		<%
      		out.print(((Model_Markt)Controll_Main.mvecModel.get(i)).getMstrName());
      		%>
      		</option>
      		<%
      	}
      }
      %>
      </select> 
  
    
     Produktname <select name="i_produkt_cmb" > 
      
      <%
      for (int i = 0;i<Controll_Main.mvecModel.size();i++)
      {     	
      	if (Controll_Main.mvecModel.get(i) instanceof Model_Produkt) {
      		%>
      		<option>
      		<%
      		out.print(((Model_Produkt)Controll_Main.mvecModel.get(i)).getMstrName());
      		%>
      		</option>
      		<%
      	}
      }
      %>
      </select> 
    
<input type="submit" name="einkauf" value ="i_einkauf_add" /> 
</form>
</fieldset>

</html>
            