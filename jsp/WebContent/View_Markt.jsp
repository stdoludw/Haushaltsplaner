<%@page import="jsp.Model_Markt"%>

<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Vector"%>
<%@page import="jsp.Controll_Main"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<html>
<header>
<h2>Alles um deine Maerkte</h2>
     
</header>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> M_ID</th> 
<th> Marktname</th> 
<th> Postleitzahl </th> 
<th> Adresse</th> 
<th> Entfernung</th> 
</tr> 

	<tr>
	
<%
for (int i = 0;i<Controll_Main.mvecModel.size();i++)
{
	
	
	if (Controll_Main.mvecModel.get(i) instanceof Model_Markt) {
	%>
	<td>
	<%
	out.print(((Model_Markt)Controll_Main.mvecModel.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)Controll_Main.mvecModel.get(i)).getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)Controll_Main.mvecModel.get(i)).getMstrPLZ());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)Controll_Main.mvecModel.get(i)).getMstrAdr());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)Controll_Main.mvecModel.get(i)).getMintEntfernung());
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
<fieldset><legend><b>Maerkte Aendern/Eintragen</b></legend>


<form action="add.jsp?file=markt" method="POST">
		ID<input type= "text" name="i_markt_ID" required>
		Name<input type= "text" name="i_markt_name" required>
		Adresse<input type="text" name="i_markt_adresse" required>
		Postleitzahl<input type="text" name="i_marktpostleitzahl" required>
				Entfernung<input type="text" name="i_marktentfernung" required>
		
		<input type="submit" name="markt" value ="i_markt_add" /> 
       <input type="submit" name="markt" value="i_markt_change" />
		
	</form>

</fieldset>

</html>
            