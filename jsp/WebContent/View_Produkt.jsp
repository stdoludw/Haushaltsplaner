<%@page import="jsp.Model_Produkt"%>
<%@page import="jsp.access"%>

<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Vector"%>
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

access bean=(access)request.getAttribute("bean");  
Vector<Object> tmp = bean.getMvecModel();

for (int i = 0;i<tmp.size();i++)
{
	
	
	if (tmp.get(i) instanceof Model_Produkt) {
	%>
	<td>
	<%
	out.print(((Model_Produkt)tmp.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Produkt)tmp.get(i)).getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Produkt)tmp.get(i)).getMintGewicht());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Produkt)tmp.get(i)).getMfltPreis());
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

<form action="Controller" method="POST">
		ID<input type="text" name="i_produkt_id" required>
		Name<input type= "text" name="i_produkt_name" required>
		Gewicht<input type="text" name="i_produkt_gewicht" required>
		Preis<input type="text" name="i_produkt_preis" required>
		
		<input type="submit" name="produkt" value ="i_produkt_add" /> 
       <input type="submit" name="produkt" value="i_produkt_change" /> 
		
	</form>

</fieldset>

</html>
            