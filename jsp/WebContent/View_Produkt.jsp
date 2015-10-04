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
<h2>Alles um deine Produkten</h2>
     
</header>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> P_ID</th> 
<th> Produktname</th> 
<th> Gewicht </th> 
<th> Preis</th> 
</tr> 

	<tr>
	
<%
for (int i = 0;i<Controll_Main.mvecModel.size();i++)
{
	
	
	if (Controll_Main.mvecModel.get(i) instanceof Model_Produkt) {
	%>
	<td>
	<%
	out.print(((Model_Produkt)Controll_Main.mvecModel.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Produkt)Controll_Main.mvecModel.get(i)).getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Produkt)Controll_Main.mvecModel.get(i)).getMintGewicht());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Produkt)Controll_Main.mvecModel.get(i)).getMfltPreis());
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
<fieldset><legend><b>Produkte Aendern/Eintragen</b></legend>


<form action="add.jsp?file=produkt" method="POST">
		ID<input type="text" name="i_produkt_id" required>
		Name<input type= "text" name="i_produkt_name" required>
		Gewicht<input type="text" name="i_produkt_gewicht" required>
		Preis<input type="text" name="i_produkt_preis" required>
		
		<input type="submit" name="produkt" value ="i_produkt_add" /> 
       <input type="submit" name="produkt" value="i_produkt_change" /> 
		
	</form>

</fieldset>

</html>
            